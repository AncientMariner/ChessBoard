package org.xander.chessboard.figuresPlacement;

import org.junit.Test;
import org.xander.chessboard.figures.Figure;

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


    @Test(expected = IllegalStateException.class)
    public void calculateAreaNegativeSmallBoard() {
        String board = "..\n" +
                       ".....\n" +
                       "....\n" +
                       "...\n" +
                       "....\n" +
                       "..\n";
        new BishopsPlacement().calculateAttackPlaces(board);
    }

    @Test(expected = IllegalStateException.class)
    public void calculateAreaNegativeBigBoard() {
        String board = ".......\n" +
                       ".......\n" +
                       ".......\n" +
                       ".......\n" +
                       ".......\n" +
                       ".......\n";
        new KingsPlacement().calculateAttackPlaces(board);
    }
}
