package org.example.structuralPatterns;

import java.util.HashMap;
import java.util.Map;

/**
 * Используется для представления множества объектов в разных частях программы,
 * которые частично отличаются в зависимости от контекста
 */
/*
   Например, расчет параметров автомобился
 */
public class FlyweightExample {
    public static void test() {
        CarFactory carFactory = new CarFactoryImpl();
        Car badRoadsCar = carFactory.createCar("badRoads");
        Car commonCar = carFactory.createCar("common");
        Car commonCar2 = carFactory.createCar("newCar");

        CarContext carContext1 = new CarContext("goodRoads", 1.1);
        CarContext carContext2 = new CarContext("badRoads", 0.4);
        badRoadsCar.getMaxSpeed(carContext1);
        badRoadsCar.getMaxSpeed(carContext2);
        commonCar.getMaxSpeed(carContext1);
        commonCar2.getMaxSpeed(carContext2);
    }
}
interface CarFactory {
    Car createCar(String name);
}

class CarFactoryImpl implements CarFactory {
    private final Map<String, Car> cars = new HashMap<>();

    @Override
    public Car createCar(String name) {
        switch (name) {
            case "badRoads": {
                Car car = cars.get(name);
                if (car == null) {
                    car = new BadContextCar(200.0);
                    cars.put(name, car);
                }
                return car;
            }
            case "common": {
                Car car = cars.get(name);
                if (car == null) {
                    car = new CommonCar(150.0);
                    cars.put(name, car);
                }
                return car;
            }
            default: {
                System.out.println("New key for car factory");
                Car car = cars.get(name);
                if (car == null) {
                    car = new CommonCar(200.0);
                    cars.put(name, car);
                }
                return car;
            }
        }
    }
}
interface Car {
    Double getMaxSpeed(CarContext context);
}
class CarContext {
    private String name;
    private Double speedModificator;

    public CarContext(String name, Double speedModificator) {
        this.name = name;
        this.speedModificator = speedModificator < 0? 0 : speedModificator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSpeedModificator() {
        return speedModificator;
    }

    public void setSpeedModificator(Double speedModificator) {
        this.speedModificator = speedModificator;
    }
}

class CommonCar implements Car {
    private final Double maxSpeed;

    public CommonCar(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public Double getMaxSpeed(CarContext context) {
        Double maxSpeedWithContext = maxSpeed*context.getSpeedModificator();
        System.out.printf("Max speed for car %s with %s context is %f\n",this.getClass().getName(), context.getName(), maxSpeedWithContext);
        return maxSpeedWithContext;
    }
}

class BadContextCar implements Car {
    private final Double maxSpeed;

    public BadContextCar(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public Double getMaxSpeed(CarContext context) {
        final Double realSpeedModificator = context.getSpeedModificator() < 0.5 ?
                context.getSpeedModificator() + 0.5 : context.getSpeedModificator();
        Double maxSpeedWithContext = maxSpeed*realSpeedModificator;
        System.out.printf("Max speed for car %s with %s context is %f\n",this.getClass().getName(), context.getName(), maxSpeedWithContext);
        return maxSpeedWithContext;
    }
}