package com.baeldung.sealed.records;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.constant.ClassDesc;

public class VehicleUnitTest {

    private static Vehicle car;
    private static Vehicle truck;

    @BeforeAll
    public static void createInstances() {
        car = new Car(4, "VZ500DA");
        truck = new Truck(16000, "VZ600TA");
    }

    @Test
    public void givenCar_whenUsingReflectionAPI_thenInterfaceIsSealed() throws ClassNotFoundException {
        Assertions.assertThat(car.getClass().isSealed()).isEqualTo(false);
        Assertions.assertThat(car.getClass().getInterfaces()[0].isSealed()).isEqualTo(true);
        Assertions.assertThat(car.getClass().getInterfaces()[0].getPermittedSubclasses())
                .contains(Class.forName(car.getClass().getCanonicalName()));
    }

    @Test
    public void givenTruck_whenUsingReflectionAPI_thenInterfaceIsSealed() throws ClassNotFoundException {
        Assertions.assertThat(truck.getClass().isSealed()).isEqualTo(false);
        Assertions.assertThat(truck.getClass().getInterfaces()[0].isSealed()).isEqualTo(true);
        Assertions.assertThat(truck.getClass().getInterfaces()[0].getPermittedSubclasses())
                .contains(Class.forName(truck.getClass().getCanonicalName()));
    }

    @Test
    public void givenCar_whenGettingPropertyTraditionalWay_thenNumberOfSeatsPropertyIsReturned() {
        Assertions.assertThat(getPropertyTraditionalWay(car)).isEqualTo(4);
    }

    @Test
    public void givenCar_whenGettingPropertyViaPatternMatching_thenNumberOfSeatsPropertyIsReturned() {
        Assertions.assertThat(getPropertyViaPatternMatching(car)).isEqualTo(4);
    }

    @Test
    public void givenTruck_whenGettingPropertyTraditionalWay_thenLoadCapacityIsReturned() {
        Assertions.assertThat(getPropertyTraditionalWay(truck)).isEqualTo(16000);
    }

    @Test
    public void givenTruck_whenGettingPropertyViaPatternMatching_thenLoadCapacityIsReturned() {
        Assertions.assertThat(getPropertyViaPatternMatching(truck)).isEqualTo(16000);
    }

    private int getPropertyTraditionalWay(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            return ((Car) vehicle).getNumberOfSeats();
        } else if (vehicle instanceof Truck) {
            return ((Truck) vehicle).getLoadCapacity();
        } else {
            throw new RuntimeException("Unknown instance of Vehicle");
        }
    }

    private int getPropertyViaPatternMatching(Vehicle vehicle) {
        if (vehicle instanceof Car car) {
            return car.getNumberOfSeats();
        } else if (vehicle instanceof Truck truck) {
            return truck.getLoadCapacity();
        } else {
            throw new RuntimeException("Unknown instance of Vehicle");
        }
    }

}
