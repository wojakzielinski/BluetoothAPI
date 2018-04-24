package bluetooth;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;

public class Bluetooth {
    private static Object lock = new Object();
    public void start() {
        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            DiscoveryAgent agent = localDevice.getDiscoveryAgent();

            agent.startInquiry(DiscoveryAgent.GIAC, new MyDiscoveryListener());

            try {
                synchronized (lock) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Device Inquiry Completed. ");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
