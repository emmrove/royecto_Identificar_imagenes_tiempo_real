package Proyecto_Final;

import java.util.Stack;
import org.opencv.core.Mat;

public class Etiqueta {

    public Mat contorno(Mat entrada, double[][][] input, double marca) {
        for (int i = 0; i < entrada.rows(); i++) {
            for (int j = 0; j < entrada.cols(); j++) {
                if (input[i][j][0] == marca) {
                    double[] data = {0.0, 0.0, 0.0};
                    entrada.put(i, j, data);
                } else {
                    double[] data = {255, 255, 255};
                    entrada.put(i, j, data);
                }
            }
        }
        return entrada;
    }

    public Mat contorno_pila(Mat entrada, double[][][] input, Stack marca) {
        for (int i = 0; i < entrada.rows(); i++) {
            for (int j = 0; j < entrada.cols(); j++) {
                if (marca.search(input[i][j][0]) != -1) {
                    double[] data = {0.0, 0.0, 0.0};
                    entrada.put(i, j, data);
                } else {
                    double[] data = {255, 255, 255};
                    entrada.put(i, j, data);
                }
            }
        }
        return entrada;
    }

    public Mat contorno_real(Mat original, Mat entrada, double[][][] input, Stack marca) {
        for (int i = 0; i < entrada.rows(); i++) {
            for (int j = 0; j < entrada.cols(); j++) {
                if (marca.search(input[i][j][0]) != -1) {
                    double[] data = {254.0, 0.0, 0.0};
                    original.put(i, j, data);
                }
            }
        }
        return original;
    }

    public double[][][] marca(double[][][] entrada) {
        double marcador = 10.0;
        Stack aux = new Stack();
        for (int i = 0; i < entrada.length; i++) {
            for (int j = 0; j < entrada[0].length; j++) {
                if (entrada[i][j][0] != 255.0) {
                    if (comprueba(vecinos(entrada, i, j)).empty()) {
                        entrada[i][j][0] = marcador;
                        marcador++;
                    } else {
                        entrada[i][j][0] = (double) comprueba(vecinos(entrada, i, j)).get(0);
                        if (comprueba(vecinos(entrada, i, j)).size() != 1) {
                            aux = comprueba(vecinos(entrada, i, j));
                            entrada = sustituye(entrada, aux, i, j);
                        }
                    }
                }
            }
        }
        return entrada;
    }

    public double[][][] sustituye(double[][][] entrada, Stack pila, int x, int y) {
        for (int i = 0; i < x + 1; i++) {
            for (int j = 0; j < y + 1; j++) {
                if (entrada[i][j][0] == (double) pila.get(0)) {
                    entrada[i][j][0] = (double) pila.get(1);
                }
            }
        }
        return entrada;
    }

    public Stack comprueba(Stack entrada) {
        Stack salida = new Stack();
        for (int i = 0; i < entrada.size(); i++) {
            if (salida.search(entrada.get(i)) == -1) {
                salida.push(entrada.get(i));
            }
        }
        return salida;
    }

    public Stack vecinos(double[][][] entrada, int x, int y) {
        Stack salida = new Stack();
        for (int i = x - 1; i < x + 1; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (i >= 0 && j >= 0 && i <= entrada.length - 1 && j <= entrada[0].length - 1) {
                    if (i != x || j != y) {
                        if (entrada[i][j][0] != 0.0 && entrada[i][j][0] != 255.0) {
                            salida.push(entrada[i][j][0]);
                        }
                    }
                }
            }
        }
        return salida;
    }

    public Mat matriz_a_mat(double[][][] entrada) {
        Mat salida = new Mat(entrada.length, entrada[0].length, 0);
        for (int i = 0; i < entrada.length; i++) {
            for (int j = 0; j < entrada[0].length; j++) {
                salida.put(i, j, entrada[i][j]);
            }
        }
        return salida;
    }

    public double[][][] mat_a_matriz(Mat entrada) {
        int rows = entrada.rows();
        int cols = entrada.cols();
        double[][][] salida = new double[rows][cols][];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double[] valor = entrada.get(i, j);
                salida[i][j] = valor;
            }
        }
        return salida;
    }

    public Mat creaMat(int tama, int tipo) {
        Mat salida;
        if (tipo == 0) {
            salida = new Mat(tama, tama, 0);
            double num = 0.0;
            for (int i = 0; i < tama; i++) {
                for (int j = 0; j < tama; j++) {
                    double[] data = {num};
                    salida.put(i, j, data);
                    num++;
                }
            }
        } else {
            salida = new Mat(tama, tama, 0);
            //creo el double
            double[][][] aux = {
                {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}},
                {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}},
                {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}},
                {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}},
                {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}},
                {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}},
                {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}},
                {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}},
                {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}},
                {{0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}, {0.0}}
            };
            for (int i = 0; i < tama; i++) {
                for (int j = 0; j < tama; j++) {
                    salida.put(i, j, aux[i][j]);
                }
            }
        }
        return salida;
    }
}
