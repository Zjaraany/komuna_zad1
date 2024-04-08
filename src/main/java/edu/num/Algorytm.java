package edu.num;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Algorytm {

    public static int[][] macierzH= {
        {1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
        {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        {1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
        {1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
        {0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
        {1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0},
        {1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1}};

    public static void kodowanie(BufferedReader plikDoZakodowania, BufferedWriter plikWyjsciowy) throws IOException {




        int val;

        while ((val = plikDoZakodowania.read()) != -1) {


            char znak = (char) val;
            byte[] bityZnaku = new byte[8];
            for (int i = 7; i >= 0; i--) {
                bityZnaku[i] = (byte) (znak & 1);
                znak /= 2;
            }

            byte[] bityParzystości = new byte[8];

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    bityParzystości[i] += macierzH[i][j] * bityZnaku[j];
                }
                bityParzystości[i] %= 2;
            }

            System.out.print(((char) val)+" ");
            for (int i = 0; i < 8; i++) {
                System.out.print(bityZnaku[i]);
                if (bityZnaku[i] == 0) {
                    plikWyjsciowy.write("0");
                } else {
                    plikWyjsciowy.write("1");
                }
            }
            System.out.print(" ");
            for (int i = 0; i < 8; i++) {
                System.out.print(bityParzystości[i]);
                if (bityParzystości[i] == 0) {
                    plikWyjsciowy.write("0");
                } else {
                    plikWyjsciowy.write("1");
                }
            }
            plikWyjsciowy.write('\n');
            System.out.println();
        }







    }
}
