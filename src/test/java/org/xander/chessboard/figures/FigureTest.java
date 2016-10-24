package org.xander.chessboard.figures;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FigureTest {
    @Test
    public void getBishop() {
        char figure = Figure.BISHOP.getFigure();
        assertThat("figure letter is different", figure, is('b'));
    }

    @Test
    public void getRook() {
        char figure = Figure.ROOK.getFigure();
        assertThat("figure letter is different", figure, is('r'));
    }

    @Test
    public void getKing() {
        char figure = Figure.KING.getFigure();
        assertThat("figure letter is different", figure, is('k'));
    }

    @Test
    public void getQueen() {
        char figure = Figure.QUEEN.getFigure();
        assertThat("figure letter is different", figure, is('q'));
    }

    @Test
    public void getKnight() {
        char figure = Figure.KNIGHT.getFigure();
        assertThat("figure letter is different", figure, is('n'));
    }

    @Test
    public void getFigure() {
        char figure = Figure.BISHOP.getFigure();
        assertThat("figure letter is different", figure, is('b'));
    }
}