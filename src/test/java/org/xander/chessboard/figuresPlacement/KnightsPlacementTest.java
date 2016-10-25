package org.xander.chessboard.figuresPlacement;

import org.junit.Before;
import org.junit.Test;
import org.xander.chessboard.Chessboard;
import org.xander.chessboard.figuresPlacement.KnightsPlacement;
import org.xander.chessboard.figuresPlacement.PlacementBehavior;

import java.util.HashSet;
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

    @Test
    public void placeThreeKnightsOnBoard() {
        int dimension = 5;
        chessboard.setDimension(dimension);

        Set<String> objects = new HashSet<>();
        objects.add(chessboard.drawEmptyBoard());
        Set<String> boards = figuresPlacement.placeNumberOfFiguresOnBoard(3, objects);
        assertTrue(boards.contains("nnn..\n" +
                     "x.xxx\n" +
                     "xxxx.\n" +
                     ".....\n" +
                     ".....\n"));
    }

    @Test
    public void placeAKnightOnBoard() {
        int dimension = 5;
        chessboard.setDimension(dimension);

        Set<String> boards = figuresPlacement.placeOneFigureOnBoardSequentially(chessboard.drawEmptyBoard());
        assertTrue(boards.contains("n....\n" +
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
