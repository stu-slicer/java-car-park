package com.nology.carpark;

public class VanSpace extends Space {

    @Override
    public SpaceType getType() {
        return SpaceType.VAN;
    }

    @Override
    public boolean canParkVehicle(VehicleType type) {
        return type == VehicleType.VAN;
    }
}
