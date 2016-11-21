package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.KING;

public class KingsPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    private static final int KING_NEIGHBOUR_POSITION = 1;

    @Override
    public char getFigure() {
        return KING.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int position, char[] boardElements, int dimension) {
        perpendicularAttackPlacement(position, dimension, boardElements);
        diagonalAttackPlacement(position, dimension, boardElements);
    }

    void perpendicularAttackPlacement(int position, int dimension, char[] boardElements) {
        attackPlaceHorizontallyRight(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
        attackPlaceHorizontallyLeft(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
        attackPlaceVerticallyAbove(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
        attackPlaceVerticallyBelow(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
    }

    void diagonalAttackPlacement(int position, int dimension, char[] boardElements) {
        attaclkPlaceDiagonallyAboveLeft(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
        attackPlaceDiagonallyAboveRight(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);

        attackPlaceDiagonallyBelowLeft(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
        attackPlaceDiagonallyBelowRight(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
    }

    boolean perpendicularAttackPlacementCalculate(int position, int dimension, char[] boardElements) {
        return attackPlaceHorizontallyRightCalc(position, boardElements, dimension, KING_NEIGHBOUR_POSITION)
                && attackPlaceHorizontallyLeftCalc(position, boardElements, dimension, KING_NEIGHBOUR_POSITION)
                && attackPlaceVerticallyAboveCalc(position, boardElements, dimension, KING_NEIGHBOUR_POSITION)
                && attackPlaceVerticallyBelowCalc(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
    }

    boolean diagonalAttackPlacementCalculate(int position, char[] boardElements, int dimension) {
        return attackPlaceDiagonallyAboveLeftCalc(position, boardElements, dimension, KING_NEIGHBOUR_POSITION)
                && attackPlaceDiagonallyAboveRightCalc(position, boardElements, dimension, KING_NEIGHBOUR_POSITION)
                && attackPlaceDiagonallyBelowLeftCalc(position, boardElements, dimension, KING_NEIGHBOUR_POSITION)
                && attackPlaceDiagonallyBelowRightCalc(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
    }

    @Override
    protected boolean possibleAttackPlaceForPositionCalculate(int position, char[] boardElements, int dimension) {
        return perpendicularAttackPlacementCalculate(position, dimension, boardElements)
                && diagonalAttackPlacementCalculate(position, boardElements, dimension);
    }
}
