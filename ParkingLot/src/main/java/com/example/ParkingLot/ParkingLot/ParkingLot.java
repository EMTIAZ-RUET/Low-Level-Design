package com.example.ParkingLot.ParkingLot;


import com.example.ParkingLot.EntryPanel.EntryPanel;
import com.example.ParkingLot.ExitPanel.ExitPanel;
import com.example.ParkingLot.ParkingFloor.ParkingFloor;
import com.example.ParkingLot.ParkingSpot.ParkingSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingLot {
    private static ParkingLot instance;
    private List<ParkingFloor> parkingFloors;
    private List<EntryPanel> entryPanels;
    private List<ExitPanel> exitPanels;
    private final ReentrantLock lock = new ReentrantLock();

    private ParkingLot() {
        this.parkingFloors = new ArrayList<>();
        this.entryPanels = new ArrayList<>();
        this.exitPanels = new ArrayList<>();
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    instance = new ParkingLot();
                }
            }
        }
        return instance;
    }

    public void addParkingFloor(ParkingFloor floor) {
        lock.lock();
        try {
            this.parkingFloors.add(floor);
        } finally {
            lock.unlock();
        }
    }

    public void addEntryPanel(EntryPanel panel) {
        lock.lock();
        try {
            this.entryPanels.add(panel);
        } finally {
            lock.unlock();
        }
    }

    public void addExitPanel(ExitPanel panel) {
        lock.lock();
        try {
            this.exitPanels.add(panel);
        } finally {
            lock.unlock();
        }
    }

    public ParkingSpot vacateParkingSpot(String parkingSpotID) {
        lock.lock();
        try {
            for (ParkingFloor floor : parkingFloors) {
                for (List<ParkingSpot> spots : floor.getParkingSpots().values()) {
                    for (ParkingSpot spot : spots) {
                        if (spot.getParkingSpotID().equals(parkingSpotID) && !spot.isSpotFree()) {
                            spot.vacateVehicleFromSpot();
                            return spot;
                        }
                    }
                }
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public List<EntryPanel> getEntryPanels() {
        return entryPanels;
    }

    public List<ExitPanel> getExitPanels() {
        return exitPanels;
    }
}