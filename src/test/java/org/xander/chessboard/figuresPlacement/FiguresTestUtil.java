package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figuresPlacement.FiguresPlacement.EMPTY_FIELD_STRING;
import static org.xander.chessboard.figuresPlacement.FiguresPlacement.FIELD_UNDER_ATTACK_STRING;
import static org.xander.chessboard.figuresPlacement.FiguresPlacement.NEXT_LINE_FIELD_STRING;

public class FiguresTestUtil {
    public static final String EMPTY_BOARD_SIZE_6 = "......\n" +
                                                    "......\n" +
                                                    "......\n" +
                                                    "......\n" +
                                                    "......\n" +
                                                    "......\n";
    public static final int DIMENSION_6 = 6;

    public static String leftOnlyFigures(String board) {
        return board.replaceAll(FIELD_UNDER_ATTACK_STRING, "").replaceAll(NEXT_LINE_FIELD_STRING, "").replaceAll("\\" + EMPTY_FIELD_STRING, "");
    }
}
