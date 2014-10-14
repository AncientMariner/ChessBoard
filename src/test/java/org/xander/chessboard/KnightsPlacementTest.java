package org.xander.chessboard;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KnightsPlacementTest {
    Chessboard chessboard;
    FiguresPlacement knightsPlacement;

    @Before
    public void setUp() {
        chessboard = new Chessboard();
        knightsPlacement = new KnightsPlacement(chessboard);
    }

    @Test
    public void calculateAreaOfTheKnightAttackTest() {
        int dimension = 6;
        chessboard.setDimension(dimension);

        String board = "......\n" +
                       "......\n" +
                       "..n...\n" +
                       ".....n\n" +
                       "......\n" +
                       "......\n";
        String actualBoard = knightsPlacement.calculateAttackPlaces(board);
        assertEquals(".x.x..\n" +
                     "x...x.\n" +
                     "..nx..\n" +
                     "x...xn\n" +
                     ".x.x..\n" +
                     "....x.\n", actualBoard);
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
        String actualBoard = knightsPlacement.calculateAttackPlaces(board);
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
        String actualBoard = knightsPlacement.calculateAttackPlaces(board);
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
        String actualBoard = knightsPlacement.calculateAttackPlaces(board);
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

        String result = knightsPlacement.placeNumberOfFiguressOnBoard(3, chessboard.drawABoard());
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

        String actual = knightsPlacement.placeOneFigureOnBoardSequentially(chessboard.drawABoard());
        assertEquals("n....\n" +
                     ".....\n" +
                     ".....\n" +
                     ".....\n" +
                     ".....\n", actual);
    }
}
