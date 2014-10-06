package org.xander.chessboard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChessboardTest {
    @Test
    public void chessBoardTest() {
        Chessboard chessboard = new Chessboard();
        int dimension = 5;
        chessboard.setDimension(dimension);

        assertEquals(dimension, chessboard.getDimension());
    }
}
