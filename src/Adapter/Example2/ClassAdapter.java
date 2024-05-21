package Adapter.Example2;

//============================================================================
//Name        : AdapterPattern.cpp
//
//The classes and/or objects participating in this pattern are:
//	1. Target   (NASocket)
//			Defines the domain-specific interface that Client (VCR)  uses.
//	2. Adapter   (ConnectorAdapterNAtoEUR)
//			Adapts the interface Adaptee to the Target interface.
//	3. Adaptee   (EURSocket)
//			Defines an existing interface that needs adapting.
//	4. Client   (VCR)
//			Collaborates with objects conforming to the Target (EURSocket)
//			interface.
//
//This uses Class Adapter.
//============================================================================


interface NASocket {//target class
    int usingNASocket();
}
class EURSocket { // adaptee class
    public int usingEURSocket() {
        System.out.println(
                "Giving you 220 Volt using Europe Connection.");
        return 220;
    }
}

class ConnectorAdapterNAtoEUR extends EURSocket implements NASocket {  // adapter extends adaptee implements target
    public int usingNASocket() {
        int voltage = usingEURSocket();
        return voltage;
    }
}
//Utility Class.
class VCR {
    public void powerUp(int voltage) { System.out.println("Powered up");}
}

public class ClassAdapter {
    public static void main(String[] args) {
        // Create the adapter.
        NASocket socket = new ConnectorAdapterNAtoEUR();
        int voltage = socket.usingNASocket();
        VCR vcr = new VCR();
        vcr.powerUp(voltage);
    }
}