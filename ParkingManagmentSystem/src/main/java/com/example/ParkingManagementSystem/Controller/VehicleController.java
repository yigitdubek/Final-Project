package com.example.ParkingManagementSystem.Controller;


import com.example.ParkingManagementSystem.Entity.Vehicle;
import com.example.ParkingManagementSystem.Service.ParkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {
    @Autowired
    ParkService parkService;





    @PostMapping("/entry3")
    public String GetVehicle2(@RequestBody Vehicle vehicle) {
        System.out.println(vehicle.getLicensePlate() + vehicle.getVehicleType());
        String  result =parkService.saveVehicle(vehicle);
        System.out.println(result);
        return "hello";
    }

    @PostMapping("/exit3")
    public HttpStatus ExitVehicle3(@RequestBody Vehicle vehicle){
        String  result =parkService.deleteVehicle(vehicle);
        System.out.println(result);
        return HttpStatus.ACCEPTED;

    }

}
