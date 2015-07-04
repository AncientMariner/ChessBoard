package org.xander.chessboard;

import java.util.*;

public class Chessboard {
    private final PlacementBehavior knightsPlacement = new KnightsPlacement(this);
    private final PlacementBehavior rooksPlacement = new RooksPlacement(this);
    private int dimension;
    private int boardSize;
    private Map<String, Integer> figureQuantityMap = new HashMap<>();

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public Map<String, Integer> getFigureQuantityMap() {
        return figureQuantityMap;
    }

    public void setFigureQuantityMap(Map<String, Integer> figureQuantityMap) {
        this.figureQuantityMap = figureQuantityMap;
    }

    public String drawABoard() {
        int xDimension = dimension;
        int yDimension = dimension;
        calculateBoardSize(xDimension, yDimension);

        StringBuilder chessBoard = new StringBuilder();
        for (int y = 0; y < yDimension; y++) {
            for (int x = 0; x < xDimension; x++) {
                chessBoard.append(".");
            }
            chessBoard.append("\n");
        }
        return chessBoard.toString();
    }

    private void calculateBoardSize(int xDimension, int yDimension) {
        boardSize = xDimension * yDimension;
    }

    public String placeFiguresOnBoard(Map<String, Integer> figureQuantityMap, String emptyBoard) {
        int numberOfKings = extractA("King", figureQuantityMap);
        int numberOfQueens = extractA("Queen", figureQuantityMap);
        int numberOfBishops = extractA("Bishop", figureQuantityMap);
        int numberOfRooks = extractA("Rook", figureQuantityMap);
        int numberOfKnights = extractA("Knight", figureQuantityMap);
        int sumOfAllFigures = numberOfBishops + numberOfKings + numberOfKnights + numberOfQueens + numberOfRooks;

        if (sumOfAllFigures > boardSize) {
            throw new IllegalStateException("There are more figures than places to put them");
        }
        String boardWithKnights = knightsPlacement.placeOneFigureOnBoardSequentially(emptyBoard);


//        String boardWithKnightsAndRooks = placeRooks(numberOfRooks, boardWithKnights);
//        String boardWithKnightsRooksAndBishops = placeBishops(numberOfBishops, boardWithKnightsAndRooks);
//        String boardWithKnightsRooksBishopsAndQueens = placeQueens(numberOfQueens, boardWithKnightsRooksAndBishops);
//        String boardWithAllFigures = placeKings(numberOfKings, boardWithKnightsRooksBishopsAndQueens);

        return boardWithKnights;
    }

    private int extractA(String figure, Map<String, Integer> figureQuantityMap) {
        if (figureQuantityMap.containsKey(figure)) {
            return figureQuantityMap.get(figure);
        }
        return 0;
    }

    public void checkBoard(String board, int dimension) {
        if (board.isEmpty() || board.length() % dimension != 0) {
            throw new IllegalStateException("There is something wrong with your board");
        }
    }
}
