package Singleton;
import java.util.ArrayList;
import java.util.HashMap;
/*
class Singleton { // it allows creation of only one instance of Singleton.
    private static Singleton instance;
    String name;
    private Singleton(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton("Ali");
        return instance;
    }
}
//Singleton class allows the creation
//of just one instance. SubSingleton should allow three.
public class SubSingleton extends Singleton {
    private static HashMap<String, SubSingleton> instances = new HashMap<>();
    private static final int MAX_INSTANCES = 3;
    private static int count = 0;


    private SubSingleton(String name) {
        super();

        // private constructor
    }



    public static void registerInstances(String name) {
        if (count < MAX_INSTANCES) {
            instances.put(name, new SubSingleton(name));
            count++;
        }
    }
    //Bonus question
    public static void registerInstances(ArrayList<String> names) {
        for (String name : names) {
            if (count < MAX_INSTANCES) {
                instances.put(name, new SubSingleton(name));
                count++;
            }
        }
    }


    public static SubSingleton getInstance(String name) {
        return instances.get(name);
    }
}



public class Test {
    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();
        System.out.println(s);
        Singleton s2 = Singleton.getInstance();
        System.out.println(s2);

        SubSingleton.registerInstances("First");
        SubSingleton.registerInstances("Second");
        SubSingleton.registerInstances("Third");

        SubSingleton s1 = SubSingleton.getInstance("First");
        System.out.println(s1);
        SubSingleton s4 = SubSingleton.getInstance("Second");
        System.out.println(s4);
        SubSingleton s3 = SubSingleton.getInstance("Third");
        System.out.println(s3);

        //BONUS PART
        ArrayList<String> names = new ArrayList<>();
        names.add("First");
        names.add("Second");
        names.add("Third");

        SubSingleton.registerInstances(names);



    }
}

*/