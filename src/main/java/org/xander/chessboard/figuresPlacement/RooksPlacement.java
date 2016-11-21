package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.ROOK;

public class RooksPlacement extends PerpendicularFiguresPlacement {
    @Override
    public char getFigure() {
        return ROOK.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int dimension, char[] boardElements, int position) {
        perpendicularAttackPlacement(position, dimension, boardElements);
    }

    @Override
    protected boolean possibleAttackPlaceForPositionCalculate(int dimension, char[] boardElements, int position) {
        return perpendicularAttackPlacementCalculate(position, dimension, boardElements);
    }
}
