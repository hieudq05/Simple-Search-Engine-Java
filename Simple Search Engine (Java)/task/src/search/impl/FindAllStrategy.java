package search.impl;

import search.FindingStrategy;
import search.Main;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindAllStrategy implements FindingStrategy {

    @Override
    public void printResult(String[] data, String query) {
        Map<String, List<Integer>> map = Main.hashData(data);

        String[] queries = query.split(" ");

        Set<Integer> indexesUnique = new HashSet<>();

        for (String key : map.keySet()) {
            for (int i = 0; i < queries.length; i++) {
                if (i == 0) {
                    if (key.equalsIgnoreCase(queries[i])) {
                        indexesUnique.addAll(map.get(key));
                    }
                } else {
                    if (key.equalsIgnoreCase(queries[i])) {
                        indexesUnique.retainAll(map.get(key));
                    }
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
