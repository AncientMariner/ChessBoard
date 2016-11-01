package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.BISHOP;

public class BishopsPlacement extends DiagonalFiguresPlacement {
    @Override
    public char getFigure() {
        return BISHOP.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int dimension, char[] boardElements, int position) {
            int positionLeftAbove = 1;
            int attackPlacesOnTheLeft = position % dimension;
        placeDiagonallyAboveLeft(dimension, boardElements, position, positionLeftAbove, attackPlacesOnTheLeft);


        int positionRightAbove = 1;
            int attackPlacesOnTheRight = dimension - position % dimension - 2;
        placeDiagonallyAboveRight(dimension, boardElements, position, positionRightAbove, attackPlacesOnTheRight);


        if (isPossibleToPlaceOnNextLine(boardElements, position + dimension)) {
            int positionLeftBelow = 1;
            attackPlacesOnTheLeft = position % dimension;
            placeDiagonallyBelowLeft(dimension, boardElements, position, positionLeftBelow, attackPlacesOnTheLeft);

            int positionRightBelow = 1;
            attackPlacesOnTheRight = dimension - position % dimension - 2;
            placeDiagonallyBelowRIght(dimension, boardElements, position, positionRightBelow, attackPlacesOnTheRight);

        }
    }
}
