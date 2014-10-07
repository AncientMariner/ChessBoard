package org.xander.chessboard;

import java.util.*;

public class Chessboard {
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
        String boardWithKnights = placeKnights(numberOfKnights, emptyBoard);
        String boardWithKnightsAndRooks = placeRooks(numberOfRooks, boardWithKnights);
        String boardWithKnightsRooksAndBishops = placeBishops(numberOfBishops, boardWithKnightsAndRooks);
        String boardWithKnightsRooksBishopsAndQueens = placeQueens(numberOfQueens, boardWithKnightsRooksAndBishops);
        String boardWithAllFigures = placeKings(numberOfKings, boardWithKnightsRooksBishopsAndQueens);

        return boardWithAllFigures;
    }

    private int extractA(String figure, Map<String, Integer> figureQuantityMap) {
        if (figureQuantityMap.containsKey(figure)) {
            return figureQuantityMap.get(figure);
        }
        return 0;
    }

    private String placeKnights(int numberOfKnights, String emptyBoard) {
        StringBuilder chessBoardWithFigures = new StringBuilder();
        char[] boardElements = emptyBoard.toCharArray();

        for (char element : boardElements) {
            if (element != '\n' && element == '.' && numberOfKnights != 0) {
                chessBoardWithFigures.append("n");
                numberOfKnights--;
            } else {
                chessBoardWithFigures.append(element);
            }
        }
        return chessBoardWithFigures.toString();
    }

    private String placeRooks(int numberOfRooks, String boardWithKnights) {
        StringBuilder chessBoardWithFigures = new StringBuilder();
        char[] boardElements = boardWithKnights.toCharArray();

        for (char element : boardElements) {
            if (element != '\n' && element == '.' && numberOfRooks != 0) {
                chessBoardWithFigures.append("r");
                numberOfRooks--;
            } else {
                chessBoardWithFigures.append(element);
            }
        }
        return chessBoardWithFigures.toString();
    }

    private String placeBishops(int numberOfBishops, String boardWithKnightsAndRooks) {
        StringBuilder chessBoardWithFigures = new StringBuilder();
        char[] boardElements = boardWithKnightsAndRooks.toCharArray();

        for (char element : boardElements) {
            if (element != '\n' && element == '.' && numberOfBishops != 0) {
                chessBoardWithFigures.append("b");
                numberOfBishops--;
            } else {
                chessBoardWithFigures.append(element);
            }
        }
        return chessBoardWithFigures.toString();
    }

    private String placeQueens(int numberOfQueens, String boardWithKnightsRooksAndBishops) {
        StringBuilder chessBoardWithFigures = new StringBuilder();
        char[] boardElements = boardWithKnightsRooksAndBishops.toCharArray();

        for (char element : boardElements) {
            if (element != '\n' && element == '.' && numberOfQueens != 0) {
                chessBoardWithFigures.append("q");
                numberOfQueens--;
            } else {
                chessBoardWithFigures.append(element);
            }
        }
        return chessBoardWithFigures.toString();
    }

    private String placeKings(int numberOfKings, String boardWithKnightsRooksBishopsAndQueens) {
        StringBuilder chessBoardWithFigures = new StringBuilder();
        char[] boardElements = boardWithKnightsRooksBishopsAndQueens.toCharArray();

        for (char element : boardElements) {
            if (element != '\n' && element == '.' && numberOfKings != 0) {
                chessBoardWithFigures.append("k");
                numberOfKings--;
            } else {
                chessBoardWithFigures.append(element);
            }
        }
        return chessBoardWithFigures.toString();
    }
}
