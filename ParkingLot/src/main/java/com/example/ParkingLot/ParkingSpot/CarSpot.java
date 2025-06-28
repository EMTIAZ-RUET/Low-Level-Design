package com.example.ParkingLot.ParkingSpot;

public class CarSpot extends ParkingSpot {
    public CarSpot(String parkingSpotID) {
        super(parkingSpotID, ParkingSpotType.COMPACT);
    }
}