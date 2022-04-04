package com.nology.carpark;

public class StandardSpace extends Space {

    @Override
    public SpaceType getType() {
        return SpaceType.STANDARD;
    }

    @Override
    public boolean canParkVehicle(VehicleType type) {
        return true;
    }
}
