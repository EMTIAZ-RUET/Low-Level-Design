package com.example.ParkingLot.Vehicle;

public abstract class Vehicle {
    private String registerNumber;
    private VehicleType type;

    public Vehicle(String registerNumber, VehicleType type) {
        this.registerNumber = registerNumber;
        this.type = type;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public VehicleType getType() {
        return type;
    }
}