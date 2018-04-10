package Proyecto_Final;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import org.opencv.core.Mat;

public class Otsu {

    public int[] histograma(Mat entrada_gris) {
        int rows = entrada_gris.rows();
        int cols = entrada_gris.cols();
        int ch = entrada_gris.channels();
        int[] salida = new int[256];
        int cont = 0;
        for (int h = 0; h < 256; h++) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    double[] valor = entrada_gris.get(i, j);
                    if (h == (int) valor[0]) {
                        cont++;
                    }
                }
            }
            salida[h] = cont;
        }
        return salida;
    }

    public int[] imageHistogram(BufferedImage input) {
        int[] histogram = new int[256];
        for (int i = 0; i < histogram.length; i++) {
            histogram[i] = 0;
        }
        for (int i = 0; i < input.getWidth(); i++) {
            for (int j = 0; j < input.getHeight(); j++) {
                int red = new Color(input.getRGB(i, j)).getRed();
                histogram[red]++;
            }
        }
        return histogram;
    }

    public int umbral_Otsu(Mat entrada, int[] histogram) throws IOException {

        int total = entrada.rows() * entrada.cols();
        float sum = 0;
        for (int i = 0; i < 256; i++) {
            sum += i * histogram[i];
        }
        float sumB = 0;
        int wB = 0;
        int wF = 0;

        float varMax = 0;
        int threshold = 0;
        for (int i = 0; i < 256; i++) {
            wB += histogram[i];
            if (wB == 0) {
                continue;
            }
            wF = total - wB;
            if (wF == 0) {
                break;
            }
            sumB += (float) (i * histogram[i]);
            float mB = sumB / wB;
            float mF = (sum - sumB) / wF;
            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);
            if (varBetween > varMax) {
                varMax = varBetween;
                threshold = i;
            }
        }
        return threshold;
    }
}
