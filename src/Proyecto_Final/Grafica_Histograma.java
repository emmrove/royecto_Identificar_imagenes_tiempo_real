package Proyecto_Final;

import javafx.scene.layout.Pane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class Grafica_Histograma extends JFrame{
    JPanel panel;
    public void grafica(int[] datos, int umbral){
        setTitle("GRAFICA");
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        crea(datos, umbral);
        setVisible(true);
    }

    public void crea(int[] datos, int umbral) {
        panel = new JPanel();
        getContentPane().add(panel);
        
        // Fuente de Datos
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        for(int i = 0; i < 256; i++){
            line_chart_dataset.addValue(datos[i], "repeticiones", Integer.toString(i) + " ");
        }
        
        // Creando el Grafico
        JFreeChart chart=ChartFactory.createLineChart3D("Histograma",
                "Valor del Umbral(Otsu) --> " + umbral, "Repeticiones",line_chart_dataset,PlotOrientation.VERTICAL,
                true,true,false);  
        
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
        
    }
    public JPanel creados(int[] datos, int umbral) {
        panel = new JPanel();
        getContentPane().add(panel);
        
        // Fuente de Datos
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        for(int i = 0; i < 256; i++){
            line_chart_dataset.addValue(datos[i], "repeticiones", Integer.toString(i) + " ");
        }
        
        // Creando el Grafico
        JFreeChart chart=ChartFactory.createLineChart3D("Histograma",
                "Valor del Umbral(Otsu) --> " + umbral, "Repeticiones",line_chart_dataset,PlotOrientation.VERTICAL,
                true,true,false);  
        
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
        return panel;
    }
}