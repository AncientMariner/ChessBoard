package org.xander.chessboard.figures;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FigureTest {
    @Test
    public void getBishop() {
        assertFigureSymbol(Figure.BISHOP.getFigure(), 'b');
    }

    @Test
    public void getRook() {
        assertFigureSymbol(Figure.ROOK.getFigure(), 'r');
    }

    @Test
    public void getKing() {
        assertFigureSymbol(Figure.KING.getFigure(), 'k');
    }

    @Test
    public void getQueen() {
        assertFigureSymbol(Figure.QUEEN.getFigure(), 'q');
    }

    @Test
    public void getKnight() {
        assertFigureSymbol(Figure.KNIGHT.getFigure(), 'n');
    }

    private void assertFigureSymbol(char figure, char value) {
        assertThat("figure letter is different", figure, is(value));
    }

    @Test
    public void getKingAsString() {
        assertFigureString(Figure.KING.getFigureAsString(), "k");
    }

    @Test
    public void getQueenAsString() {
        assertFigureString(Figure.QUEEN.getFigureAsString(), "q");
    }

    @Test
    public void getBishopAsString() {
        assertFigureString(Figure.BISHOP.getFigureAsString(), "b");
    }

    @Test
    public void getRookAsString() {
        assertFigureString(Figure.ROOK.getFigureAsString(), "r");
    }

    @Test
    public void getKnightString() {
        assertFigureString(Figure.KNIGHT.getFigureAsString(), "n");
    }

    private void assertFigureString(String figure, String b) {
        assertThat("figure letter as string is different", figure, is(b));
    }
}