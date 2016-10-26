package org.xander.chessboard.figuresPlacement;

public class BoardUtils {
    String transformArrayToStringBuilder(char[] boardElements) {
        StringBuilder chessBoardWithFigures = new StringBuilder();

        for (char element : boardElements) {
            chessBoardWithFigures.append(element);
        }
        return chessBoardWithFigures.toString();
    }

    public static void checkBoard(String board, int dimension) {
        if (board == null || board.isEmpty() || board.length() % dimension != 0) {
            throw new IllegalStateException("There is something wrong with your board");
        }
    }
}