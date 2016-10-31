package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.BISHOP;

public class BishopsPlacement extends DiagonalFiguresPlacement {
    @Override
    public char getFigure() {
        return BISHOP.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int dimension, char[] boardElements, int position) {
        placeDiagonallyAbove(boardElements, position, dimension);
        placeDiagonallyBelow(boardElements, position, dimension);
    }
}
