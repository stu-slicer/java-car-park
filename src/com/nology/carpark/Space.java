package com.nology.carpark;

/**
 * Space has no concapt of what can be parked here.
 * Because a lorry can park across three spaces - no individual space will know if a lorry can part there.
 */
public abstract class Space {

    private Vehicle parkedVehicle;

    public abstract SpaceType getType();
    public abstract boolean canParkVehicle(VehicleType type);

    public boolean isFull() {
        return parkedVehicle != null;
    }

    public boolean isEmpty() {
        return parkedVehicle == null;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void parkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }

    public Vehicle removeVehicle() {
        return this.parkedVehicle;
    }
}
