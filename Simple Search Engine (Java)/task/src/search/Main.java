package search;

import search.impl.FindAllStrategy;
import search.impl.FindAnyStrategy;
import search.impl.FindNoneStrategy;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] data = enterData(args[1]);

        int choice;

        do {
            System.out.println("""
                    \n=== Menu ===
                    1. Find a person
                    2. Print all people
                    0. Exit""");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    findPerson(data, scanner);
                }

                case 2 -> {
                    printAllPeople(data);
                }

                case 0 -> {
                    exit();
                }

                default -> {
                    System.out.println("\nIncorrect option! Try again.");
                }
            }
        } while (choice != 0);

    }

    public static void findPerson(String[] words, Scanner scanner) {
        Finder finder = new Finder();

        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        String strategy = scanner.nextLine();

        System.out.println("\nEnter a name or email to search all suitable people.");
        String query = scanner.nextLine();

        switch (strategy) {
            case "ALL" -> {
                finder.setStrategy(new FindAllStrategy());
                finder.find(words, query);
            }
            case "ANY" -> {
                finder.setStrategy(new FindAnyStrategy());
                finder.find(words, query);
            }
            case "NONE" -> {
                finder.setStrategy(new FindNoneStrategy());
                finder.find(words, query);
            }
            default -> {
                System.out.println("\nIncorrect strategy! Try again.");
            }
        }
    }

    public static void printAllPeople(String[] words) {
        System.out.println("\n=== List of people ===");
        for (String word : words) {
            System.out.println(word);
        }
    }

    public static void exit() {
        System.out.println("\nBye!");
    }

    public static String[] enterData(String fileName) {
        System.out.println("File name: " + fileName);
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            List<String> words = new ArrayList<>();

            for (int i = 0; scanner.hasNextLine(); i++) {
                words.add(scanner.nextLine());
            }

            return words.toArray(String[]::new);
        } catch (Exception e) {
            System.out.println("Error reading file.");
            return null;
        }
    }

    public static Map<String, List<Integer>> hashData(String[] words) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String[] parts = words[i].split(" ");
            for (String part : parts) {
                List<Integer> indexes;

                if (map.containsKey(part)) {
                    indexes = map.get(part);
                } else {
                    indexes = new ArrayList<>();
                }

                indexes.add(i);
                map.put(part, indexes);
            }
        }
        return map;
    }
}
