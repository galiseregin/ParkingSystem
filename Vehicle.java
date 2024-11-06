package ParkingSystem;
import java.time.LocalDateTime;


// Enum for Vehicle Types
enum VehicleType {
    MOTORCYCLE,
    CAR,
    BUS
}

/**
 * Abstract class representing a vehicle.
 * Subclasses must provide their specific vehicle type.
 */
abstract class Vehicle {
    private final String licensePlate;
    private final LocalDateTime entryTime;

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
        this.entryTime = LocalDateTime.now();
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    /**
     * Abstract method to get the type of the vehicle.
     * @return the type of the vehicle
     */
    public abstract VehicleType getType();
}