package com.example.ParkingManagementSystem.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="vehicle")
public class Vehicle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private int id;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column (name = "vehicle_type")
    private String vehicleType;

    @Column(name="car_enable")
    private boolean carEnable;

    @Column(name = "start_time")
    private String startTime;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "parking_slot_id")
    private ParkSlot parkSlot;

    @OneToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User  user;

    public Vehicle() {
    }

    public Vehicle(String licensePlate, String vehicleType, boolean carEnable, String startTime) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.carEnable = carEnable;
        this.startTime = startTime;
    }

    public Vehicle(String licensePlate, String vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isCarEnable() {
        return carEnable;
    }

    public void setCarEnable(boolean carEnable) {
        this.carEnable = carEnable;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public ParkSlot getParkSlot() {
        return parkSlot;
    }

    public void setParkSlot(ParkSlot parkSlot) {
        this.parkSlot = parkSlot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", carEnable=" + carEnable +
                ", startTime='" + startTime + '\'' +
                ", parkSlot=" + parkSlot +
                ", user=" + user +
                '}';
    }
}
