package com.example.ParkingManagementSystem;

import com.example.ParkingManagementSystem.DAO.ParkingDAO;
import com.example.ParkingManagementSystem.Entity.ParkLocation;
import com.example.ParkingManagementSystem.Entity.ParkSlot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ParkingManagementSystemApplication {

	private ParkingDAO parkingDAO;

	public static void main(String[] args) {
		SpringApplication.run(ParkingManagementSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (ParkingDAO parkingDAO){

		return runner -> {
			//createParkLocationWithSlot(parkingDAO);
			//findParkLocationAndSlotId(parkingDAO);
		};


	}

	public void  createParkLocationWithSlot(ParkingDAO theParkingDao){
		ParkLocation tempParkLocation = new ParkLocation("YigitPark",50);

		for(int i =1; i <= tempParkLocation.getTotalSlot(); i++){
			if(i<=40){
			tempParkLocation.addSlot(new ParkSlot("C"+i,false));
			}
			else{
				tempParkLocation.addSlot(new ParkSlot("M"+i,false));
			}
		}
		theParkingDao.saveParkingLocation(tempParkLocation);

	}




}
