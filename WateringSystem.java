// imports from firmata4j
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;

public class WateringSystem {
    //Various static variables for things like my com, the moisture and pump pin, and the based drythreshold
    private static final String port = "COM4";
    private static final int moistPIN = 15;  // A2
    private static final int pumpPIN = 6;
    private static final double dryThresHold = 3.5;

    public static void main(String[] args) throws Exception {
        new WateringSystem().run();
    }

    public void run() throws Exception {
        //arduino device
        IODevice arduino = new FirmataDevice(port);
        arduino.start();
        arduino.ensureInitializationIsDone();
        //connecting pins for sensor / pump
        Pin moistureSensor = arduino.getPin(moistPIN);
        Pin pump = arduino.getPin(pumpPIN);

        moistureSensor.setMode(Pin.Mode.ANALOG);
        pump.setMode(Pin.Mode.OUTPUT);

//line to create a graph for moisture lvls.
        Graph graph = new Graph();

        while (true) {
            //gets moisture value from the sensor
            long moistureValue = moistureSensor.getValue();

           // converts to moisture level (in voltage)
            double moistureLevel = moistureValue * (5.0 / 1023.0);
            System.out.printf("Moisture Level: %.2f V%n", moistureLevel);

            // Plot the data
            graph.addData(moistureLevel);
//Sensor readings are higher in dry conditions and lower in wet conditions
/so values above the threshold indicate that the soil needs water
            if (moistureLevel > dryThresHold) {
                //if the soil is dry (1) to activate pump, stop 1 sec, stop
                pump.setValue(1);
                Thread.sleep(1000);
                pump.setValue(0);
                System.out.println("dry, watering the plant..");

                //LOOP
            } else {
                //if not then do nothing, its already wet
                System.out.println("Soil is Moist enough, [stop].");

                //LOOP
            }

            //shows the graph on the screen
            graph.show();

            //sleeps for 1 second (Inside while loop)
            Thread.sleep(1000);
        }
    }
}