package org.xander.chessboard.figuresPlacement;

import org.junit.Before;
import org.junit.Test;
import org.xander.chessboard.figures.Figure;

import static org.junit.Assert.assertEquals;

public class KingsAttackPlacesTest extends FiguresAttackPlacesTest {
    private final Figure FIGURE = Figure.KING;

    @Before
    public void setUp() {
        figuresPlacement = new KingsPlacement();
    }

    @Test
    public void placeKing() {
        placeFigure(FIGURE);
    }

    @Test
    public void areaOfTheKingAttackTopLeftCorner() {
        String expectedBoardWithTwoQueens = "kx....\n" +
                                            "xx....\n" +
                                            "......\n" +
                                            "......\n" +
                                            "......\n" +
                                            "......\n";
        String actual = calculateAttackOfTheFigureOnBoard(FIGURE);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void areaOfTheKingAttackBottomLeftCorner() {
        String board = "......\n" +
                       "..k...\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "k.....\n";
        String expectedBoard = ".xxx..\n" +
                               ".xkx..\n" +
                               ".xxx..\n" +
                               "......\n" +
                               "xx....\n" +
                               "kx....\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfTheKingAttackBottomLeftCornerSingle() {
        String expectedBoard = "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "xx....\n" +
                               "kx....\n";
        String actual = calculateAttackOfTheFigureOnBoardBottomLeft(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfTheKingAttackTopRightCorner() {
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
    public void areaOfTheKingAttackTopRightCornerSingle() {
        String expected = "....xk\n" +
                          "....xx\n" +
                          "......\n" +
                          "......\n" +
                          "......\n" +
                          "......\n";
        String actual = calculateAttackOfTheFigureOnBoardTopRight(FIGURE);
        assertEquals(expected, actual);
    }

    @Test
    public void areaOfTheKingAttackBottomRightCorner() {
        String expectedBoardWithTwoQueens = "......\n" +
                                            "......\n" +
                                            "......\n" +
                                            "......\n" +
                                            "....xx\n" +
                                            "....xk\n";
        String actual = calculateAttackOfTheFigureOnBoardBottomRight(FIGURE);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void areaOfTheKingAttackBorderSingleCorners() {
        String expectedBoardWithKings = "kx..xk\n" +
                                        "xx..xx\n" +
                                        "......\n" +
                                        "......\n" +
                                        "xx..xx\n" +
                                        "kx..xk\n";
        String actual = calculateAttackOfTheFigureOnBoardAllCorners(FIGURE);
        assertEquals(expectedBoardWithKings, actual);
    }

    @Test
    public void areaOfTheKingAttackMix() {
        String expectedBoardWithTwoQueens = "..xkx.\n" +
                                            "..xxx.\n" +
                                            "....xx\n" +
                                            "....xk\n" +
                                            ".xxxxx\n" +
                                            ".xkx..\n";
        String actual = calculateAttackOfTheFigureOnBoardMix(FIGURE);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void areaOfTheKingAttackBorder() {
        String expectedBoardWithKings = "kkkkkk\n" +
                                        "kxxxxk\n" +
                                        "kx..xk\n" +
                                        "kx..xk\n" +
                                        "kxxxxk\n" +
                                        "kkkkkk\n";
        String actual = calculateAttackOfTheFigureOnBoardAllBorders(FIGURE);
        assertEquals(expectedBoardWithKings, actual);
    }
}
