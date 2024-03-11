package week4_CompositePattern;
/*
(Component) Klasör  - )  Alt Klasör =Composite
        -)   Dosya =Leaf
 */

import java.util.ArrayList;

interface Klasor{
    public void Add(Klasor k);
    public void Remove (Klasor k);
    public String getName();
    void Display(int id);  //int depth

}

class AltKlasor implements Klasor{

    private ArrayList<Klasor>files2=new ArrayList<Klasor>();
    public String name;
    public AltKlasor(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void Add(Klasor d){
        files2.add(d);
    }
    public void Display(int id){
        for (int i=1;i<=id;i++){
            System.out.println(" + " + getName());;
        }
    }
    public void Remove(Klasor d){
        for(int i=0;i<files2.size();i++){
            if(files2.get(i).getName()==d.getName()){
                files2.remove(i);
                return;
            }

        }
    }


}
class File implements Klasor{
    public String name;
    public File(String name){
        this.name=name;
    }
    public void Add (Klasor d){
        System.out.println("Can not add a element to leaf");
    }
    public void Remove(Klasor d){
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

public class FileSystem {  //client
    public static void main(String[] args) {

        Klasor folder1 = new AltKlasor("Folder1");
        folder1.Add(new File("File1"));
        folder1.Add(new File("File2"));

        Klasor folder2 = new AltKlasor("Folder2");
        folder1.Add(new File("File3"));
        Klasor folder3 = new AltKlasor("Folder3");
        folder1.Add(folder3);
        folder1.Add(folder2);

        folder1.Display(0);
    }
}
