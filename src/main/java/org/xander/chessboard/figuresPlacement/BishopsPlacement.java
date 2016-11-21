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
    public boolean possibleAttackPlaceForPositionCalculate(int position, char[] boardElements, int dimension) {
        return diagonalAttackPlacementCalculate(position, boardElements, dimension);
    }
}
