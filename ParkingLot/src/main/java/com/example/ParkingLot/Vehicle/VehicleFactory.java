package com.example.ParkingLot.Vehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType type, String registerNumber) {
        switch (type) {
            case CAR:
                return new Car(registerNumber);
            case TRUCK:
                return new Truck(registerNumber);
            case ELECTRIC_CAR:
                return new ElectricCar(registerNumber);
            case MOTORBIKE:
                return new Motorbike(registerNumber);
            // Add other vehicle types as needed
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}
