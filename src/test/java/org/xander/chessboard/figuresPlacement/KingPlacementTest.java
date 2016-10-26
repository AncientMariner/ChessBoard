package org.xander.chessboard.figuresPlacement;

import org.junit.Before;
import org.junit.Test;
import org.xander.chessboard.Chessboard;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.xander.chessboard.ChessboardTest.DIMENSION_6;

public class KingPlacementTest {
    private Chessboard chessboard;
    private PlacementBehavior figuresPlacement;

    @Before
    public void setUp() {
        chessboard = Chessboard.newBuilder(null).withDimension(DIMENSION_6).build();
        figuresPlacement = new KingsPlacement();
    }

    @Test
    public void placeKing() {
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
        Set<String> boards = figuresPlacement.placeOneFigureOnBoardSequentially(expectedBoard);
        assertTrue(boards.contains(expectedBoardWithTwoKings));
    }

    @Test
    public void calculateAreaOfTheKingAttackTopLeftCorner() {
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
        String board = ".......\n" +
                       ".......\n" +
                       "..k....\n" +
                       ".k.....\n" +
                       ".......\n" +
                       ".......\n";
        figuresPlacement.calculateAttackPlaces(board);
    }
}
