package com.example.ParkingManagementSystem.DAO;

import com.example.ParkingManagementSystem.DTO.VehicleExıtInformation;
import com.example.ParkingManagementSystem.Entity.ParkLocation;
import com.example.ParkingManagementSystem.Entity.ParkSlot;
import com.example.ParkingManagementSystem.Entity.Vehicle;

import java.util.List;

public interface ParkingDAO{

      void saveParkingLocation (ParkLocation parkLocation);
      void saveParkSlot(int theId, Boolean status);
      void saveVehicle(Vehicle vehicle);
      void saveVehicleExıtInformation(VehicleExıtInformation information);
      ParkLocation findParkLocationAndParkSlotByParkLocationId(int theId);
      ParkSlot findParkSlotById(int theId);
      public ParkSlot findEmptySlotByCar();
      public ParkSlot findEmptySlotByMotorBike();
      public Vehicle getVehicleByLicensePlate(String licensePlate);
      public void deleteVehicle(int theId);
      Vehicle findLatestCreatedVehicle();
      boolean vehicleSubscriber(String LicensePlate);
      public VehicleExıtInformation findLatestCreatedVehicleExitInformation();

}
