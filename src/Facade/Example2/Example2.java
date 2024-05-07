package Facade.Example2;

class Example2 {
    public static void main(String[] args) {
        //Bu sınıf, Facade desenini kullanarak bir televizyon seyretme senaryosunu temsil eder.
        EntertainmentFacade facade = new EntertainmentFacade();
        facade.watchMovie("Netflix","Cinema");
    }
}
// facade
class EntertainmentFacade{
    private TV tv;
    private SoundBar sb;
    private StreamingDevice sd;
    public EntertainmentFacade(){
        tv=new TV();
        sb=new SoundBar();
        sd=new StreamingDevice();
    }
    public void watchMovie(String streamingService, String soundMode) {
        tv.turnOn();
        sb.on();
        sb.changeMod(soundMode);
        sd.startDevice();
        sd.chooseApp(streamingService);
        System.out.println("Enjoy...");
    }



}

//Subsystem1
class TV{
    public void turnOn(){System.out.println("TV turned on...");}
}
//Subsystem2
class SoundBar{
    public void on(){System.out.println("The SoundBar is on now...");}
    public void changeMod(String mod){
        if(mod.equals("Cinema") || mod.equals("Tv Show"))
            System.out.println("The SoundBar mod changed to " + mod);
        else
            System.out.println("Wrong mod...");
    }
}
//Subsystem3
class StreamingDevice{
    public void startDevice(){System.out.println("Device is ready to stream...");}
    public void chooseApp(String app){
        if(app.equals("Netflix") || app.equals("Exxen") || app.equals("Amazon Prime"))
            System.out.println("Streaming from " + app);
        else
            System.out.println("Wrong app name...");
    }
}