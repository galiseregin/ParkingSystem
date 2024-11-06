package ParkingSystem;

class Bus extends Vehicle {
    public Bus(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.BUS;
    }
}