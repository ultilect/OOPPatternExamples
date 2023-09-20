package org.example.creationalPatterns;

/**
 * Новые экземпляры класса создаются на основе уже существующего класса-прототипа(клонируются).
 * Может использоваться с другими паттернами, например, с фабрикой
 */

/*
    Например, создание разных населенных пунктов в игре
 */
public class PrototypeExample {
    public static void test() {
        LocalityFactory factory = new LocalityFactoryImpl(
                new BigCity("cityHall"),
                new SmallVillage("villageMainBuilding"));
        try {
            City newCity = factory.createCity();
            Village newVillage = factory.createVillage();
            System.out.printf("%s with main building %s\n", newCity.getClass().getName(), newCity.getCityHall());
            System.out.printf("%s with main building %s", newVillage.getClass().getName(), newVillage.getMainBuilding());
        } catch (CloneNotSupportedException ex) {
            System.out.println("error");
            System.out.println(ex.getMessage());
        }
    }
}
@SuppressWarnings("unchecked")
abstract class Prototype<T> implements Cloneable{
    public T copy() throws CloneNotSupportedException {
        return (T) super.clone();
    }

}

interface LocalityFactory {
    City createCity() throws CloneNotSupportedException;
    Village createVillage() throws CloneNotSupportedException;
}

class LocalityFactoryImpl implements LocalityFactory{
    private final City city;
    private final Village village;

    public LocalityFactoryImpl(City city, Village village) {
        this.city = city;
        this.village = village;
    }

    @Override
    public City createCity() throws CloneNotSupportedException {
        return city.copy();
    }

    @Override
    public Village createVillage() throws CloneNotSupportedException {
        return village.copy();
    }
}
abstract class City extends Prototype<City>{
    abstract String getCityHall();
}

abstract class Village extends Prototype<Village> {
    abstract String getMainBuilding();
}
 class BigCity extends City {
    private final String cityHall;

    public BigCity(String cityHall) {
        this.cityHall = "Big " + cityHall;
    }

    @Override
    String getCityHall() {
        return this.cityHall;
    }
}

class SmallVillage extends Village {
    private final String mainBuilding;

    public SmallVillage(String mainBuilding) {
        this.mainBuilding = "Small " + mainBuilding;
    }

    @Override
    String getMainBuilding() {
        return this.mainBuilding;
    }
}