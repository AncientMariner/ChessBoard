package org.xander.chessboard.figuresPlacement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xander.chessboard.figures.Figure;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class QueensPlacementTest extends FiguresAttackPlacesTest {
    private final Figure FIGURE = Figure.QUEEN;

    @BeforeEach
    public void setUp() {
        figuresPlacement = new QueensPlacement();
    }

    @Test
    public void placeQueen() {
        String expectedBoard = FIGURE.getFigureAsString() + ".....\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n";
        String expectedBoardWithTwoQueens = FIGURE.getFigureAsString() + ".....\n" +
                ".." + FIGURE.getFigureAsString() + "...\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n";
        Set<String> boards = figuresPlacement.placeCertainFigureOnBoard(expectedBoard);
        assertTrue(boards.size() == 20);
        assertTrue(boards.contains(expectedBoardWithTwoQueens));
    }

    @Test
    public void calculateAreaOfTheQueenAttackTopLeft() {
        String expectedBoardWithTwoQueens = "qxxxxx\n" +
                                            "xx....\n" +
                                            "x.x...\n" +
                                            "x..x..\n" +
                                            "x...x.\n" +
                                            "x....x\n";
        String actual = calculateAttackOfTheFigureOnBoard(FIGURE);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheQueenAttackBottomLeft() {
        String expectedBoardWithTwoQueens = "x....x\n" +
                                            "x...x.\n" +
                                            "x..x..\n" +
                                            "x.x...\n" +
                                            "xx....\n" +
                                            "qxxxxx\n";
        String actual = calculateAttackOfTheFigureOnBoardBottomLeft(FIGURE);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheQueenAttackTopRightCorner() {
        String expectedBoardWithTwoQueens = "xxxxxq\n" +
                                            "....xx\n" +
                                            "...x.x\n" +
                                            "..x..x\n" +
                                            ".x...x\n" +
                                            "x....x\n";
        String actual = calculateAttackOfTheFigureOnBoardTopRight(FIGURE);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheQueenAttackBottomRightCorner() {
        String expectedBoardWithTwoQueens = "x....x\n" +
                                            ".x...x\n" +
                                            "..x..x\n" +
                                            "...x.x\n" +
                                            "....xx\n" +
                                            "xxxxxq\n";
        String actual = calculateAttackOfTheFigureOnBoardBottomRight(FIGURE);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheQueenAttackAllCorners() {
        String expectedBoardWithTwoQueens = "qxxxxq\n" +
                                            "xx..xx\n" +
                                            "x.xx.x\n" +
                                            "x.xx.x\n" +
                                            "xx..xx\n" +
                                            "qxxxxq\n";
        String actual = calculateAttackOfTheFigureOnBoardAllCorners(FIGURE);
        assertEquals(expectedBoardWithTwoQueens, actual);
    }

    @Test
    public void calculateAreaOfTheQueenAttackBorder() {
        String expectedBoardWithQueens = "qqqqqq\n" +
                                         "qxxxxq\n" +
                                         "qxxxxq\n" +
                                         "qxxxxq\n" +
                                         "qxxxxq\n" +
                                         "qqqqqq\n";
        String actual = calculateAttackOfTheFigureOnBoardAllBorders(FIGURE);
        assertEquals(expectedBoardWithQueens, actual);
    }

    @Test
    public void calculateAreaOfTheTwoQueenAttack() {
        String expectedBoard = "xxxqxx\n" +
                               "..xxxx\n" +
                               ".xxxxx\n" +
                               "xxxxxq\n" +
                               ".xxxxx\n" +
                               "xxqxxx\n";
        String actual = calculateAttackOfTheFigureOnBoardMix(FIGURE);
        assertEquals(expectedBoard, actual);
    }
}
