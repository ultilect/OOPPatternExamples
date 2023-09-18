package org.example.creationalPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 *   Предоставляет нтерфейс для создания взаимосвязанных или взаимозависимых объектов,
 *   не специфируя их конкретный класс
 */

/*
    Например, взаимодействие с различными сетями блокчейн
 */

class AbstractFactoryExample {
    static void check() {
        BlockchainClient client;
        SolanaFactory solanaFactory = new SolanaFactory();

        client = new BlockchainClient(solanaFactory);

        EthereumFactory ethereumFactory = new EthereumFactory();
        client = new BlockchainClient(ethereumFactory);

    }
}
class BlockchainClient {
    private  ProviderConnection connection;
    private List<Transaction> transactionList;
    private Wallet wallet;
    BlockchainClient(BlockchainFactory factory) {
        connection = factory.createProviderConnection();
        transactionList = new ArrayList<>();
        wallet = factory.createWallet();
    }

    public void addTransaction() {
        this.transactionList.add(this.connection.createTransaction());
    }

}

//Abstract factory
interface BlockchainFactory {
    ProviderConnection createProviderConnection();
    Wallet createWallet();
}

interface ProviderConnection {
    String getTransactionInfo(Transaction transaction);
    List<String> getAllWalletFT(Wallet wallet);

    Transaction createTransaction();
    String getProviderUrl();
}

interface Transaction {
    void sendTransaction(ProviderConnection connection);
    String getSignature();
}

interface Wallet {
    Float getBalance(ProviderConnection connection);
}
//Concrete realization 1
class SolanaFactory implements BlockchainFactory {
    @Override
    public ProviderConnection createProviderConnection() {
        return new SolanaProviderConnection();
    }

    @Override
    public Wallet createWallet() {
        return new SolanaWallet();
    }
}

class SolanaProviderConnection implements ProviderConnection {
    private final String connectionUrl;

    public SolanaProviderConnection() {
        this.connectionUrl = "solana url";
    }

    @Override
    public String getTransactionInfo(Transaction transaction) {
        return transaction.getSignature();
    }

    @Override
    public List<String> getAllWalletFT(Wallet wallet) {
        System.out.println("Fetching data...");
        System.out.println("No ft on this solana wallet");
        return null;
    }

    @Override
    public String getProviderUrl() {
        return this.connectionUrl;
    }

    @Override
    public Transaction createTransaction() {
        return new SolanaTransaction();
    }
}

class SolanaTransaction implements Transaction {
    @Override
    public void sendTransaction(ProviderConnection connection) {
        System.out.printf("Sending transaction using %s connection%n", connection.getProviderUrl());
    }

    @Override
    public String getSignature() {
        return "Solana TransactionSignature";
    }
}

class SolanaWallet implements Wallet {
    @Override
    public Float getBalance(ProviderConnection connection) {
        System.out.printf("Getting wallet balance using %s%n", connection.getProviderUrl());
        return 0.0F;
    }
}

//Concrete realization 2
class EthereumFactory implements BlockchainFactory {
    @Override
    public EthereumProviderConnection createProviderConnection() {
        return new EthereumProviderConnection();
    }

    @Override
    public EthereumWallet createWallet() {
        return new EthereumWallet();
    }
}

class EthereumProviderConnection implements ProviderConnection {
    private final String providerUrl;

    public EthereumProviderConnection() {
        this.providerUrl = "eth provider";
    }

    @Override
    public String getTransactionInfo(Transaction transaction) {
        return transaction.getSignature();
    }

    @Override
    public List<String> getAllWalletFT(Wallet wallet) {
        System.out.println("Fetching data for eth wallet");
        System.out.println("No ft on eth wallet");
        return null;
    }

    @Override
    public String getProviderUrl() {
        return this.providerUrl;
    }

    @Override
    public Transaction createTransaction() {
        return new EthereumTranaction();
    }
}

class EthereumTranaction implements Transaction {
    @Override
    public void sendTransaction(ProviderConnection connection) {
        System.out.printf("Sending transaction using: %s\n", connection.getProviderUrl());
    }

    @Override
    public String getSignature() {
        return "Ethereum transaction signature";
    }
}

class EthereumWallet implements Wallet {

    @Override
    public Float getBalance(ProviderConnection connection) {
        System.out.printf("Getting wallet balance using %s%n", connection.getProviderUrl());
        return 0.0F;
    }
}