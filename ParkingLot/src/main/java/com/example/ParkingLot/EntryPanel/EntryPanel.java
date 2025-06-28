package com.example.ParkingLot.EntryPanel;

import com.example.ParkingLot.ParkingFloor.ParkingFloor;
import com.example.ParkingLot.ParkingLot.ParkingLot;
import com.example.ParkingLot.ParkingSpot.ParkingSpot;
import com.example.ParkingLot.ParkingTicket.ParkingTicket;
import com.example.ParkingLot.ParkingTicket.ParkingTicketBuilder;
import com.example.ParkingLot.Vehicle.Vehicle;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class EntryPanel {
    private String entryPanelID;
    private final ReentrantLock lock = new ReentrantLock();

    public EntryPanel(String entryPanelID) {
        this.entryPanelID = entryPanelID;
    }

    public String getEntryPanelID() {
        return entryPanelID;
    }

    public ParkingTicket getParkingTicket(Vehicle vehicle) {
        lock.lock();
        try {
            ParkingLot parkingLot = ParkingLot.getInstance();
            for (ParkingFloor floor : parkingLot.getParkingFloors()) {
                ParkingSpot availableSpot = floor.getAvailableSpot(vehicle);
                if (availableSpot != null) {
                    availableSpot.assignVehicleToSpot(vehicle);
                    System.out.println("Vehicle " + vehicle.getRegisterNumber() + " assigned to spot " + availableSpot.getParkingSpotID() + " on floor " + floor.getParkingFloorID());
                    return generateParkingTicket(vehicle, floor.getParkingFloorID(), availableSpot.getParkingSpotID());
                }
            }
            System.out.println("No available spot for vehicle: " + vehicle.getRegisterNumber());
            return null;
        } finally {
            lock.unlock();
        }
    }

    private ParkingTicket generateParkingTicket(Vehicle vehicle, String parkingFloorID, String parkingSpotID) {
        String ticketID = UUID.randomUUID().toString();
        return new ParkingTicketBuilder()
                .setParkingTicketID(ticketID)
                .setVehicleType(vehicle.getType())
                .setVehicleRegisterNumber(vehicle.getRegisterNumber())
                .setParkingFloorID(parkingFloorID)
                .setParkingSpotID(parkingSpotID)
                .build();
    }
}

