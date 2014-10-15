package org.xander.chessboard;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RooksPlacementTest {
    Chessboard chessboard;
    FiguresPlacement figuresPlacement;

    @Before
    public void setUp() {
        chessboard = new Chessboard();
        figuresPlacement = new RooksPlacement(chessboard);
    }

    @Test
    public void placeOneRookTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String expectedBoard = "r.....\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n";

        String expectedBoardWithTwoRooks = "rr....\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n";

        String actual = figuresPlacement.placeOneFigureOnBoardSequentially(chessboard.drawABoard());
        assertEquals(expectedBoard, actual);
        String actualWithTwoRooks = figuresPlacement.placeOneFigureOnBoardSequentially(actual);
        assertEquals(expectedBoardWithTwoRooks, actualWithTwoRooks);
    }

    @Test
    public void calculateAreaOfTheRookAttackTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".....r\n" +
                "...r..\n" +
                "..r...\n" +
                "....r.\n" +
                ".r....\n" +
                "r.....\n";

        String expectedBoardWithTwoRooks = "xxxxxr\n" +
                "xxxrxx\n" +
                "xxrxxx\n" +
                "xxxxrx\n" +
                "xrxxxx\n" +
                "rxxxxx\n";

        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoRooks, actual);
    }

    @Test
    public void calculateAreaOfOneRookAttackTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "......\n" +
                "......\n" +
                "...r..\n" +
                "......\n" +
                "......\n" +
                "......\n";

        String expectedBoardWithTwoRooks = "...x..\n" +
                "...x..\n" +
                "xxxrxx\n" +
                "...x..\n" +
                "...x..\n" +
                "...x..\n";

        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoRooks, actual);
    }

    @Test
    public void placeThreeRooksOnBoardTest() {
        int dimension = 5;
        chessboard.setDimension(dimension);

        String result = figuresPlacement.placeNumberOfFiguresOnBoard(3, chessboard.drawABoard());
        assertEquals("rxxxx\n" +
                "xrxxx\n" +
                "xxrxx\n" +
                "xxx..\n" +
                "xxx..\n", result);
    }
}
