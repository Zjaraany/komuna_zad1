package edu.num;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Algorytm {

    public static int[][] macierzH= {
            {1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0},
            {1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}};

//        {1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
//        {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0},
//        {0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//        {1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
//        {1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
//        {0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
//        {1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0},
//        {1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1}};

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

    public static void odkodowanie(BufferedReader plikDoOdkodowania, BufferedWriter plikWyjsciowy) throws IOException {
        String linia;

        while ((linia = plikDoOdkodowania.readLine()) != null) {
            String bityZnakuStr = linia.substring(0, 8);
            String bityParzystościStr = linia.substring(8, 16);
            String bityPrzekazuStr = linia.substring(0, 16);

            byte[] bityZnaku = new byte[8];
            byte[] bityParzystości = new byte[8];
            byte[] bityPrzekazu = new byte[16];

            // Parsowanie ciągu bitów do tablicy bajtów
            for (int i = 0; i < 8; i++) {
                bityZnaku[i] = (byte) (bityZnakuStr.charAt(i) - '0');
                bityParzystości[i] = (byte) (bityParzystościStr.charAt(i) - '0');
            }

            for (int i = 0; i < 16; i++) {
                bityPrzekazu[i] = (byte) (bityPrzekazuStr.charAt(i) - '0');
            }

            byte[] macierzIloczynow = new byte[8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 16; j++) {
                    macierzIloczynow[i] += macierzH[i][j] * bityPrzekazu[j];
                }
                macierzIloczynow[i] %= 2;
            }
            boolean czyWykrytoBlad = false;
            int pozBledu = -1;
            for (int k = 0; k < 8; k++) {
                if (macierzIloczynow[k] != 0) {
                    czyWykrytoBlad = true;
                }
            }

            if (czyWykrytoBlad) {
                for (int l = 0; l < 8; l++) System.out.print(macierzIloczynow[l]);
                for (int i = 0; i < 16; i++) {
                    int corrCounter = 0;
                    for (int j = 0; j < 8; j++) {

                        if (macierzIloczynow[j] == macierzH[j][i]) {
                            corrCounter++;
                        }
                        if (corrCounter == 8) {
                            pozBledu = i;
                        }
                    }
                }

                System.out.println(pozBledu);
                if (pozBledu != -1) {
                    bityPrzekazu[pozBledu] ^= 1;
                } else {
                    int[] pomKol = new int[8];
                    for (int i = 0; i < 15; i++) {
                        for (int j = i + 1; j < 16; j++) {
                            for (int wiersz = 0; wiersz < 8; wiersz++) {
                                pomKol[wiersz] = (macierzH[wiersz][i] + macierzH[wiersz][j]) % 2; //stworzenie pomocniczej kolumny przy pomocy 2 innych
                            }
                            int counter = 0;
                            for (int wiersz = 0; wiersz < 8; wiersz++) {
                                if (pomKol[wiersz] == macierzIloczynow[wiersz]) {
                                    counter++;
                                }
                            }

                            if (counter == 8) {
                                System.out.println("Indeks i: " + i + " Indeks j: " + j);
                                bityPrzekazu[i] ^= 1;
                                bityPrzekazu[j] ^= 1;
                            }
                        }
                    }
                }

            }

            char znak = 0;

            for (int i = 7; i >= 0; i--) {
                if (bityPrzekazu[7 - i] == 1) {
                    znak = (char) (znak | (1 << i));
                }
            }
            plikWyjsciowy.write(znak);

        }
    }

}