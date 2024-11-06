package ParkingSystem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ParkingLotCLI {
    private static final String RED_TEXT = "\u001B[31m"; // ANSI code for red text
    private static final String RESET = "\u001B[0m"; // ANSI code to reset text color

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(2, 2); // Initialize ParkingLot with 2 compact and 2 large spaces
        Scanner scanner = new Scanner(System.in); // Create a scanner to read user input
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Formatter for date/time

        // Main loop for user interaction
        while (true) {
            displayMenu(); // Show available options
            int choice = getUserChoice(scanner); // Get user's choice

            // Handle user choiceZ
            switch (choice) {
                case 1:
                    parkVehicle(scanner, parkingLot, formatter); // Park a vehicle
                    break;
                case 2:
                    releaseVehicle(scanner, parkingLot, formatter); // Release a vehicle
                    break;
                case 3:
                    parkingLot.displayStatus(); // Display current status of parking spaces
                    break;
                case 4:
                    System.out.println("Exiting..."); // Exit the application
                    scanner.close(); // Close the scanner to free resources
                    return; // Exit the main method
                default:
                    System.out.println("Invalid choice. Please select a valid option."); // Handle invalid choices
            }
        }
    }

    /**
     * Displays the menu options to the user.
     */
    private static void displayMenu() {
        System.out.println("\nOptions:");
        System.out.println("1. Park a vehicle");
        System.out.println("2. Release a vehicle");
        System.out.println("3. Display parking status");
        System.out.println("4. Exit");
        System.out.print("Please select an option: ");
    }

    /**
     * Gets the user's choice as an integer, ensuring valid input.
     * @param scanner the Scanner object to read input
     * @return the user's choice
     */
    private static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) { // Validate input to ensure it's an integer
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume invalid input
        }
        return scanner.nextInt(); // Get user choice
    }

    /**
     * Handles the process of parking a vehicle.
     * @param scanner the Scanner object to read user input
     * @param parkingLot the ParkingLot instance managing parking spaces
     * @param formatter the DateTimeFormatter for displaying entry times
     */
    private static void parkVehicle(Scanner scanner, ParkingLot parkingLot, DateTimeFormatter formatter) {
        System.out.print("Enter vehicle type (MOTORCYCLE, CAR, BUS): ");
        String vehicleTypeInput = scanner.next().toUpperCase(); // Get vehicle type

        VehicleType vehicleType;
        try {
            vehicleType = VehicleType.valueOf(vehicleTypeInput); // Convert input to VehicleType enum
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid vehicle type."); // Handle invalid vehicle types
            return;
        }

        System.out.print("Enter license plate: ");
        String licensePlate = scanner.next(); // Get license plate

        Vehicle vehicle = createVehicle(vehicleType, licensePlate); // Create vehicle object
        if (vehicle != null) {
            ParkingTicket ticket = parkingLot.parkVehicle(vehicle); // Attempt to park vehicle
            if (ticket != null) {
                // Display success message with details
                System.out.println(RED_TEXT + "Vehicle " + licensePlate +
                        " parked at space " + ticket.getParkingSpace().getId() +
                        " on " + ticket.getEntryTime().format(formatter) + RESET);
            }
        }
    }

    /**
     * Creates a Vehicle object based on the provided type and license plate.
     * @param vehicleType the type of vehicle
     * @param licensePlate the license plate of the vehicle
     * @return the created Vehicle object, or null if invalid type
     */
    private static Vehicle createVehicle(VehicleType vehicleType, String licensePlate) {
        switch (vehicleType) {
            case MOTORCYCLE:
                return new Motorcycle(licensePlate); // Create a Motorcycle object
            case CAR:
                return new Car(licensePlate); // Create a Car object
            case BUS:
                return new Bus(licensePlate); // Create a Bus object
            default:
                System.out.println("Invalid vehicle type."); // Handle unexpected vehicle types
                return null;
        }
    }

    /**
     * Handles the process of releasing a vehicle from the parking lot.
     * @param scanner the Scanner object to read user input
     * @param parkingLot the ParkingLot instance managing parking spaces
     * @param formatter the DateTimeFormatter for displaying exit times
     */
    private static void releaseVehicle(Scanner scanner, ParkingLot parkingLot, DateTimeFormatter formatter) {
        System.out.print("Enter license plate of vehicle to release: ");
        String licensePlate = scanner.next(); // Get license plate of the vehicle to release
        ParkingTicket releasedTicket = parkingLot.releaseVehicle(licensePlate, LocalDateTime.now()); // Release vehicle

        if (releasedTicket != null) {
            // Display success message with fee details
            System.out.println(RED_TEXT + "Vehicle " + licensePlate +
                    " released. Parking Fee: $" + releasedTicket.calculateFee() +
                    " on " + LocalDateTime.now().format(formatter) + RESET);
        } else {
            // Handle case where no active ticket is found
            System.out.println("No active ticket found for vehicle " + licensePlate);
        }
    }
}
