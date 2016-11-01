package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.ROOK;

public class RooksPlacement extends PerpendicularFiguresPlacement {
    @Override
    public char getFigure() {
        return ROOK.getFigure();
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


    }
}
