package com.pluralsight.UserInterface;

import java.util.List;
import java.util.Scanner;

public class ConsoleHelper {

    private static Scanner sc = new Scanner(System.in);

    public static String promptForString(String prompt) {
        System.out.print(prompt + ": ");
        return sc.nextLine();
    }

    public static int promptForInt(String prompt) {
        System.out.print(prompt + ": ");
        int result = sc.nextInt();
        sc.nextLine();
        return result;
    }

    public static char promptForChar(String prompt) {
        System.out.print(prompt + ": ");
        char result = sc.next().toUpperCase().charAt(0);
        sc.nextLine();
        return result;
    }

    public static double promptForDouble(String prompt) {
        System.out.print(prompt + ": ");
        double result = sc.nextDouble();
        sc.nextLine();
        return result;
    }

    public static boolean promptForYesNo(String prompt){

        System.out.print(prompt + " (Y/N): ");
        return sc.nextLine().toLowerCase().contains("y");
    }

    public static void displayList(List<?> values) {
        int totalNumber = values.size();
        int numberPerRow = 5;
        int indexOfNext = 0;

        while (indexOfNext < totalNumber) {
            for (int i = 1; i <= numberPerRow; i++) {
                if (indexOfNext >= totalNumber) {
                    System.out.println();
                    return;
                }
                System.out.printf("%15s", values.get(indexOfNext++).toString());
            }
            System.out.println();
        }
    }
}
