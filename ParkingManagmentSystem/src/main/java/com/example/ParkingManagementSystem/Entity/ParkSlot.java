package com.example.ParkingManagementSystem.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "park_slot")
public class ParkSlot {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name="slot_location")
    private String slotLocation;

    @Column(name="available")
    private boolean available;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name = "location_slot",
            joinColumns = @JoinColumn(name = "slot_id"),
            inverseJoinColumns = @JoinColumn(name="location_id"))
    private List<ParkLocation>  parkLocations;

    @OneToOne(mappedBy = "parkSlot" )
    private Vehicle vehicle;

    public ParkSlot() {
    }

    public ParkSlot( String slotLocation, boolean available) {
        this.slotLocation = slotLocation;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    public void addParkLocation(ParkLocation theParkLocation){
        if (parkLocations == null ){
            parkLocations = new ArrayList<>();
        }
        parkLocations.add(theParkLocation);
    }

    @Override
    public String toString() {
        return "ParkSlot{" +
                "id=" + id +
                ", slotLocation='" + slotLocation + '\'' +
                ", available=" + available +
                '}';
    }

    public List<ParkLocation> getParkLocations() {
        return parkLocations;
    }

    public void setParkLocations(List<ParkLocation> parkLocations) {
        this.parkLocations = parkLocations;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
