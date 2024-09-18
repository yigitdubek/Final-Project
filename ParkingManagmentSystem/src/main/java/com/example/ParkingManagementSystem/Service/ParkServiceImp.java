package com.example.ParkingManagementSystem.Service;

import com.example.ParkingManagementSystem.DAO.ParkingDAO;
import com.example.ParkingManagementSystem.DTO.VehicleExıtInformation;
import com.example.ParkingManagementSystem.Entity.ParkSlot;
import com.example.ParkingManagementSystem.Entity.Vehicle;
import com.example.ParkingManagementSystem.Util.ParkPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import java.util.Date;

@Service
public class ParkServiceImp implements ParkService {

    @Autowired
    private ParkingDAO theParkDAO;
    @Autowired
    private ParkPrice parkPrice;

    private static final Logger logger = LoggerFactory.getLogger(ParkServiceImp.class);


    public ParkServiceImp(ParkingDAO theParkDAO,ParkPrice parkPrice ) {
        this.theParkDAO = theParkDAO;
        this.parkPrice =parkPrice;
    }
    @Override
    public String saveVehicle(Vehicle vehicle) {
        Date currenTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = formatter.format(currenTime);
        // This snippet is the registered controller
        Boolean Subscriber = theParkDAO.vehicleSubscriber(vehicle.getLicensePlate());
        System.out.println(Subscriber);
        if (Subscriber.equals(true)){
            Vehicle registeredVehicle = theParkDAO.getVehicleByLicensePlate(vehicle.getLicensePlate());
            if (registeredVehicle.getVehicleType().equals("car")){
                    ParkSlot slot = theParkDAO.findEmptySlotByCar();
                    registeredVehicle.setParkSlot(slot);
                }
                else if (registeredVehicle.getVehicleType().equals("motorbike")){
                    ParkSlot slot = theParkDAO.findEmptySlotByMotorBike();
                    registeredVehicle.setParkSlot(slot);
                }
            theParkDAO.saveParkSlot(registeredVehicle.getParkSlot().getId(),true);
            registeredVehicle.setStartTime(timeStamp);
            registeredVehicle.setCarEnable(true);
            theParkDAO.saveVehicle(registeredVehicle);
        }
        else if (Subscriber.equals(false)){
            Vehicle newVehicle = new Vehicle(vehicle.getLicensePlate(),vehicle.getVehicleType());
            if (newVehicle.getVehicleType().equals("car")){
                ParkSlot slot = theParkDAO.findEmptySlotByCar();
                newVehicle.setParkSlot(slot);
            }
            else if (newVehicle.getVehicleType().equals("motorbike")){
                ParkSlot slot = theParkDAO.findEmptySlotByMotorBike();
                newVehicle.setParkSlot(slot);
            }
            theParkDAO.saveParkSlot(newVehicle.getParkSlot().getId(),true);
            newVehicle.setStartTime(timeStamp);
            newVehicle.setCarEnable(true);
            theParkDAO.saveVehicle(newVehicle);
            System.out.println(newVehicle);
        }


        return null;
    }
    @Override
    public String deleteVehicle(Vehicle vehicle){
        Date lastTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastTimeStamp = formatter.format(lastTime);
        Boolean subscriber = theParkDAO.vehicleSubscriber(vehicle.getLicensePlate());
        System.out.println(subscriber);
        Vehicle registeredVehicle = theParkDAO.getVehicleByLicensePlate(vehicle.getLicensePlate());
        int hour=parkPrice.parkPrice(registeredVehicle.getStartTime());
        int parkFee = parkPrice.getMoney(hour);


        if (subscriber.equals(true)){
            VehicleExıtInformation information = new VehicleExıtInformation(registeredVehicle.getLicensePlate(),
                    registeredVehicle.getParkSlot().getSlotLocation(),registeredVehicle.getUser().getFirstName(),
                    registeredVehicle.getUser().getLastName(),parkFee,hour,lastTimeStamp);
            theParkDAO.saveVehicleExıtInformation(information);
            theParkDAO.saveParkSlot(registeredVehicle.getParkSlot().getId(),false);
            registeredVehicle.setParkSlot(null);
            registeredVehicle.setCarEnable(false);
            registeredVehicle.setStartTime(null);
            registeredVehicle.getUser().setBalance(registeredVehicle.getUser().getBalance()-parkFee);
            theParkDAO.saveVehicle(registeredVehicle);

        }

        else if (subscriber.equals(false)){
            VehicleExıtInformation information = new VehicleExıtInformation(registeredVehicle.getLicensePlate(),
                    registeredVehicle.getParkSlot().getSlotLocation(),parkFee   ,hour,lastTimeStamp);
            theParkDAO.saveVehicleExıtInformation(information);
            theParkDAO.saveParkSlot(registeredVehicle.getParkSlot().getId(),false);
            theParkDAO.deleteVehicle(registeredVehicle.getId());
        }

        return null;
    }



    @Override
    public Vehicle getLatestCreatedVehicle() {
        return theParkDAO.findLatestCreatedVehicle();
    }

    public VehicleExıtInformation getLatestVehicleExıtInformation(){
        return theParkDAO.findLatestCreatedVehicleExitInformation();
    }

}
