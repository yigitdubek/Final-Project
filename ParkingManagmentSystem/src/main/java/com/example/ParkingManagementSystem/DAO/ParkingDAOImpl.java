package com.example.ParkingManagementSystem.DAO;

import com.example.ParkingManagementSystem.DTO.VehicleExıtInformation;
import com.example.ParkingManagementSystem.Entity.ParkLocation;
import com.example.ParkingManagementSystem.Entity.ParkSlot;
import com.example.ParkingManagementSystem.Entity.Vehicle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ParkingDAOImpl implements ParkingDAO {

    @Autowired
    private EntityManager entityManager;


    public  ParkingDAOImpl(EntityManager entityManager){this.entityManager = entityManager;}

    @Override
    @Transactional
    public void saveParkingLocation(ParkLocation theParkLocation) {

        entityManager.persist(theParkLocation);

    }

    @Override
    public ParkLocation findParkLocationAndParkSlotByParkLocationId (int theId) {

        TypedQuery<ParkLocation> query = entityManager.createQuery("select p from ParkLocation p "
                + "JOIN FETCH p.slots "
                + "WHERE p.id = :data" ,ParkLocation.class);

        query.setParameter("data" ,theId);

        // executed query

        ParkLocation location =query.getSingleResult();

        return location;
    }

    @Override
    @Transactional
    public void saveVehicle(Vehicle vehicle){
        entityManager.persist(vehicle);
    }
    @Override
    public ParkSlot findParkSlotById(int theId){
        return entityManager.find(ParkSlot.class,theId);
    }

    @Override
    public ParkSlot findEmptySlotByCar(){
        return entityManager.createQuery("Select p FROM ParkSlot p " +
                "where p.available = false AND p.slotLocation LIKE 'c%' ",ParkSlot.class).setMaxResults(1).getSingleResult();

    }

    @Override
    @Transactional
    public void saveParkSlot(int theId, Boolean status) {

        ParkSlot parkSlot = entityManager.find(ParkSlot.class,theId);
            parkSlot.setAvailable(status);
            entityManager.merge(parkSlot);
    }

    @Override
    public ParkSlot findEmptySlotByMotorBike() {
        return entityManager.createQuery("Select p FROM ParkSlot p " +
                "where p.available = false AND p.slotLocation LIKE 'm%' ",ParkSlot.class).setMaxResults(1).getSingleResult();
    }

    @Override
    public Vehicle getVehicleByLicensePlate(String licensePlate) {
        // Tek sorguda tüm verileri çekme
        String jpql = "SELECT v FROM Vehicle v " +
                "LEFT JOIN FETCH v.user " +
                "LEFT JOIN FETCH v.parkSlot " +
                "WHERE v.licensePlate = :licensePlate";

        TypedQuery<Vehicle> query = entityManager.createQuery(jpql, Vehicle.class);
        query.setParameter("licensePlate", licensePlate);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Vehicle with license plate " + licensePlate + " not found.");
            return null; // Belirtilen plakaya sahip araç bulunamazsa null döner
        }
    }


    @Override
    @Transactional
    public void deleteVehicle(int theId) {

        Vehicle tempVehicle = entityManager.find(Vehicle.class,theId);
            entityManager.remove(tempVehicle);

        }

    @Override
    public Vehicle findLatestCreatedVehicle() {
        TypedQuery<Vehicle> query = entityManager.createQuery("SELECT v FROM Vehicle v ORDER BY v.startTime DESC", Vehicle.class);
        return query.setMaxResults(1).getSingleResult();
    }

    @Override
    public boolean vehicleSubscriber(String licensePlate) {
        String jpql = "SELECT COUNT(u) FROM User u JOIN u.vehicle v WHERE v.licensePlate = :licensePlate";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("licensePlate", licensePlate);

        Long count;
        try {
            count = query.getSingleResult();
        } catch (NoResultException e) {
            return false; // Sonuç bulunamazsa false döndür
        }

        return count > 0;
    }

    @Override
    @Transactional
    public void saveVehicleExıtInformation(VehicleExıtInformation information) {
        entityManager.merge(information);
    }
    @Override
    public VehicleExıtInformation findLatestCreatedVehicleExitInformation() {
        TypedQuery<VehicleExıtInformation> query = entityManager.createQuery("SELECT vei FROM VehicleExıtInformation vei ORDER BY vei.lastTime DESC", VehicleExıtInformation.class);
        return query.setMaxResults(1).getSingleResult();
    }
}
