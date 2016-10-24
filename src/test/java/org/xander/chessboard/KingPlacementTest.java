package org.xander.chessboard;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KingPlacementTest {
    private Chessboard chessboard;
    private PlacementBehavior figuresPlacement;

    @Before
    public void setUp() {
        chessboard = new Chessboard(null);
        figuresPlacement = new KingsPlacement(chessboard);
    }

    @Test
    public void placeKingTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String expectedBoard = "k.....\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n";
        String expectedBoardWithTwoKings = "kk....\n" +
                                           "......\n" +
                                           "......\n" +
                                           "......\n" +
                                           "......\n" +
                                           "......\n";
        String actual = figuresPlacement.placeOneFigureOnBoardSequentially(chessboard.drawEmptyBoard());
        assertEquals(expectedBoard, actual);
        String actualWithTwoKings = figuresPlacement.placeOneFigureOnBoardSequentially(actual);
        assertEquals(expectedBoardWithTwoKings, actualWithTwoKings);
    }

    @Test
    public void calculateAreaOfTheKingAttackTopLeftCornerTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "k.....\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n";
        String expectedBoardWithTwoQueens = "kx....\n" +
                                            "xx....\n" +
                                            "......\n" +
                                            "......\n" +
                                            "......\n" +
                                            "......\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheKingAttackBottomLeftCornerTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "......\n" +
                       "..k...\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "k.....\n";
        String expectedBoardWithTwoQueens = ".xxx..\n" +
                                            ".xkx..\n" +
                                            ".xxx..\n" +
                                            "......\n" +
                                            "xx....\n" +
                                            "kx....\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheKingAttackTopRightCornerTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".....k\n" +
                       "......\n" +
                       "......\n" +
                       ".k....\n" +
                       "......\n" +
                       "......\n";
        String expectedBoardWithTwoQueens = "....xk\n" +
                                            "....xx\n" +
                                            "xxx...\n" +
                                            "xkx...\n" +
                                            "xxx...\n" +
                                            "......\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheKingAttackBottomRightCornerTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "......\n" +
                       ".k....\n" +
                       "......\n" +
                       "......\n" +
                       ".k....\n" +
                       ".....k\n";
        String expectedBoardWithTwoQueens = "xxx...\n" +
                                            "xkx...\n" +
                                            "xxx...\n" +
                                            "xxx...\n" +
                                            "xkx.xx\n" +
                                            "xxx.xk\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheKingAttackBorderTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board =  "kkkkkk\n" +
                        "k....k\n" +
                        "k....k\n" +
                        "k....k\n" +
                        "k....k\n" +
                        "kkkkkk\n";
        String expectedBoardWithKings = "kkkkkk\n" +
                                        "kxxxxk\n" +
                                        "kx..xk\n" +
                                        "kx..xk\n" +
                                        "kxxxxk\n" +
                                        "kkkkkk\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithKings, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheKingAttackNegativeTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".\n" +
                       ".....\n" +
                       "..k...\n" +
                       ".k...k\n" +
                       "...\n" +
                       ".....\n";
        figuresPlacement.calculateAttackPlaces(board);
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheKingAttackNegativeBigBoardTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".......\n" +
                       ".......\n" +
                       "..k....\n" +
                       ".k.....\n" +
                       ".......\n" +
                       ".......\n";
        figuresPlacement.calculateAttackPlaces(board);
    }
}
