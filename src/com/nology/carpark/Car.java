package com.nology.carpark;

public class Car extends Vehicle {

    public Car(String regNumber) {
        super(regNumber);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.CAR;
    }

    @Override
    public String toString() {
        return "Car " + this.regNumber + " " + "\uD83D\uDE97";
    }

}
