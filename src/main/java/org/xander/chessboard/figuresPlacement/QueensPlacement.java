package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.QUEEN;

public class QueensPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    @Override
    public char getFigure() {
        return QUEEN.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int dimension, char[] boardElements, int position) {
        int rightPosition = 1;
        pHorizontallyRight(dimension, boardElements, position, rightPosition);

        int leftPosition = 1;
        pHorizontallyLeft(dimension, boardElements, position, leftPosition);

        int numberOfLinesBelow = 1;
        pVerticallyBelow(dimension, boardElements, position, numberOfLinesBelow);

        int numberOfLinesAbove = 1;
        pVerticallyAbove(dimension, boardElements, position, numberOfLinesAbove);

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
