package org.example.creationalPatterns;

/**
 * Фабричный метод позволяет передать право создания подклассу.
 * Возможна более сложная связь подкласса с другими классами
 */

/*
    Например, получение машины конкретного класса для разных производителей
 */
public class FactoryMethodExample {
    public static void test() {
        CarFactory carFactory = new AudiCarFactory();
        Car car = carFactory.getCar(CarClass.SPORT);
        System.out.println(car);
        carFactory = new LadaCarFactory();
        car = carFactory.getCar(CarClass.HATCHBACK);
        System.out.println(car);
    }
}

enum CarClass {
    SPORT,
    HATCHBACK
}
interface CarFactory {
    Car getCar(CarClass car);
}
abstract class Car {
    CarClass carClass;

    abstract Double getMaxSpeed();
}

class AudiCar extends Car {
    public AudiCar(CarClass carClass){
        this.carClass = carClass;
    }

    @Override
    Double getMaxSpeed() {
        return switch (this.carClass) {
            case HATCHBACK -> 150.0;
            case SPORT -> 250.0;
        };
    }

    @Override
    public String toString() {
        return String.format("Audi car with type %s\nMaxSpeed: %f", this.carClass, this.getMaxSpeed());
    }
}

class LadaCar extends Car {
    public LadaCar(CarClass carClass){
        this.carClass = carClass;
    }

    @Override
    Double getMaxSpeed() {
        return switch (this.carClass) {
            case HATCHBACK -> 140.0;
            case SPORT -> 220.0;
        };
    }

    @Override
    public String toString() {
        return String.format("Lada car with type %s\nMaxSpeed: %f", this.carClass, this.getMaxSpeed());
    }
}
class AudiCarFactory implements CarFactory {
    @Override
    public Car getCar(CarClass car) {
        return new AudiCar(car);
    }
}

class LadaCarFactory implements CarFactory {
    @Override
    public Car getCar(CarClass car) {
        return new LadaCar(car);
    }
}
