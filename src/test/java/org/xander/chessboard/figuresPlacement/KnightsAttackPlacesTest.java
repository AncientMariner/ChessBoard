package org.xander.chessboard.figuresPlacement;

import org.junit.Before;
import org.junit.Test;
import org.xander.chessboard.figures.Figure;

import static org.junit.Assert.assertEquals;

public class KnightsAttackPlacesTest extends FiguresAttackPlacesTest {
    private final Figure FIGURE = Figure.KNIGHT;

    @Before
    public void setUp() {
        figuresPlacement = new KnightsPlacement();
    }

    @Test
    public void placeKnight() {
        placeFigure(FIGURE);
    }

    @Test
    public void areaOfBishopAttackTopLeftCorner() {
        String expectedBoard = "n.....\n" +
                               "..x...\n" +
                               ".x....\n" +
                               "......\n" +
                               "......\n" +
                               "......\n";
        String actual = calculateAttackOfTheFigureOnBoard(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfBishopAttackBottomLeftCorner() {
        String expectedBoard = "......\n" +
                               "......\n" +
                               "......\n" +
                               ".x....\n" +
                               "..x...\n" +
                               "n.....\n";
        String actual = calculateAttackOfTheFigureOnBoardBottomLeft(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfBishopAttackTopRightCorner() {
        String expectedBoard = ".....n\n" +
                               "...x..\n" +
                               "....x.\n" +
                               "......\n" +
                               "......\n" +
                               "......\n";
        String actual = calculateAttackOfTheFigureOnBoardTopRight(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfBishopAttackBottomRightCorner() {
        String expectedBoard = "......\n" +
                               "......\n" +
                               "......\n" +
                               "....x.\n" +
                               "...x..\n" +
                               ".....n\n";
        String actual = calculateAttackOfTheFigureOnBoardBottomRight(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfTheKnightAttackCorners() {
        String actualBoard = calculateAttackOfTheFigureOnBoardAllCorners(FIGURE);
        assertEquals("n....n\n" +
                               "..xx..\n" +
                               ".x..x.\n" +
                               ".x..x.\n" +
                               "..xx..\n" +
                               "n....n\n", actualBoard);
    }

    @Test
    public void areaOfTheKnightAttackMix() {
        String actualBoard = calculateAttackOfTheFigureOnBoardMix(FIGURE);
        assertEquals("...n..\n" +
                               ".x..xx\n" +
                               "..xxx.\n" +
                               ".x.x.n\n" +
                               "x..xx.\n" +
                               "..n.x.\n", actualBoard);
    }

    @Test
    public void areaOfTheKnightAttackAllBorders() {
        String actualBoard = calculateAttackOfTheFigureOnBoardAllBorders(FIGURE);
        assertEquals("nnnnnn\n" +
                               "nxxxxn\n" +
                               "nxxxxn\n" +
                               "nxxxxn\n" +
                               "nxxxxn\n" +
                               "nnnnnn\n", actualBoard);
    }

    @Test
    public void areaOfTheKnightAttackOnVerticalBorder() {
        String board = "n....n\n" +
                       "n....n\n" +
                       "n....n\n" +
                       "n....n\n" +
                       "n....n\n" +
                       "n....n\n";
        String actualBoard = figuresPlacement.calculateAttackPlaces(board);
        assertEquals("nxxxxn\n" +
                               "nxxxxn\n" +
                               "nxxxxn\n" +
                               "nxxxxn\n" +
                               "nxxxxn\n" +
                               "nxxxxn\n", actualBoard);
    }

    @Test
    public void areaOfTheKnightAttackOnHorizontalBorder() {
        String board = "nnnnnn\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "nnnnnn\n";
        String actualBoard = figuresPlacement.calculateAttackPlaces(board);
        assertEquals("nnnnnn\n" +
                               "xxxxxx\n" +
                               "xxxxxx\n" +
                               "xxxxxx\n" +
                               "xxxxxx\n" +
                               "nnnnnn\n", actualBoard);
    }

    @Test
    public void areaOfTheKnightAttackOnBorderAndMiddle() {
        String board = "n....n\n" +
                       "......\n" +
                       "..nn..\n" +
                       "..nn..\n" +
                       "......\n" +
                       "n....n\n";
        String actualBoard = figuresPlacement.calculateAttackPlaces(board);
        assertEquals("nxxxxn\n" +
                               "xxxxxx\n" +
                               "xxnnxx\n" +
                               "xxnnxx\n" +
                               "xxxxxx\n" +
                               "nxxxxn\n", actualBoard);
    }
}
