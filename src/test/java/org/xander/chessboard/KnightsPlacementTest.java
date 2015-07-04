package org.xander.chessboard;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KnightsPlacementTest {
    Chessboard chessboard;
    PlacementBehavior figuresPlacement;

    @Before
    public void setUp() {
        chessboard = new Chessboard();
        figuresPlacement = new KnightsPlacement(chessboard);
    }

    @Test
    public void calculateAreaOfTheKnightAttackCornersTest() {
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
    public void calculateAreaOfTheKnightAttackTest() {
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
    public void calculateAreaOfTheKnightAttackOnBorderAndMiddleTest() {
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
    public void calculateAreaOfTheKnightAttackOnVerticalBorderTest() {
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
    public void calculateAreaOfTheKnightAttackOnHorizontalBorderTest() {
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

    @Test
    public void placeThreeKnightsOnBoardTest() {
        int dimension = 5;
        chessboard.setDimension(dimension);

        String result = figuresPlacement.placeNumberOfFiguresOnBoard(3, chessboard.drawABoard());
        assertEquals("nnn..\n" +
                     "x.xxx\n" +
                     "xxxx.\n" +
                     ".....\n" +
                     ".....\n", result);
    }

    @Test
    public void placeAKnightOnBoardTest() {
        int dimension = 5;
        chessboard.setDimension(dimension);

        String actual = figuresPlacement.placeOneFigureOnBoardSequentially(chessboard.drawABoard());
        assertEquals("n....\n" +
                     ".....\n" +
                     ".....\n" +
                     ".....\n" +
                     ".....\n", actual);
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaOfTheKnightAttackNegativeTest() {
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
    public void calculateAreaOfTheKnightAttackNegativeBigBoardTest() {
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
