package org.xander.chessboard.figuresPlacement;

import org.junit.Before;
import org.junit.Test;
import org.xander.chessboard.figures.Figure;

import static org.junit.Assert.assertEquals;

public class BishopsAttackPlacesTest extends FiguresAttackPlacesTest {
    private final Figure FIGURE = Figure.BISHOP;

    @Before
    public void setUp() {
        figuresPlacement = new BishopsPlacement();
    }

    @Test
    public void placeBishop() {
        placeFigure(FIGURE);
    }

    @Test
    public void areaOfBishopAttackTopLeftCorner() {
        String expectedBoard = "b.....\n" +
                               ".x....\n" +
                               "..x...\n" +
                               "...x..\n" +
                               "....x.\n" +
                               ".....x\n";
        String actual = calculateAttackOfTheFigureOnBoard(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfBishopAttackBottomLeftCorner() {
        String expectedBoard = ".....x\n" +
                               "....x.\n" +
                               "...x..\n" +
                               "..x...\n" +
                               ".x....\n" +
                               "b.....\n";
        String actual = calculateAttackOfTheFigureOnBoardBottomLeft(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfBishopAttackTopRightCorner() {
        String expectedBoard = ".....b\n" +
                               "....x.\n" +
                               "...x..\n" +
                               "..x...\n" +
                               ".x....\n" +
                               "x.....\n";
        String actual = calculateAttackOfTheFigureOnBoardTopRight(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfBishopAttackBottomRightCorner() {
        String expectedBoard = "x.....\n" +
                               ".x....\n" +
                               "..x...\n" +
                               "...x..\n" +
                               "....x.\n" +
                               ".....b\n";
        String actual = calculateAttackOfTheFigureOnBoardBottomRight(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfBishopAttackAllCorners() {
        String expectedBoard = "b....b\n" +
                               ".x..x.\n" +
                               "..xx..\n" +
                               "..xx..\n" +
                               ".x..x.\n" +
                               "b....b\n";
        String actual = calculateAttackOfTheFigureOnBoardAllCorners(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfBishopAttackMix() {
        String expectedBoard = "..xb..\n" +
                               "..xxx.\n" +
                               ".x..xx\n" +
                               "x...xb\n" +
                               ".x.xx.\n" +
                               "..bx..\n";
        String actual = calculateAttackOfTheFigureOnBoardMix(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfTheBishopAttackBorder() {
        String expectedBoardWithBishops = "bbbbbb\n" +
                                          "bxxxxb\n" +
                                          "bxxxxb\n" +
                                          "bxxxxb\n" +
                                          "bxxxxb\n" +
                                          "bbbbbb\n";
        String actual = calculateAttackOfTheFigureOnBoardAllBorders(FIGURE);
        assertEquals(expectedBoardWithBishops, actual);
    }
}
