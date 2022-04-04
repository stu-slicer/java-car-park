package com.nology.carpark;

public class CompactSpace extends Space {

    @Override
    public SpaceType getType() {
        return SpaceType.COMPACT;
    }

    @Override
    public boolean canParkVehicle(VehicleType type) {
        return type == VehicleType.MOTORBIKE || type == VehicleType.CAR;
    }
}
