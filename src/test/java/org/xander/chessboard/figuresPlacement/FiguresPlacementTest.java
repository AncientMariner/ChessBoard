package org.xander.chessboard.figuresPlacement;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.EMPTY_BOARD_SIZE_6;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.leftOnlyFigures;

public class FiguresPlacementTest {

    @Test
    public void numberOfFigureOnBoard() {
        FiguresPlacement figuresPlacement = new BishopsPlacement();

        HashSet<String> boards = new HashSet<>();
        boards.add(EMPTY_BOARD_SIZE_6);
        Set<String> result = figuresPlacement.placeNumberOfFiguresOnBoard(3, boards);
        System.out.println();

        for (String board : result) {
            assertTrue("all elements are not present on each board", !board.contains("k") && !board.contains("q") && board.contains("b"));
            assertTrue("all elements are not present on each board", leftOnlyFigures(board).length() == 3);
        }
    }
}
