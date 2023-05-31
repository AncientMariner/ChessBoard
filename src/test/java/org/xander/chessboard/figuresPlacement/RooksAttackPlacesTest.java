package org.xander.chessboard.figuresPlacement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xander.chessboard.figures.Figure;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RooksAttackPlacesTest extends FiguresAttackPlacesTest {
    private final Figure FIGURE = Figure.ROOK;

    @BeforeEach
    public void setUp() {
        figuresPlacement = new RooksPlacement();
    }

    @Test
    public void placeRook() {
        String expectedBoard = FIGURE.getFigureAsString() + ".....\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n";
        String expectedBoardWithTwoRooks = FIGURE.getFigureAsString() + ".....\n" +
                "." + FIGURE.getFigureAsString() + "....\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n";
        Set<String> boards = figuresPlacement.placeCertainFigureOnBoard(expectedBoard);
        assertTrue(boards.size() == 25);
        assertTrue(boards.contains(expectedBoardWithTwoRooks));
    }

    @Test
    public void areaOfBishopAttackTopLeftCorner() {
        String expectedBoard = "rxxxxx\n" +
                               "x.....\n" +
                               "x.....\n" +
                               "x.....\n" +
                               "x.....\n" +
                               "x.....\n";
        String actual = calculateAttackOfTheFigureOnBoard(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfBishopAttackBottomLeftCorner() {
        String expectedBoard = "x.....\n" +
                               "x.....\n" +
                               "x.....\n" +
                               "x.....\n" +
                               "x.....\n" +
                               "rxxxxx\n";
        String actual = calculateAttackOfTheFigureOnBoardBottomLeft(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfBishopAttackTopRightCorner() {
        String expectedBoard = "xxxxxr\n" +
                               ".....x\n" +
                               ".....x\n" +
                               ".....x\n" +
                               ".....x\n" +
                               ".....x\n";
        String actual = calculateAttackOfTheFigureOnBoardTopRight(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfBishopAttackBottomRightCorner() {
        String expectedBoard = ".....x\n" +
                               ".....x\n" +
                               ".....x\n" +
                               ".....x\n" +
                               ".....x\n" +
                               "xxxxxr\n";
        String actual = calculateAttackOfTheFigureOnBoardBottomRight(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfBishopAttackAllCorners() {
        String expectedBoard = "rxxxxr\n" +
                               "x....x\n" +
                               "x....x\n" +
                               "x....x\n" +
                               "x....x\n" +
                               "rxxxxr\n";
        String actual = calculateAttackOfTheFigureOnBoardAllCorners(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfBishopAttackMix() {
        String expectedBoard = "xxxrxx\n" +
                               "..xx.x\n" +
                               "..xx.x\n" +
                               "xxxxxr\n" +
                               "..xx.x\n" +
                               "xxrxxx\n";
        String actual = calculateAttackOfTheFigureOnBoardMix(FIGURE);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void areaOfTheBishopAttackBorder() {
        String expectedBoardWithBishops = "rrrrrr\n" +
                                          "rxxxxr\n" +
                                          "rxxxxr\n" +
                                          "rxxxxr\n" +
                                          "rxxxxr\n" +
                                          "rrrrrr\n";
        String actual = calculateAttackOfTheFigureOnBoardAllBorders(FIGURE);
        assertEquals(expectedBoardWithBishops, actual);
    }


    @Test
    public void areaOfTheRookAttack() {
        String board = ".....r\n" +
                       "...r..\n" +
                       "..r...\n" +
                       "....r.\n" +
                       ".r....\n" +
                       "r.....\n";
        String expectedBoardWithTwoRooks = "xxxxxr\n" +
                                           "xxxrxx\n" +
                                           "xxrxxx\n" +
                                           "xxxxrx\n" +
                                           "xrxxxx\n" +
                                           "rxxxxx\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoRooks, actual);
    }

    @Test
    public void areaOfOneRookAttack() {
        String board = "......\n" +
                       "......\n" +
                       "...r..\n" +
                       "......\n" +
                       "......\n" +
                       "......\n";
        String expectedBoardWithTwoRooks = "...x..\n" +
                                           "...x..\n" +
                                           "xxxrxx\n" +
                                           "...x..\n" +
                                           "...x..\n" +
                                           "...x..\n";

        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoRooks, actual);
    }

    @Test
    public void areaOfTheRookAttackOnCornersTopRightBottomLeft() {
        String board = ".....r\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "r.....\n";
        String expectedBoardWithTwoRooks = "xxxxxr\n" +
                                           "x....x\n" +
                                           "x....x\n" +
                                           "x....x\n" +
                                           "x....x\n" +
                                           "rxxxxx\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoRooks, actual);
    }

    @Test
    public void areaOfTheRookAttackOnCornersTopLeftBottomRight() {
        String board = "r.....\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       ".....r\n";
        String expectedBoardWithTwoRooks = "rxxxxx\n" +
                                           "x....x\n" +
                                           "x....x\n" +
                                           "x....x\n" +
                                           "x....x\n" +
                                           "xxxxxr\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoRooks, actual);
    }

    @Test
    public void areaOfTheRookAttackMix() {
        String board = "......\n" +
                       "....r.\n" +
                       "......\n" +
                       "......\n" +
                       ".r....\n" +
                       "......\n";
        String expectedBoardWithTwoRooks = ".x..x.\n" +
                                           "xxxxrx\n" +
                                           ".x..x.\n" +
                                           ".x..x.\n" +
                                           "xrxxxx\n" +
                                           ".x..x.\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoRooks, actual);
    }

    @Test
    public void areaOfTheRookAttackAnotherMix() {
        String board = "......\n" +
                       "......\n" +
                       "..r...\n" +
                       "...r..\n" +
                       "......\n" +
                       "......\n";
        String expectedBoardWithTwoRooks = "..xx..\n" +
                                           "..xx..\n" +
                                           "xxrxxx\n" +
                                           "xxxrxx\n" +
                                           "..xx..\n" +
                                           "..xx..\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithTwoRooks, actual);
    }

    //todo make method to detect illegal placement on the initial board provided by user
}
