package com.example.ParkingLot.Main;

import com.example.ParkingLot.DisplayBoard.DisplayBoard;
import com.example.ParkingLot.EntryPanel.EntryPanel;
import com.example.ParkingLot.ExitPanel.ExitPanel;
import com.example.ParkingLot.ParkingFee.DefaultPricingStrategy;
import com.example.ParkingLot.ParkingFloor.ParkingFloor;
import com.example.ParkingLot.ParkingLot.ParkingLot;
import com.example.ParkingLot.ParkingSpot.ParkingSpotFactory;
import com.example.ParkingLot.ParkingSpot.ParkingSpotType;
import com.example.ParkingLot.ParkingTicket.ParkingTicket;
import com.example.ParkingLot.Vehicle.Vehicle;
import com.example.ParkingLot.Vehicle.VehicleFactory;
import com.example.ParkingLot.Vehicle.VehicleType;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Initialize Parking Lot (Singleton)
        ParkingLot parkingLot = ParkingLot.getInstance();

        // Setup Parking Floors
        DisplayBoard displayBoard1 = new DisplayBoard("DB1");
        ParkingFloor floor1 = new ParkingFloor("F1", displayBoard1);
        floor1.addParkingSpot(ParkingSpotFactory.createParkingSpot(ParkingSpotType.COMPACT, "C1-1"));
        floor1.addParkingSpot(ParkingSpotFactory.createParkingSpot(ParkingSpotType.COMPACT, "C1-2"));
        floor1.addParkingSpot(ParkingSpotFactory.createParkingSpot(ParkingSpotType.LARGE, "L1-1"));
        floor1.addParkingSpot(ParkingSpotFactory.createParkingSpot(ParkingSpotType.ELECTRIC_CAR, "E1-1"));
        floor1.addParkingSpot(ParkingSpotFactory.createParkingSpot(ParkingSpotType.MOTORCYCLE, "M1-1"));
        parkingLot.addParkingFloor(floor1);

        DisplayBoard displayBoard2 = new DisplayBoard("DB2");
        ParkingFloor floor2 = new ParkingFloor("F2", displayBoard2);
        floor2.addParkingSpot(ParkingSpotFactory.createParkingSpot(ParkingSpotType.COMPACT, "C2-1"));
        floor2.addParkingSpot(ParkingSpotFactory.createParkingSpot(ParkingSpotType.LARGE, "L2-1"));
        parkingLot.addParkingFloor(floor2);

        // Setup Entry and Exit Panels
        EntryPanel entryPanel1 = new EntryPanel("EP1");
        parkingLot.addEntryPanel(entryPanel1);
        ExitPanel exitPanel1 = new ExitPanel("XP1", new DefaultPricingStrategy());
        parkingLot.addExitPanel(exitPanel1);

        System.out.println("\n--- Initial Parking Lot Status ---");
        floor1.showDisplayBoard();
        floor2.showDisplayBoard();

        // Simulate Vehicles Entering
        Vehicle car1 = VehicleFactory.createVehicle(VehicleType.CAR, "KA01-1234");
        ParkingTicket ticket1 = entryPanel1.getParkingTicket(car1);
        if (ticket1 != null) {
            System.out.println("Generated Ticket for Car1: " + ticket1.getParkingTicketID());
        }

        Vehicle electricCar1 = VehicleFactory.createVehicle(VehicleType.ELECTRIC_CAR, "DL05-5678");
        ParkingTicket ticket2 = entryPanel1.getParkingTicket(electricCar1);
        if (ticket2 != null) {
            System.out.println("Generated Ticket for Electric Car1: " + ticket2.getParkingTicketID());
        }

        Vehicle motorbike1 = VehicleFactory.createVehicle(VehicleType.MOTORBIKE, "UP65-9012");
        ParkingTicket ticket3 = entryPanel1.getParkingTicket(motorbike1);
        if (ticket3 != null) {
            System.out.println("Generated Ticket for Motorbike1: " + ticket3.getParkingTicketID());
        }

        System.out.println("\n--- Parking Lot Status After Entries ---");
        floor1.showDisplayBoard();
        floor2.showDisplayBoard();

        // Simulate some time passing
        TimeUnit.SECONDS.sleep(5); // Simulate 5 seconds for demonstration

        // Simulate Vehicles Exiting
        if (ticket1 != null) {
            System.out.println("\n--- Checking out Car1 ---");
            exitPanel1.checkout(ticket1);
        }

        System.out.println("\n--- Parking Lot Status After Exit ---");
        floor1.showDisplayBoard();
        floor2.showDisplayBoard();

        // Try to park another car after one has left
        Vehicle car2 = VehicleFactory.createVehicle(VehicleType.CAR, "MH02-3456");
        ParkingTicket ticket4 = entryPanel1.getParkingTicket(car2);
        if (ticket4 != null) {
            System.out.println("Generated Ticket for Car2: " + ticket4.getParkingTicketID());
        }

        System.out.println("\n--- Final Parking Lot Status ---");
        floor1.showDisplayBoard();
        floor2.showDisplayBoard();
    }
}