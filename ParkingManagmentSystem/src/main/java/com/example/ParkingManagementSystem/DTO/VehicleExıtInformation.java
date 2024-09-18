package com.example.ParkingManagementSystem.DTO;

import jakarta.persistence.*;

@Entity
@Table(name="vehicle_exit_information")
public class VehicleExıtInformation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name="license_plate")
    private String licensePlate;

    @Column(name="slot_location")
    private String slotLocation;

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="money")
    private int money;

    @Column(name = "hour")
    private int durationTime;

    @Column(name="last_time")
    private String lastTime;


    public VehicleExıtInformation() {
    }

    public VehicleExıtInformation(String licensePlate, String slotLocation, String firstName, String lastName, int money, int durationTime, String lastTime) {
        this.licensePlate = licensePlate;
        this.slotLocation = slotLocation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.money = money;
        this.durationTime = durationTime;
        this.lastTime = lastTime;
    }

    public VehicleExıtInformation(String licensePlate, String slotLocation, int money, int durationTime, String lastTime) {
        this.licensePlate = licensePlate;
        this.slotLocation = slotLocation;
        this.money = money;
        this.durationTime = durationTime;
        this.lastTime = lastTime;
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

    public String getSlotLocation() {
        return slotLocation;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "VehicleExıtInformation{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", slotLocation='" + slotLocation + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", money=" + money +
                ", durationTime=" + durationTime +
                ", lastTime='" + lastTime + '\'' +
                '}';
    }
}


