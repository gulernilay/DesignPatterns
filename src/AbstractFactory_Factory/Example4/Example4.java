package AbstractFactory_Factory.Example4;
import java.util.ArrayList;

//============================================================================
//Name        : FactoryMethod.cpp
//
//The classes and/or objects participating in this pattern are:
//1. Product  (Engine,Transmission - Abstract)
//	 Defines the interface of objects the factory method creates
//2. ConcreteProduct  (OPEL_Engine, OPEL_Transmission)
//	 Implements the Product interface
//3. Creator  (CarCreator)
//	 Declares the factory method, which returns an object of type Product.
//	 Creator may also define a default implementation of the factory method
//	 that returns a default ConcreteProduct object.
//	 May call the factory method to create a Product object.
//4. ConcreteCreator (OPELCreator)
//	 Overrides the factory method to return an instance of a ConcreteProduct.
//============================================================================

// Top "Abstract Product" Part class
abstract class Part {
    abstract public String displayName();
    abstract double getPrice();
}
// Engine base class.
class Engine extends Part {
    protected double price;
    protected String name;
    public double getPrice(){ return price; }
    public String displayName() { return name;	}
}
//Transmission base class
class Transmission extends Part {
    protected double price;
    protected String name;
    public double getPrice(){ return price; }
    public String displayName() { return name;	}
}

//A 'ConcreteProduct' class
class OPEL_Engine extends Engine {
    public OPEL_Engine(double p) {
        price = p;
        name = new String("OPEL Engine");
        System.out.println("OPEL Engine is created...");
    }
}

//A 'ConcreteProduct ' class
class OPEL_Transmission extends Transmission {
    public OPEL_Transmission(double p) {
        price = p;
        name = new String("OPEL Transmission");
        System. out.println("OPEL Transmission is created...");
    }
}

//An 'Abstract Creator' class
//--> CarCreator
abstract class CarCreator {
    // Object creation is delegated to factory.
    abstract public Engine createEngine();
    abstract public Transmission createTransmission();
    public void createCar() {
        parts = new ArrayList<Part>();
        parts.add(createEngine());
        parts.add(createTransmission());
    }
    void displayParts() {
        System.out.println("\tListing Parts\n\t-------------");
        parts.forEach(p-> System.out.println("\t" + p.displayName() +
                " " + p.getPrice()));
    }
    private ArrayList<Part> parts;
}

//A 'ConcreteCreator' class ---> OPELCreator

class  OPELCreator extends CarCreator {
    // Factory Method implementation
    // We are overriding the factory method
    public OPEL_Engine createEngine() {
        return new OPEL_Engine (25000.00);
    }

    // We are overriding the factory method
    public OPEL_Transmission createTransmission() {
        return new OPEL_Transmission(10000.00);
    }
}

public class Example4 {
    //Factory Method Design Pattern.
    //Entry point into main application.
    public static void main(String[] args) {
        // Create an OPELcar.
        CarCreator creator = new OPELCreator();
        System.out.println("Creating OPEL");
        creator.createCar();
        creator.displayParts();
    }
}
