package week3_iterator_Ders_TraverselIterator;
import java.util.ArrayList; //collection
import java.util.Iterator; // built-in Iterator Interface
import java.util.ListIterator;


public class IteratorExample {
    static void printAggregate(Iterator<Item> itr) {
        System.out.println("Iterating over collection:");
        while (itr.hasNext()) {
            Item element = itr.next();
            System.out.println(element.getName());
        }
        System.out.println();
    }

    static void printBackwardsAggregate(ListIterator<Item> itr) {
        System.out.println("Iterating Backwards:");
        while (itr.hasPrevious()){
            Item element = itr.previous();
            System.out.println(element.getName());

        }
        System.out.println();
    }


    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Ali");
        names.add("Veli");
        names.add("Funda");

        Iterator<String> iterator = names.iterator();
        while(iterator.hasNext()) { // arraylist içerisinde sırada bir eleman olduğu sürece dolaş
            String name=iterator.next(); // sıradaki elemanı çek
            System.out.println(name);
        }
        // 'Charlie' elemanını sil
        if (names.equals("Charlie")) {
            iterator.remove();
        }
        // Silme işleminden sonra listenin güncel halini yazdır
        System.out.println("\n'Charlie' silindikten sonra liste:");
        for (String name : names) {
            System.out.println(name);
        }

        ArrayList<Item> aggregate = new ArrayList<Item>();
        aggregate.add(new Item("Item 0"));
        aggregate.add(new Item("Item 1"));
        aggregate.add(new Item("Item 2"));
        aggregate.add(new Item("Item 3"));
        aggregate.add(new Item("Item 4"));
        aggregate.add(new Item("Item 5"));
        aggregate.add(new Item("Item 6"));
        aggregate.add(new Item("Item 7"));
        aggregate.add(new Item("Item 8"));

        Iterator<Item> iterator2 = aggregate.iterator();
        ListIterator<Item> itr = aggregate.listIterator(aggregate.size());


        printAggregate(iterator2);
        printBackwardsAggregate(itr);

        aggregate.forEach(item -> printString(item));
    }
    private static void printString(Item  it) {
        System.out.println(it.getName());
    }






    }


/*
first(), isDone(), next(), ve currentItem() metodları, genellikle Iterator tasarım kalıbında kullanılan ama Java'nın standart Iterator interface'inde bulunmayan metodlardır. Bu metodlar, özellikle daha soyut veya özelleştirilmiş iterator yapılarında görülür ve genellikle aşağıdaki gibi tanımlanırlar:

first(): Iterator'ü koleksiyonun ilk elemanına konumlandırır.
isDone(): Iterator'ün koleksiyonun sonuna ulaşıp ulaşmadığını kontrol eder. Eğer sona ulaşıldıysa true, aksi halde false döndürür.
next(): Iterator'ü koleksiyonun bir sonraki elemanına ilerletir.
currentItem(): Iterator'ün şu anki konumundaki elemanı döndürür.
 */
