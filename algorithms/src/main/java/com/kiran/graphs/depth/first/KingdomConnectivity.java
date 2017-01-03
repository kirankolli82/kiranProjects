package com.kiran.graphs.depth.first;

import java.util.*;

/**
 * https://www.hackerrank.com/challenges/kingdom-connectivity
 * Created by Kiran Kolli on 25-09-2016.
 */
public class KingdomConnectivity {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        line1 = line1.replace(System.lineSeparator(), "");
        Integer numberOfCities = Integer.valueOf(line1.split(" ")[0]);
        Integer linesToRead = Integer.valueOf(line1.split(" ")[1]);
        int index = 0;
        Map<Integer, List<Integer>> roads = new HashMap<>();
        while (index < linesToRead) {
            String line = scanner.nextLine();
            line = line.replace(System.lineSeparator(), "");
            Integer tail = Integer.valueOf(line.split(" ")[0]);
            Integer head = Integer.valueOf(line.split(" ")[1]);
            appendListMap(roads, tail, head);
            index++;
        }

        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        try {
            findPath(1, numberOfCities, roads, new HashMap<>(), currentPath, paths);
            System.out.println(paths.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void findPath(Integer start, Integer end,
                                 Map<Integer, List<Integer>> roads,
                                 Map<Integer, Integer> visited,
                                 List<Integer> currentPath,
                                 List<List<Integer>> paths) throws Exception {
        if (visited.containsKey(start)) {
            throw new Exception("INFINITE PATHS");
        }

        if (start.intValue() == end.intValue()) {
            currentPath.add(start);
            paths.add(currentPath);
            return;
        }

        if (!roads.containsKey(start)) {
            return;
        }

        for (Integer head : roads.get(start)) {
            visited.put(start, head);
            currentPath.add(start);
            findPath(head, end, roads, new HashMap<>(visited), new ArrayList<>(currentPath), paths);
        }
    }

    private static void appendListMap(Map<Integer, List<Integer>> map, Integer key, Integer value) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(value);
    }
}
