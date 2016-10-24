package org.xander.chessboard;

import java.util.Random;

public abstract class FiguresPlacement implements PlacementBehavior {
    public static final char EMPTY_FIELD = '.';
    public static final char FIELD_UNDER_ATTACK = 'x';
    public static final char NEXT_LINE_FIELD = '\n';

    protected final BoardUtils boardUtils = new BoardUtils();

    public String placeNumberOfFiguresOnBoard(int numberOfFigures, String board) {
        if (board.contains(".")) {
            String boardWithFigures = board;
            while (numberOfFigures > 0) {
                boardWithFigures = placeOneFigureOnBoardSequentially(boardWithFigures);
                boardWithFigures = calculateAttackPlaces(boardWithFigures);
                numberOfFigures--;
            }
            return boardWithFigures;
        }
        return board;
    }

    public String placeFigureOnBoard(char figure, String board) {
        StringBuilder chessboardWithFigures = new StringBuilder();
        char[] boardElements = board.toCharArray();
        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] != NEXT_LINE_FIELD && boardElements[i] == EMPTY_FIELD) {
                boardElements[i] = figure;
                break;
            }
        }
        for (char element : boardElements) {
            chessboardWithFigures.append(element);
        }
        return chessboardWithFigures.toString();
    }

    public String placeFigureOnBoardRandomly(char figure, String board) {
        StringBuilder chessboardWithFigures = new StringBuilder();
        char[] boardElements = board.toCharArray();
        Random randomGenerator = new Random();
        int position = randomGenerator.nextInt(board.length());

        while (true) {
            if (boardElements[position] != NEXT_LINE_FIELD && boardElements[position] == EMPTY_FIELD) {
                boardElements[position] = figure;
                break;
            }
        }
        for (char element : boardElements) {
            chessboardWithFigures.append(element);
        }
        return chessboardWithFigures.toString();
    }

}
