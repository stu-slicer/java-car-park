package com.nology.carpark;

public abstract class Vehicle {

    protected String regNumber;

    public Vehicle(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public abstract VehicleType getType();
}
