package Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Abstract Subject class
abstract class SchoolBell {
    protected int _time; // internal clock
    protected Map<Classroom, Boolean> observers = new HashMap<>();

    public void Attach(Classroom classroom) {
        observers.put(classroom, true);
    }

    public void Detach(Classroom classroom) {
        observers.remove(classroom);
    }

    public void Notify() {
        for (Map.Entry<Classroom, Boolean> entry : observers.entrySet()) {
            if (entry.getValue()) {
                entry.getKey().Update(this);
            }
        }
    }

    abstract public void setTime(int time);
}

// Concrete class
class ConcreteSchoolBell extends SchoolBell {
    public ConcreteSchoolBell(int time) {
        _time = time;
    }

    public void setTime(int time) {
        _time = time;
        Notify();
    }
}

// Abstract Observer interface
interface Observer {
    public void Update(SchoolBell schoolBell);  // update observer state
}

// ConcreteObserver
class Classroom implements Observer {
    private SchoolBell _schoolBell;
    private String _classroomName; // Internal Observer state
    private Counter counter;

    // Constructor
    public Classroom(String name) {
        _classroomName = name;
        counter = new Counter();
    }

    public void Update(SchoolBell schoolBell) {
        _schoolBell = schoolBell;
        int time = _schoolBell._time;
        if (time == 20 || time == 50) {
            System.out.println("Notified " + _classroomName + " at " + time + " minutes.");
            counter.increaseCounter();
            System.out.println("Update Counter: " + counter.getCounter());
        }
    }
}

// Counter class
class Counter {
    private int updateCounter;

    public void increaseCounter() {
        updateCounter++;
    }

    public int getCounter() {
        return updateCounter;
    }
}

// Principal class
class Principal {
    private Map<ConcreteSchoolBell, List<Classroom>> bellClassroomMap = new HashMap<>();

    public void addBell(ConcreteSchoolBell bell, List<Classroom> classrooms) {
        bellClassroomMap.put(bell, classrooms);
    }

    public void removeBell(ConcreteSchoolBell bell) {
        bellClassroomMap.remove(bell);
    }

    public void modifyBell(ConcreteSchoolBell bell, List<Classroom> classrooms) {
        bellClassroomMap.put(bell, classrooms);
    }
}

// MainApp test application
public class Main {
    public static void main(String[] args) {
        // Create classrooms
        Classroom classroom1 = new Classroom("Classroom 1");
        Classroom classroom2 = new Classroom("Classroom 2");

        // Create School Bell and attach classrooms
        ConcreteSchoolBell schoolBell = new ConcreteSchoolBell(20); // Initial time

        schoolBell.Attach(classroom1);
        schoolBell.Attach(classroom2);

        // Create Principal and manage bells and classrooms
        Principal principal = new Principal();
        List<Classroom> classroomsForBell = new ArrayList<>();
        classroomsForBell.add(classroom1);
        classroomsForBell.add(classroom2);
        principal.addBell(schoolBell, classroomsForBell);

        // Change time, which notifies classrooms
        schoolBell.setTime(20);
        schoolBell.setTime(50);
        schoolBell.setTime(30);
        schoolBell.setTime(40);

        // Removing Classroom 2 from Notification list
        System.out.println("Removing Classroom 2 from Notification list");
        schoolBell.Detach(classroom2);

        schoolBell.setTime(20);
        schoolBell.setTime(50);
    }
}
