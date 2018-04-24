package bluetooth;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

public class MyDiscoveryListener implements DiscoveryListener {
    private static Object lock=new Object();

    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass arg1) {
        String name;
        try {
            name = btDevice.getFriendlyName(false);
        } catch (Exception e) {
            name = btDevice.getBluetoothAddress();
        }

        System.out.println("device found: " + name);

    }

    public void inquiryCompleted(int arg0) {
        synchronized (lock) {
            lock.notify();
        }
    }

    public void serviceSearchCompleted(int arg0, int arg1) {
    }

    public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
    }

}
