package org.xander.chessboard;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueensPlacementTest {
    Chessboard chessboard;
    FiguresPlacement figuresPlacement;

    @Before
    public void setUp() {
        chessboard = new Chessboard();
        figuresPlacement = new QueensPlacement(chessboard);
    }

    @Test
    public void placeQueenTest() {
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
        String actual = figuresPlacement.placeOneFigureOnBoardSequentially(chessboard.drawABoard());
        assertEquals(expectedBoard, actual);
        String actualWithTwoRooks = figuresPlacement.placeOneFigureOnBoardSequentially(actual);
        assertEquals(expectedBoardWithTwoQueens, actualWithTwoRooks);
    }

    @Test
    public void calculateAreaOfTheQueenAttackTopLeftCornerTest() {
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
    public void calculateAreaOfTheQueenAttackTopRightCornerTest() {
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
    public void calculateAreaOfTheQueenAttackBottomLeftCornerTest() {
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
    public void calculateAreaOfTheQueenAttackBottomRightCornerTest() {
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
    public void calculateAreaOfTheQueenAttackMiddleTest() {
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
    public void calculateAreaOfTheQueenAttackBorderTest() {
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
    public void calculateAreaOfTheTwoQueenAttackTest() {
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
    public void calculateAreaOfTheQueenAttackNegativeTest() {
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
    public void calculateAreaOfTheQueenAttackNegativeBigBoardTest() {
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
