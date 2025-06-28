package com.example.ParkingLot.ParkingFloor;

import com.example.ParkingLot.DisplayBoard.DisplayBoard;
import com.example.ParkingLot.ParkingSpot.ParkingSpot;
import com.example.ParkingLot.ParkingSpot.ParkingSpotType;
import com.example.ParkingLot.Vehicle.Vehicle;
import com.example.ParkingLot.Vehicle.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingFloor {
    private String parkingFloorID;
    private Map<ParkingSpotType, List<ParkingSpot>> parkingSpots;
    private DisplayBoard displayBoard;
    private final ReentrantLock lock = new ReentrantLock();

    public ParkingFloor(String parkingFloorID, DisplayBoard displayBoard) {
        this.parkingFloorID = parkingFloorID;
        this.displayBoard = displayBoard;
        this.parkingSpots = new HashMap<>();
        for (ParkingSpotType type : ParkingSpotType.values()) {
            parkingSpots.put(type, new ArrayList<>());
        }
    }

    public String getParkingFloorID() {
        return parkingFloorID;
    }

    public Map<ParkingSpotType, List<ParkingSpot>> getParkingSpots() {
        return parkingSpots;
    }

    public void addParkingSpot(ParkingSpot spot) {
        lock.lock();
        try {
            parkingSpots.get(spot.getParkingSpotType()).add(spot);
        } finally {
            lock.unlock();
        }
    }

    public ParkingSpot getAvailableSpot(Vehicle vehicle) {
        lock.lock();
        try {
            ParkingSpotType spotType = getSpotTypeForVehicle(vehicle.getType());
            for (ParkingSpot spot : parkingSpots.get(spotType)) {
                if (spot.isSpotFree()) {
                    return spot;
                }
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void showDisplayBoard() {
        lock.lock();
        try {
            Map<ParkingSpotType, Integer> freeSpots = new HashMap<>();
            for (Map.Entry<ParkingSpotType, List<ParkingSpot>> entry : parkingSpots.entrySet()) {
                int count = 0;
                for (ParkingSpot spot : entry.getValue()) {
                    if (spot.isSpotFree()) {
                        count++;
                    }
                }
                freeSpots.put(entry.getKey(), count);
            }
            displayBoard.displayMessage(freeSpots);
        } finally {
            lock.unlock();
        }
    }

    private ParkingSpotType getSpotTypeForVehicle(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
            case VAN:
            case TRUCK:
                return ParkingSpotType.LARGE;
            case ELECTRIC_CAR:
                return ParkingSpotType.ELECTRIC_CAR;
            case MOTORBIKE:
                return ParkingSpotType.MOTORCYCLE;
            default:
                return ParkingSpotType.COMPACT; // Default for other types
        }
    }

    public List<ParkingSpot> getInUseSpotID(VehicleType vehicleType) {
        lock.lock();
        try {
            ParkingSpotType spotType = getSpotTypeForVehicle(vehicleType);
            List<ParkingSpot> inUseSpots = new ArrayList<>();
            for (ParkingSpot spot : parkingSpots.get(spotType)) {
                if (!spot.isSpotFree()) {
                    inUseSpots.add(spot);
                }
            }
            return inUseSpots;
        } finally {
            lock.unlock();
        }
    }
}