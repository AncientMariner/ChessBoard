package org.xander.chessboard;

public abstract class FiguresPlacement implements PlacementBehavior {
    public static final char EMPTY_FIELD = '.';
    public static final char FIELD_UNDER_ATTACK = 'x';

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
            if (boardElements[i] != '\n' && boardElements[i] == EMPTY_FIELD) {
                boardElements[i] = figure;
                break;
            }
        }
        for (char element : boardElements) {
            chessboardWithFigures.append(element);
        }
        return chessboardWithFigures.toString();
    }
}
