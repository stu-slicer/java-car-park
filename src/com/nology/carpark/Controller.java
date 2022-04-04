package com.nology.carpark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    private static CarPark carPark = new CarPark();

    private static Random random = new Random();

    public static void main(String[] args) {

        List<Vehicle> vehicles = buildVehciles();
        displayDetails();

        for (Vehicle vehicle : vehicles) {

            if( carPark.hasSpaceAvailable(vehicle) ) {
                carPark.parkVehicle(vehicle);
                displayDetails();
            } else {
                System.out.printf("Unable to park %s\n", vehicle);
            }
        }
    }

    private static void displayDetails() {

        System.out.printf("Is car park empty: %b\n", carPark.isCarParkEmpty());
        System.out.printf("Is car park full: %b\n", carPark.isCarParkFull());
        System.out.printf("Total free spaces: %d\n", carPark.getRemainingSpaces());
        System.out.printf("Total free spaces - compact: %d\n", carPark.getRemainingSpaces(SpaceType.COMPACT));
        System.out.printf("Total free spaces - standard: %d\n", carPark.getRemainingSpaces(SpaceType.STANDARD));
        System.out.printf("Total free spaces - van: %d\n", carPark.getRemainingSpaces(SpaceType.VAN));

    }

    private static List<Vehicle> buildVehciles() {
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int rand = random.nextInt(10);
            if( rand < 5 ) {
                vehicles.add( VehicleFactory.create( VehicleType.CAR) );
            } else if( rand < 8 ) {
                vehicles.add( VehicleFactory.create( VehicleType.MOTORBIKE) );
            } else {
                vehicles.add( VehicleFactory.create( VehicleType.VAN) );
            }
        }
        vehicles.forEach( System.out::println);
        return vehicles;
    }


}
