package week4_CompositePattern;

import java.util.ArrayList;

/*
Composite Pattern:
bireysel nesneleri ve nesnelerin birleşimlerini müşteriler için aynı arayüzü kullanarak işlemeye olanak tanır. Bu sayede müşteriler, tek nesnelerle ya da nesne kompozisyonlarıyla aynı şekilde etkileşimde bulunabilirler.
Client: Component arayüzü ile çalışan ve Component türündeki nesnelerle etkileşimde bulunan nesnedir.
Component Interface/Abstract Class :Client tarafından erişilen ve Leaf ve Composite sınıfları tarafından uygulanan soyut sınıftır ya da arayüzdür.
Leaf: Bireysel nesneleri temsil eder ve Component arayüzünün işlemlerini uygular. Kendi içinde çocukları olmayan nesnelerdir.
Composite: Component arayüzünü uygular ve alt Component nesnelerini içerebilir. Bir Composite nesnesi, birden fazla Leaf ve/veya Composite nesnesi içerebilir. Add, Remove ve GetChild metotları, bu alt nesneleri yönetmek için kullanılır.
Diagramın sağ alt köşesindeki döngüsel notasyon, Composite sınıfının bir işlemi yürütürken, içerdiği tüm Component nesneler üzerinde bu işlemi yürüteceğini gösterir (for each child in children child.Operation()). Bu, bir Composite nesnesinin bir operasyonu çağırdığı zaman, bu operasyonun tüm alt bileşenleri üzerinde tekrar edilerek uygulanacağını anlatır.
*/
interface Component{
    void Add(Component d);
    void Remove(Component d);
    void Display(int id);
    public String getName();
}

class Leaf implements Component{
    public String name;
    public Leaf(String name){
        this.name=name;
    }
    public void Add (Component d){
        System.out.println("Can not add a element to leaf");
    }
    public void Remove(Component d){
        System.out.println("Can not remove a element to leaf");
    }
    public void Display(int id){
        for(int i=0;i<=id;i++){
            System.out.println(" "+ name);
        }
    }
    public String getName(){
        return name;
    }
}

class Composite implements Component{
    private ArrayList<Component> components=new ArrayList<Component>();
    private String name;
    public Composite(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void Add(Component d){
        components.add(d);
    }
    @Override
    public void Remove(Component d) {
        String name=d.getName();
        for (int a = 0; a < components.size(); a++) {
            if (components.get(a).equals(name)) {
                components.remove(a);
                break;
            }
        }
    }
    public void Display(int id){
        for (int i=1;i<=id;i++){
            System.out.println(" + " + getName());;
        }
        for (int i=0;i<=components.size();i++){
            components.get(i).Display(id+2);
        }
    }
}

public class Main {  // Client
    public static void main(String[] args) {

        //Create a tree structure:
        Component root=new Composite("Electronics");
        root.Add(new Leaf("Red Line"));
        root.Add(new Leaf("Blue Circle"));
        root.Add(new Leaf("Green Box"));

        Component comp=new Composite("2 circles");
        root.Add(new Leaf("Black Circle"));
        root.Add(new Leaf("White Circle"));
        root.Add(comp);

        Component comp2=new Composite("Yellow Line");
        root.Add(new Leaf("RedLine"));
        root.Add(comp2);
        root.Remove(comp);
        root.Display(2);


    }
}
