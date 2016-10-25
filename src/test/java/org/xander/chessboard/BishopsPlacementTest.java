package org.xander.chessboard;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.xander.chessboard.figures.Figure;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
        Set<String> strings = figuresPlacement.placeOneFigureOnBoardSequentially(chessboard.drawEmptyBoard());
        assertTrue(strings.contains(expectedBoard));
//        figuresPlacement.placeOneFigureOnBoardSequentially(chessboard.drawEmptyBoard());
//        assertEquals(expectedBoardWithTwoBishops, actualWithTwoBishops);
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

    @Ignore
    @Test
    public void sequentialFigurePlacement() {
        FiguresPlacement figuresPlacement = new BishopsPlacement();
//        String board = figuresPlacement.placeFigureOnBoard(Figure.BISHOP.getFigure(), "......\n" +
//                "......\n" +
//                "......\n" +
//                "......\n" +
//                "......\n" +
//                "......\n");
//        assertThat("board is different", board, is("b.....\n" +
//                "......\n" +
//                "......\n" +
//                "......\n" +
//                "......\n" +
//                "......\n"));
    }
    @Test
    public void randomFigurePlacement() {
        FiguresPlacement figuresPlacement = new BishopsPlacement();
        Set<String> strings = figuresPlacement.placeFigureOnBoard(Figure.BISHOP.getFigure(), "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n");


        assertThat("boards are not full", strings.size() == 36, is(true));
        assertThat("board is different", strings.contains("......\n" +
                "......\n" +
                "......\n" +
                "...b..\n" +
                "......\n" +
                "......\n"), is(true));

    }

    @Test
    public void numberOfFigureOnBoard() {

        FiguresPlacement figuresPlacement = new BishopsPlacement();
//        figuresPlacement.placeNumberOfFiguresOnBoard(3, "......\n" +
//                "......\n" +
//                "......\n" +
//                "......\n" +
//                "......\n" +
//                "......\n");
    }

}
