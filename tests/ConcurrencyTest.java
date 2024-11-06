package ParkingSystem.tests;

/*
import java.time.LocalDateTime;
import java.util.Random;

public class ConcurrencyTest {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(2, 2); // Create a parking lot with 2 compact and 2 large spaces.

        // Create threads for parking vehicles
        Runnable parkTask = () -> {
            Vehicle vehicle;
            String licensePlate;
            String vehicleType;
            Random random = new Random();
            String[] types = {"CAR", "MOTORCYCLE", "BUS"};

            for (int i = 0; i < 5; i++) { // Try to park 5 vehicles
                vehicleType = types[random.nextInt(types.length)];
                licensePlate = vehicleType + i; // Generate a unique license plate
                switch (vehicleType) {
                    case "MOTORCYCLE":
                        vehicle = new Motorcycle(licensePlate);
                        break;
                    case "CAR":
                        vehicle = new Car(licensePlate);
                        break;
                    case "BUS":
                        vehicle = new Bus(licensePlate);
                        break;
                    default:
                        continue;
                }
                parkingLot.parkVehicle(vehicle);
                try {
                    Thread.sleep(random.nextInt(200)); // Simulate random parking duration
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable releaseTask = () -> {
            Random random = new Random();
            String[] vehicleTypes = {"CAR", "MOTORCYCLE", "BUS"};
            for (int i = 0; i < 5; i++) { // Try to release vehicles
                String licensePlate = vehicleTypes[random.nextInt(vehicleTypes.length)] + i;
                parkingLot.releaseVehicle(licensePlate, LocalDateTime.now());
                try {
                    Thread.sleep(random.nextInt(200)); // Simulate random release duration
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Create multiple threads for parking and releasing
        Thread parkThread1 = new Thread(parkTask);
        Thread parkThread2 = new Thread(parkTask);
        Thread releaseThread1 = new Thread(releaseTask);
        Thread releaseThread2 = new Thread(releaseTask);

        // Start the threads
        parkThread1.start();
        parkThread2.start();
        releaseThread1.start();
        releaseThread2.start();

        // Wait for all threads to finish
        try {
            parkThread1.join();
            parkThread2.join();
            releaseThread1.join();
            releaseThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check the status of the parking lot
        parkingLot.displayStatus();
    }
}
*/