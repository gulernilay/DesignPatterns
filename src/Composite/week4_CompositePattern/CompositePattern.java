package week4_CompositePattern;
import java.util.ArrayList;
//
// The classes and/or objects participating in this pattern are:
// 1. Component   (DrawingElement)
//		Declares the interface for objects in the composition. Implements
//      default behavior for the interface common to all classes, as
//      appropriate. declares an interface for accessing and managing its
//		child components.
// 2. Leaf   (PrimitiveElement)
//		represents leaf objects in the composition. A leaf has no children.
//	    Defines behavior for primitive objects in the composition.
// 3. Composite   (CompositeElement)
//		defines behavior for components having children. Stores child
//		components. Implements child-related operations in the Component interface.
// 4. Client  (CompositeApp)
//		Manipulates objects in the composition through the Component interface.

// This is the "Component". (i.e tree node.)

/*
interface HepsiTrendy11 {
    void Add(HepsiTrendy11 d);
    void Remove(HepsiTrendy11 d);
    void Display(int indent);
    public String getName();
}
//This is the "Leaf".
class Product implements HepsiTrendy11 {
    private String name;
    private double price;
    private String description;

    public String getName() { return name;}
    public double getPrice(){ return price;}
    public String getDescription(){ return  description; }

    public Product(String name,double price,String description) {
        this.name = name;
        this.price=price;
        this.description=description;
    }

    public void Add(HepsiTrendy11 c) {
        System.out.println("Cannot add to a Product.");
    }
    public  void Remove(HepsiTrendy11 c) {
        System.out.println("Cannot remove from a Product.");
    }
    public void Display(int indent) {
        for(int i = 1;i <= indent;i++) 	System.out.print("-");
        System.out.println(" "  + name);
    }
}
// This is the "Composite"
class Category implements HepsiTrendy11 {
    private String name;
    public String getName() { return name;}

    public Category(String name) {this.name = name;}
    public void Add(HepsiTrendy11 d) {elements.add(d);};
    public void Remove(HepsiTrendy11 d) {
        for (int i = 0; i< elements.size(); i++) {
            if (elements.get(i).getName() == d.getName()) {
                elements.remove(i);
                return;
            }
        }
    }
    public	void Display(int indent) {
        for(int i = 1;i <= indent;i++) System.out.print("-");
        System.out.println( "+ " + getName());

        // Display each child element on this node
        for (int i= 0; i< elements.size(); i++) {
            elements.get(i).Display(indent+2);
        }
    }

    public boolean find(String name) {
        if (getName().equals(name)) { //
            return true;
        }

        for (HepsiTrendy11 element : elements) {
            if (element instanceof Category) {
                Category category = (Category) element;
                if (category.find(name)) {
                    return true;
                }
            } else if (element instanceof Product) {
                Product product = (Product) element;
                if (product.getName().equals(name)) {
                    return true;
                }
            }
        }

        return false;
    }

    public int totalPrice(String name) {
        if (this.name.equals(name)) {
            return calculateTotalPrice();
        } else {
            for (HepsiTrendy11 element : elements) {
                if (element instanceof Category) {
                    Category category = (Category) element;
                    int result = category.totalPrice(name);
                    if (result != -1) {
                        return result;
                    }
                }
            }
        }
        return -1; // If the category does not exist in the hierarchy
    }

    private int calculateTotalPrice() {
        int total = 0;
        for (HepsiTrendy11 element : elements) {
            if (element instanceof Product) {
                Product product = (Product) element;
                total += product.getPrice();
            } else if (element instanceof Category) {
                Category category = (Category) element;
                total += category.calculateTotalPrice();
            }
        }
        return total;
    }





    private	ArrayList<HepsiTrendy11> elements = new ArrayList<HepsiTrendy11>();
}

//This is the "client"
public class CompositePattern {
    public static void main(String[] args) {

        Category root = new Category("HepsiTrendy11 Menu");

        Category electronics = new Category("Electronics");

        Category tv = new Category("TV");
        tv.Add(new Product("OLED TV", 1000, "High-quality OLED TV"));
        tv.Add(new Product("QLED TV", 800, "High-quality QLED TV"));
        electronics.Add(tv);

        Category pc = new Category("PC");
        pc.Add(new Product("RAM", 50, "High-speed RAM"));
        pc.Add(new Product("SSD", 100, "High-capacity SSD"));
        electronics.Add(pc);
        root.Add(electronics);

        Category fashion = new Category("Fashion");
        Category men = new Category("Men");
        men.Add(new Product("Suit", 300, "Elegant suit for men"));
        fashion.Add(men);

        Category women = new Category("Women");
        women.Add(new Product("Shirt", 50, "Stylish shirt for women"));
        women.Add(new Product("Skirt", 60, "Beautiful skirt for women"));
        fashion.Add(women);
        root.Add(fashion);

        Category outdoor = new Category("Outdoor");
        outdoor.Add(new Product("Tent", 150, "Durable tent for camping"));
        root.Add(outdoor);

        Category cosmetics = new Category("Cosmetics");
        Category skinCare = new Category("Skin Care");
        skinCare.Add(new Product("Face Cream", 40, "Moisturizing face cream"));
        skinCare.Add(new Product("Sun Protector", 30, "Effective sun protector"));
        cosmetics.Add(skinCare);
        cosmetics.Add(new Product("Shampoo", 20, "Nourishing shampoo"));
        cosmetics.Add(new Product("Parfum", 70, "Fragrant perfume"));
        root.Add(cosmetics);

        root.Display(0);
        boolean result= root.find("Computer");
        System.out.println("Result : " + result);
        int price=root.totalPrice("Fashion");
        System.out.println(price);
    }
}
*/