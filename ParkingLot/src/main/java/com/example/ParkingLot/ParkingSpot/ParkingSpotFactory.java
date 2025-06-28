package com.example.ParkingLot.ParkingSpot;

public class ParkingSpotFactory {
    public static ParkingSpot createParkingSpot(ParkingSpotType type, String parkingSpotID) {
        switch (type) {
            case LARGE:
                return new LargeSpot(parkingSpotID);
            case COMPACT:
                return new CarSpot(parkingSpotID);
            case DISABLED:
                return new DisabledSpot(parkingSpotID);
            case ELECTRIC_CAR:
                return new ElectricCarSpot(parkingSpotID);
            case MOTORCYCLE:
                return new MotorcycleSpot(parkingSpotID);
            // Add other parking spot types as needed
            default:
                throw new IllegalArgumentException("Unknown parking spot type: " + type);
        }
    }
}
