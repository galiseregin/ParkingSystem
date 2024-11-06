package ParkingSystem;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Represents a parking ticket issued for a vehicle in a parking space.
 */
public class ParkingTicket {
    private Vehicle vehicle;
    private ParkingSpace parkingSpace;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;


    /**
     * Constructs a ParkingTicket for a vehicle parked in a specific space.
     * @param vehicle the vehicle being parked
     * @param parkingSpace the space where the vehicle is parked
     */
    public ParkingTicket(Vehicle vehicle, ParkingSpace parkingSpace) {
        if (vehicle == null || parkingSpace == null) {
            throw new IllegalArgumentException("Vehicle and ParkingSpace cannot be null.");
        }
        this.vehicle = vehicle;
        this.parkingSpace = parkingSpace;
        this.entryTime = vehicle.getEntryTime();
    }


    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }


    /**
    important!
     in case you want to test the fees you need to give exit time by yourself
     otherwise the time that will be taken is the current time
    **/
    public void setExitTime(LocalDateTime exitTime) {
        if (exitTime.isBefore(entryTime)) {
            throw new IllegalArgumentException("Exit time cannot be before entry time.");
        }
        this.exitTime = exitTime;
    }

    /**
     * Calculates the parking fee based on the duration parked.
     * @return the calculated parking fee
     */
    public double calculateFee() {
        // If exitTime is not provided, use the current time
        return calculateFee(LocalDateTime.now());
    }

    /**
     * in case we want to make test and
     * take time that is not default this method can be used
     */
    public double calculateFee(LocalDateTime exitTime) {

        // Calculate the duration between entry and exit times
        Duration duration = Duration.between(entryTime, exitTime);
        long minutes = duration.toMinutes();

        // Calculate the total hours parked, rounding up for any part of an hour
        double hours = Math.ceil(minutes / 60.0);

        // Calculate the parking fee
        double fee = 5.0; // Base fee for the first hour
        if (hours > 1) {
            fee += (hours - 1) * 2; // Additional $2 for each additional hour or part of it
        }

        return fee;
    }


    @Override
    public String toString() {
        return "ParkingTicket{" +
                "vehicle=" + vehicle.getLicensePlate() +
                ", parkingSpace=" + parkingSpace.getId() +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", fee=" + calculateFee() +
                '}';
    }
}
