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

    static int routingtable[][];
    static int tabiniziale[][];
    static int tabordinamento[][];

    static int nodi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("numero di nodi ");
        nodi = Integer.parseInt(br.readLine());

        //grafico con inizializzazione
        routingtable = new int[nodi][nodi];
        tabiniziale = new int[nodi][nodi];
        tabordinamento = new int[nodi][nodi];

        //inserimento valori in tabella iniziale
        for (int i = 0; i < nodi; i++) {
            for (int j = 0; j < nodi; j++) {
                if (i == j) {
                    tabiniziale[i][j] = 0;
                } else {
                    tabiniziale[i][j] = 9999;
                }
            }
        }

        //chiedo i dati per ogni nodo
        for (int i = 0; i < nodi; i++) {
            System.out.println("nodo i " + (i + 1) + ":");
            //nodo di arrivo
            System.out.print("arrivo ");
            int a = Integer.parseInt(br.readLine()) - 1;

            //costo
            System.out.print("costo del collegamento ");
            int cn = Integer.parseInt(br.readLine());
            tabiniziale[i][a] = cn;
            tabiniziale[a][i] = cn;
        }

        //aggiungo i val alle altre tabele 
        for (int i = 0; i < nodi; i++) {
            for (int j = 0; j < nodi; j++) {
                if (i == j) {
                    routingtable[i][j] = 0;//costo a loro 0
                    tabordinamento[i][j] = i;//collegamenti
                } else {
                    routingtable[i][j] = 9999;//metto i cosi a "infinito"
                    tabordinamento[i][j] = 1000;//metto un cikkegamento impossibile
                }
            }
        }

        System.out.println(" - ");
        Visualizzatabella();
    }

    //stampa la routing table dopo averla ordinata
    static void Visualizzatabella() {
        Ordina();
        Stampatabella();
        System.out.println();
    }
   
//inizio ordinamento
    static void Aggiornatabella(int n) {
        for (int i = 0; i < nodi; i++) {
            if (tabiniziale[n][i] != 9999) {//se il costo è normale 
                int dist = tabiniziale[n][i];//allora lo prendo
                for (int j = 0; j < nodi; j++) {
                    int inter_dist = routingtable[i][j];//prendo il costo della routing table
                    if (tabordinamento[i][j] == n) { // se invece il collegamento è con il nodo stesso 
                        inter_dist = 9999;// allora riemtto un costo "infinito" in modo che non esegua il prox if
                    }
                    if (dist + inter_dist < routingtable[n][j]) {//se cositi minori di quelli in uso 
                        routingtable[n][j] = dist + inter_dist; // settati
                        tabordinamento[n][j] = i;//creazione collegamento
                    }
                }
            }
        }
    }
//stampa la routing table
    static void Stampatabella() { 
        for ( int x = 1; x<nodi; x++)//stampata prima riga
        {
            System.out.println( x );
        }
        System.out.println();
        for (int i = 0; i < nodi; i++) {//costi che vengono stampati 
            System.out.println(i + 1 + "-");//stampa prima colonna con i nodi
            for (int j = 0; j < nodi; j++) {
                System.out.print(routingtable[i][j] + " - ");//stampo costo
            }
            System.out.println();
        }
    }
//ordina la routing table
    static void Ordina() {
        int x = 0;
        for (int i = 0; i < 4 * nodi; i++) {
            Aggiornatabella(x);
            x++;
            if (x == nodi) {
                x = 0;
            }
        }
    }

}
