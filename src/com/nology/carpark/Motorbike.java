package com.nology.carpark;

public class Motorbike extends Vehicle {

    public Motorbike(String regNumber) {
        super(regNumber);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.MOTORBIKE;
    }

    @Override
    public String toString() {
        return "Motorbike " + this.regNumber + " " + "\uD83D\uDEB2";
    }

}
