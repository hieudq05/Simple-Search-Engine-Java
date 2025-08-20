package search.impl;

import search.FindingStrategy;
import search.Main;

import java.util.*;

public class FindNoneStrategy implements FindingStrategy {
    @Override
    public void printResult(String[] data, String query) {
        Map<String, List<Integer>> map = Main.hashData(data);

        String[] queries = query.split(" ");

        Set<Integer> indexesUnique = new HashSet<>();
        for (int i = 0; i < data.length; i++) {
            indexesUnique.add(i);
        }

        for (String key : map.keySet()) {
            for (String q : queries) {
                if (key.equalsIgnoreCase(q)) {
                    indexesUnique.removeAll(map.get(key));
                }
            }
        }

        if (indexesUnique.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            System.out.printf("%d persons found:\n", indexesUnique.size());
            indexesUnique.forEach(index -> System.out.println(data[index]));
        }
    }
}
