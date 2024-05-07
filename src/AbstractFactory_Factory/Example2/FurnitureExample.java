package AbstractFactory_Factory.Example2;

// Abstract Factory : FurnitureCompany
// Concrete Factory :  Art Modern Company, History Furniture
// Abstract Product: Chair , Table
// Concrete product : Antique Chair, Antique Table


import java.util.ArrayList;

// Abstract Product  and Concrete Products
// Abstract Product1
abstract class Part {
    abstract public String DisplayName();
    abstract public double DisplayPrice();
}
// Abstract Product2
abstract class Chair extends Part{
    protected String name;
    protected double price;
    @Override
    public String DisplayName() {
        return name;
    }
    @Override
    public double DisplayPrice() {
        return price;
    }
}
// Abstract Product3
abstract class Table extends Part{
    protected String name;
    protected double price;
    @Override
    public String DisplayName() {
        return name;
    }
    @Override
    public double DisplayPrice() {
        return price;
    }
}
//Concrete Class 1
class ArtModern_Chair extends Chair{
    public ArtModern_Chair(String name,double price){
        this.name=name;
        this.price=price;
    }

}
//Concrete Class 2
class ArtModern_Table extends Table{
    public ArtModern_Table(String name,double price){
        this.name=name;
        this.price=price;
    }
}
//Concrete Class 3
class HistoryFurniture_Chair extends Chair{
    public HistoryFurniture_Chair(String name,double price){
        this.name=name;
        this.price=price;
    }
}
//Concrete Class 4
class HistoryFurniture_Table extends Table{
    public HistoryFurniture_Table(String name,double price){
        this.name=name;
        this.price=price;
    }
}
//Concrete Class 5
class FutureNow_Table extends Table{
    public FutureNow_Table(String name,double price){
        this.name=name;
        this.price=price;
    }
}
//Concrete Class 6
class FutureNow_Chair extends Chair{
    public FutureNow_Chair(String name,double price){
        this.name=name;
        this.price=price;
    }
}

// Abstract Factory and Concrete Factories:
// Abstract Factory

abstract class FurnitureCompany{
    abstract Chair createChair();
    abstract Table createTable();
}
// Concrete Factory 1
class ArtModernFurniture extends FurnitureCompany{
    @Override
    protected Chair createChair() {
        return new ArtModern_Chair("AC",700);
    }
    @Override
    protected Table createTable() {
        return new ArtModern_Table("AT",6000);
    }
}
// Concrete Factory 2
class HistoryFurniture extends FurnitureCompany{
    @Override
    protected Chair createChair() {
        return new HistoryFurniture_Chair("hc",67);
    }
    @Override
    protected Table createTable() {
        return new HistoryFurniture_Table("HT",67000);
    }
}
// Concrete Factory 3
class FutureNow extends FurnitureCompany{
    @Override
    protected Chair createChair() {
        return new FutureNow_Chair("fc",67777);
    }
    @Override
    protected Table createTable() {
        return new FutureNow_Table("ft",6770);
    }
}
// Client
class BuildFurniture{
    private ArrayList parts=new ArrayList<Part>();
    protected void createFurniture(FurnitureCompany fc){
         parts.add(fc.createTable());
         parts.add(fc.createChair());
    }
    protected void DisplayParts(){
        for (int i = 0; i < parts.size(); i++) {
            Part p = (Part) parts.get(i);
            System.out.println("\t" + p.DisplayName() + " " + p.DisplayPrice());
        }
    }

}

public class FurnitureExample{
    public static void main(String[] args) {
       ArtModernFurniture af=new ArtModernFurniture();
       HistoryFurniture hf=new HistoryFurniture();
       FutureNow fn=new FutureNow();
       BuildFurniture bf=new BuildFurniture();
       bf.createFurniture(af);
       bf.createFurniture(hf);
       bf.createFurniture(fn);
       bf.DisplayParts();
    }
}


