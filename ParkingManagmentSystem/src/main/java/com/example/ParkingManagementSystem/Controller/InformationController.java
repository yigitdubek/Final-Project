package com.example.ParkingManagementSystem.Controller;

import com.example.ParkingManagementSystem.DTO.VehicleExıtInformation;
import com.example.ParkingManagementSystem.Entity.Vehicle;
import com.example.ParkingManagementSystem.Service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformationController {
    @Autowired
    ParkService parkService;

    @GetMapping("/entryInformation")
    public String entryInformation(Model theModel){

        Vehicle data = parkService.getLatestCreatedVehicle();
        theModel.addAttribute("slotLocation",data.getParkSlot().getSlotLocation());
        theModel.addAttribute("licensePlate",data.getLicensePlate());
        theModel.addAttribute("time",data.getStartTime());
        if (data.getUser() == null){
            theModel.addAttribute("firstName", "Anonymous");
            theModel.addAttribute("lastName", "Anonymous");
        }
        else {
            theModel.addAttribute("firstName",data.getUser().getFirstName());
            theModel.addAttribute("lastName",data.getUser().getLastName());

        }

        return "EntryInformation";
    }
    @GetMapping("/exitInformation")
    public String exitInformation(Model theModel){

        VehicleExıtInformation data = parkService.getLatestVehicleExıtInformation();
        theModel.addAttribute("slotLocation",data.getSlotLocation());
        theModel.addAttribute("licensePlate",data.getLicensePlate());
        theModel.addAttribute("time",data.getLastTime());
        theModel.addAttribute("hour",data.getDurationTime());
        theModel.addAttribute("parkingFee",data.getMoney());
        if (data.getFirstName() == null){
            theModel.addAttribute("firstName", "Anonymous");
            theModel.addAttribute("lastName", "Anonymous");
        }
        else {
            theModel.addAttribute("firstName",data.getFirstName());
            theModel.addAttribute("lastName",data.getLastName());

        }

        return "ExıtInformation";
    }
}
