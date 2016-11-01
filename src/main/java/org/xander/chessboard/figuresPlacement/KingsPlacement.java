package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.KING;

public class KingsPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    private static final int NEIGHBOUR_POSITION = 1;

    @Override
    public char getFigure() {
        return KING.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int dimension, char[] boardElements, int position) {
        perpendicularPlacement(position, dimension, boardElements);
        diagonalPlacement(position, dimension, boardElements);
    }

    void perpendicularPlacement(int position, int dimension, char[] boardElements) {
        placeHorizontallyRight(dimension, boardElements, position, NEIGHBOUR_POSITION);
        placeHorizontallyLeft(dimension, boardElements, position, NEIGHBOUR_POSITION);

        placeVerticallyAbove(dimension, boardElements, position, NEIGHBOUR_POSITION);
        placeVerticallyBelow(dimension, boardElements, position, NEIGHBOUR_POSITION);
    }

    void diagonalPlacement(int position, int dimension, char[] boardElements) {
        placeDiagonallyAboveLeft(dimension, boardElements, position, NEIGHBOUR_POSITION, NEIGHBOUR_POSITION);
        placeDiagonallyAboveRight(dimension, boardElements, position, NEIGHBOUR_POSITION, NEIGHBOUR_POSITION);

        placeDiagonallyBelowLeft(dimension, boardElements, position, NEIGHBOUR_POSITION, NEIGHBOUR_POSITION);
        placeDiagonallyBelowRight(dimension, boardElements, position, NEIGHBOUR_POSITION, NEIGHBOUR_POSITION);
    }
}
