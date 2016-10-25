package org.xander.chessboard;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RooksPlacementTest {
    private Chessboard chessboard;
    private PlacementBehavior figuresPlacement;

    @Before
    public void setUp() {
        chessboard = new Chessboard(null);
        figuresPlacement = new RooksPlacement();
    }

    @Test
    public void placeRook() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String expectedBoard = "r.....\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n";
        String expectedBoardWithTwoRooks = "rr....\n" +
                                           "......\n" +
                                           "......\n" +
                                           "......\n" +
                                           "......\n" +
                                           "......\n";
        Set<String> boards = figuresPlacement.placeOneFigureOnBoardSequentially(chessboard.drawEmptyBoard());
        assertTrue(boards.contains(expectedBoard));
//        String actualWithTwoRooks = figuresPlacement.placeOneFigureOnBoardSequentially(actual);
//        assertEquals(expectedBoardWithTwoRooks, actualWithTwoRooks);
    }

    @Test
    public void calculateAreaOfTheRookAttack() {
        int dimension = 6;
        chessboard.setDimension(dimension);

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
    public void calculateAreaOfOneRookAttack() {
        int dimension = 6;
        chessboard.setDimension(dimension);

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
    public void calculateAreaOfTheRookAttackOnCornersTopRightBottomLeft() {
        int dimension = 6;
        chessboard.setDimension(dimension);
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
    public void calculateAreaOfTheRookAttackOnCornersTopLeftBottomRight() {
        int dimension = 6;
        chessboard.setDimension(dimension);
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
    public void calculateAreaOfTheRookAttackMix() {
        int dimension = 6;
        chessboard.setDimension(dimension);
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
    public void calculateAreaOfTheRookAttackAnotherMix() {
        int dimension = 6;
        chessboard.setDimension(dimension);

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

    @Test
    public void calculateAreaOfTheRookAttackBorder() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "rrrrrr\n" +
                       "r....r\n" +
                       "r....r\n" +
                       "r....r\n" +
                       "r....r\n" +
                       "rrrrrr\n";
        String expectedBoardWithRooks = "rrrrrr\n" +
                                        "rxxxxr\n" +
                                        "rxxxxr\n" +
                                        "rxxxxr\n" +
                                        "rxxxxr\n" +
                                        "rrrrrr\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithRooks, actual);
    }

    @Test
    public void placeThreeRooksOnBoard() {
        int dimension = 5;
        chessboard.setDimension(dimension);
        Set<String> objects = new HashSet<>();
        objects.add(chessboard.drawEmptyBoard());
        Set<String> boards = figuresPlacement.placeNumberOfFiguresOnBoard(3, objects);

        assertTrue(boards.contains("rxxxx\n" +
                     "xrxxx\n" +
                     "xxrxx\n" +
                     "xxx..\n" +
                     "xxx..\n"));
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheRookAttackNegative() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".\n" +
                       ".....\n" +
                       "..r...\n" +
                       ".r...r\n" +
                       "...\n" +
                       ".....\n";
        figuresPlacement.calculateAttackPlaces(board);
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheRookAttackNegativeBoardBiggerThanDimension() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".......\n" +
                       ".......\n" +
                       "..r....\n" +
                       ".r.....\n" +
                       ".......\n" +
                       ".......\n";
        figuresPlacement.calculateAttackPlaces(board);
    }
}
