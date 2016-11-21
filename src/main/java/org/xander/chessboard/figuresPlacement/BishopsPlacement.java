package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.BISHOP;

public class BishopsPlacement extends DiagonalFiguresPlacement {
    @Override
    public char getFigure() {
        return BISHOP.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int dimension, char[] boardElements, int position) {
        diagonalAttackPlacement(position, dimension, boardElements);
    }

    @Override
    public boolean possibleAttackPlaceForPositionCalculate(int dimension, char[] boardElements, int position) {
        return diagonalAttackPlacementCalculate(position, dimension, boardElements);
    }
}
