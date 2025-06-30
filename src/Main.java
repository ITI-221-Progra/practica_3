import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();

        // Problema 1
        int num = rand.nextInt(16) + 15;
        JOptionPane.showMessageDialog(null, "Problema 1\nNumero aleatorio generado: " + num);

        int[] vector1 = new int[3];
        int[] vector2 = new int[3];

        for (int i = 0; i < 3; i++) {
            vector1[i] = Integer.parseInt(JOptionPane.showInputDialog("Vector 1 - Valor " + (i + 1)));
        }

        for (int i = 0; i < 3; i++) {
            vector2[i] = Integer.parseInt(JOptionPane.showInputDialog("Vector 2 - Valor " + (i + 1)));
        }

        int suma1 = Arrays.stream(vector1).sum();
        int suma2 = Arrays.stream(vector2).sum();

        Set<Integer> set = new HashSet<>();
        for (int v : vector1) set.add(v);
        for (int v : vector2) set.add(v);

        StringBuilder resultado1 = new StringBuilder("Suma vector 1: " + suma1 + "\nSuma vector 2: " + suma2);
        if (suma1 != num || suma2 != num) resultado1.append("\nError: alguna suma no coincide con " + num);
        if (set.size() < 6) resultado1.append("\nError: hay numeros repetidos entre vectores.");

        JOptionPane.showMessageDialog(null, resultado1.toString());

        // Problema 2
        Integer[] A = {1, 2, 3, 4, 5};
        Integer[] B = {4, 5, 6, 7};
        Set<Integer> conjuntoA = new HashSet<>(Arrays.asList(A));
        Set<Integer> conjuntoB = new HashSet<>(Arrays.asList(B));
        Set<Integer> union = new HashSet<>(conjuntoA);
        union.addAll(conjuntoB);
        Set<Integer> interseccion = new HashSet<>(conjuntoA);
        interseccion.retainAll(conjuntoB);
        JOptionPane.showMessageDialog(null, "Problema 2\nUnion: " + union + "\nInterseccion: " + interseccion);

        // Problema 3
        int[][] matriz1 = {
                {10, 6},
                {3, 5}
        };
        int[][] matriz2 = {
                {9, 2, 10, 6},
                {16, 10, 6, 3},
                {3, 3, 5, 5},
                {0, 2, 1, 7}
        };
        boolean contenido = false;
        outer:
        for (int i = 0; i <= matriz2.length - matriz1.length; i++) {
            for (int j = 0; j <= matriz2[0].length - matriz1[0].length; j++) {
                boolean match = true;
                for (int k = 0; k < matriz1.length; k++) {
                    for (int l = 0; l < matriz1[0].length; l++) {
                        if (matriz1[k][l] != matriz2[i + k][j + l]) {
                            match = false;
                        }
                    }
                }
                if (match) {
                    contenido = true;
                    break outer;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Problema 3\nMatriz 1 esta contenida en Matriz 2? " + (contenido ? "Si" : "No"));

        // Problema 4
        int[][] A4 = {
                {1, 2},
                {3, 4}
        };
        int[][] B4 = {
                {2, 0},
                {1, 2}
        };
        int[][] suma = new int[2][2];
        int[][] producto = new int[2][2];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                suma[i][j] = A4[i][j] + B4[i][j];
                producto[i][j] = A4[i][0] * B4[0][j] + A4[i][1] * B4[1][j];
            }

        StringBuilder mat4 = new StringBuilder("Problema 4\nMatriz Suma:\n");
        for (int[] fila : suma) {
            for (int val : fila) mat4.append(val).append("\t");
            mat4.append("\n");
        }
        mat4.append("Matriz Producto:\n");
        for (int[] fila : producto) {
            for (int val : fila) mat4.append(val).append("\t");
            mat4.append("\n");
        }
        JOptionPane.showMessageDialog(null, mat4.toString());

        // Problema 5
        String[] paises = {"Panama", "Costa Rica", "Nicaragua"};
        int n = paises.length;
        int años = Integer.parseInt(JOptionPane.showInputDialog("Cuantos anos desea proyectar?"));
        double[][] matriz = new double[n][años + 3];

        for (int i = 0; i < n; i++) {
            double a2003 = Double.parseDouble(JOptionPane.showInputDialog("Pais: " + paises[i] + "\nIngrese valor para 2003:"));
            double a2004 = Double.parseDouble(JOptionPane.showInputDialog("Pais: " + paises[i] + "\nIngrese valor para 2004:"));
            matriz[i][0] = a2003;
            matriz[i][1] = a2004;
            matriz[i][2] = ((a2004 - a2003) / a2003) * 100;
            for (int j = 3; j < años + 3; j++) {
                matriz[i][j] = matriz[i][j - 1] * (1 + matriz[i][2] / 100);
            }
        }

        StringBuilder tabla = new StringBuilder("Problema 5\nPais\t2003\t2004\t%\t");
        for (int a = 1; a <= años; a++) {
            tabla.append((2004 + a)).append("\t");
        }
        tabla.append("\n");

        for (int i = 0; i < n; i++) {
            tabla.append(paises[i]).append("\t");
            for (int j = 0; j < años + 3; j++) {
                tabla.append(String.format("%.2f", matriz[i][j])).append("\t");
            }
            tabla.append("\n");
        }

        JTextArea area = new JTextArea(tabla.toString());
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new java.awt.Dimension(700, 200));
        JOptionPane.showMessageDialog(null, scroll, "Resultado Proyeccion", JOptionPane.INFORMATION_MESSAGE);
    }
}