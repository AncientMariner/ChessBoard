package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.ROOK;

public class RooksPlacement extends PerpendicularFiguresPlacement {
    @Override
    public char getFigure() {
        return ROOK.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int position, char[] boardElements, int dimension) {
        perpendicularAttackPlacement(position, dimension, boardElements);
    }

    @Override
    protected boolean possibleAttackPlaceForPositionCalculate(int position, char[] boardElements, int dimension) {
        return perpendicularAttackPlacementCalculate(position, dimension, boardElements);
    }
}
