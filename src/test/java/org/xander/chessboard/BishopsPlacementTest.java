package org.xander.chessboard;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BishopsPlacementTest {
    Chessboard chessboard;
    FiguresPlacement figuresPlacement;

    @Before
    public void setUp() {
        chessboard = new Chessboard();
        figuresPlacement = new BishopsPlacement(chessboard);
    }

    @Test
    public void placeOneBishopTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String expectedBoard = "b.....\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n";
        String expectedBoardWithTwoBishops = "bb....\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n";
        String actual = figuresPlacement.placeOneFigureOnBoardSequentially(chessboard.drawABoard());
        assertEquals(expectedBoard, actual);
        String actualWithTwoBishops = figuresPlacement.placeOneFigureOnBoardSequentially(actual);
        assertEquals(expectedBoardWithTwoBishops, actualWithTwoBishops);
    }

    @Test
    public void calculateAreaOfBishopAttackTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "......\n" +
                       "......\n" +
                       "......\n" +
                       "..bb..\n" +
                       "......\n" +
                       "......\n";

        String expectedBoard = "x....x\n" +
                               "xx..xx\n" +
                               ".xxxx.\n" +
                               "..bb..\n" +
                               ".xxxx.\n" +
                               "xx..xx\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }
}
