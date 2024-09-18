package com.example.ParkingManagementSystem.Service;

import com.example.ParkingManagementSystem.DTO.VehicleExıtInformation;
import com.example.ParkingManagementSystem.Entity.Vehicle;

public interface ParkService {

    String saveVehicle(Vehicle vehicle);

    String deleteVehicle(Vehicle vehicle);
    Vehicle getLatestCreatedVehicle();
    public VehicleExıtInformation getLatestVehicleExıtInformation();

}
