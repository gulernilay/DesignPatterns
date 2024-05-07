package Adapter.Example3;

// Monitör sınıfı, HDMI arabirimini uyguluyor.
interface HDMI {
    void connectHDMI();
}

// Hedef Arabirim
interface VGA {
    void connectVGA();
}

// Adapte edilecek sınıf
class Monitor implements HDMI {
    @Override
    public void connectHDMI() {
        System.out.println("HDMI ile monitör bağlantısı yapıldı.");
    }
}

// Object Adaptor :  HDMI arabirimini VGA arabirimine dönüştüren adapter sınıfı
class HDMItoVGAAdapter implements VGA {
    private HDMI hdmiMonitor;

    public HDMItoVGAAdapter(HDMI hdmiMonitor) {
        this.hdmiMonitor = hdmiMonitor;
    }

    @Override
    public void connectVGA() {
        hdmiMonitor.connectHDMI();
        System.out.println("Dönüştürücü aracılığıyla VGA ile monitör bağlantısı yapıldı.");
    }
}

//HDMI destekleyen bir monitör oluşturuyoruz. Daha sonra, bu HDMI monitörünü VGA arabirimine dönüştüren bir adaptör oluşturuyoruz ve son olarak VGA arabirimini kullanarak monitör bağlantısını gerçekleştiriyoruz.
public class Main {
    public static void main(String[] args) {
        // HDMI destekleyen bir monitör oluştur
        HDMI hdmiMonitor = new Monitor();

        // HDMI'ı VGA'ya dönüştüren adaptör oluştur
        VGA vgaMonitor = new HDMItoVGAAdapter(hdmiMonitor);

        // VGA ile monitör bağlantısı yap
        vgaMonitor.connectVGA();
    }
}

/*
Bu örnekte, bir nesne adaptörü (object adapter) kullandım. Nesne adaptörü, adapte edilecek sınıfı (HDMI) bir örneği olarak içerir ve hedef arabirimi (VGA) uygular. Adapter sınıfı, adapte edilecek sınıfın örneğini alır ve hedef arabirimdeki metotları çağırarak adaptasyon işlemini gerçekleştirir.

Yani, HDMItoVGAAdapter sınıfı, VGA arabirimini uygulayan bir sınıf olarak tasarlanmıştır. Bu sınıfın yapıcı metodunda, adaptasyon için bir HDMI nesnesi alınır ve connectVGA() metodunu çağırdığında, aslında içindeki HDMI nesnesinin connectHDMI() metodunu çağırmaktadır. Bu, nesne adaptörü deseninin bir örneğidir.

 */