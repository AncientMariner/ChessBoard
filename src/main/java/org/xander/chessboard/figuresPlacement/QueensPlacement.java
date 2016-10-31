package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.QUEEN;

public class QueensPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    @Override
    public char getFigure() {
        return QUEEN.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int dimension, char[] boardElements, int position) {
        placeHorizontally(boardElements, position, dimension);
        placeVertically(boardElements, position, dimension);
        placeDiagonallyAbove(boardElements, position, dimension);
        placeDiagonallyBelow(boardElements, position, dimension);
    }
}
