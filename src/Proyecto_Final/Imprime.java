package Proyecto_Final;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class Imprime {

    public JFrame mostrar_img(Image img) {
        JLabel label = crear_label(img);
        JFrame frame = crear_frame(label);
        return frame;
    }

    public JFrame crear_frame(JLabel entrada) {
        String titulo = "Imagen";
        JFrame ventanaW = new JFrame(titulo);//ventanaM es la nueva instancia de un Jframe
        Dimension d = new Dimension();//objeto para obtener el tamaño de la pantalla        
        ventanaW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //finaliza el programa cuando se da click en la X
        ventanaW.setResizable(false);//para configurar si se redimensiona la ventana
        ventanaW.setLocation((int) ((d.getWidth() / 2) + 400), 100);//para ubicar inicialmente donde se muestra la ventana (x, y)
        ventanaW.setSize(entrada.getWidth() + 50, entrada.getHeight() + 50);//configurando tamaño de la ventana (ancho, alto)
        ventanaW.setVisible(true);//configurando visualización de la ventana        
        ventanaW.setLayout(null);
        ventanaW.setResizable(true);
        ventanaW.add(entrada);
        return ventanaW;
    }

    public JLabel crear_label(Image img) {
        JLabel salida = new JLabel(new ImageIcon(img));
        salida.setSize(img.getWidth(salida), img.getHeight(salida));
        return salida;
    }

    public Image sacar_img(Mat entrada) {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".png", entrada, matOfByte);
        byte[] byteArray = matOfByte.toArray();
        BufferedImage img_cargada = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            img_cargada = ImageIO.read(in);
        } catch (IOException e) {
        }
        return (Image) img_cargada;
    }

    public void print_Mat(Mat entrada) {
        int rows = entrada.rows();
        int cols = entrada.cols();
        int ch = entrada.channels();
        double[][][] salida = new double[rows][cols][];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                salida[i][j] = entrada.get(i, j);
            }
        }
        for (int i = 0; i < rows; i++) {
            System.out.print("{");
            for (int j = 0; j < cols; j++) {
                System.out.print("[");
                for (int k = 0; k < salida[i][j].length; k++) {
                    System.out.print("(");
                    System.out.print(salida[i][j][k]);
                    System.out.print(")");
                }
                System.out.print("]");
            }
            System.out.print("}");
            System.out.println();
        }
    }

    public void print_arre(int[] entrada) {
        for (int i = 0; i < entrada.length; i++) {
            System.out.println("indice " + i + " valor " + entrada[i]);
        }
    }

    public void print_2arre(double[][] entrada) {
        System.out.println("etiqueta\t\t puntos\t\t");
        for (int i = 0; i < entrada.length; i++) {
            System.out.print(entrada[i][0] + "\t");
            System.out.print(entrada[i][1] + "\t");
            System.out.println();
        }
    }

    public String barra(int tama) {
        String salida = "";
        for (int i = 0; i < tama; i = i + 100) {
            salida = salida.concat("*");
        }
        return salida;
    }

    public void grafica_consola(int[] entrada) {
        System.out.println("Posicion\t" + "Valor\t" + "Histograma\t");
        for (int i = 0; i < entrada.length; i++) {
            System.err.println(i + "\t" + entrada[i] + "\t" + barra(entrada[i]) + "\t");
        }
    }

    public void print_Matriz(double[][][] entrada) {
        for (int i = 0; i < entrada.length; i++) {
            System.out.print("{");
            for (int j = 0; j < entrada[0].length; j++) {
                System.out.print("[");
                for (int k = 0; k < 1; k++) {
                    System.out.print("(");
                    System.out.print(entrada[i][j][k]);
                    System.out.print(")");
                }
                System.out.print("]");
            }
            System.out.print("}");
            System.out.println();
        }
    }
}
