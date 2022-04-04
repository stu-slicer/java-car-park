package com.nology.carpark;

import java.util.Random;

public class VehicleFactory {

    private static Random random = new Random();

    public static Vehicle create(VehicleType type) {
        switch (type) {
            case MOTORBIKE:
                return new Motorbike( generateRegNumber() ) ;
            case CAR:
                return new Car( generateRegNumber() ) ;
            case VAN:
                return new Van( generateRegNumber() ) ;
        }
        throw new IllegalArgumentException("Don't recognise the type'");
    }

    private static String generateRegNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            char ch = (char) ('A' + (char) random.nextInt(26));
            sb.append( ch ) ;
        }
        sb.append( random.nextInt(100) );
        sb.append(" ");
        for (int i = 0; i < 3; i++) {
            char ch = (char) ('A' + (char) random.nextInt(26));
            sb.append( ch ) ;
        }
        return sb.toString();
    }

}
