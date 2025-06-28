package com.example.ParkingLot.ParkingFee;

import com.example.ParkingLot.ParkingSpot.ParkingSpotType;

import java.util.HashMap;
import java.util.Map;

public class HourlyCost {
    private Map<ParkingSpotType, Double> hourlyCosts;

    public HourlyCost() {
        this.hourlyCosts = new HashMap<>();
        // Default hourly costs, can be loaded from config or database
        hourlyCosts.put(ParkingSpotType.COMPACT, 10.0);
        hourlyCosts.put(ParkingSpotType.LARGE, 12.0);
        hourlyCosts.put(ParkingSpotType.MOTORCYCLE, 5.0);
        hourlyCosts.put(ParkingSpotType.ELECTRIC_CAR, 15.0);
        hourlyCosts.put(ParkingSpotType.DISABLED, 8.0);
    }

    public double getCost(ParkingSpotType parkingSpotType) {
        return hourlyCosts.getOrDefault(parkingSpotType, 0.0);
    }
}