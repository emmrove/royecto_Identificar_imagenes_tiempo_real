package Proyecto_Final;

import java.awt.Image;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class Tomar {
    public Mat takecam() throws IOException {
        Mat imageMat = new Mat(); // matriz de imagem //

        //tomar captura y sacar una imagen y pasarlo a Mat -----------------------
        VideoCapture take = new VideoCapture();
        take.open(0); // abre dispositivo de video de índice 0 //
        take.read(imageMat); //Captura imagen
        return imageMat;
    }
    
    public void opencam() throws IOException {
        Imagen img = new Imagen();
        Imprime muestra = new Imprime();

        Mat imageMat = new Mat(); // matriz de imagem //

        //tomar captura y sacar una imagen y pasarlo a Mat -----------------------
        VideoCapture take = new VideoCapture();
        take.open(0); // abre dispositivo de video de índice 0 //
        take.read(imageMat); //Captura imagen
        
        Image mostrar = muestra.sacar_img(imageMat);
        
        JLabel label = muestra.crear_label(mostrar);
        JFrame ventana = muestra.crear_frame(label);

        if (take.isOpened()) {
            System.out.println("Activando Camara ...");
        } else {
            System.out.println("La Camara ya esta Activada");
        }
        while (take.read(imageMat)) {
            take.read(imageMat);
            if (imageMat.empty()) {
                System.out.println("No hay imagen");
                break;
            } else {
                Image salida = muestra.sacar_img(imageMat);
                Icon icon = new ImageIcon(salida);
                label.setIcon(icon);               
                try {
                    Thread.sleep(0); // 1000 es tiempo en milisegundos.
                } catch (InterruptedException e) {
                }
            }
        }
        take.release();
    }
}

