package week3_Lab;
import java.util.ArrayList;
import java.util.*;
/*
TASK :a list of TV channels is iterated.
 */

//Iterator pattern:
//
//Provide a way to access the elements of an aggregate object
//sequentially without exposing its underlying representation.
//
//The classes and/or objects participating in this pattern are:

//1. Iterator  (AbstractIterator)
//		defines an interface for accessing and traversing elements.
//2. ConcreteIterator  (Iterator)
//		implements the Iterator interface.
//		keeps track of the current position in the traversal of the aggregate.
//3. Aggregate  (AbstractCollection)
//		defines an interface for creating an Iterator object
//4. ConcreteAggregate  (Collection)
//		implements the Iterator creation interface to return an instance of the proper ConcreteIterator
//

public class IteratorPattern {
    static void printAggregate(AbstractIterator i) {
        System.out.println("Iterating over collection:");
        for(i.First();  !i.IsDone(); i.Next()) {
            System.out.print(i.CurrentItem().getName() + " , ");
            System.out.print(i.CurrentItem().getFrequency()+ " , ");
            System.out.println(i.CurrentItem().getCountryOrigin()  );
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        // Create Aggregate.
        AbstractAggregate aggregate = new Collection();
        aggregate.add(new Channel("Das Erste",10,"Germany"));
        aggregate.add(new Channel("CCTV-1", 657,"China "));
        aggregate.add(new Channel( "NOW",555, "Türkiye"));
        aggregate.add(new Channel("Show Tv",0,"Türkiye"));
        aggregate.add(new Channel("TVNZ-1",999,"NEW Zealand"));

        // Create Iterator
        AbstractIterator iterator = aggregate.CreateIterator();
        // Traverse the Aggregate.
        printAggregate(iterator);

        // Create TurkiyeIterator for channels broadcasted in Türkiye
        AbstractIterator turkiyeIterator = new TurkiyeIterator((Collection) aggregate);
        System.out.println("Iterating over channels broadcasted in Türkiye:");
        printAggregate(turkiyeIterator);
        // Create Frequency Iterator
        System.out.println("Please enters a number between “10 – 999”");
        int number= scanner.nextInt();
        AbstractIterator frequencyIterator=new FrequencyIterator((Collection) aggregate ,number);
        System.out.println("Iterating over channels that has frequency bw 0 and " +number);
        printAggregate(frequencyIterator);

        // Create Default Iterator
        AbstractIterator defaultIterator = aggregate.CreateIterator(IteratorType.DEFAULT, 0);
        printAggregate(defaultIterator);

        // Create TurkiyeIterator
        AbstractIterator turkiyeIterator2 = aggregate.CreateIterator(IteratorType.TURKIYE, 0);
        System.out.println("Iterating over channels broadcasted in Türkiye:");
        printAggregate(turkiyeIterator);

        // Create FrequencyIterator
        System.out.println("Please enter a number between 10 and 999:");
        int number2 = scanner.nextInt();
        AbstractIterator frequencyIterator2 = aggregate.CreateIterator(IteratorType.FREQUENCY, number2);
        System.out.println("Iterating over channels with frequency between 0 and " + number + ":");
        printAggregate(frequencyIterator);

        scanner.close();
    }
}


class TurkiyeIterator implements AbstractIterator {
    private Collection _collection;
    private int _current;

    public TurkiyeIterator(Collection collection) {
        _collection = collection;
        _current = 0;
        moveToNextTurkiyeChannel();
    }

    public void First() {
        _current = 0;
        moveToNextTurkiyeChannel();
    }

    public void Next() {
        _current++;
        moveToNextTurkiyeChannel();
    }

    public Channel CurrentItem() {
        return (IsDone() ? null : _collection.get(_current));
    }

    public Boolean IsDone() {
        return _current >= _collection.getCount();
    }

    private void moveToNextTurkiyeChannel() {
        while (!IsDone() && !_collection.get(_current).getCountryOrigin().equals("Türkiye")) {
            _current++;
        }
    }
}



class FrequencyIterator implements AbstractIterator {
    private Collection _collection;
    private int _current;
    private int _maxFrequency;

    public FrequencyIterator(Collection collection, int maxFrequency) {
        _collection = collection;
        _current = 0;
        _maxFrequency = maxFrequency;
        moveToNextChannelInFrequencyRange();
    }

    public void First() {
        _current = 0;
        moveToNextChannelInFrequencyRange();
    }

    public void Next() {
        _current++;
        moveToNextChannelInFrequencyRange();
    }

    public Channel CurrentItem() {
        return (IsDone() ? null : _collection.get(_current));
    }

    public Boolean IsDone() {
        return _current >= _collection.getCount();
    }

    private void moveToNextChannelInFrequencyRange() {
        while (!IsDone() && (_collection.get(_current).getFrequency() < 0 || _collection.get(_current).getFrequency() > _maxFrequency)) {
            _current++;
        }
    }
}



//
//Our concrete collection consists of Items.
//

class Item {
    public Item(String name) { _name = name; };
    public String getName() { return _name;};
    private String _name;
}

//
//This is the abstract "Iterator".
//		AbstractIterator
//

interface  AbstractIterator {
    void First();
    void Next();
    Boolean IsDone () ;
    Channel CurrentItem() ;
}

//
//This is the "concrete" Iterator for collection.
//		CollectionIterator
//

class CollectionIterator implements AbstractIterator {
    public void First() {_current = 0;}
    public void Next()  {_current++; }
    public Channel CurrentItem() { return (IsDone()?null:_collection.get(_current)); }
    public Boolean IsDone() {	return _current >= _collection.getCount(); }
    public CollectionIterator(Collection collection) {
        _collection = collection;
        _current = 0;
    }
    private Collection _collection;
    private int _current;
};


//
//This is the abstract "Aggregate".
//			AbstractAggregate
//

interface AbstractAggregate {
    AbstractIterator CreateIterator();
    AbstractIterator CreateIterator(IteratorType type, int data);
    void add(Channel channel);
    int getCount();
    Channel get(int index);
}


//
//This is the concrete Aggregate.
//			Collection
//
enum IteratorType {
    DEFAULT,
    TURKIYE,
    FREQUENCY
}
class Collection implements AbstractAggregate {
    private	 ArrayList<Channel> _channels= new ArrayList<Channel>();
    public	CollectionIterator CreateIterator() {
        return new CollectionIterator(this);
    }
    public TurkiyeIterator CreateTurkiyeIterator() {
        return new TurkiyeIterator(this);
    }

    public FrequencyIterator CreateFrequencyIterator(int maxFrequency) {
        return new FrequencyIterator(this, maxFrequency);
    }

    public int getCount () {return _channels.size(); }
    public void add(Channel channel) {_channels.add(channel);};
    public Channel get(int index) { return _channels.get(index);};
    public AbstractIterator CreateIterator(IteratorType type, int maxFrequency) {
        switch (type) {
            case TURKIYE:
                return new TurkiyeIterator(this);
            case FREQUENCY:
                return new FrequencyIterator(this, maxFrequency);
            default:
                return new CollectionIterator(this);
        }
    }


    }





class Channel{
    private String name;
    private int frequency;
    private String countryOrigin;

    public Channel(String name,int frequency,String countryOrigin) {
        this.name=name;
        this.frequency=frequency;
        this.countryOrigin=countryOrigin;

    };

    public String getName(){
        return name;
    }
    public int getFrequency(){
        return frequency;
    }
    public String getCountryOrigin(){
        return countryOrigin;
    }
    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name + '\'' +
                ", frequency=" + frequency +
                ", countryOrigin='" + countryOrigin + '\'' +
                '}';
    }

}

