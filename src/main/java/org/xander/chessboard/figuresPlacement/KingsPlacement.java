package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.KING;

public class KingsPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    private static final int KING_NEIGHBOUR_POSITION = 1;

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
        placeHorizontallyRight(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
        placeHorizontallyLeft(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
        placeVerticallyAbove(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
        placeVerticallyBelow(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
    }

    void diagonalPlacement(int position, int dimension, char[] boardElements) {
        placeDiagonallyAboveLeft(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
        placeDiagonallyAboveRight(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);

        placeDiagonallyBelowLeft(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
        placeDiagonallyBelowRight(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
    }

    boolean perpendicularPlacementCalculate(int position, int dimension, char[] boardElements) {
        return placeHorizontallyRightCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION)
                && placeHorizontallyLeftCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION)
                && placeVerticallyAboveCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION)
                && placeVerticallyBelowCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
    }

    boolean diagonalPlacementCalculate(int position, int dimension, char[] boardElements) {
        return placeDiagonallyAboveLeftCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION)
                && placeDiagonallyAboveRightCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION)
                && placeDiagonallyBelowLeftCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION)
                && placeDiagonallyBelowRightCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
    }

    @Override
    protected boolean possibleAttackPlaceForPositionCalculate(int dimension, char[] boardElements, int position) {
        return perpendicularPlacementCalculate(position, dimension, boardElements)
                && diagonalPlacementCalculate(position, dimension, boardElements);
    }
}
