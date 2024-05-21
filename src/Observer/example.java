package Observer;
//simple weather station application- notify display devices (observers) whenever it updates its measurements such as temperature and humidity.

import java.util.ArrayList;
import java.util.List;

interface Observer { //all observers have update methods
    void update(float temperature, float humidity);
}

class WeatherStation {
    private float temperature;
    private float humidity;
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        measurementsChanged();
    }
}

class DisplayDevice implements Observer {
    private float temperature;
    private float humidity;

    @Override
    public void update(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("Current conditions: " + temperature + "C degrees and " + humidity + "% humidity");
    }
}






public class example {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        DisplayDevice displayDevice = new DisplayDevice();

        weatherStation.addObserver(displayDevice);
        weatherStation.setMeasurements(29.2f, 85.0f);
    }
}
