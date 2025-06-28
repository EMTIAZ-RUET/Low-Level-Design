package com.example.ParkingLot.ParkingFee;

import com.example.ParkingLot.ParkingSpot.ParkingSpotType;

public class DefaultPricingStrategy implements PricingStrategy {
    private HourlyCost hourlyCost;

    public DefaultPricingStrategy() {
        this.hourlyCost = new HourlyCost();
    }

    @Override
    public double calculatePrice(long durationInHours, ParkingSpotType parkingSpotType) {
        return durationInHours * hourlyCost.getCost(parkingSpotType);
    }
}
