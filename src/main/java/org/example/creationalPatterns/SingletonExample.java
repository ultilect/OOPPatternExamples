package org.example.creationalPatterns;

import java.util.UUID;

/**
 * Служит гарантией единственности экземпляра классп
 */
/*
    Например, нельзя создавать более 1 соединения в сети
 */
public class SingletonExample {
    public static void test() {
        Connection connection = Connection.getInstance();
        System.out.println(connection.getId());
        connection = Connection.getInstance();
        System.out.println(connection.getId());
    }
}

class Connection {
    private static Connection instance = null;
    private final UUID id;
    private Connection() {
        id = UUID.randomUUID();
    }

    public static Connection getInstance() {
        if (instance == null) {
            instance =  new Connection();
        }
        return instance;
    }

    public UUID getId() {
        return id;
    }
}