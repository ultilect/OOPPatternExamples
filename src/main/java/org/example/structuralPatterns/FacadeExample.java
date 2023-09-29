package org.example.structuralPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Упрощает доступ к отдельным компонентам системы, предоставляе единый интерфейс.
 * Причем при необходимости фасад может предоставить доступ к отдельным компонентам
 */
/*
    Например, начала/конец работы организации
 */
public class FacadeExample {
    public static void test() {
        Worker worker1 = new CommonWorker(1);
        Worker worker2 = new CommonWorker(2);
        Worker worker3 = new CommonWorker(3);
        Building organizationBuilding = new OrganizationBuilding();

        Organization organization = new Organization(organizationBuilding);
        organization.addWorker(worker1);
        organization.addWorker(worker2);
        organization.addWorker(worker3);

        organization.beginWorkDay();
        System.out.println();
        organization.endWorkDay();
    }
}

interface Worker {
    void startWork();
    void endWork();
}

class CommonWorker implements Worker {
    private final Integer id;

    public CommonWorker(Integer id) {
        this.id = id;
    }

    @Override
    public void startWork() {
        System.out.printf("%d worker started working\n", id);
    }

    @Override
    public void endWork() {
        System.out.printf("%d worker ended working\n", id);
    }
}
interface Building {
    void open();
    void closed();
}

class OrganizationBuilding implements Building {
    @Override
    public void open() {
        System.out.println("Building is opened");
    }

    @Override
    public void closed() {
        System.out.println("Building is closed");
    }
}
// Facade
class Organization {
    final List<Worker> workers;
    Building building;

    public Organization(Building building) {
        this.workers = new ArrayList<>();
        this.building = building;
    }

    void addWorker(Worker worker) {
        workers.add(worker);
    }
    void beginWorkDay() {
        building.open();
        workers.forEach(Worker::startWork);
    }

    void endWorkDay() {
        workers.forEach(Worker::endWork);
        building.closed();
    }
}