package org.xander.chessboard.figuresPlacement;

import org.junit.jupiter.api.Test;
import org.xander.chessboard.figures.Figure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FiguresAttackPlacesTest {
    PlacementBehavior figuresPlacement;

    String calculateAttackOfTheFigureOnBoard(Figure figure) {
        String expectedBoard = figure.getFigureAsString() + ".....\n" +
                                                            "......\n" +
                                                            "......\n" +
                                                            "......\n" +
                                                            "......\n" +
                                                            "......\n";
        return figuresPlacement.calculateAttackPlaces(expectedBoard);
    }

    String calculateAttackOfTheFigureOnBoardBottomLeft(Figure figure) {
        String expectedBoard = "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
         figure.getFigureAsString() + ".....\n";
        return figuresPlacement.calculateAttackPlaces(expectedBoard);
    }

    String calculateAttackOfTheFigureOnBoardTopRight(Figure figure) {
        String expectedBoard = "....." + figure.getFigureAsString() + "\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n";
        return figuresPlacement.calculateAttackPlaces(expectedBoard);
    }

    String calculateAttackOfTheFigureOnBoardBottomRight(Figure figure) {
        String expectedBoard = "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "......\n" +
                               "....." + figure.getFigureAsString() + "\n";
        return figuresPlacement.calculateAttackPlaces(expectedBoard);
    }

    String calculateAttackOfTheFigureOnBoardAllCorners(Figure figure) {
        String expectedBoard = figure.getFigureAsString() + "...." + figure.getFigureAsString() + "\n" +
                                                                                            "......\n" +
                                                                                            "......\n" +
                                                                                            "......\n" +
                                                                                            "......\n" +
                               figure.getFigureAsString() + "...." + figure.getFigureAsString() + "\n";
        return figuresPlacement.calculateAttackPlaces(expectedBoard);
    }

    String calculateAttackOfTheFigureOnBoardAllBorders(Figure figure) {
        String expectedBoard = figure.getFigureAsString() + figure.getFigureAsString() + figure.getFigureAsString() + figure.getFigureAsString() + figure.getFigureAsString() + figure.getFigureAsString() + "\n" +
                figure.getFigureAsString() + "...." + figure.getFigureAsString() + "\n" +
                figure.getFigureAsString() + "...." + figure.getFigureAsString() + "\n" +
                figure.getFigureAsString() + "...." + figure.getFigureAsString() + "\n" +
                figure.getFigureAsString() + "...." + figure.getFigureAsString() + "\n" +
                figure.getFigureAsString() + figure.getFigureAsString() + figure.getFigureAsString() + figure.getFigureAsString() + figure.getFigureAsString() + figure.getFigureAsString() + "\n";
        return figuresPlacement.calculateAttackPlaces(expectedBoard);
    }

    String calculateAttackOfTheFigureOnBoardMix(Figure figure) {
        String expectedBoard = "..." + figure.getFigureAsString() + ".." + "\n" +
                               "......\n" +
                               "......\n" +
                               "....." + figure.getFigureAsString() + "\n" +
                               "......\n" +
                               ".." + figure.getFigureAsString() + "...\n";
        return figuresPlacement.calculateAttackPlaces(expectedBoard);
    }


    @Test
    public void calculateAreaNegativeSmallBoard() {
        String board = "..\n" +
                       ".....\n" +
                       "....\n" +
                       "...\n" +
                       "....\n" +
                       "..\n";
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> new BishopsPlacement().calculateAttackPlaces(board));
        assertEquals("There is something wrong with your board", ex.getMessage());
    }

    @Test
    public void calculateAreaNegativeBigBoard() {
        String board = ".......\n" +
                       ".......\n" +
                       ".......\n" +
                       ".......\n" +
                       ".......\n" +
                       ".......\n";
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> new KingsPlacement().calculateAttackPlaces(board));
        assertEquals("There is something wrong with your board", ex.getMessage());
    }

    @Test
    public void calculateAreaNegativeNullBoard() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> new KingsPlacement().calculateAttackPlaces(null));
        assertEquals("board is null", ex.getMessage());
    }
}
