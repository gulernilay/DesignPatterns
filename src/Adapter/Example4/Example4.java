package Adapter.Example4;
// Class Adapter örneği

// Adaptee
class HDMI {
    void connectHDMI() {
        System.out.println("HDMI ile monitör bağlantısı yapıldı.");
    }
}

// target
interface VGA {
    void connectVGA();
}

// class adapter extends adaptee implements target
class HDMItoVGAAdapter extends HDMI implements VGA {
    @Override
    public void connectVGA() {
        connectHDMI();
        System.out.println("Dönüştürücü aracılığıyla VGA ile monitör bağlantısı yapıldı.");
    }
}

// Kullanım
public class Example4 {
    public static void main(String[] args) {
        // HDMI'ı VGA'ya dönüştüren adaptör oluştur
        VGA vgaMonitor = new HDMItoVGAAdapter();
        // VGA ile monitör bağlantısı yap
        vgaMonitor.connectVGA();
    }
}

