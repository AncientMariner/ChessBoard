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
    public void placeRookTest() {
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
    public void calculateAreaOfTheRookAttackOnCornersTopRightBottomLeftTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);
        String board = ".....r\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "r.....\n";
        String expectedBoardWithTwoRooks = "xxxxxr\n" +
                                           "x....x\n" +
                                           "x....x\n" +
                                           "x....x\n" +
                                           "x....x\n" +
                                           "rxxxxx\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoRooks, actual);
    }

    @Test
    public void calculateAreaOfTheRookAttackOnCornersTopLeftBottomRightTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);
        String board = "r.....\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       ".....r\n";
        String expectedBoardWithTwoRooks = "rxxxxx\n" +
                                           "x....x\n" +
                                           "x....x\n" +
                                           "x....x\n" +
                                           "x....x\n" +
                                           "xxxxxr\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoRooks, actual);
    }

    @Test
    public void calculateAreaOfTheRookAttackMixTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);
        String board = "......\n" +
                       "....r.\n" +
                       "......\n" +
                       "......\n" +
                       ".r....\n" +
                       "......\n";
        String expectedBoardWithTwoRooks = ".x..x.\n" +
                                           "xxxxrx\n" +
                                           ".x..x.\n" +
                                           ".x..x.\n" +
                                           "xrxxxx\n" +
                                           ".x..x.\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoRooks, actual);
    }

    @Test
    public void calculateAreaOfTheRookAttackAnotherMixTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "......\n" +
                       "......\n" +
                       "..r...\n" +
                       "...r..\n" +
                       "......\n" +
                       "......\n";
        String expectedBoardWithTwoRooks = "..xx..\n" +
                                           "..xx..\n" +
                                           "xxrxxx\n" +
                                           "xxxrxx\n" +
                                           "..xx..\n" +
                                           "..xx..\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoRooks, actual);
    }

    @Test
    public void calculateAreaOfTheRookAttackBorderTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "rrrrrr\n" +
                       "r....r\n" +
                       "r....r\n" +
                       "r....r\n" +
                       "r....r\n" +
                       "rrrrrr\n";
        String expectedBoardWithRooks = "rrrrrr\n" +
                                        "rxxxxr\n" +
                                        "rxxxxr\n" +
                                        "rxxxxr\n" +
                                        "rxxxxr\n" +
                                        "rrrrrr\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithRooks, actual);
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

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheRookAttackNegativeTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".\n" +
                       ".....\n" +
                       "..r...\n" +
                       ".r...r\n" +
                       "...\n" +
                       ".....\n";
        figuresPlacement.calculateAttackPlaces(board);
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheRookAttackNegativeBigBoardTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".......\n" +
                       ".......\n" +
                       "..r....\n" +
                       ".r.....\n" +
                       ".......\n" +
                       ".......\n";
        figuresPlacement.calculateAttackPlaces(board);
    }
}
