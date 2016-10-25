package org.xander.chessboard;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KingPlacementTest {
    private Chessboard chessboard;
    private PlacementBehavior figuresPlacement;

    @Before
    public void setUp() {
        chessboard = new Chessboard(null);
        figuresPlacement = new KingsPlacement();
    }

    @Test
    public void placeKing() {
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
        Set<String> boards = figuresPlacement.placeOneFigureOnBoardSequentially(chessboard.drawEmptyBoard());
        assertTrue(boards.contains(expectedBoard));
//        String actualWithTwoKings = figuresPlacement.placeOneFigureOnBoardSequentially(actual);
//        assertEquals(expectedBoardWithTwoKings, actualWithTwoKings);
    }

    @Test
    public void calculateAreaOfTheKingAttackTopLeftCorner() {
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
    public void calculateAreaOfTheKingAttackBottomLeftCorner() {
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
    public void calculateAreaOfTheKingAttackTopRightCorner() {
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
    public void calculateAreaOfTheKingAttackBottomRightCorner() {
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
    public void calculateAreaOfTheKingAttackBorder() {
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
    public void calculateAreaOfTheKingAttackNegative() {
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
    public void calculateAreaOfTheKingAttackNegativeBigBoard() {
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
