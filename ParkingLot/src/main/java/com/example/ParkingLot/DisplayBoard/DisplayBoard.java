package com.example.ParkingLot.DisplayBoard;

import com.example.ParkingLot.ParkingSpot.ParkingSpotType;

import java.util.Map;

public class DisplayBoard {
    private String displayBoardID;

    public DisplayBoard(String displayBoardID) {
        this.displayBoardID = displayBoardID;
    }

    public void displayMessage(Map<ParkingSpotType, Integer> freeSpots) {
        System.out.println("---- Display Board (" + displayBoardID + ") ----");
        for (Map.Entry<ParkingSpotType, Integer> entry : freeSpots.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " free spots");
        }
        System.out.println("----------------------------------");
    }
}
