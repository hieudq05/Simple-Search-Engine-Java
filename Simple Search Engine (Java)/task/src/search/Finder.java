package search;

import java.util.Scanner;

public class Finder {
    private FindingStrategy strategy;

    public Finder() {
    }

    public Finder(FindingStrategy strategy) {
        this.strategy = strategy;
    }

    public void find(String[] data, String query) {
        strategy.printResult(data, query);
    }

    public void setStrategy(FindingStrategy strategy) {
        this.strategy = strategy;
    }
}
