package edu.num;

import java.io.*;
import java.util.Scanner;

public class Main {

//    public static void main(String[] args) {
//
//        String fileName = "data/plik";
//        String encodedFileName = "data/plik_encoded";
//        String decodedFileName = "data/plik_decoded";
//
//        try (BufferedReader br = new BufferedReader(new FileReader(fileName));
//             BufferedWriter encodedBw = new BufferedWriter(new FileWriter(encodedFileName));
//             BufferedWriter decodedBw = new BufferedWriter(new FileWriter(decodedFileName))) {
//
//            // Kodowanie
//            Algorytm.kodowanie(br, encodedBw);
//
//            // Zamykamy BufferedWriter dla kodowanego pliku
//            encodedBw.close();
//
//            // Otwieramy BufferedReader dla zakodowanego pliku
//            try (BufferedReader encodedBr = new BufferedReader(new FileReader(encodedFileName))) {
//                // Dekodowanie
//                Algorytm.odkodowanie(encodedBr, decodedBw);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


    public static void main(String[] args) {

        String fileName = new String("data/daneWej");
        String fileName2 = new String("data/kod");
        String fileName3 = new String("data/daneWyj");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextInt() == 1) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName)); BufferedWriter bw = new BufferedWriter(new FileWriter(fileName2))) {

                Algorytm.kodowanie(br, bw);


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName2)); BufferedWriter bw = new BufferedWriter(new FileWriter(fileName3))) {

                Algorytm.odkodowanie(br, bw);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}