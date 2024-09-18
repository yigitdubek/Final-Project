package com.example.ParkingManagementSystem.Util;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class ParkPrice {

    public int parkPrice(String entryTime){
        // Get the current date and time
        LocalDateTime currentTime = LocalDateTime.now();

        // Define the date-time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Parse the entry time string to LocalDateTime
        LocalDateTime startTime = LocalDateTime.parse(entryTime, formatter);

        // Calculate the duration between the start time and the current time
        Duration duration = Duration.between(startTime, currentTime);

        // Calculate the total hours
        int totalHours = (int) Math.ceil(duration.toMinutes() / 60.0);  // Rounding up to the next whole hour

        return totalHours;
    }
    public int getMoney(int durationTime){
        if (durationTime >= 0 && durationTime < 2) {
            return 70;
        } else if (durationTime >= 2 && durationTime < 4) {
            return 300;
        } else if (durationTime >= 4 && durationTime < 8) {
            return 600;
        } else if (durationTime >= 8 && durationTime < 12) {
            return 900;
        } else if (durationTime >= 12) {
            return durationTime * 90;
        } else {
            throw new IllegalArgumentException("Geçersiz Giriş");
        }

    }
}
