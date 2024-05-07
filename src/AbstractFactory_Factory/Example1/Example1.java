package AbstractFactory_Factory.Example1;
import java.util.ArrayList;

//=======================================================================
//Name        : AbstractFactory.cpp
// 1. AbstractFactory  ( CarFactory )
//	  Declares an interface for operations that create abstract products
// 2. ConcreteFactory  (OPELFactory,FordFactory)
//	  Implements the operations to create concrete product objects
// 3. AbstractProduct   (Part, Engine, Transmission)
//	  Declares an interface for a type of product object
// 4. Product  (OPEL_Engine, OPEL_Transmission,
//				 FORD_Engine, FORD_Transmission.)
//	  Defines a product object to be created by the corresponding
//    concrete factory implements the AbstractProduct interface
// 5. Client  (BuildCar)
//	  Uses interfaces declared by AbstractFactory and AbstractProduct
//    classes
//=======================================================================

// Product Part

//Abstract Product : Part
abstract class Part {
    abstract public String DisplayName() ;
    abstract public double DisplayPrice();
}
abstract class Engine extends Part{
    protected String name;
    protected double price;
    @Override
    public String DisplayName() { return name;}
    @Override
    public double DisplayPrice() {return price;}
}
abstract class Transmission extends Part{
    protected String name;
    protected double price;
    @Override
    public String DisplayName() {return name;}
    @Override
    public double DisplayPrice() {return price;}
}
class OpelEngine extends Engine{
    public OpelEngine(String name,double price) {
        this.name=name;
        this.price=price;
    }
}
class FordEngine extends Engine{
    public FordEngine(String name,double price) {
        this.name=name;
        this.price=price;
    }
}
class OpelTransmission extends Transmission{
    public OpelTransmission(String name,double price) {
        this.name=name;
        this.price=price;
    }
}
class FordTransmission extends Transmission{
    public FordTransmission(String name,double price) {
        this.name=name;
        this.price=price;
    }
}

abstract class CarFactory{
    abstract public Engine createEngine();
    abstract public Transmission createTransmission();
}
class OpelFactory extends CarFactory{
    public OpelEngine createEngine() {
        return new OpelEngine("OpelEngine1",5000);
    }
    public OpelTransmission createTransmission() {
        return new OpelTransmission("OpelTransmission1",50000);
    }
}
class FordFactory extends CarFactory{
    public FordEngine createEngine() {
        return new FordEngine("FordEngine1",50700);
    }
    public FordTransmission createTransmission() {
        return new FordTransmission("FordTransmission1",500400);
    }
}

//The 'Client'.
class BuildCar {
    // Object creation is delegated to factory.
    public void createCar(CarFactory factory) {
        parts = new ArrayList<Part>();
        parts.add(factory.createEngine());
        parts.add(factory.createTransmission());
    }
    void displayParts() {
        System.out.println("\tListing Parts\n\t-------------");
        parts.forEach(p  -> System.out.println("\t"+ p.DisplayName() +
                " " + p.DisplayPrice()));
    }
    private ArrayList<Part> parts;
}



public class Example1{
    public static void main(String[] args) {
        // Create factories.
        CarFactory OPEL = new OpelFactory();
        CarFactory FORD = new FordFactory();

        BuildCar car = new BuildCar();
        System.out.println("Creating OPEL");
        car.createCar(OPEL);
        car.displayParts();

        System.out.println("Creating FORD");
        car.createCar(FORD);
        car.displayParts();

    }
}





