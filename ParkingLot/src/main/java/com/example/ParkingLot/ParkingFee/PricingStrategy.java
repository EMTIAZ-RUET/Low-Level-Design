package com.example.ParkingLot.ParkingFee;

import com.example.ParkingLot.ParkingSpot.ParkingSpotType;

public interface PricingStrategy {
    double calculatePrice(long durationInHours, ParkingSpotType parkingSpotType);
}