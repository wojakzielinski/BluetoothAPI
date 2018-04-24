import bluetooth.Bluetooth;
import chart.Chart;

public class Main {
    public static void main(String[] args) throws Exception {
        Bluetooth bluetooth = new Bluetooth();
        bluetooth.start();

        Chart chart = new Chart();
        chart.start();

    }
}
