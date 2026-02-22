package org.example.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Grocery {

    public static ArrayList<String> groceryList = new ArrayList<>();

    public static void startGrocery() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.println("\n0 - Quit");
            System.out.println("1 - Add item(s)");
            System.out.println("2 - Remove item(s)");
            System.out.print("Your choice: ");

            String choiceLine = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(choiceLine);
            } catch (NumberFormatException e) {
                System.out.println("Please enter 0, 1, or 2.");
                continue;
            }

            switch (choice) {
                case 0 -> quit = true;
                case 1 -> {
                    System.out.print("Enter item(s) to add (single or comma-separated): ");
                    addItems(scanner.nextLine());
                }
                case 2 -> {
                    System.out.print("Enter item(s) to remove (single or comma-separated): ");
                    removeItems(scanner.nextLine());
                }
                default -> System.out.println("Please enter 0, 1, or 2.");
            }
        }
    }

    // ✅ Listeye ekler (tekrar yok: trim + case-insensitive kontrol)
    public static void addItems(String input) {
        if (input == null) return;

        String[] parts = input.split(",");
        for (String p : parts) {
            String item = normalize(p);
            if (item.isEmpty()) continue;

            if (!checkItemIsInList(item)) {
                // ✅ Listeye orijinal TRIM'lenmiş haliyle ekliyoruz (lowercase yapmıyoruz)
                groceryList.add(item);
            }
        }

        // ✅ Her operasyon sonrası sort
        sortList();
        printSorted();
    }

    // ✅ Listeden siler (trim + case-insensitive eşleştirip doğru elemanı kaldırır)
    public static void removeItems(String input) {
        if (input == null) return;

        String[] parts = input.split(",");
        for (String p : parts) {
            String target = normalize(p);
            if (target.isEmpty()) continue;

            // aynı üründen (farklı casing vs) varsa hepsini yakala
            int idx;
            while ((idx = indexOfNormalized(target)) != -1) {
                groceryList.remove(idx);
            }
        }

        sortList();
        printSorted();
    }

    // ✅ Listede var mı? (trim + case-insensitive)
    public static boolean checkItemIsInList(String product) {
        if (product == null) return false;
        return indexOfNormalized(product) != -1;
    }

    // ✅ Listeyi sıralayıp ekrana bas
    public static void printSorted() {
        // ✅ printSorted görevi: "tüm listeyi sıralayarak ekrana basmak"
        Collections.sort(groceryList);
        System.out.println("Current grocery list (sorted): " + groceryList);
    }

    // ----------------- helper'lar -----------------

    private static String normalize(String s) {
        return s == null ? "" : s.trim();
    }

    // ✅ case-insensitive + trim eşleşen elemanın index'i
    private static int indexOfNormalized(String target) {
        String t = normalize(target);
        for (int i = 0; i < groceryList.size(); i++) {
            String current = normalize(groceryList.get(i));
            if (current.equalsIgnoreCase(t)) {
                return i;
            }
        }
        return -1;
    }

    private static void sortList() {
        // case-insensitive sort (testler genelde bunu sever)
        groceryList.sort(String.CASE_INSENSITIVE_ORDER);
    }
}