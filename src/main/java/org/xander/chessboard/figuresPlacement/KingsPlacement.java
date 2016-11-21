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
        perpendicularAttackPlacement(position, dimension, boardElements);
        diagonalAttackPlacement(position, dimension, boardElements);
    }

    void perpendicularAttackPlacement(int position, int dimension, char[] boardElements) {
        attackPlaceHorizontallyRight(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
        attackPlaceHorizontallyLeft(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
        attackPlaceVerticallyAbove(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
        attackPlaceVerticallyBelow(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
    }

    void diagonalAttackPlacement(int position, int dimension, char[] boardElements) {
        attaclkPlaceDiagonallyAboveLeft(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
        attackPlaceDiagonallyAboveRight(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);

        attackPlaceDiagonallyBelowLeft(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
        attackPlaceDiagonallyBelowRight(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
    }

    boolean perpendicularAttackPlacementCalculate(int position, int dimension, char[] boardElements) {
        return attackPlaceHorizontallyRightCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION)
                && attackPlaceHorizontallyLeftCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION)
                && attackPlaceVerticallyAboveCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION)
                && attackPlaceVerticallyBelowCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
    }

    boolean diagonalAttackPlacementCalculate(int position, int dimension, char[] boardElements) {
        return attackPlaceDiagonallyAboveLeftCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION)
                && attackPlaceDiagonallyAboveRightCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION)
                && attackPlaceDiagonallyBelowLeftCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION)
                && attackPlaceDiagonallyBelowRightCalc(dimension, boardElements, position, KING_NEIGHBOUR_POSITION);
    }

    @Override
    protected boolean possibleAttackPlaceForPositionCalculate(int dimension, char[] boardElements, int position) {
        return perpendicularAttackPlacementCalculate(position, dimension, boardElements)
                && diagonalAttackPlacementCalculate(position, dimension, boardElements);
    }
}
