package com.nology.carpark;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class CarPark {

    private static final int STANDARD_PER_VAN = 3;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("hh:mm:ss");

    private List<CompactSpace> compactSpaces = new ArrayList<>();
    private List<StandardSpace> standardSpaces = new ArrayList<>();
    private List<VanSpace> vanSpaces = new ArrayList<>();

    private Map<Vehicle, Set<Space>> parkedVehicles = new HashMap<>();

    public CarPark() {
        initialise(12, 54, 5 );
    }

    public CarPark(int numCompact, int numStandard, int numLorry) {
        initialise(numCompact, numStandard, numLorry);
    }

    public void parkVehicle(Vehicle vehicle) {
        List<Space> spaces = getFreeSpacesForVehicle(vehicle.getType());
        if (spaces.isEmpty()) {
            throw new NoSpaceException(String.format("No free spaces for %s", vehicle.getRegNumber()));
        }
        if (vehicle.getType() == VehicleType.VAN && spaces.get(0).getType() == SpaceType.STANDARD) {
            // Space must be a standard, so van parks across a number.
            Set<Space> spacesUsed = new HashSet<>();
            for (int i = 0; i < 3; i++) {
                spaces.get(i).parkedVehicle(vehicle);
                spacesUsed.add( spaces.get(i));
            }
            parkedVehicles.put(vehicle, spacesUsed);

        } else {
            spaces.get(0).parkedVehicle(vehicle);
            parkedVehicles.put( vehicle, Set.of(spaces.get(0)) );
        }
        System.out.printf("Successfully parked %s at %s\n", vehicle, LocalTime.now().format( FORMATTER ) );
    }

    public void removeVehicle(Vehicle vehicle) {
        if (this.parkedVehicles.containsKey(vehicle)) {
            try {
                Set<Space> spaces = this.parkedVehicles.get(vehicle);
                spaces.forEach(space -> space.removeVehicle());
            } finally {
                this.parkedVehicles.remove(vehicle);
            }
        }
    }

    public boolean hasSpaceAvailable(Vehicle vehicle) {
        List<Space> spaces = getFreeSpacesForVehicle(vehicle.getType());
        return ! spaces.isEmpty();
    }

    private List<Space> getFreeSpacesForVehicle(VehicleType type) {
        List<Space> freeSpaces = new ArrayList<>();
        switch (type) {
            case MOTORBIKE:
                freeSpaces.addAll( compactSpaces.stream().filter( Space::isEmpty ).collect(Collectors.toList()) );
                freeSpaces.addAll( standardSpaces.stream().filter( Space::isEmpty ).collect(Collectors.toList()) );
                freeSpaces.addAll( vanSpaces.stream().filter( Space::isEmpty ).collect(Collectors.toList()) );
                break;
            case CAR:
                freeSpaces.addAll( standardSpaces.stream().filter( Space::isEmpty ).collect(Collectors.toList()) );
                freeSpaces.addAll( compactSpaces.stream().filter( Space::isEmpty ).collect(Collectors.toList()) );
                break;
            case VAN:
                freeSpaces.addAll( vanSpaces.stream().filter( Space::isEmpty ).collect(Collectors.toList()) );
                // Vans can be parked in 3 standard spaces
                List<StandardSpace> freeVanSpace = standardSpaces.stream().filter( Space::isEmpty ).collect(Collectors.toList());
                if( freeVanSpace.size() >= STANDARD_PER_VAN) {
                    freeSpaces.addAll(freeVanSpace);
                }
                break;
        }
        return freeSpaces;
    }

    /**
     * Total number of spaces remaining
     * @return
     */
    public int getRemainingSpaces() {
        return getRemainingSpaces(SpaceType.COMPACT) + getRemainingSpaces(SpaceType.STANDARD) + getRemainingSpaces(SpaceType.VAN);
    }

    /**
     * Total number of spaces remaining for given space type.
     * @param type
     * @return
     */
    public int getRemainingSpaces(SpaceType type) {
        switch (type) {
            case COMPACT:
                return (int) this.compactSpaces.stream().filter(Space::isEmpty).count();
            case STANDARD:
                return (int) this.standardSpaces.stream().filter(Space::isEmpty).count();
            case VAN:
                return (int) this.vanSpaces.stream().filter(Space::isEmpty).count();
        }
        return 0;
    }

    /**
     * Is the carp park full?
     * @return
     */
    public boolean isCarParkFull() {
        if(this.compactSpaces.stream().anyMatch(Space::isEmpty)) {
            return false;
        }
        if(this.standardSpaces.stream().anyMatch(Space::isEmpty)) {
            return false;
        }
        if(this.vanSpaces.stream().anyMatch(Space::isEmpty)) {
            return false;
        }
        return true;
    }

    /**
     * Is the car park empty?
     * @return
     */
    public boolean isCarParkEmpty() {
        if(this.compactSpaces.stream().anyMatch(Space::isFull)) {
            return false;
        }
        if(this.standardSpaces.stream().anyMatch(Space::isFull)) {
            return false;
        }
        if(this.vanSpaces.stream().anyMatch(Space::isFull)) {
            return false;
        }
        return true;
    }

    private void initialise(int numCompact, int numStandard, int numVan) {
        for (int i = 0; i < numCompact; i++) {
            compactSpaces.add( new CompactSpace());
        }
        for (int i = 0; i < numStandard; i++) {
            standardSpaces.add( new StandardSpace());
        }
        for (int i = 0; i < numVan; i++) {
            vanSpaces.add( new VanSpace());
        }
    }

}
