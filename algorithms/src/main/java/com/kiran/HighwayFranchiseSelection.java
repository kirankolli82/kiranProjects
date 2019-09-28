package com.kiran;

import java.util.Arrays;
import java.util.Random;

/**
 * Problem statement: Given there is a highway where a franchise from one of 3 types is to be built at the exit for each town.
 * The cost of building each type of franchise is different. Two consecutive exits must not have the same type of franchise.
 * What is the lowest cost of constructing a franchise at each exit on the highway. <br/>
 * Solution: If there are 10 towns on the highway and 3 types of franchises then the costs can be represented
 * in 10 X 3 array. Using dynamic programing we have to evaluate the possibility that a franchise is part of the optimal solution
 * and recursively compute for al the previous exits. Once the optimal solution has been calculated we need to cache the solution
 * so that we do not re-calculate the same solution over and over again. If we do so we can compute the result in
 * 30 or Order(n) computations.
 */
public class HighwayFranchiseSelection {

    private static StringBuffer[] paths;
    private static int[][] calculatedLowestCosts;

    public static void main(String[] args) {
        int[][] priceChart = createChart();
        paths = new StringBuffer[priceChart.length];
        Arrays.fill(paths, new StringBuffer());
        calculatedLowestCosts = new int[priceChart.length][];
        for (int i = 0; i < priceChart.length; i++) {
            calculatedLowestCosts[i] = new int[priceChart[i].length];
            Arrays.fill(calculatedLowestCosts[i], -1);
        }
        int lowestCost = calculateLowestCost(priceChart);
        System.out.println(lowestCost);
        Arrays.stream(calculatedLowestCosts).forEach(ints -> {
            System.out.println("");
            Arrays.stream(ints).forEach(value -> System.out.printf(value + ","));
        });
    }

    private static int calculateLowestCost(int[][] priceChart) {
        int lowestCost = Integer.MAX_VALUE;
        for (int i = 0; i < priceChart.length; i++) {
            int lowestCostAtIndex = priceChart[i][priceChart[i].length - 1]
                    + calculateLowestCost(priceChart, priceChart[i].length - 2, i);
            if (lowestCost > lowestCostAtIndex) {
                lowestCost = lowestCostAtIndex;
            }
        }

        return lowestCost;
    }

    private static int calculateLowestCost(int[][] priceChart, int index, int excludingIndex) {
        if (calculatedLowestCosts[excludingIndex][index] != -1) {
            return calculatedLowestCosts[excludingIndex][index];
        }
        int lowestCost = Integer.MAX_VALUE;
        int lowestCostIndex = 0;
        for (int i = 0; i < priceChart.length; i++) {
            if (i == excludingIndex) {
                continue;
            }
            if (index == 0) {
                if (lowestCost > priceChart[i][0]) {
                    lowestCost = priceChart[i][0];
                    lowestCostIndex = i;
                }
            } else {
                int localLowestCost = priceChart[i][index] + calculateLowestCost(priceChart, index - 1, i);
                if (lowestCost > localLowestCost) {
                    lowestCost = localLowestCost;
                    lowestCostIndex = i;
                }
            }
        }
        calculatedLowestCosts[excludingIndex][index] = lowestCost;
        paths[excludingIndex].append(lowestCostIndex).append(",");
        System.out.println("Lowest cost at " + index + " is: " + lowestCost + ", for excluding index = " + excludingIndex);
        return lowestCost;
    }

    private static int[][] createChart() {
        int[][] priceChart = new int[3][10];
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < priceChart.length; i++) {
            stringBuffer.append(System.lineSeparator());
            for (int j = 0; j < priceChart[i].length; j++) {
                priceChart[i][j] = random.nextInt(10);
                stringBuffer.append(priceChart[i][j]).append("\t");
            }
            /*stringBuffer.append(System.lineSeparator());
            for (int j = 0; j < priceChart[i].length; j++) {
                stringBuffer.append("i=").append(i).append(",j=").append(j).append(" ");
            }*/
        }

        System.out.println(stringBuffer);
        return priceChart;
    }
}
