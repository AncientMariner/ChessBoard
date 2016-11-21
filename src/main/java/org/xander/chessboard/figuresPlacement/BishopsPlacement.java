package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.BISHOP;

public class BishopsPlacement extends DiagonalFiguresPlacement {
    @Override
    public char getFigure() {
        return BISHOP.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int position, char[] boardElements, int dimension) {
        diagonalAttackPlacement(position, dimension, boardElements);
    }

    @Override
    public boolean isAttackPlacesForPositionNotHarmingToAnotherFigures(int position, char[] boardElements, int dimension) {
        return isDiagonalAttackPlacementNotHarming(position, boardElements, dimension);
    }
}
