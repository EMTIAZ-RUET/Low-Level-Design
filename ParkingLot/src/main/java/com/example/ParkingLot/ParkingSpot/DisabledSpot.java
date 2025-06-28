package com.example.ParkingLot.ParkingSpot;

public class DisabledSpot extends ParkingSpot {
    public DisabledSpot(String parkingSpotID) {
        super(parkingSpotID, ParkingSpotType.DISABLED);
    }
}