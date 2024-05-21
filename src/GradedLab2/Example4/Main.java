package GradedLab2.Example4;
import java.util.HashMap;
import java.util.Map;
/*
// Singleton Pattern - InventoryManagementSystem
public class InventoryManagementSystem {
    private static InventoryManagementSystem instance;
    private Map<String, Integer> inventory;

    private InventoryManagementSystem() {
        inventory = new HashMap<>();
    }

    public static synchronized InventoryManagementSystem getInstance() {
        if (instance == null) {
            instance = new InventoryManagementSystem();
        }
        return instance;
    }

    public void addItem(String item, int quantity) {
        inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
        System.out.println(quantity + " units of " + item + " added to inventory.");
    }

    public void removeItem(String item, int quantity) {
        int currentQuantity = inventory.getOrDefault(item, 0);
        if (currentQuantity >= quantity) {
            inventory.put(item, currentQuantity - quantity);
            System.out.println(quantity + " units of " + item + " removed from inventory.");
        } else {
            System.out.println("Insufficient quantity of " + item + " in inventory.");
        }
    }

    public int checkStockLevel(String item) {
        return inventory.getOrDefault(item, 0);
    }

    public void generateInventoryReport() {
        System.out.println("Inventory Report:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

// Facade Pattern - InventoryFacade
public class InventoryFacade {
    private final InventoryManagementSystem inventorySystem;

    public InventoryFacade() {
        this.inventorySystem = InventoryManagementSystem.getInstance();
    }

    public void addItem(String item, int quantity) {
        inventorySystem.addItem(item, quantity);
    }

    public void removeItem(String item, int quantity) {
        inventorySystem.removeItem(item, quantity);
    }

    public int checkStockLevel(String item) {
        return inventorySystem.checkStockLevel(item);
    }

    public void generateInventoryReport() {
        inventorySystem.generateInventoryReport();
    }
}

// Abstract Factory Pattern - InventoryFactory
public interface InventoryFactory {
    InventoryManager createInventoryManager();
    StockChecker createStockChecker();
    ReportGenerator createReportGenerator();
}

// Concrete Factories
public class InMemoryInventoryFactory implements InventoryFactory {
    @Override
    public InventoryManager createInventoryManager() {
        return new InMemoryInventoryManager();
    }

    @Override
    public StockChecker createStockChecker() {
        return new InMemoryStockChecker();
    }

    @Override
    public ReportGenerator createReportGenerator() {
        return new InMemoryReportGenerator();
    }
}

public class DatabaseInventoryFactory implements InventoryFactory {
    @Override
    public InventoryManager createInventoryManager() {
        return new DatabaseInventoryManager();
    }

    @Override
    public StockChecker createStockChecker() {
        return new DatabaseStockChecker();
    }

    @Override
    public ReportGenerator createReportGenerator() {
        return new DatabaseReportGenerator();
    }
}

// Abstract Products
public interface InventoryManager {
    void addItem(String item, int quantity);
    void removeItem(String item, int quantity);
}

public interface StockChecker {
    int checkStockLevel(String item);
}

public interface ReportGenerator {
    void generateInventoryReport();
}

// Concrete Products
public class InMemoryInventoryManager implements InventoryManager {
    private final InventoryManagementSystem inventorySystem = InventoryManagementSystem.getInstance();

    @Override
    public void addItem(String item, int quantity) {
        inventorySystem.addItem(item, quantity);
    }

    @Override
    public void removeItem(String item, int quantity) {
        inventorySystem.removeItem(item, quantity);
    }
}

public class InMemoryStockChecker implements StockChecker {
    private final InventoryManagementSystem inventorySystem = InventoryManagementSystem.getInstance();

    @Override
    public int checkStockLevel(String item) {
        return inventorySystem.checkStockLevel(item);
    }
}

public class InMemoryReportGenerator implements ReportGenerator {
    private final InventoryManagementSystem inventorySystem = InventoryManagementSystem.getInstance();

    @Override
    public void generateInventoryReport() {
        inventorySystem.generateInventoryReport();
    }
}

// Database implementations can be similar to the in-memory implementations but interact with a database

public class Main {
    public static void main(String[] args) {
        // Use facade to interact with the inventory management system
        InventoryFacade inventoryFacade = new InventoryFacade();
        inventoryFacade.addItem("Product A", 100);
        inventoryFacade.addItem("Product B", 200);
        inventoryFacade.removeItem("Product A", 50);
        System.out.println("Stock level of Product A: " + inventoryFacade.checkStockLevel("Product A"));
        inventoryFacade.generateInventoryReport();
    }
}



/*

Certainly, here's another question incorporating the Adapter, Singleton, Facade, and Abstract Factory patterns:

Question:

You have been assigned to develop a system for managing inventory in a warehouse using Java. The system should handle various operations such as adding/removing items from inventory, tracking stock levels, and generating inventory reports. Additionally, it should provide a user-friendly interface for warehouse staff to interact with.

Adapter Pattern:
Implement an adapter to integrate the existing barcode scanner with the inventory management system. The adapter should adapt the barcode scanning functionality to the standard interface used by the system for adding items to inventory.
Singleton Pattern:
Ensure that the inventory management system follows the Singleton pattern to ensure that there is only one instance of the system available throughout the application's lifecycle.
Facade Pattern:
Design a facade to simplify the process of interacting with the inventory management system. The facade should provide methods for adding/removing items from inventory, checking stock levels, and generating inventory reports.
Abstract Factory Pattern:
Implement an abstract factory to create different types of inventory management components (e.g., InventoryManager, StockChecker, ReportGenerator). Each factory should be capable of creating components that support specific functionalities and data storage mechanisms (e.g., in-memory, database).
Your implementation should demonstrate the usage of these design patterns to create a modular, efficient, and user-friendly inventory management system for the warehouse.

Provide the Java code for your implementation along with explanations of how each design pattern is applied in the context of the inventory management system.
 */



