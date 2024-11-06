package ParkingSystem;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents a parking lot containing multiple parking spaces.
 */
public class ParkingLot {
    private List<ParkingSpace> parkingSpaces;
    Map<String, ParkingTicket> activeTickets;
    private ReentrantLock lock;


    /**
     * Constructs a ParkingLot with the specified number of compact and large spaces.
     * @param numberOfCompact the number of compact parking spaces
     * @param numberOfLarge the number of large parking spaces
     */
    public ParkingLot(int numberOfCompact, int numberOfLarge) {
        if (numberOfCompact < 0 || numberOfLarge < 0) {
            throw new IllegalArgumentException("Number of parking spaces cannot be negative.");
        }
        parkingSpaces = new ArrayList<>();
        int id = 1;
        for (int i = 0; i < numberOfCompact; i++) {
            parkingSpaces.add(new ParkingSpace(id++, ParkingSpaceType.COMPACT));
        }
        for (int i = 0; i < numberOfLarge; i++) {
            parkingSpaces.add(new ParkingSpace(id++, ParkingSpaceType.LARGE));
        }
        activeTickets = new HashMap<>();
        lock = new ReentrantLock();
    }


    /**
     * Parks a vehicle in an available parking space.
     * @param vehicle the vehicle to park
     * @return the parking ticket issued for the parked vehicle, or null if parking fails
     */
    public ParkingTicket parkVehicle(Vehicle vehicle) {
        lock.lock(); //  lock for thread safety
        try {
            if (vehicle == null) {
                System.out.println("Cannot park a null vehicle.");
                return null;
            }

            // Check if the vehicle is already parked
            if (activeTickets.containsKey(vehicle.getLicensePlate())) {
                System.out.println("Vehicle with license plate " + vehicle.getLicensePlate() + " is already parked.");
                return null;
            }

            // Try to find an available parking space suitable for the vehicle
            ParkingSpace allocatedSpace = null;
            for (ParkingSpace space : parkingSpaces) {
                if (space.isAvailable() && isSpaceSuitable(space, vehicle)) {
                    allocatedSpace = space;
                    break;
                }
            }

            if (allocatedSpace != null) {
                allocatedSpace.park(vehicle);
                ParkingTicket ticket = new ParkingTicket(vehicle, allocatedSpace);
                activeTickets.put(vehicle.getLicensePlate(), ticket);
                return ticket;
            } else {
                System.out.println("No available parking space for vehicle " + vehicle.getLicensePlate());
                return null;
            }
        } finally {
            lock.unlock();// Ensure the lock is released
        }
    }


    /**
     * Releases a vehicle from the parking lot and calculates its parking fee.
     * @param licensePlate the license plate of the vehicle to release
     * @param exitTime the time when the vehicle exits
     * @return the parking ticket for the released vehicle, or null if release fails
     */
    public ParkingTicket releaseVehicle(String licensePlate, LocalDateTime exitTime) {
        lock.lock();
        try {
            if (licensePlate == null || licensePlate.isEmpty()) {
                System.out.println("Invalid license plate provided.");
                return null;
            }

            ParkingTicket ticket = activeTickets.get(licensePlate);
            if (ticket == null) {
                return null;
            }

            // Set the exit time before releasing
            ticket.setExitTime(exitTime);

            ParkingSpace space = ticket.getParkingSpace();
            space.release();
            activeTickets.remove(licensePlate);
            System.out.println("Vehicle " + licensePlate + " released from space " + space.getId());
            System.out.println("Parking Fee: $" + ticket.calculateFee());
            return ticket;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Checks if the specified parking space is suitable for the given vehicle type.
     * @param space the parking space to check
     * @param vehicle the vehicle to check against the space type
     * @return true if the space is suitable, false otherwise
     */
    private boolean isSpaceSuitable(ParkingSpace space, Vehicle vehicle) {
        switch (vehicle.getType()) {
            case MOTORCYCLE:
                return true; // Motorcycles can park in any space
            case CAR:
                return space.getType() == ParkingSpaceType.COMPACT || space.getType() == ParkingSpaceType.LARGE; // Cars can park in compact or large spaces
            case BUS:
                return space.getType() == ParkingSpaceType.LARGE; // Buses can only park in large spaces
            default:
                return false;
        }
    }


    /**
     * Display parking lot status
     */
    public void displayStatus() {
        System.out.println("Current Parking Lot Status:");
        for (ParkingSpace space : parkingSpaces) {
            System.out.println(space);
        }
    }

    //used for testing
    public int getCurrentOccupiedSpaces() {
        int count = 0;
        for (ParkingSpace space : parkingSpaces) {
            if (!space.isAvailable()) {
                count++;
            }
        }
        return count;
    }
}
