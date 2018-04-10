package Proyecto_Final;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class Imagen {

    public Mat cargar_img() {
        Mat origen = Imgcodecs.imread("/home/themisamus/NetBeansProjects/Proyecto_Final/testv1.png");
        return origen;
    }

    public Mat buffer_a_mat(BufferedImage entrada) {
        byte[] data = ((DataBufferByte) entrada.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(entrada.getHeight(), entrada.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, data);
        return mat;
    }

    public Mat a_gris(Mat entrada) {
        Mat gris = new Mat(entrada.height(), entrada.width(), entrada.type());
        int rows = entrada.rows();
        int cols = entrada.cols();
        int ch = entrada.channels();
        double[][][] salida = new double[rows][cols][];
        double valor = 0.0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                salida[i][j] = entrada.get(i, j);
                for (int k = 0; k < ch; k++) {
                    valor = valor + salida[i][j][k];
                }
                valor = valor / 3;
                double[] inserta = {valor, valor, valor};
                gris.put(i, j, inserta);
            }
        }
        return gris;
    }

    public Mat a_binario(Mat entrada, double umbral) {
        Mat binario = new Mat(entrada.height(), entrada.width(), entrada.type());
        int rows = entrada.rows();
        int cols = entrada.cols();
        int ch = entrada.channels();
        double[] salida = new double[3];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double[] valor = entrada.get(i, j);
                for (int k = 0; k < ch; k++) {
                    if (valor[0] <= umbral) {
                        salida[k] = 0.0;
                    } else {
                        salida[k] = 255.0;
                    }
                }
                binario.put(i, j, salida);
            }
        }
        return binario;
    }

    public BufferedImage a_buffer(Mat imagen) {
        byte[] data = new byte[imagen.rows() * imagen.cols() * (int) (imagen.elemSize())];
        imagen.get(0, 0, data);
        if (imagen.channels() == 3) {
            for (int i = 0; i < data.length; i += 3) {
                byte temp = data[i];
                data[i] = data[i + 2];
                data[i + 2] = temp;
            }
        }
        BufferedImage image = new BufferedImage(imagen.cols(), imagen.rows(), BufferedImage.TYPE_3BYTE_BGR);
        image.getRaster().setDataElements(0, 0, imagen.cols(), imagen.rows(), data);
        return image;
    }
}
