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
    @Override
    void perpendicularAttackPlacement(int position, int dimension, char[] boardElements) {
        attackPlaceHorizontallyRight(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
        attackPlaceHorizontallyLeft(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
        attackPlaceVerticallyAbove(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
        attackPlaceVerticallyBelow(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
    }

    @Override
    void diagonalAttackPlacement(int position, int dimension, char[] boardElements) {
        attaclkPlaceDiagonallyAboveLeft(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
        attackPlaceDiagonallyAboveRight(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);

        attackPlaceDiagonallyBelowLeft(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
        attackPlaceDiagonallyBelowRight(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
    }

    @Override
    boolean isPerpendicularAttackPlacementNotHarming(int position, int dimension, char[] boardElements) {
        return isAttackPlaceHorizontallyRightNotHarming(position, boardElements, dimension, KING_NEIGHBOUR_POSITION)
                && isAttackPlaceHorizontallyLeftNotHarming(position, boardElements, dimension, KING_NEIGHBOUR_POSITION)
                && isAttackPlaceVerticallyAboveNotHarming(position, boardElements, dimension, KING_NEIGHBOUR_POSITION)
                && isAttackPlaceVerticallyBelowNotHarming(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
    }

    @Override
    boolean isDiagonalAttackPlacementNotHarming(int position, char[] boardElements, int dimension) {
        return isAttackPlaceDiagonallyAboveLeftNotHarming(position, boardElements, dimension, KING_NEIGHBOUR_POSITION)
                && isAttackPlaceDiagonallyAboveRightNotHarming(position, boardElements, dimension, KING_NEIGHBOUR_POSITION)
                && isAttackPlaceDiagonallyBelowLeftNotHarming(position, boardElements, dimension, KING_NEIGHBOUR_POSITION)
                && isAttackPlaceDiagonallyBelowRightNotHarming(position, boardElements, dimension, KING_NEIGHBOUR_POSITION);
    }

    @Override
    protected boolean isAttackPlacesForPositionNotHarmingToAnotherFigures(int position, char[] boardElements, int dimension) {
        return isPerpendicularAttackPlacementNotHarming(position, dimension, boardElements)
                && isDiagonalAttackPlacementNotHarming(position, boardElements, dimension);
    }
}
