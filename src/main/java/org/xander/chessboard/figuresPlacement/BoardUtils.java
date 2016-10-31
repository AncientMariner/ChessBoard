package org.xander.chessboard.figuresPlacement;

import java.util.stream.IntStream;

public class BoardUtils {
    static String transformArrayToString(char[] boardElements) {
        StringBuilder board = new StringBuilder();

        IntStream.range(0, boardElements.length).forEachOrdered((i) -> board.append(boardElements[i]));

        return board.toString();
    }

    public static void checkBoard(String board, int dimension) {
        if (board == null || board.isEmpty() || board.length() % dimension != 0) {
            throw new IllegalStateException("There is something wrong with your board");
        }
    }
}