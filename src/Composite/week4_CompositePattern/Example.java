

package week4_CompositePattern;

import java.util.ArrayList;

public class Example {  //Client class
    public static void main(String[] args) {
        Component2 root=new Composite2("University Departmants");
        Composite2 departmant1=new Composite2("Fizik");
        Composite2 departmant2=new Composite2("Matematik");

        root.Add(departmant1);
        root.Add(departmant2);

        Leaf2 hoca1=new Leaf2("Kaya HOCA");
        Leaf2 hoca2=new Leaf2("aHMET HOCA");
        departmant1.Add(hoca1);
        departmant1.Add(hoca2);


        Leaf2 hoca3=new Leaf2("yALIN HOCA");
        Leaf2 hoca4=new Leaf2("Nihal HOCA");
        departmant2.Add(hoca3);
        departmant2.Add(hoca4);

        root.Display(1);
        departmant1.Remove(hoca1);
        root.Display(1);



    }
}

interface Component2{
    void Add(Component2 d);
    void Remove(Component2 d);
    void Display(int id);
    public String getName();
}

 class Leaf2 implements Component2{
    public String name;
    public Leaf2(String name){
        this.name=name;
    }
    @Override
    public void Add(Component2 d) {
        System.out.println("Leaf node has no child so , there is no addition");
    }

    @Override
    public void Remove(Component2 d) {
        System.out.println("Leaf node can't be deleted.");
    }
    @Override
    public void Display(int id){
        //for(int i=0;i<=id;i++){
        //  System.out.println(" "+ name);
        // }
        System.out.println(" ".repeat(id) + "- " + getName());
    }

    @Override
    public String getName() {
        return name;
    }
}

class Composite2 implements Component2{
    private ArrayList<Component2> list=new ArrayList<>(100);
    public String name;
    public Composite2(String name){
        this.name=name;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void Add(Component2 d) {
        list.add(d);
    }

    @Override
    public void Remove(Component2 d) {
        for (int a = 0; a < list.size(); a++) {
            if (list.get(a).getName()==d.getName()) {
                list.remove(a);
                break;
            }
        }
    }
    @Override
    public void Display(int id){
            System.out.println(" ".repeat(id) + "+ " + getName());
        for (Component2 component : list) {
            component.Display(id + 2);
        }
    }

}
