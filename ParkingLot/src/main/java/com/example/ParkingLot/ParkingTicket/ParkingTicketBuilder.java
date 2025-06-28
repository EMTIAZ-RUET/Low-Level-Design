package com.example.ParkingLot.ParkingTicket;

import com.example.ParkingLot.Vehicle.VehicleType;

public class ParkingTicketBuilder {
    private String parkingTicketID;
    private VehicleType vehicleType;
    private String vehicleRegisterNumber;
    private String parkingSpotID;
    private String parkingFloorID;

    public ParkingTicketBuilder setParkingTicketID(String parkingTicketID) {
        this.parkingTicketID = parkingTicketID;
        return this;
    }

    public ParkingTicketBuilder setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public ParkingTicketBuilder setVehicleRegisterNumber(String vehicleRegisterNumber) {
        this.vehicleRegisterNumber = vehicleRegisterNumber;
        return this;
    }

    public ParkingTicketBuilder setParkingSpotID(String parkingSpotID) {
        this.parkingSpotID = parkingSpotID;
        return this;
    }

    public ParkingTicketBuilder setParkingFloorID(String parkingFloorID) {
        this.parkingFloorID = parkingFloorID;
        return this;
    }

    public ParkingTicket build() {
        return new ParkingTicket(parkingTicketID, vehicleType, vehicleRegisterNumber, parkingSpotID, parkingFloorID);
    }
}
