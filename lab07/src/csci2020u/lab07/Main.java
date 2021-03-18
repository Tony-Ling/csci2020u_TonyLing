package csci2020u.lab07;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class Main extends Application{
    public static int floodCounter;
    public static int thunderstormCounter;
    public static int tornadoCounter;
    public static int marineCounter;

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lab 07 Solution");

        read();

        PieChart pc = new PieChart();

        PieChart.Data slice1 = new PieChart.Data("FLASH FLOOD", floodCounter);
        PieChart.Data slice2 = new PieChart.Data("SEVERE THUNDERSTORM", thunderstormCounter);
        PieChart.Data slice3 = new PieChart.Data("SPECIAL MARINE", marineCounter);
        PieChart.Data slice4 = new PieChart.Data("TORNADO", tornadoCounter);

        pc.getData().addAll(slice1, slice2, slice3, slice4);

        VBox vb = new VBox(pc);
        Scene scene = new Scene(vb);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void read() {
        String file = "src/csci2020u/lab07/weatherwarnings-2015.csv";
        String line = "";
        List<String> list = new ArrayList<String>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null){
                String[] value = line.split(",");
                list.add(value[5]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        floodCounter = Collections.frequency(list, "FLASH FLOOD");
        thunderstormCounter = Collections.frequency(list, "SEVERE THUNDERSTORM");
        tornadoCounter = Collections.frequency(list, "SPECIAL MARINE");
        marineCounter = Collections.frequency(list, "TORNADO");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}