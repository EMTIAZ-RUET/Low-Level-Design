package com.example.ParkingLot.ExitPanel;

import com.example.ParkingLot.ParkingFee.PricingStrategy;
import com.example.ParkingLot.ParkingLot.ParkingLot;
import com.example.ParkingLot.ParkingSpot.ParkingSpotType;
import com.example.ParkingLot.ParkingTicket.ParkingTicket;
import com.example.ParkingLot.Vehicle.VehicleType;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ExitPanel {
    private String exitPanelID;
    private PricingStrategy pricingStrategy;
    private final ReentrantLock lock = new ReentrantLock();

    public ExitPanel(String exitPanelID, PricingStrategy pricingStrategy) {
        this.exitPanelID = exitPanelID;
        this.pricingStrategy = pricingStrategy;
    }

    public String getExitPanelID() {
        return exitPanelID;
    }

    public ParkingTicket checkout(ParkingTicket parkingTicket) {
        lock.lock();
        try {
            parkingTicket.setEndTime(new Date());
            long duration = calculateDurationInHours(parkingTicket);
            double amount = pricingStrategy.calculatePrice(duration, parkingTicket.getVehicleType().equals(VehicleType.CAR) ? ParkingSpotType.COMPACT : ParkingSpotType.LARGE); // Simplified for now
            parkingTicket.setAmount(amount);

            ParkingLot parkingLot = ParkingLot.getInstance();
            parkingLot.vacateParkingSpot(parkingTicket.getParkingSpotID());

            System.out.println("Vehicle " + parkingTicket.getVehicleRegisterNumber() + " checked out from spot " + parkingTicket.getParkingSpotID() + " on floor " + parkingTicket.getParkingFloorID() + ". Duration: " + duration + " hours, Amount: " + amount);
            return parkingTicket;
        } finally {
            lock.unlock();
        }
    }

    private long calculateDurationInHours(ParkingTicket parkingTicket) {
        long diffInMillies = parkingTicket.getEndTime().getTime() - parkingTicket.getStartTime().getTime();
        return TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}

