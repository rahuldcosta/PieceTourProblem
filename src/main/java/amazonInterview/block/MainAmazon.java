package amazonInterview.block;

import java.util.*;

public class MainAmazon {

    static int NUMBEROFROWS;
    static int NUMBEROFCOLUMNS;
    static List<Integer> templeLocations;

    public static void main(String args[]) {
        int numberOfRows = 6;
        int numberOfColumns = 6;
        List<List<Integer>> grid = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(0);
        row1.add(1);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(1);
        grid.add(row1);
        row1 = new ArrayList<>();
        row1.add(0);
        row1.add(0);
        row1.add(1);
        row1.add(1);
        row1.add(1);
        row1.add(0);
        grid.add(row1);
        row1 = new ArrayList<>();
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        grid.add(row1);
        row1 = new ArrayList<>();
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        grid.add(row1);
        row1 = new ArrayList<>();
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        grid.add(row1);
        row1 = new ArrayList<>();
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        row1.add(0);
        grid.add(row1);
        templeLocations = Arrays.asList(2,3,4);
        numberAmazonGoStores(numberOfRows, numberOfColumns, grid);

    }

    static int numberAmazonGoStores(int rows, int column, List<List<Integer>> grid) {
        NUMBEROFROWS = rows;
        NUMBEROFCOLUMNS = column;
        grid.size();
        int clusterCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                if (grid.get(i).get(j) == 1)
                    if (findCluster(i, j, grid) > 0) {
                        clusterCount++;
                    }
            }
        }

        return clusterCount;
    }

    private static Integer findCluster(int rowNo, int colNo, List<List<Integer>> grid) {
        List<Integer> tempList = new ArrayList<>();
        tempList.add(grid.get(rowNo).get(colNo));
        if (!checkIfAdjacentBlockExist(rowNo, colNo, grid)) {
            grid.get(rowNo).set(colNo, 0);
            return 1;
        } else {
            grid.get(rowNo).set(colNo, 0);
            if (checkIfValidAdjacentBlock(rowNo - 1, colNo, grid)) {
                tempList.add(findCluster(rowNo - 1, colNo, grid));
            }
            if (checkIfValidAdjacentBlock(rowNo, colNo + 1, grid)) {
                tempList.add(findCluster(rowNo, colNo + 1, grid));
            }
            if (checkIfValidAdjacentBlock(rowNo + 1, colNo, grid)) {
                tempList.add(findCluster(rowNo + 1, colNo, grid));
            }
            if (checkIfValidAdjacentBlock(rowNo, colNo - 1, grid)) {
                tempList.add(findCluster(rowNo, colNo - 1, grid));
            }

        }
        return tempList.size();
    }

    private static boolean checkIfAdjacentBlockExist(int rowNo, int colNo, List<List<Integer>> grid) {

        return checkIfValidAdjacentBlock(rowNo - 1, colNo, grid)
                ||
                checkIfValidAdjacentBlock(rowNo, colNo + 1, grid)
                ||
                checkIfValidAdjacentBlock(rowNo + 1, colNo, grid)
                ||
                checkIfValidAdjacentBlock(rowNo, colNo - 1, grid);
    }

    private static boolean checkIfValidAdjacentBlock(int rowNo, int colNo, List<List<Integer>> grid) {
        return isAValidAdjacentBlock(rowNo, colNo) && grid.get(rowNo).get(colNo).equals(1)
                && (!templeLocations.contains(rowNo) && !templeLocations.contains(colNo)) ;

    }

    private static boolean isAValidAdjacentBlock(int nextRowNumber, int nextColumnNumber) {
        return checkIfBlockIsWithinBoundaries(nextRowNumber, nextColumnNumber);
    }

    private static boolean checkIfBlockIsWithinBoundaries(int rowBoundary, int columnBoundary) {
        return (rowBoundary >= 0 && rowBoundary < NUMBEROFROWS) && (columnBoundary >= 0 && columnBoundary < NUMBEROFCOLUMNS);
    }
}
