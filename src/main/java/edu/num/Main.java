package edu.num;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String fileName = new String("data/plik.txt");
        String fileName2 = new String("data/plik2.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)); BufferedWriter bw = new BufferedWriter(new FileWriter(fileName2))) {

            Algorytm.kodowanie(br, bw);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}