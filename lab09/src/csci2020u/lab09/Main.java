package csci2020u.lab09;

import java.io.*;
import java.util.*;
import java.net.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application{

    final private String stock1 = "https://query1.finance.yahoo.com/v7/finance/download/GOOG?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
    final private String fileDIR1 = "src/csci2020u/lab09/stock1.csv";
    final private String stock2 = "https://query1.finance.yahoo.com/v7/finance/download/AAPL?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
    final private String fileDIR2 = "src/csci2020u/lab09/stock2.csv";
    public LineChart<Number, Number> lc;
    public XYChart.Series series1;
    public XYChart.Series series2;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lab 09 Solution");

        List<Double> stockList1 = new ArrayList<>();
        List<Double> stockList2 = new ArrayList<>();

        downloadStockPrices(stock1, fileDIR1);
        downloadStockPrices(stock2, fileDIR2);

        readFile("src/csci2020u/lab09/stock1.csv", stockList1);
        readFile("src/csci2020u/lab09/stock2.csv", stockList2);

        drawLinePlot(stockList1, stockList2);

        primaryStage.setScene(plotLine());
        primaryStage.show();
    }

    public static void downloadStockPrices(String URL, String fileDIR) throws IOException {
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(URL).openStream());
            FileOutputStream fileOP = new FileOutputStream(fileDIR)) {
            byte data[] = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOP.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawLinePlot(List list1, List list2) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        lc = new LineChart<Number, Number>(xAxis, yAxis);
        lc.setLegendVisible(false);
        series1 = new XYChart.Series();
        series2 = new XYChart.Series();

        for(int i = 0; i < list1.size(); i++) {
            double value1 = Double.parseDouble((String) list1.get(i));
            double value2 = Double.parseDouble((String) list2.get(i));

            series1.getData().add(new XYChart.Data(i, value1));
            series2.getData().add(new XYChart.Data(i, value2));
        }
    }

    public Scene plotLine() {
        lc.getData().addAll(series1, series2);
        lc.setCreateSymbols(false);
        lc.getXAxis().setTickLabelsVisible(false);
        lc.getYAxis().setTickLabelsVisible(false);
        lc.getXAxis().setOpacity(0);
        lc.getYAxis().setOpacity(0);
        lc.setHorizontalGridLinesVisible(false);
        lc.setVerticalGridLinesVisible(false);

        ((Node) series1.nodeProperty().get()).setStyle("-fx-stroke: red;");
        ((Node) series2.nodeProperty().get()).setStyle("-fx-stroke: blue;");

        Scene scene = new Scene(lc);

        return scene;
    }

    public void readFile(String file, List list) throws IOException {

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            String line = null;

            while((line = br.readLine()) != null) {
                String[] stockPrice = line.split(",");
                list.add(stockPrice[4]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
