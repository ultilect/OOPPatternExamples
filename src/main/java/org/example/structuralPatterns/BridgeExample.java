package org.example.structuralPatterns;

import java.util.prefs.PreferenceChangeEvent;

/**
 * Служит для разделения абстракции и реализации, чтобы их можно было независимо изменять.
 * Например, в случае существования множества реализаций интерфейса, каждая из которых реализует еще что-то
 */
/*
    Например, различные производители ноутбуков, у которых могут быть процессоры различных производителей
 */
public class BridgeExample {
    public static void test() {
        Laptop laptop = new CompanyLaptop("model1", new SecondProcessor("processor1"));
        System.out.printf("Model %s\n", laptop.getModel());
        laptop.executeTask("NewTask1");
        laptop = new Company2Laptop("newmodel", new FirstProcessor("processor2"));

        System.out.printf("Model %s\n", laptop.getModel());
        laptop.executeTask("NewTask2");
    }
}

interface Laptop {
    String getModel();
    void executeTask(String name);
    Processor getProcessor();
}

interface Processor {
    String getName();

    void performTask(String name);
}

class CompanyLaptop implements Laptop{
    private final String model;
    private final Processor processor;

    public CompanyLaptop(String model, Processor processor) {
        this.model = model;
        this.processor = processor;
    }

    @Override
    public String getModel() {
        return String.format("First company laptop of model %s with processor %s",
                this.model,
                this.processor.getName());
    }

    @Override
    public Processor getProcessor() {
        return processor;
    }

    @Override
    public void executeTask(String name) {
        this.processor.performTask(name);
    }
}


class Company2Laptop implements Laptop{
    private final String model;
    private final Processor processor;

    public Company2Laptop(String model, Processor processor) {
        this.model = model;
        this.processor = processor;
    }

    @Override
    public String getModel() {
        return String.format("Second company laptop of model %s with processor %s",
                this.model,
                this.processor.getName());
    }

    @Override
    public Processor getProcessor() {
        return processor;
    }

    @Override
    public void executeTask(String name) {
        this.processor.performTask(name);
    }
}

class FirstProcessor implements Processor {
    private final String name;
    FirstProcessor(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void performTask(String name) {
        System.out.printf("Executing task %s using first processor type\n", name);
    }
}

class SecondProcessor implements Processor {
    private final String name;
    SecondProcessor(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void performTask(String name) {
        System.out.printf("Executing task %s using second processor type\n", name);
    }
}
