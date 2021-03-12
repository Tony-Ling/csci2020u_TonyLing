package csci2020u.lab06;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private static double[] avgHousingPricesByYear = {
        247381.0,264171.4,287715.3,294736.1,
        308431.4,322635.9,340253.0,363153.7
    };

    private static double[] avgCommercialPricesByYear = {
        1121585.3,1219479.5,1246354.2,1295364.8,
        1335932.6,1472362.0,1583521.9,1613246.3
    };

    private static String[] ageGroups = {
        "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    
    private static int[] purchasesByAgeGroup = {
        648, 1021, 2453, 3173, 1868, 2247
    };
    
    private static Color[] pieColours = {
        Color.AQUA, Color.GOLD, Color.DARKORANGE,
        Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };
        
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lab 06");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        bc.setBarGap(0);

        XYChart.Series series1 = new XYChart.Series<>();

        series1.getData().add(new XYChart.Data<>("", avgHousingPricesByYear[0]));
        series1.getData().add(new XYChart.Data<>(" ", avgHousingPricesByYear[1]));
        series1.getData().add(new XYChart.Data<>("  ", avgHousingPricesByYear[2]));
        series1.getData().add(new XYChart.Data<>("   ", avgHousingPricesByYear[3]));
        series1.getData().add(new XYChart.Data<>("    ", avgHousingPricesByYear[4]));
        series1.getData().add(new XYChart.Data<>("     ", avgHousingPricesByYear[5]));
        series1.getData().add(new XYChart.Data<>("      ", avgHousingPricesByYear[6]));
        series1.getData().add(new XYChart.Data<>("       ", avgHousingPricesByYear[7]));

        XYChart.Series series2 = new XYChart.Series<>();

        series2.getData().add(new XYChart.Data<>("", avgCommercialPricesByYear[0]));
        series2.getData().add(new XYChart.Data<>(" ", avgCommercialPricesByYear[1]));
        series2.getData().add(new XYChart.Data<>("  ", avgCommercialPricesByYear[2]));
        series2.getData().add(new XYChart.Data<>("   ", avgCommercialPricesByYear[3]));
        series2.getData().add(new XYChart.Data<>("    ", avgCommercialPricesByYear[4]));
        series2.getData().add(new XYChart.Data<>("     ", avgCommercialPricesByYear[5]));
        series2.getData().add(new XYChart.Data<>("      ", avgCommercialPricesByYear[6]));
        series2.getData().add(new XYChart.Data<>("       ", avgCommercialPricesByYear[7]));

        bc.getData().addAll(series1, series2);

        for(Node n: bc.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: red;");
        }

        for(Node n: bc.lookupAll(".default-color1.chart-bar")) {
            n.setStyle("-fx-bar-fill: blue;");
        }

        PieChart pc = new PieChart();

        PieChart.Data slice1 = new PieChart.Data(ageGroups[0], purchasesByAgeGroup[0]);
        PieChart.Data slice2 = new PieChart.Data(ageGroups[1], purchasesByAgeGroup[1]);
        PieChart.Data slice3 = new PieChart.Data(ageGroups[2], purchasesByAgeGroup[2]);
        PieChart.Data slice4 = new PieChart.Data(ageGroups[3], purchasesByAgeGroup[3]);
        PieChart.Data slice5 = new PieChart.Data(ageGroups[4], purchasesByAgeGroup[4]);
        PieChart.Data slice6 = new PieChart.Data(ageGroups[5], purchasesByAgeGroup[5]);

        pc.getData().addAll(slice1, slice2, slice3, slice4, slice5, slice6);

        HBox hb = new HBox(bc, pc);

        Scene scene = new Scene(hb);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
