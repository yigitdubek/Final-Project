package com.example.ParkingManagementSystem.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="park_location")
public class ParkLocation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int ıd;

    @Column(name = "park_name")
    private String parkName;

    @Column(name = "total_slot")
    private int totalSlot;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name = "location_slot",
                        joinColumns = @JoinColumn(name = "location_id"),
                        inverseJoinColumns = @JoinColumn(name="slot_id"))
    private List<ParkSlot> slots;



    public ParkLocation() {
    }

    public ParkLocation(String parkName, int totalSlot) {
        this.parkName = parkName;
        this.totalSlot = totalSlot;
    }

    public int getId() {
        return ıd;
    }

    public void setId(int ıd) {
        this.ıd = ıd;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public int getTotalSlot() {
        return totalSlot;
    }

    public void setTotalSlot(int totalSlot) {
        this.totalSlot = totalSlot;
    }

    public List<ParkSlot> getSlots() {
        return slots;
    }

    public void setSlots(List<ParkSlot> slots) {
        this.slots = slots;
    }


    public void addSlot(ParkSlot theSlots){
        if (slots == null ){
            slots = new ArrayList<>();
        }
        slots.add(theSlots);
    }

    @Override
    public String toString() {
        return "ParkLocation{" +
                "ıd=" + ıd +
                ", parkName='" + parkName + '\'' +
                ", totalSlot=" + totalSlot +
                '}';
    }
}
