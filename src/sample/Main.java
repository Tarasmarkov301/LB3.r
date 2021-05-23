package sample;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main extends Application
{
    static int Count;
    static int[] arr1Int;
    static int[] arr2Int;
    static String line;
    public static final int SERVER_PORT = 50001;
    @Override
    public void start(Stage stage) {

        stage.setTitle("Графики");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Графики");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Графики масивов");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("По убыванию");
        series.setName("По возрастанию");
        //populating the series with data
        for(int i=0;i<Count;i++) {
            series.getData().add(new XYChart.Data(i + 1, arr1Int[i]));
        }
        for(int i=0;i<Count;i++) {
            series1.getData().add(new XYChart.Data(i + 1,arr2Int[i]));
        }
        lineChart.setCreateSymbols(false);
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        lineChart.getData().add(series1);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[])
    {


        String st = "Надо получить данние";
        JOptionPane.showMessageDialog(null, st);
        try {
            ServerSocket server = new ServerSocket(SERVER_PORT);
            Socket clientConn = server.accept();
            BufferedReader fromServer;
            fromServer = new BufferedReader(new InputStreamReader(clientConn.getInputStream()));
            line = fromServer.readLine();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println(line);
        String[] k=line.split("h");
        System.out.println(k[0]);
        System.out.println(k[1]);
        String[] buff = k[1].split("&");
        System.out.println(buff[1]);
        String[] arr1 = buff[0].split(":");
        String[] arr2 = buff[1].split(":");
        arr1Int = new int[Integer.parseInt(k[0])];
        arr2Int = new int[Integer.parseInt(k[0])];
        Count = Integer.parseInt(k[0]);
        int counter =0;
        for(int i =0;i< arr1.length;i++)
        {
            String[] j = arr1[i].split(";");
            for(int h = 0;h<Integer.parseInt(j[1]);h++)
            {
                arr1Int[counter] = Integer.parseInt(j[0]);
                counter++;
            }
        }
        counter = 0;
        for(int i =0;i< arr2.length;i++)
        {
            String[] j = arr2[i].split(";");
            for(int h = 0;h<Integer.parseInt(j[1]);h++)
            {
                arr2Int[counter] = Integer.parseInt(j[0]);
                counter++;
            }
        }
        launch(args);

    }

}
