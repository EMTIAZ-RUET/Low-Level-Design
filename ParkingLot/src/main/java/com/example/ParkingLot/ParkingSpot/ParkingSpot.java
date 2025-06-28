package com.example.ParkingLot.ParkingSpot;

import com.example.ParkingLot.Vehicle.Vehicle;

public abstract class ParkingSpot {
    private String parkingSpotID;
    private boolean isSpotAvailable;
    private Vehicle vehicle;
    private ParkingSpotType parkingSpotType;

    public ParkingSpot(String parkingSpotID, ParkingSpotType parkingSpotType) {
        this.parkingSpotID = parkingSpotID;
        this.isSpotAvailable = true;
        this.parkingSpotType = parkingSpotType;
    }

    public boolean isSpotFree() {
        return isSpotAvailable;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public String getParkingSpotID() {
        return parkingSpotID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void assignVehicleToSpot(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isSpotAvailable = false;
    }

    public void vacateVehicleFromSpot() {
        this.vehicle = null;
        this.isSpotAvailable = true;
    }
}
