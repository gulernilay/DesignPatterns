package Adapter.Example1;
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
//This uses Objects Adapter.
//============================================================================

//This the  "Target" class.
//North America Socket. Our
//device is manufactured for to be
//used with a North American Socket.

interface NASocket {
    int usingNASocket();
}
//This is the "Adaptee" class. Continential Europe Socket
//The device will be used in Europe which has different sockets.

class EURSocket {
    public int usingEURSocket() {
        System.out.println(
                "Giving you 220 Volt using Europe Connection.");
        return 220;
    }
}

// This is the "Adapter" class. ConnectorAdapterNAtoEUR.
// We need a connector so our device works.

class ConnectorAdapterNAtoEUR implements NASocket {
    public int usingNASocket() {
        // Possibly do some other work and then call
        // usingEURSocket from the European socket.
        int voltage = _adaptee.usingEURSocket();
        return voltage;
    }
    ConnectorAdapterNAtoEUR (EURSocket adaptee) {
        _adaptee = adaptee;
    }
    private EURSocket _adaptee;
}
//Utility Class.

class VCR {
    public void powerUp(int voltage) { System.out.println("Powered up");}
}

// This is our client which will be using
// the Target (NASocket) Interface.

public class ObjectAdapter {
    public static void main(String[] args) {

        // Create adapter and place a request
        NASocket socket = new ConnectorAdapterNAtoEUR (
                new EURSocket()
        );
        // socket is-a North American socket. So our North
        // American device can connect.
        int voltage = socket.usingNASocket();
        VCR vcr = new VCR();
        vcr.powerUp(voltage);
    }
}


/*
Adapter deseni, bir sınıfın arabirimini başka bir sınıfın beklediği arabirime dönüştürmek için kullanılan bir yapısal desen olarak bilinir.
Bu desen, mevcut bir sınıfı, istemci kodunun kullanabileceği farklı bir arabirime sahip bir sınıfa uyarlamak için kullanılır.

Nesne Adaptörü (Object Adapter):
Bu yaklaşımda, adaptör sınıfı, adapte edilecek sınıfın bir örneğini içerir.
Adapte edilen sınıfın tüm davranışlarını (metodları ve özellikleri) kendi içinde kullanarak, istemciye uygun bir arabirim sunar.

Sınıf Adaptörü (Class Adapter):
Bu yaklaşımda, adaptör sınıfı, adapte edilecek sınıfı miras alır ve istemci için gerekli arabirimi sağlar.
Adapte edilen sınıfın tüm davranışlarını miras alarak, istemciye uygun bir arabirim sunar.

Örnekler:
Elektrik Adaptörleri: Farklı priz tipleri farklı ülkelerde bulunabilir. Örneğin, bir Avrupa ülkesinden Amerika'ya seyahat ederken, Avrupa prizini Amerikan prizine dönüştüren bir adaptöre ihtiyacınız olur. Bu adaptör, Avrupa prizine uyum sağlayan bir nesne adaptörü olarak düşünülebilir.
Yazılım Kütüphaneleri: Farklı yazılım kütüphaneleri veya framework'lerin farklı arabirimlere sahip olması yaygındır. Bir yazılım projesinde, bir kütüphanenin arabirimini, projenin geri kalanına uyumlu bir arabirime dönüştürmek için bir adaptör kullanılabilir. Bu, nesne adaptörü olarak düşünülebilir.
Veri Dönüşümü Araçları: Farklı veri formatları arasında dönüşüm yapmak için kullanılan araçlar da adapter desenine örnektir. Örneğin, bir XML verisini JSON formatına dönüştüren bir araç, nesne adaptörü olarak işlev görür.
Bluetooth Adaptörleri: Mobil cihazlar ve bilgisayarlar genellikle Bluetooth iletişimini destekler. Ancak, farklı Bluetooth versiyonları ve protokolleri olabilir. Bir cihazın Bluetooth 4.0 ile uyumlu olmasına rağmen, bir diğeri Bluetooth 5.0 kullanabilir. Bu durumda, bir adaptör aracılığıyla uyumsuz cihazların birbiriyle iletişim kurması sağlanabilir. Örneğin, Bluetooth 4.0 uyumlu bir cihaz, Bluetooth 5.0 kullanabilen bir cihazla iletişim kurmak için bir adaptör aracılığıyla uyum sağlayabilir. Bu, nesne adaptörü olarak düşünülebilir.
 */