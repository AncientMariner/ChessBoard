package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.KING;

public class KingsPlacement extends FiguresPlacement {
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

    private void placeHorizontally(char[] boardElements, int position, int dimension) {
        if (position % dimension + 1 < dimension) {
            if (boardElements[position + 1] != NEXT_LINE_FIELD_CHAR && boardElements[position + 1] == EMPTY_FIELD_CHAR) {
                boardElements[position + 1] = FIELD_UNDER_ATTACK_CHAR;
            }
        }
        if (position % dimension - 1 >= 0) {
            if (boardElements[position - 1] != NEXT_LINE_FIELD_CHAR && boardElements[position - 1] == EMPTY_FIELD_CHAR) {
                boardElements[position - 1] = FIELD_UNDER_ATTACK_CHAR;
            }
        }
    }

    private void placeVertically(char[] boardElements, int position, int dimension) {
        if (position + dimension < boardElements.length) {
            if (boardElements[position + dimension] != NEXT_LINE_FIELD_CHAR && boardElements[position + dimension] == EMPTY_FIELD_CHAR) {
                boardElements[position + dimension] = FIELD_UNDER_ATTACK_CHAR;
            }
        }

        if (position - dimension >= 0) {
            if (boardElements[position - dimension] != NEXT_LINE_FIELD_CHAR && boardElements[position - dimension] == EMPTY_FIELD_CHAR) {
                boardElements[position - dimension] = FIELD_UNDER_ATTACK_CHAR;
            }
        }
    }

    private void placeDiagonallyAbove(char[] boardElements, int position, int dimension) {
        if (position - dimension - 1 >= 0 && (position - dimension - 1) % dimension >= 0 ) {
            if (boardElements[position - dimension - 1] != NEXT_LINE_FIELD_CHAR
                    && boardElements[position - dimension - 1] == EMPTY_FIELD_CHAR) {
                boardElements[position - dimension - 1] = FIELD_UNDER_ATTACK_CHAR;
            }
        }

        if (position - dimension + 1 >= 0 && (position - dimension + 1) % dimension < dimension - 1) {
            if (boardElements[position - dimension + 1] != NEXT_LINE_FIELD_CHAR
                    && boardElements[position - dimension + 1] == EMPTY_FIELD_CHAR) {
                boardElements[position - dimension + 1] = FIELD_UNDER_ATTACK_CHAR;
            }
        }
    }

    private void placeDiagonallyBelow(char[] boardElements, int position, int dimension) {
        if((position + dimension - 1) % dimension < dimension
                && position + dimension - 1 < boardElements.length) {
            if (boardElements[position + dimension - 1] != NEXT_LINE_FIELD_CHAR
                    && boardElements[position + dimension - 1] == EMPTY_FIELD_CHAR) {
                boardElements[position + dimension - 1] = FIELD_UNDER_ATTACK_CHAR;
            }
        }

        if(position + dimension + 1 < boardElements.length
                && (position + dimension + 1) % dimension < dimension) {
            if (boardElements[position + dimension + 1] != NEXT_LINE_FIELD_CHAR
                    && boardElements[position + dimension + 1] == EMPTY_FIELD_CHAR) {
                boardElements[position + dimension + 1] = FIELD_UNDER_ATTACK_CHAR;
            }
        }
    }
}
