package Proyecto_Final;

import java.util.Stack;

public class Calcula {

    public Stack funcion(double[][] entrada) {

        double area_origen = 3944;
        double perimetro_origen = 104;
        double funcion_origen = area_origen/perimetro_origen;
        Stack salida = new Stack();
        double parametro = 0;
        for (int i = 0; i < entrada.length; i++) {
            double area_entrada = entrada[i][1];
            double perimetro_entrada = entrada[i][2];
            double funcion_entrada = area_entrada / perimetro_entrada;
            if (area_origen >= area_entrada && perimetro_origen >= perimetro_entrada) {
                double area_funcion = area_origen / area_entrada;
                double peri_funcion = perimetro_origen / perimetro_entrada;
                parametro = area_funcion / peri_funcion;
                if (parametro * funcion_entrada >= funcion_origen - 0.1 && parametro * funcion_entrada <= funcion_origen + 0.1) {
                    if (salida.search(entrada[i][0]) == -1) {
                        salida.push(entrada[i][0]);
                    }
                }
            } else {
                if (area_origen <= area_entrada && perimetro_origen <= perimetro_entrada) {
                    double area_funcion = area_entrada / area_origen;
                    double peri_funcion = perimetro_entrada / perimetro_origen;
                    parametro = area_funcion / peri_funcion;
                    if (parametro * funcion_origen >= funcion_entrada - 0.001 && parametro * funcion_origen <= funcion_entrada + 0.001) {
                        if (salida.search(entrada[i][0]) == -1) {
                            salida.push(entrada[i][0]);
                        }
                    }
                }
            }
        }
        return salida;
    }

    public double[][] eti_are_peri(double[][][] entrada) {
        Stack area = new Stack();
        Stack perimetro = new Stack();
        Stack etiqueta = new Stack();

        for (int i = 0; i < entrada.length; i++) {
            for (int j = 0; j < entrada[0].length; j++) {
                if (entrada[i][j][0] != 255.0) {
                    if (etiqueta.search(entrada[i][j][0]) == -1) {
                        etiqueta.push(entrada[i][j][0]);
                        area.push(0.0);
                        perimetro.push(0.0);
                    }
                }
            }
        }
        for (int i = 0; i < etiqueta.size(); i++) {
            for (int j = 1; j < entrada.length - 1; j++) {
                for (int k = 0; k < entrada[0].length; k++) {
                    if (entrada[j][k][0] == 255) {
                        if (entrada[j - 1][k][0] == (double) etiqueta.get(i)) {
                            double are = 1;
                            double peri = 1;
                            for (int l = j; l < entrada.length - 1; l++) {
                                if (entrada[l][k][0] == 255) {
                                    are++;
                                    if (entrada[l + 1][k][0] == (double) etiqueta.get(i)) {
                                        peri = (double) perimetro.get(i) + peri;
                                        are = (double) area.get(i) + are;
                                        perimetro.setElementAt(peri, i);
                                        area.setElementAt(are, i);
                                    }
                                } else {
                                    if (entrada[l][k][0] == (double) etiqueta.get(i)) {
                                        peri++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        double[][] salida = new double[etiqueta.size()][3];
        int i = 0;
        while(!etiqueta.empty()) {
            salida[i][0] = (double) etiqueta.pop();
            salida[i][1] = (double) area.pop();
            salida[i][2] = (double) perimetro.pop();
            i++;
        }
        return salida;
    }
}
