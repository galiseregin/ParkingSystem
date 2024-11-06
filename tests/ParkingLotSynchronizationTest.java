package ParkingSystem.tests;
/*

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParkingLotSynchronizationTest {

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(2, 2); // Assuming 2 compact and 2 large spaces

        // Create an ExecutorService to manage concurrent threads
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Simulate parking and releasing vehicles in parallel
        for (int i = 1; i <= 5; i++) {
            int vehicleId = i;
            executorService.submit(() -> {
                Vehicle car = new Car("CAR" + vehicleId);
                ParkingTicket ticket = parkingLot.parkVehicle(car);
                if (ticket != null) {
                    System.out.println("Parked " + car.getLicensePlate() + " with ticket: " + ticket);
                    // Simulate parking duration
                    try {
                        Thread.sleep(100); // Simulating time the car is parked
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    parkingLot.releaseVehicle(car.getLicensePlate(), LocalDateTime.now());
                } else {
                    System.out.println("Failed to park CAR" + vehicleId + " due to lack of space.");
                }
            });
        }

        // Shutdown the executor service
        executorService.shutdown();
        try {
            // Wait for all threads to finish
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        // Check final parking lot status
        parkingLot.displayStatus();
    }
}
*/