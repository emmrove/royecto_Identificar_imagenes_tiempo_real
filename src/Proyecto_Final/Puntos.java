package Proyecto_Final;

import java.util.Stack;

public class Puntos {

    public Stack solo_flechas(Stack marcas, Stack puntos) {
        Stack flechas = new Stack();
        for (int i = 0; i < marcas.size(); i++) {
            if ((double) puntos.get(i) == 5.0 || (double) puntos.get(i) == 6.0 || (double) puntos.get(i) == 7.0) {
                if (flechas.search(marcas.get(i)) == -1) {
                    flechas.push(marcas.get(i));
                }
            }
        }
        return flechas;
    }

    public Stack cuenta_marcas(double[][][] marcados) {
        Stack marca = new Stack();
        for (int i = 0; i < marcados.length; i++) {
            for (int j = 0; j < marcados[0].length; j++) {
                if (marcados[i][j][0] != 255.0) {
                    if (marca.search(marcados[i][j][0]) == -1) {
                        marca.push(marcados[i][j][0]);
                    }
                }
            }
        }
        return marca;
    }

    public int vecinos_completos(double[][][] marcados, int x, int y, double marca) {
        int cont = 0;
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (marcados[i][j][0] == marca) {
                    if (i != x || j != y) {
                        cont++;
                    }
                }
            }
        }
        return cont;
    }

    public Stack cuenta_puntos_n(double[][][] marcados, Stack num_marcas) {
        Stack puntos = new Stack();
        for (int i = 0; i < num_marcas.size(); i++) {
            puntos.push(0.0);
        }
        for (int k = 0; k < num_marcas.size(); k++) {
            for (int i = 1; i < marcados.length - 1; i++) {
                for (int j = 1; j < marcados[0].length - 1; j++) {
                    if (marcados[i][j][0] == 255) {
                        int cont = vecinos_completos(marcados, i, j, (double) num_marcas.get(k));
                        if (cont >= 5) {
                            double valor = (double) puntos.get(k) + 1;
                            puntos.setElementAt(valor, k);
                        }
                    }
                }
            }
        }
        return puntos;
    }
    public Stack cuenta_puntos_b(double[][][] marcados, Stack num_marcas) {
        Stack puntos = new Stack();
        for (int i = 0; i < num_marcas.size(); i++) {
            puntos.push(0.0);
        }
        for (int k = 0; k < num_marcas.size(); k++) {
            for (int i = 1; i < marcados.length - 1; i++) {
                for (int j = 1; j < marcados[0].length - 1; j++) {
                    if (marcados[i][j][0] == (double) num_marcas.get(k)) {
                        int cont = vecinos_completos(marcados, i, j, 255.0);
                        if (cont >= 5) {
                            double valor = (double) puntos.get(k) + 1;
                            puntos.setElementAt(valor, k);
                        }
                    }
                }
            }
        }
        return puntos;
    }
}
