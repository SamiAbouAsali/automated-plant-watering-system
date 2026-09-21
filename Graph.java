
//Imports such as Array list to store data for graphing, and princeton for graphs
import java.util.ArrayList;
import edu.princeton.cs.introcs.StdDraw;

public class Graph {
    //Array lists to store time/voltage points for graphing later
    private ArrayList<Double> dataforTime = new ArrayList<>();
    private ArrayList<Double> dataforVoltage = new ArrayList<>();
    private double time = 0;

    public Graph() {
        // sets up canvas for the graph
        StdDraw.setCanvasSize(800, 600);

        //sets the scales for both x axis and y axis
        StdDraw.setXscale(0, 10);  // X-axis range from 0 to 10 seconds
        StdDraw.setYscale(0, 5);  // Y-axis range from 0 to 5 volts


        //the thickness of the graph line,
        StdDraw.setPenRadius(0.005);

        // adds labels for x and y axis
        StdDraw.text(5, -0.4, "Time [M]");
        for (int i = 0; i <= 10; i++) {
            StdDraw.text(i, -0.2, String.valueOf(i));
        }

        StdDraw.text(-0.5, 2.5, "Voltage [V]");
        for (int i = 0; i <= 5; i++) {
            StdDraw.text(-0.3, i, String.valueOf(i));
        }
    }

    public void addData(double voltage) {
        //stores current voltage and time
        dataforTime.add(time);
        dataforVoltage.add(voltage);
        time += 0.1;

        //Draws the graph
        StdDraw.clear();
        StdDraw.line(0, 0, 10, 0);  // X-axis (updated for new range)
        StdDraw.line(0, 0, 0, 5);   // Y-axis (updated for new range)

        StdDraw.text(5, -0.4, "Time [s]");
        for (int i = 0; i <= 10; i++) {
            StdDraw.text(i, -0.2, String.valueOf(i));
        }

        StdDraw.text(-0.5, 2.5, "Voltage [V]");
        for (int i = 0; i <= 5; i++) {
            StdDraw.text(-0.3, i, String.valueOf(i));
        }

        // Plots and connects the data points to make a line
        for (int i = 1; i < dataforTime.size(); i++) {
            double x1 = dataforTime.get(i - 1);
            double y1 = dataforVoltage.get(i - 1);
            double x2 = dataforTime.get(i);
            double y2 = dataforVoltage.get(i);
            StdDraw.line(x1, y1, x2, y2);
        }
        for (int i = 0; i < dataforTime.size(); i++) {
            StdDraw.point(dataforTime.get(i), dataforVoltage.get(i));  // Plot the points
        }
    }

    public void show() {
        StdDraw.show();
    }
}