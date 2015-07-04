package org.xander.chessboard;

public abstract class FiguresPlacement implements PlacementBehavior {
    protected final BoardUtils boardUtils = new BoardUtils();

    public String placeNumberOfFiguresOnBoard(int numberOfFigures, String board) {
        String boardWithFigures = board;
        while (numberOfFigures > 0) {
            boardWithFigures = placeOneFigureOnBoardSequentially(boardWithFigures);
            boardWithFigures = calculateAttackPlaces(boardWithFigures);
            numberOfFigures--;
        }
        return boardWithFigures;
    }

    public String placeFigureOnBoard(char figure, String board) {
        StringBuilder chessboardWithFigures = new StringBuilder();
        char[] boardElements = board.toCharArray();
        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] != '\n' && boardElements[i] == '.') {
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
