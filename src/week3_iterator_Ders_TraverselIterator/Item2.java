package week3_iterator_Ders_TraverselIterator;
import java.util.ArrayList;


// Define the Aggregate interface
// Iterator sınıfından referans gönderen CreateIterator metotunu içerir.
interface AbstractAggregate {
    AbstractIterator CreateIterator(); // Method to create an iterator for the aggregate.
    void add(Item item);// Method to add an item to the aggregate.
    int getCount();// Method to get the number of items in the aggregate.
    Item get(int idx);// Method to retrieve an item by its index in the aggregate.
}

//A simple class representing an item in the collection.
class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Define the Iterator interface
//An interface that defines methods for iterating over a collection.
interface AbstractIterator {
    void First(); //imleci ilk elemena getirir.
    void Next(); // imleci bir sonraki elemena getirir.
    boolean isDone(); // koleksiyonun sonuna geldik mi gelmedikmi
    Item CurrentItem(); //
}

// Implement the ConcreteAggregate
// A concrete implementation of the aggregate interface using an ArrayList.
class Collection implements AbstractAggregate {  // Aggregate sınıfından türeyen diziler ,ağaçlar olabilir. Burada class Collection yerine Class Array da denilebilirdi.
    private ArrayList<Item> items = new ArrayList<>();

    @Override
    //// Method to create a new iterator for this collection.
    public AbstractIterator CreateIterator() {
        return new CollectionIterator(this);
    }

    // Method to add an item to the collection.
    @Override
    public void add(Item item) {
        items.add(item);
    }
    // Method to get the number of items in the collection.
    @Override
    public int getCount() {
        return items.size();
    }
    // Method to get an item by its index.
    @Override
    public Item get(int idx) {
        return items.get(idx);
    }
}

// A concrete iterator for the Collection class.
class CollectionIterator implements AbstractIterator {
    private int current = 0;// Index of the current item in the iteration.
    private Collection collection;// Reference to the collection being iterated.

    // Constructor initializes the iterator with the collection to iterate over.
    public CollectionIterator(Collection collection) {
        this.collection = collection;
    }
    // Resets the current position to the start of the collection.
    @Override
    public void First() {
        current = 0;
    }
    // Advances the current position to the next item in the collection.
    @Override
    public void Next() {
        current++;
    }
    // Checks if the current position is beyond the end of the collection.
    @Override
    public boolean isDone() {
        return current >= collection.getCount();
    }
    // Returns the current item or null if the end has been reached.
    @Override
    public Item CurrentItem() {
        if (isDone()) {
            return null;
        }
        return collection.get(current);
    }
}

// Utilize the Iterator to print items in the Aggregate
public class Item2 {
    // Method to iterate over a collection and print each item.
    static void printAggregate(AbstractIterator iterator) {
        System.out.println("Iterating over collection:");
        for (iterator.First(); !iterator.isDone(); iterator.Next()) {
            Item currentItem = iterator.CurrentItem();
            if (currentItem != null) {
                System.out.println(currentItem.getName());
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Create an instance of the collection aggregate.
        AbstractAggregate aggregate = new Collection(); // ister array,ister binary tree olsun farketmiyor
        aggregate.add(new Item("Nilay"));
        aggregate.add(new Item("Halil"));

        // Create an iterator for the aggregate.
        AbstractIterator iterator = aggregate.CreateIterator();

        // Traverse the Aggregate
        printAggregate(iterator);
    }
}

/*
To implement reverse traversal iterator:


class ReverseCollectionIterator implements AbstractIterator {
    private int current;
    private Collection collection;

    public ReverseCollectionIterator(Collection collection) {
        this.collection = collection;
        // Start at the end of the collection
        this.current = collection.getCount() - 1;
    }

    // Reset the current position to the last item in the collection.
    @Override
    public void First() {
        current = collection.getCount() - 1;
    }

    // Move the current position to the previous item in the collection.
    @Override
    public void Next() {
        current--;
    }

    // Check if the current position is before the start of the collection.
    @Override
    public boolean isDone() {
        return current < 0;
    }

    // Return the current item or null if before the start of the collection.
    @Override
    public Item CurrentItem() {
        if (isDone()) {
            return null;
        }
        return collection.get(current);
    }
}

public static void main(String[] args) {
    AbstractAggregate aggregate = new Collection();
    aggregate.add(new Item("Nilay"));
    aggregate.add(new Item("Halil"));

    // Create a reverse iterator for the aggregate
    AbstractIterator reverseIterator = new ReverseCollectionIterator((Collection) aggregate);

    // Use the iterator to traverse and print the aggregate's items in reverse
    printAggregate(reverseIterator);
}







 */
