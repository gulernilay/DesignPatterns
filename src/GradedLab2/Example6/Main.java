package GradedLab2.Example6;
import java.util.ArrayList;
import java.util.List;

// Abstract Factory and Product Interfaces
interface CarFactory {
    Engine createEngine();
    Transmission createTransmission();
    ElectricalSystem createElectricalSystem();
}

abstract class Part {
    public abstract double getPrice();
    public abstract String getDescription();
}

abstract class Engine extends Part { }
abstract class Transmission extends Part { }

// Concrete Products
class V8Engine extends Engine {
    private double price;
    public V8Engine(double price) { this.price = price; }
    public double getPrice() { return price; }
    public String getDescription() { return "V8 Engine"; }
}

class AutomaticTransmission extends Transmission {
    private double price;
    public AutomaticTransmission(double price) { this.price = price; }
    public double getPrice() { return price; }
    public String getDescription() { return "6-Speed Automatic Transmission"; }
}

// Adapter for Electrical System
interface ElectricalSystem {
    int getVoltage();
}

class SimpleElectricalSystem implements ElectricalSystem {
    public int getVoltage() { return 12; }
}

class SpecialElectricalSystem {
    public int provideSpecialPower() { return 24; }
}

class ElectricalSystemAdapter implements ElectricalSystem {
    private SpecialElectricalSystem specialSystem;

    public ElectricalSystemAdapter(SpecialElectricalSystem special) {
        this.specialSystem = special;
    }

    public int getVoltage() {
        return specialSystem.provideSpecialPower() / 2; // Convert 24V to 12V
    }
}

// Concrete Factory
class FordFactory implements CarFactory {
    public Engine createEngine() {
        return new V8Engine(20000);
    }
    public Transmission createTransmission() {
        return new AutomaticTransmission(5000);
    }
    public ElectricalSystem createElectricalSystem() {
        return new ElectricalSystemAdapter(new SpecialElectricalSystem());
    }
}

// Singleton to manage factory instance
class FactoryManager {
    private static CarFactory instance = new FordFactory();
    public static CarFactory getInstance() {
        return instance;
    }
}

// Facade
class CarBuildingFacade {
    public void buildCar() {
        CarFactory factory = FactoryManager.getInstance();
        Engine engine = factory.createEngine();
        Transmission transmission = factory.createTransmission();
        ElectricalSystem electricalSystem = factory.createElectricalSystem();

        System.out.println("Building a Ford car:");
        System.out.println("Engine: " + engine.getDescription() + ", Price: " + engine.getPrice());
        System.out.println("Transmission: " + transmission.getDescription() + ", Price: " + transmission.getPrice());
        System.out.println("Electrical System Voltage: " + electricalSystem.getVoltage() + " volts");
    }
}

public class Main {
    public static void main(String[] args) {
        CarBuildingFacade facade = new CarBuildingFacade();
        facade.buildCar();
    }
}