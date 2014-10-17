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
    public void placeBishopTest() {
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
    public void calculateAreaOfBishopAttackTopLeftCornerTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "b.....\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n";
        String expectedBoard = "b.....\n" +
                               ".x....\n" +
                               "..x...\n" +
                               "...x..\n" +
                               "....x.\n" +
                               ".....x\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void calculateAreaOfBishopAttackBottomLeftCornerTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "b.....\n";
        String expectedBoard = ".....x\n" +
                               "....x.\n" +
                               "...x..\n" +
                               "..x...\n" +
                               ".x....\n" +
                               "b.....\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void calculateAreaOfBishopAttackTopRightCornerTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".....b\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n";
        String expectedBoard = ".....b\n" +
                               "....x.\n" +
                               "...x..\n" +
                               "..x...\n" +
                               ".x....\n" +
                               "x.....\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void calculateAreaOfBishopAttackBottomRightCornerTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       ".....b\n";
        String expectedBoard = "x.....\n" +
                               ".x....\n" +
                               "..x...\n" +
                               "...x..\n" +
                               "....x.\n" +
                               ".....b\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void calculateAreaOfBishopAttackAllCornersTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "b....b\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "b....b\n";
        String expectedBoard = "b....b\n" +
                               ".x..x.\n" +
                               "..xx..\n" +
                               "..xx..\n" +
                               ".x..x.\n" +
                               "b....b\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void calculateAreaOfBishopAttackMixTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "...b..\n" +
                       "......\n" +
                       "......\n" +
                       ".....b\n" +
                       "......\n" +
                       "..b...\n";
        String expectedBoard = "..xb..\n" +
                               "..xxx.\n" +
                               ".x..xx\n" +
                               "x...xb\n" +
                               ".x.xx.\n" +
                               "..bx..\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void calculateAreaOfTheKingAttackBorderTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board =  "bbbbbb\n" +
                        "b....b\n" +
                        "b....b\n" +
                        "b....b\n" +
                        "b....b\n" +
                        "bbbbbb\n";
        String expectedBoardWithBishops = "bbbbbb\n" +
                                          "bxxxxb\n" +
                                          "bxxxxb\n" +
                                          "bxxxxb\n" +
                                          "bxxxxb\n" +
                                          "bbbbbb\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithBishops, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheBishopAttackNegativeSmallBoardTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "..\n" +
                       ".....\n" +
                       "..b.\n" +
                       ".b.\n" +
                       "....\n" +
                       "..\n";
        figuresPlacement.calculateAttackPlaces(board);
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheBishopAttackNegativeBigBoardTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".......\n" +
                       ".......\n" +
                       "..b....\n" +
                       ".b.....\n" +
                       ".......\n" +
                       ".......\n";
        figuresPlacement.calculateAttackPlaces(board);
    }
}
