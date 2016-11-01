package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.KNIGHT;

public class KnightsPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    @Override
    public char getFigure() {
        return KNIGHT.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int dimension, char[] boardElements, int position) {
        placeRight(boardElements, position, dimension);
        placeBelow(boardElements, position, dimension);
        placeLeft(boardElements, position, dimension);
        placeTop(boardElements, position, dimension);
    }

    private void placeTop(char[] boardElements, int position, int dimension) {
        if (isPossibleToPlaceOnPreviousLine(position - dimension * 2 + 1)) {
            if (position % dimension + 1 < dimension)
                if (boardElements[position - dimension * 2 + 1] == EMPTY_FIELD_CHAR)
                    boardElements[position - dimension * 2 + 1] = FIELD_UNDER_ATTACK_CHAR;
        }
        if (isPossibleToPlaceOnPreviousLine(position - dimension * 2 - 1)) {
            if (position % dimension - 1 >= 0)
            if (boardElements[position - dimension * 2 - 1] == EMPTY_FIELD_CHAR)
                boardElements[position - dimension * 2 - 1] = FIELD_UNDER_ATTACK_CHAR;
        }
    }

    private void placeLeft(char[] boardElements, int position, int dimension) {
        if (position % dimension - 2 >= 0) {
            if (isPossibleToPlaceOnNextLine(boardElements, position + dimension))
                if (boardElements[position + dimension - 2] == EMPTY_FIELD_CHAR)
                    boardElements[position + dimension - 2] = FIELD_UNDER_ATTACK_CHAR;
            if (isPossibleToPlaceOnPreviousLine(position - dimension))
                if (boardElements[position - dimension - 2] == EMPTY_FIELD_CHAR)
                    boardElements[position - dimension - 2] = FIELD_UNDER_ATTACK_CHAR;
        }
    }

    private void placeRight(char[] boardElements, int position, int dimension) {
        if (position % dimension + 2 < dimension) {
            if (isPossibleToPlaceOnNextLine(boardElements, position + dimension))
                if (boardElements[position + dimension + 2] == EMPTY_FIELD_CHAR)
                    boardElements[position + dimension + 2] = FIELD_UNDER_ATTACK_CHAR;
            if (isPossibleToPlaceOnPreviousLine(position - dimension))
                if (boardElements[position - dimension + 2] == EMPTY_FIELD_CHAR)
                    boardElements[position - dimension + 2] = FIELD_UNDER_ATTACK_CHAR;
        }
    }

    private void placeBelow(char[] boardElements, int position, int dimension) {
        if (isPossibleToPlaceOnNextLine(boardElements, position + dimension * 2 + 1)) {
            if (position % dimension + 1 < dimension)
                if (boardElements[position + dimension * 2 + 1] == EMPTY_FIELD_CHAR)
                    boardElements[position + dimension * 2 + 1] = FIELD_UNDER_ATTACK_CHAR;
        }
        if (isPossibleToPlaceOnNextLine(boardElements, position + dimension * 2 - 1)){
            if (position % dimension - 1 >= 0) {
                if (boardElements[position + dimension * 2 - 1] == EMPTY_FIELD_CHAR) {
                    boardElements[position + dimension * 2 - 1] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
        }
    }


}