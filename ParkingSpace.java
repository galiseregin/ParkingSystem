package ParkingSystem;
enum ParkingSpaceType {
    COMPACT,
    LARGE
}
public class ParkingSpace {
    private final int id;
    private final ParkingSpaceType type;
    private boolean isAvailable;
    private Vehicle currentVehicle;

    /**
     * Constructs a ParkingSpace with the given ID and type.
     * @param id the unique identifier for the parking space
     * @param type the type of parking space (compact or large)
     */
    public ParkingSpace(int id, ParkingSpaceType type) {
        this.id = id;
        this.type = type;
        this.isAvailable = true;
        this.currentVehicle = null;
    }

    public int getId() {
        return id;
    }

    public ParkingSpaceType getType() {
        return type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }


    /**
     * Parks a vehicle in this parking space.
     * @param vehicle the vehicle to park
     */
    public void park(Vehicle vehicle) {
        this.currentVehicle = vehicle;
        this.isAvailable = false;
    }

    /**
     * Releases this parking space, making it available again.
     */
    public void release() {
        this.currentVehicle = null;
        this.isAvailable = true;
    }



    @Override
    public String toString() {
        return "ParkingSpace{" +
                "id=" + id +
                ", type=" + type +
                ", isAvailable=" + isAvailable +
                ", currentVehicle=" + (currentVehicle != null ? currentVehicle.getLicensePlate() : "None") +
                '}';
    }
}