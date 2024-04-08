package edu.num;

import java.io.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        String fileName = new String("data/daneWej");
        String fileName2 = new String("data/kod");
        String fileName3 = new String("data/daneWyj");

        System.out.println("Wybierz opcje: ");
        System.out.println("1. kodowanie");
        System.out.println("2. dekodowanie");
        Scanner scanner = new Scanner(System.in);


        if (scanner.nextInt() == 1) {
            System.out.println("Podaj sciezke do pliku z danymi z roszerzeniem: ");
            fileName = scanner.next();
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