package com.example.ParkingLot.ParkingTicket;

import com.example.ParkingLot.Vehicle.VehicleType;

import java.util.Date;

public class ParkingTicket {
    private String parkingTicketID;
    private VehicleType vehicleType;
    private String vehicleRegisterNumber;
    private String parkingSpotID;
    private String parkingFloorID;
    private Date startTime;
    private Date endTime;
    private double amount;

    public ParkingTicket(String parkingTicketID, VehicleType vehicleType, String vehicleRegisterNumber, String parkingSpotID, String parkingFloorID) {
        this.parkingTicketID = parkingTicketID;
        this.vehicleType = vehicleType;
        this.vehicleRegisterNumber = vehicleRegisterNumber;
        this.parkingSpotID = parkingSpotID;
        this.parkingFloorID = parkingFloorID;
        this.startTime = new Date(); // Set start time upon creation
    }

    public String getParkingTicketID() {
        return parkingTicketID;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getVehicleRegisterNumber() {
        return vehicleRegisterNumber;
    }

    public String getParkingSpotID() {
        return parkingSpotID;
    }

    public String getParkingFloorID() {
        return parkingFloorID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
