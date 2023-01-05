/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package progetto;

import java.io.*;

/**
 *
 * @author Acer
 */
public class Progetto {

    static int strada[][];
    static int grafico[][];
    
    static int rt[][];
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("numero di nodi ");
        c = Integer.parseInt(br.readLine());

        grafico = new int[c][c];
        strada = new int[c][c];
        rt = new int[c][c];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                if (i == j) {
                    grafico[i][j] = 0;
                } else {
                    grafico[i][j] = 9999;
                }
            }
        }

        for (int i = 0; i < c; i++) {
            System.out.println("dati del nodo " + (i + 1) + ":");
            
            System.out.print("arrivo ");
            int a = Integer.parseInt(br.readLine()) - 1;
            
            System.out.print("costo del collegamento ");
            int cn = Integer.parseInt(br.readLine());
            grafico[i][a] = cn;
            grafico[a][i] = cn;
        }
        Inizio();

        System.out.println(" - ");
        Visualizzatabella();
    }

    static void Visualizzatabella() {
        Aggiorna();
        Stampatabella();
        System.out.println();
    }

    static void Aggiornatabella(int source) {
        for (int i = 0; i < c; i++) {
            if (grafico[source][i] != 9999) {
                int dist = grafico[source][i];
                for (int j = 0; j < c; j++) {
                    int inter_dist = rt[i][j];
                    if (strada[i][j] == source) {
                        inter_dist = 9999;
                    }
                    if (dist + inter_dist < rt[source][j]) {
                        rt[source][j] = dist + inter_dist;
                        strada[source][j] = i;
                    }
                }
            }
        }
    }

    static void Aggiorna() {
        int k = 0;
        for (int i = 0; i < 4 * c; i++) {
            Aggiornatabella(k);
            k++;
            if (k == c) {
                k = 0;
            }
        }
    }

    static void Inizio() {
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                if (i == j) {
                    rt[i][j] = 0;
                    strada[i][j] = i;
                } else {
                    rt[i][j] = 9999;
                    strada[i][j] = 100;
                }
            }
        }
    }

    static void Stampatabella() {
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print("distanza " + rt[i][j] + "    ");
            }
            System.out.println();
        }
    }
}

