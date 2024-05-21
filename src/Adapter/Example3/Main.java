package Adapter.Example3;

// target1 connects to target2
interface HDMI {
    void connectHDMI();
}

// target2
interface VGA {
    void connectVGA();
}

// Adaptee connects to target1
class Monitor implements HDMI {
    @Override
    public void connectHDMI() {
        System.out.println("HDMI ile monitör bağlantısı yapıldı.");
    }
}

// Object Adapter implements target
class HDMItoVGAAdapter implements VGA {
    private HDMI hdmiMonitor; // adaptee object

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

