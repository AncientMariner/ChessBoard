package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.QUEEN;

public class QueensPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    @Override
    public char getFigure() {
        return QUEEN.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int position, char[] boardElements, int dimension) {
        perpendicularAttackPlacement(position, dimension, boardElements);
        diagonalAttackPlacement(position, dimension, boardElements);
    }

    @Override
    protected boolean possibleAttackPlaceForPositionCalculate(int position, char[] boardElements, int dimension) {
        return perpendicularAttackPlacementCalculate(position, dimension, boardElements)
            && diagonalAttackPlacementCalculate(position, boardElements, dimension);
    }
}
