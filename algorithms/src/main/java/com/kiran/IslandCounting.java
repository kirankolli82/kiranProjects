package com.kiran;

import java.util.Random;

public class IslandCounting {
    /*
    W	L	W	W	L
    L	L	W	W	L
    L	W	W	L	W
    L	L	W	W	W
    W	L	W	L	L
    3
    */


    public static void main(String[] args) {
        char[][] map = createMap();
        System.out.println(countIslands(map));
    }

    private static int countIslands(char[][] map) {
        int islands = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                boolean isLand = map[i][j] == 'L';
                if (!isLand) {
                    continue;
                }
                visit(map, i, j);
                islands++;
            }
        }

        return islands;
    }

    private static void visit(char[][] map, int i, int j) {
        boolean isLand = map[i][j] == 'L';
        if (!isLand) {
            return;
        }

        map[i][j] = 'o';
        if (i + 1 < map.length) {
            visit(map, i + 1, j);
        }
        if (j + 1 < map[i].length) {
            visit(map, i, j + 1);
        }

        if (i + 1 < map.length && j + 1 < map[i].length) {
            visit(map, i + 1, j + 1);
        }

        if (i + 1 < map.length && j - 1 >= 0) {
            visit(map, i + 1, j - 1);
        }

        if (j - 1 >= 0) {
            visit(map, i, j - 1);
        }
    }

    private static char[][] createMap() {
        char[][] map = new char[5][5];
        Random random = new Random();
        char[] seeds = {'L', 'W'};
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < map.length; i++) {
            output.append(System.lineSeparator());
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = seeds[random.nextInt(2)];
                output.append(map[i][j]).append("\t");
            }
        }

        System.out.println(output);
        return map;
    }


}
