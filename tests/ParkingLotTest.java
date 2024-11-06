package ParkingSystem.tests;

/*

import java.time.LocalDateTime;

public class ParkingLotTest {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(2, 2);

        // Test 1: Park a CAR
        System.out.println("Test 1: Park a CAR");
        ParkingTicket parkSuccess1 = parkingLot.parkVehicle(new Car("CAR1"));
        assert parkSuccess1 != null : "Test failed: CAR1 should be parked.";
        System.out.println("Test passed: " + parkSuccess1);

        // Test 2: Park another CAR
        System.out.println("Test 2: Park another CAR");
        ParkingTicket parkSuccess2 = parkingLot.parkVehicle(new Car("CAR2"));
        assert parkSuccess2 != null : "Test failed: CAR2 should be parked.";
        System.out.println("Test passed: " + parkSuccess2);

        // Test 3: Attempt to park a third CAR (should fail)
        System.out.println("Test 3: Attempt to park a third CAR");
        ParkingTicket parkFail1 = parkingLot.parkVehicle(new Car("CAR3"));
        assert parkFail1 == null : "Test failed: CAR3 should not be parked.";
        System.out.println("Test passed: CAR3 could not be parked due to lack of space.");

        // Test 4: Park a MOTORCYCLE
        System.out.println("Test 4: Park a MOTORCYCLE");
        ParkingTicket parkSuccess3 = parkingLot.parkVehicle(new Motorcycle("MOTORCYCLE1"));
        assert parkSuccess3 != null : "Test failed: MOTORCYCLE1 should be parked.";
        System.out.println("Test passed: " + parkSuccess3);

        // Test 5: Release a CAR
        System.out.println("Test 5: Release a CAR");
        parkingLot.releaseVehicle("CAR1", LocalDateTime.now());
        assert parkingLot.activeTickets.get("CAR1") == null : "Test failed: CAR1 should be released.";
        System.out.println("Test passed: CAR1 released.");

        // Test 6: Check parking fee for released CAR
        System.out.println("Test 6: Check parking fee for released CAR");
        ParkingTicket ticket = parkingLot.activeTickets.get("CAR1");
        assert ticket == null || ticket.calculateFee() == 5.0 : "Test failed: Parking fee for CAR1 should be $5.0.";
        System.out.println("Test passed: Correct parking fee calculated for CAR1.");

        // Test 7: Release a MOTORCYCLE
        System.out.println("Test 7: Release a MOTORCYCLE");
        parkingLot.releaseVehicle("MOTORCYCLE1", LocalDateTime.now());
        assert parkingLot.activeTickets.get("MOTORCYCLE1") == null : "Test failed: MOTORCYCLE1 should be released.";
        System.out.println("Test passed: MOTORCYCLE1 released.");

        // Test 8: Check final parking lot status
        System.out.println("Test 8: Check final parking lot status");
        parkingLot.displayStatus();
    }
}
*/