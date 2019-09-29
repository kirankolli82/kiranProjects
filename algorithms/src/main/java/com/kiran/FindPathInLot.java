package com.kiran;

import java.util.Arrays;
import java.util.List;

/**
 * Problem : An empty lot in the form of a mXn array or list has an obstacle and a few trenches. A demolition truck
 * must start the at the top left corner and move one block at a time (up, left, down, right) while avoiding trenches
 * and find the shortest path to the obstacle.
 */
public class FindPathInLot {

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot) {
        int pathLength = path(lot, 0, 0, 0);
        if (pathLength == Integer.MAX_VALUE) {
            return -1;
        }

        return pathLength;
    }
    // METHOD SIGNATURE ENDS

    int path(List<List<Integer>> lot, int i, int j, int pathLength) {
        if (i >= lot.size()) {
            return Integer.MAX_VALUE;
        }

        if (j >= lot.get(i).size()) {
            return Integer.MAX_VALUE;
        }

        if (lot.get(i).get(j) == 0) {
            return Integer.MAX_VALUE;
        }

        if (lot.get(i).get(j) == 9) {
            return pathLength + 1;
        }

        if (lot.get(i).get(j) == 1) {
            pathLength++;
            int pathLength2 = path(lot, i + 1, j, pathLength);
            int pathLength3 = path(lot, i, j + 1, pathLength);

            int pathLength4 = Integer.MAX_VALUE;
            int pathLength5 = Integer.MAX_VALUE;

            if (pathLength2 == Integer.MAX_VALUE && pathLength3 == Integer.MAX_VALUE) {
                if (i - 1 > 0) {
                    pathLength4 = path(lot, i - 1, j, pathLength);
                }
                if (j - 1 > 0) {
                    pathLength5 = path(lot, i, j - 1, pathLength);
                }

            }
            int min1 = Math.min(pathLength2, pathLength3);
            int min2 = Math.min(pathLength4, pathLength5);

            if (min1 != Integer.MAX_VALUE && min1 < min2) {
                return min1;
            } else if (min2 != Integer.MAX_VALUE && min2 < min1) {
                return min2;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        FindPathInLot solution2 = new FindPathInLot();
        List<List<Integer>> lot = Arrays.asList(
                Arrays.asList(1, 0, 0),
                Arrays.asList(1, 0, 0),
                Arrays.asList(1, 9, 0)
        );

        int result = solution2.removeObstacle(3, 3, lot);
        System.out.println(result);
    }
}
