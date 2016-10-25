package org.xander.chessboard.figuresPlacement;

import org.junit.Before;
import org.junit.Test;
import org.xander.chessboard.Chessboard;
import org.xander.chessboard.figuresPlacement.PlacementBehavior;
import org.xander.chessboard.figuresPlacement.QueensPlacement;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueensPlacementTest {
    private Chessboard chessboard;
    private PlacementBehavior figuresPlacement;

    @Before
    public void setUp() {
        chessboard = new Chessboard(null);
        figuresPlacement = new QueensPlacement();
    }

    @Test
    public void placeQueen() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String expectedBoard = "q.....\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n";
        String expectedBoardWithTwoQueens = "qq....\n" +
                                            "......\n" +
                                            "......\n" +
                                            "......\n" +
                                            "......\n" +
                                            "......\n";
        Set<String> boards = figuresPlacement.placeOneFigureOnBoardSequentially(expectedBoard);
        assertTrue(boards.contains(expectedBoardWithTwoQueens));
    }

    @Test
    public void calculateAreaOfTheQueenAttackTopLeftCorner() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "q.....\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n";
        String expectedBoardWithTwoQueens = "qxxxxx\n" +
                                            "xx....\n" +
                                            "x.x...\n" +
                                            "x..x..\n" +
                                            "x...x.\n" +
                                            "x....x\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheQueenAttackTopRightCorner() {
        int dimension = 6;
        chessboard.setDimension(dimension);


        String board = ".....q\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n";
        String expectedBoardWithTwoQueens = "xxxxxq\n" +
                                            "....xx\n" +
                                            "...x.x\n" +
                                            "..x..x\n" +
                                            ".x...x\n" +
                                            "x....x\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheQueenAttackBottomLeftCorner() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "q.....\n";
        String expectedBoardWithTwoQueens = "x....x\n" +
                                            "x...x.\n" +
                                            "x..x..\n" +
                                            "x.x...\n" +
                                            "xx....\n" +
                                            "qxxxxx\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheQueenAttackBottomRightCorner() {
        int dimension = 6;
        chessboard.setDimension(dimension);


        String board = "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       ".....q\n";
        String expectedBoardWithTwoQueens = "x....x\n" +
                                            ".x...x\n" +
                                            "..x..x\n" +
                                            "...x.x\n" +
                                            "....xx\n" +
                                            "xxxxxq\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheQueenAttackMiddle() {
        int dimension = 6;
        chessboard.setDimension(dimension);


        String board = "......\n" +
                       "......\n" +
                       "..q...\n" +
                       "......\n" +
                       "......\n" +
                       "......\n";
        String expectedBoardWithTwoQueens = "x.x.x.\n" +
                                            ".xxx..\n" +
                                            "xxqxxx\n" +
                                            ".xxx..\n" +
                                            "x.x.x.\n" +
                                            "..x..x\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheQueenAttackBorder() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "qqqqqq\n" +
                       "q....q\n" +
                       "q....q\n" +
                       "q....q\n" +
                       "q....q\n" +
                       "qqqqqq\n";
        String expectedBoardWithQueens = "qqqqqq\n" +
                                         "qxxxxq\n" +
                                         "qxxxxq\n" +
                                         "qxxxxq\n" +
                                         "qxxxxq\n" +
                                         "qqqqqq\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithQueens, actual);
    }

    @Test
    public void calculateAreaOfTheTwoQueenAttack() {
        int dimension = 6;
        chessboard.setDimension(dimension);


        String board = "......\n" +
                       "......\n" +
                       "..q...\n" +
                       "......\n" +
                       "......\n" +
                       "....q.\n";
        String expectedBoardWithTwoQueens = "x.x.x.\n" +
                                            "xxxxx.\n" +
                                            "xxqxxx\n" +
                                            ".xxxx.\n" +
                                            "x.xxxx\n" +
                                            "xxxxqx\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheQueenAttackNegative() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".\n" +
                       ".....\n" +
                       "..q...\n" +
                       ".q...q\n" +
                       "...\n" +
                       ".....\n";
        figuresPlacement.calculateAttackPlaces(board);
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheQueenAttackNegativeBigBoard() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".......\n" +
                       ".......\n" +
                       "..q....\n" +
                       ".q.....\n" +
                       ".......\n" +
                       ".......\n";
        figuresPlacement.calculateAttackPlaces(board);
    }
}
