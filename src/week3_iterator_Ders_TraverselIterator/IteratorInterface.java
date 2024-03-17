package week3_iterator_Ders_TraverselIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorInterface {
    public static void main(String[] args) {
        ArrayList<String> ages = new ArrayList<>();
        ages.add("5");
        ages.add("Ali");
        ages.add("5.8");

        Iterator<String> iterator2 = ages.iterator() ;
        while(iterator2.hasNext()){
            for(int a=0;a<ages.size();a++){
                //Tüm elemanları bastırma
                String result= iterator2.next();
                System.out.println(result);

            }
            //Remove elements
            String name= iterator2.next();
            if(name.equals("5")){
                iterator2.remove();
            }



        }
    }
}
