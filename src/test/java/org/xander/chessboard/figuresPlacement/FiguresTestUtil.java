package org.xander.chessboard.figuresPlacement;

public class FiguresTestUtil {
    public static final String EMPTY_BOARD_SIZE_6 = "......\n" +
            "......\n" +
            "......\n" +
            "......\n" +
            "......\n" +
            "......\n";

    public static String leftOnlyFigures(String board) {
        return board.replaceAll("x", "").replaceAll("\n", "").replaceAll("\\.", "");
    }
}
