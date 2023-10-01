package org.example.structuralPatterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Используется для представления составных объектов со связью часть-целое.
 * Позволяет обращаться с составным объектом и его частями через единый интерфейс.
 */
/*
    Например, корзина с товарами(могут быть комплекты, состоящие из нескольких товаров, просто товары)
 */
public class CompositeExample {
    public static void test() {
       BasketComposite basket = new Basket(new ArrayList<>());
       basket.add(new Product(10.0, "product1"));
       BasketComposite set = new SetOfGoods(new ArrayList<>(), "setofgoods1");
       set.add(new Product(20.0, "product2"));
       set.add(new Product(30.0, "product3"));
       basket.add(set);
       basket.print();
    }
}

abstract class BasketComposite {
    Double getPrice() {
        return 0.0;
    }
    abstract void add(BasketComposite child);
    abstract void remove(BasketComposite child);
    abstract void print();
}

class Basket extends BasketComposite {
    private final List<BasketComposite> children;
    public Basket(List<BasketComposite> children) {
        this.children= children;
    }

    @Override
    Double getPrice() {
        return children.stream().mapToDouble(BasketComposite::getPrice).sum();
    }

    @Override
    void add(BasketComposite child) {
        children.add(child);
    }

    @Override
    void remove(BasketComposite child) {
        children.remove(child);
    }

    @Override
    void print() {
        System.out.printf("This is basket.\n Total price: %f\nElements:\n", this.getPrice());
        children.forEach(BasketComposite::print);
    }
}

class Product extends BasketComposite {
    private final double price;
    private final String name;
    public Product(double price, String name) {
        this.price = price;
        this.name = name;
    }

    @Override
    Double getPrice() {
        return this.price;
    }

    @Override
    void add(BasketComposite child) {
        System.out.println("Warning, unsupported operation add for product");
    }

    @Override
    void remove(BasketComposite child) {
        System.out.println("Warning, unsupported operation delete for product");
    }

    @Override
    void print() {
        System.out.printf("Name: %s with price: %f \n", this.name, this.getPrice());
    }
}

class SetOfGoods extends BasketComposite {
   private final List<BasketComposite> children;
   private final String name;

    public SetOfGoods(List<BasketComposite> children, String name) {
        this.children = children;
        this.name = name;
    }

    @Override
    Double getPrice() {
        return this.children.stream().mapToDouble(BasketComposite::getPrice).sum();
    }

    @Override
    void add(BasketComposite child) {
        this.children.add(child);
    }

    @Override
    void remove(BasketComposite child) {
        this.children.remove(child);
    }

    @Override
    void print() {
        System.out.printf("This is set of goods with name %s and price %f \nGoods:",this.name, this.getPrice());
        this.children.forEach(BasketComposite::print);
    }
}