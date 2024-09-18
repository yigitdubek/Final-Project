package com.example.ParkingManagementSystem.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @Id
    private  int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="subscriber")
    private boolean subscriber;

    @Column(name = "balance")
    private int balance;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private  Vehicle vehicle;

    public User() {
    }

    public User(int id, String firstName, String lastName, boolean subscriber, int balance, Vehicle vehicle) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subscriber = subscriber;
        this.balance = balance;
        this.vehicle = vehicle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isSubscriber() {
        return subscriber;
    }

    public void setSubscriber(boolean subscriber) {
        this.subscriber = subscriber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subscriber=" + subscriber +
                ", balance=" + balance +
                ", vehicle=" + vehicle +
                '}';
    }
}
