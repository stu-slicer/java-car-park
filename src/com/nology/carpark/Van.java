package com.nology.carpark;

public class Van extends Vehicle {

    public Van(String regNumber) {
        super(regNumber);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.VAN;
    }

    @Override
    public String toString() {
        return "Van " + this.regNumber + " " + "\uD83D\uDE9A";
    }

}
