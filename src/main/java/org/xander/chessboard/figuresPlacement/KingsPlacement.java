package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.KING;

public class KingsPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    @Override
    public char getFigure() {
        return KING.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int dimension, char[] boardElements, int position) {
        if (isPossibleToPlaceRight(position % dimension + 1, dimension)) {

            placeHorizontallyRight(boardElements, position, dimension, 1);
        }
        if (isPossibleToPlaceLeft(position % dimension - 1)) {

            placeHorizontallyLeft(boardElements, position, dimension, 1);
        }
        if (isPossibleToPlaceOnNextLine(boardElements, position + dimension)) {

            placeVerticallyBelow(boardElements, position, dimension, 1);
        }
        if (isPossibleToPlaceOnPreviousLine(position - dimension)) {

            placeVerticallyAbove(boardElements, position, dimension, 1);
        }



        int positionLeftAbove = 1;
        int attackPlacesOnTheLeft = 1;
        placeDiagonallyAboveLeft(dimension, boardElements, position, positionLeftAbove, attackPlacesOnTheLeft);

        int positionRightAbove = 1;
        int attackPlacesOnTheRight = 1;
        placeDiagonallyAboveRight(dimension, boardElements, position, positionRightAbove, attackPlacesOnTheRight);

        if (isPossibleToPlaceOnNextLine(boardElements, position + dimension)) {
            int positionLeftBelow = 1;
            attackPlacesOnTheLeft = 1;
            placeDiagonallyBelowLeft(dimension, boardElements, position, positionLeftBelow, attackPlacesOnTheLeft);
            int positionRightBelow = 1;
            attackPlacesOnTheRight = 1;
            placeDiagonallyBelowRIght(dimension, boardElements, position, positionRightBelow, attackPlacesOnTheRight);
        }

    }




}
