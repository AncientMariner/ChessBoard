package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.ROOK;

public class RooksPlacement extends PerpendicularFiguresPlacement {
    @Override
    public char getFigure() {
        return ROOK.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int dimension, char[] boardElements, int position) {
        placeHorizontally(boardElements, position, dimension);
        placeVertically(boardElements, position, dimension);
    }
}
