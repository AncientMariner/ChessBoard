package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.QUEEN;

public class QueensPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    @Override
    public char getFigure() {
        return QUEEN.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int dimension, char[] boardElements, int position) {
        perpendicularAttackPlacement(position, dimension, boardElements);
        diagonalAttackPlacement(position, dimension, boardElements);
    }

    @Override
    protected boolean possibleAttackPlaceForPositionCalculate(int dimension, char[] boardElements, int position) {
        return perpendicularAttackPlacementCalculate(position, dimension, boardElements)
            && diagonalAttackPlacementCalculate(position, dimension, boardElements);
    }
}
