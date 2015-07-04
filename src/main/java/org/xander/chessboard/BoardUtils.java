package org.xander.chessboard;

public class BoardUtils {
    String transformArrayToStringBuilder(char[] boardElements) {
        StringBuilder chessBoardWithFigures = new StringBuilder();

        for (char element : boardElements) {
            chessBoardWithFigures.append(element);
        }
        return chessBoardWithFigures.toString();
    }
}