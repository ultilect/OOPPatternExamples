package org.example.structuralPatterns;
/**
 * Позволяет 'навесить' какие-то доп.свойства(изменить поведение) декорируемого класса.
 * Причем клиент не видит разницы между взаимодействием с декоратором и декорируемым классом
 */
/*
    Например, печать с помощью принтера, перед тем как печатать можно отформатировать текст
    или что-либо добавить.
 */
public class DecoratorExample {
    public static void test() {
        Printer printer = new ConcretePrinter();
        System.out.println("Print without decorator");
        printer.print();
        System.out.println("Print with decorator");
        Printer printerWithDecorator = new PrinterTitleDecorator(printer);
        printerWithDecorator.print();
    }
}

interface Printer {
    void print();
}

class ConcretePrinter implements Printer {
    @Override
    public void print() {
      System.out.println("Printing plain text");
    }
}

class PrinterTitleDecorator implements Printer {
    private final Printer printer;

    public PrinterTitleDecorator(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void print() {
        System.out.println("Printing title");
        printer.print();
    }
}