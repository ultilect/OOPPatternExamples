package org.example.structuralPatterns;

import javax.xml.crypto.Data;

/**
 * Служит для косвенного обращения к другому объекту
 */
/*
    Например, можно использовать для кэширования
 */
public class ProxyExample {
    public static void test() {
        BigData proxy = BigDataProxy.getInstance();
        String data = proxy.getData();
        String cacheData = proxy.getData();
    }
}

interface BigData {
    String getData();
}

class BigDataImpl implements BigData {
    @Override
    public String getData() {
        try {
            System.out.println("Getting data");
            Thread.sleep(1000);
            return "Big data";
        } catch (InterruptedException ex) {
            System.out.println("Error getting data");
            return "error";
        }
    }
}

class BigDataProxy implements BigData {
    private static BigDataProxy instance;

    private BigData data;
    private String cache;
    private BigDataProxy() {
        data = new BigDataImpl();
    }

    public static BigDataProxy getInstance() {
        if(instance == null) {
            instance = new BigDataProxy();
        }

        return instance;
    }

    @Override
    public String getData() {
        if (cache == null) {
            cache = this.data.getData();
        }
        return cache;
    }
}