package org.xander.chessboard.figuresPlacement;

import org.junit.Before;
import org.junit.Test;
import org.xander.chessboard.Chessboard;
import org.xander.chessboard.figures.Figure;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.EMPTY_BOARD_SIZE_6;

public class BishopsPlacementTest {
    private Chessboard chessboard;
    private PlacementBehavior figuresPlacement;

    @Before
    public void setUp() {
        chessboard = new Chessboard(null);
        figuresPlacement = new BishopsPlacement();
    }

    @Test
    public void placeBishop() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String expectedBoard = "b.....\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n";
        String expectedBoardWithTwoBishops = "bb....\n" +
                                             "......\n" +
                                             "......\n" +
                                             "......\n" +
                                             "......\n" +
                                             "......\n";
        Set<String> boards = figuresPlacement.placeOneFigureOnBoardSequentially(expectedBoard);
        assertTrue(boards.contains(expectedBoardWithTwoBishops));
    }

    @Test
    public void calculateAreaOfBishopAttackTopLeftCorner() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "b.....\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n";
        String expectedBoard = "b.....\n" +
                               ".x....\n" +
                               "..x...\n" +
                               "...x..\n" +
                               "....x.\n" +
                               ".....x\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void calculateAreaOfBishopAttackBottomLeftCorner() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "b.....\n";
        String expectedBoard = ".....x\n" +
                               "....x.\n" +
                               "...x..\n" +
                               "..x...\n" +
                               ".x....\n" +
                               "b.....\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void calculateAreaOfBishopAttackTopRightCorner() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".....b\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n";
        String expectedBoard = ".....b\n" +
                               "....x.\n" +
                               "...x..\n" +
                               "..x...\n" +
                               ".x....\n" +
                               "x.....\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void calculateAreaOfBishopAttackBottomRightCorner() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       ".....b\n";
        String expectedBoard = "x.....\n" +
                               ".x....\n" +
                               "..x...\n" +
                               "...x..\n" +
                               "....x.\n" +
                               ".....b\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void calculateAreaOfBishopAttackAllCorners() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "b....b\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "b....b\n";
        String expectedBoard = "b....b\n" +
                               ".x..x.\n" +
                               "..xx..\n" +
                               "..xx..\n" +
                               ".x..x.\n" +
                               "b....b\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void calculateAreaOfBishopAttackMix() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "...b..\n" +
                       "......\n" +
                       "......\n" +
                       ".....b\n" +
                       "......\n" +
                       "..b...\n";
        String expectedBoard = "..xb..\n" +
                               "..xxx.\n" +
                               ".x..xx\n" +
                               "x...xb\n" +
                               ".x.xx.\n" +
                               "..bx..\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoard, actual);
    }

    @Test
    public void calculateAreaOfTheKingAttackBorder() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board =  "bbbbbb\n" +
                        "b....b\n" +
                        "b....b\n" +
                        "b....b\n" +
                        "b....b\n" +
                        "bbbbbb\n";
        String expectedBoardWithBishops = "bbbbbb\n" +
                                          "bxxxxb\n" +
                                          "bxxxxb\n" +
                                          "bxxxxb\n" +
                                          "bxxxxb\n" +
                                          "bbbbbb\n";
        String actual = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(expectedBoardWithBishops, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheBishopAttackNegativeSmallBoard() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "..\n" +
                       ".....\n" +
                       "..b.\n" +
                       ".b.\n" +
                       "....\n" +
                       "..\n";
        figuresPlacement.calculateAttackPlaces(board);
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheBishopAttackNegativeBigBoard() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".......\n" +
                       ".......\n" +
                       "..b....\n" +
                       ".b.....\n" +
                       ".......\n" +
                       ".......\n";
        figuresPlacement.calculateAttackPlaces(board);
    }

    @Test
    public void sequentialFigurePlacement() {
        FiguresPlacement figuresPlacement = new BishopsPlacement();
        Set<String> boards = figuresPlacement.placeFigureOnBoard(Figure.BISHOP.getFigure(), EMPTY_BOARD_SIZE_6);
        assertThat("board is different", boards.contains("b.....\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n"), is(true));
    }

    @Test
    public void randomFigurePlacement() {
        FiguresPlacement figuresPlacement = new BishopsPlacement();
        Set<String> boards = figuresPlacement.placeFigureOnBoard(Figure.BISHOP.getFigure(), EMPTY_BOARD_SIZE_6);

        assertThat("boards are not full", boards.size() == 36, is(true));
        assertThat("board is different", boards.contains("......\n" +
                "......\n" +
                "......\n" +
                "...b..\n" +
                "......\n" +
                "......\n"), is(true));
    }
}
