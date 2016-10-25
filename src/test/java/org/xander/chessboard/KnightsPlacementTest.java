package org.xander.chessboard;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KnightsPlacementTest {
    private Chessboard chessboard;
    private PlacementBehavior figuresPlacement;

    @Before
    public void setUp() {
        chessboard = new Chessboard(null);
        figuresPlacement = new KnightsPlacement();
    }

    @Test
    public void calculateAreaOfTheKnightAttackCorners() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "n....n\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "......\n" +
                       "n....n\n";
        String actualBoard = figuresPlacement.calculateAttackPlaces(board);
        assertEquals("n....n\n" +
                     "..xx..\n" +
                     ".x..x.\n" +
                     ".x..x.\n" +
                     "..xx..\n" +
                     "n....n\n", actualBoard);
    }

    @Test
    public void calculateAreaOfTheKnightAttack() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "......\n" +
                       "......\n" +
                       "..n...\n" +
                       ".n...n\n" +
                       "......\n" +
                       "......\n";
        String actualBoard = figuresPlacement.calculateAttackPlaces(board);
        assertEquals(".x.x..\n" +
                     "x.x.x.\n" +
                     "..nx..\n" +
                     "xn..xn\n" +
                     ".x.x..\n" +
                     "x.x.x.\n", actualBoard);
    }

    @Test
    public void calculateAreaOfTheKnightAttackOnBorderAndMiddle() {
        int dimension = 6;
        chessboard.setDimension(dimension);

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

    @Test
    public void calculateAreaOfTheKnightAttackOnVerticalBorder() {
        int dimension = 6;
        chessboard.setDimension(dimension);

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
    public void calculateAreaOfTheKnightAttackOnHorizontalBorder() {
        int dimension = 6;
        chessboard.setDimension(dimension);

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

    @Ignore
    @Test
    public void placeThreeKnightsOnBoard() {
        int dimension = 5;
        chessboard.setDimension(dimension);

//        String result = figuresPlacement.placeNumberOfFiguresOnBoard(3, chessboard.drawEmptyBoard());
//        assertEquals("nnn..\n" +
//                     "x.xxx\n" +
//                     "xxxx.\n" +
//                     ".....\n" +
//                     ".....\n", result);
    }

    @Test
    public void placeAKnightOnBoard() {
        int dimension = 5;
        chessboard.setDimension(dimension);

        Set<String> strings = figuresPlacement.placeOneFigureOnBoardSequentially(chessboard.drawEmptyBoard());
        assertTrue(strings.contains("n....\n" +
                ".....\n" +
                ".....\n" +
                ".....\n" +
                ".....\n"));
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheKnightAttackNegative() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".\n" +
                       ".....\n" +
                       "..n...\n" +
                       ".n...n\n" +
                       "...\n" +
                       ".....\n";
        figuresPlacement.calculateAttackPlaces(board);
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheKnightAttackNegativeBigBoard() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = ".......\n" +
                       ".......\n" +
                       "..n....\n" +
                       ".n.....\n" +
                       ".......\n" +
                       ".......\n";
        figuresPlacement.calculateAttackPlaces(board);
    }
}
