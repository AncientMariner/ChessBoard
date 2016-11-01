package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.KING;

public class KingsPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    @Override
    public char getFigure() {
        return KING.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int dimension, char[] boardElements, int position) {
        placeHorizontally(boardElements, position, dimension);
        placeVertically(boardElements, position, dimension);
        placeDiagonallyAbove(boardElements, position, dimension);
        placeDiagonallyBelow(boardElements, position, dimension);
    }

    public void placeHorizontally(char[] boardElements, int position, int dimension) {
        if (isPossibleToPlaceRight(position % dimension + 1, dimension)) {
            if (boardElements[position + 1] != NEXT_LINE_FIELD_CHAR && boardElements[position + 1] == EMPTY_FIELD_CHAR) {
                boardElements[position + 1] = FIELD_UNDER_ATTACK_CHAR;
            }
        }
        if (isPossibleToPlaceLeft(position % dimension - 1)) {
            if (boardElements[position - 1] != NEXT_LINE_FIELD_CHAR && boardElements[position - 1] == EMPTY_FIELD_CHAR) {
                boardElements[position - 1] = FIELD_UNDER_ATTACK_CHAR;
            }
        }
    }

    public void placeVertically(char[] boardElements, int position, int dimension) {
        if (isPossibleToPlaceOnNextLine(boardElements, position + dimension)) {
            if (boardElements[position + dimension] != NEXT_LINE_FIELD_CHAR && boardElements[position + dimension] == EMPTY_FIELD_CHAR) {
                boardElements[position + dimension] = FIELD_UNDER_ATTACK_CHAR;
            }
        }

        if (isPossibleToPlaceOnPreviousLine(position - dimension)) {
            if (boardElements[position - dimension] != NEXT_LINE_FIELD_CHAR && boardElements[position - dimension] == EMPTY_FIELD_CHAR) {
                boardElements[position - dimension] = FIELD_UNDER_ATTACK_CHAR;
            }
        }
    }

    public void placeDiagonallyAbove(char[] boardElements, int position, int dimension) {
        if (isPossibleToPlaceDiagLeftAbove(position, dimension, 1) ) {
            if (boardElements[position - dimension - 1] != NEXT_LINE_FIELD_CHAR
                    && boardElements[position - dimension - 1] == EMPTY_FIELD_CHAR) {
                boardElements[position - dimension - 1] = FIELD_UNDER_ATTACK_CHAR;
            }
        }

        if (isPossibleToPlaceDiagRightAbove(position, dimension, 1)) {
            if (boardElements[position - dimension + 1] != NEXT_LINE_FIELD_CHAR
                    && boardElements[position - dimension + 1] == EMPTY_FIELD_CHAR) {
                boardElements[position - dimension + 1] = FIELD_UNDER_ATTACK_CHAR;
            }
        }
    }

    public void placeDiagonallyBelow(char[] boardElements, int position, int dimension) {
        if(isPossibleToPlaceDiagLeftBelow(boardElements.length, position, dimension, 1)) {
            if (boardElements[position + dimension - 1] != NEXT_LINE_FIELD_CHAR
                    && boardElements[position + dimension - 1] == EMPTY_FIELD_CHAR) {
                boardElements[position + dimension - 1] = FIELD_UNDER_ATTACK_CHAR;
            }
        }

        if(isPossibleToPlaceDiagRightBelow(boardElements.length, position, dimension, 1)) {
            if (boardElements[position + dimension + 1] != NEXT_LINE_FIELD_CHAR
                    && boardElements[position + dimension + 1] == EMPTY_FIELD_CHAR) {
                boardElements[position + dimension + 1] = FIELD_UNDER_ATTACK_CHAR;
            }
        }
    }
}
