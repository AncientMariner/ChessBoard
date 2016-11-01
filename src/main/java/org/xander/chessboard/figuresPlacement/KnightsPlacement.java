package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.KNIGHT;
import static org.xander.chessboard.figuresPlacement.BoardUtils.isBoardElementEmpty;

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
        placeAbove(boardElements, position, dimension);
    }

    private void placeAbove(char[] boardElements, int position, int dimension) {
        if (isPossibleToPlaceOnPreviousLine(positionAboveRight(position, dimension))
                && isPositionInRangeOnTheRight(position, dimension)
                && isBoardElementEmpty(boardElements[positionAboveRight(position, dimension)])) {
            boardElements[positionAboveRight(position, dimension)] = FIELD_UNDER_ATTACK_CHAR;
        }
        if (isPossibleToPlaceOnPreviousLine(positionAboveLeft(position, dimension))
                && isPositionInRangeOnTheLeft(position, dimension)
                && isBoardElementEmpty(boardElements[positionAboveLeft(position, dimension)])) {
            boardElements[positionAboveLeft(position, dimension)] = FIELD_UNDER_ATTACK_CHAR;
        }
    }

    private void placeBelow(char[] boardElements, int position, int dimension) {
        if (isPossibleToPlaceOnNextLine(boardElements, positionBelowRight(position, dimension))
                && isPositionInRangeOnTheRight(position, dimension)
                && isBoardElementEmpty(boardElements[positionBelowRight(position, dimension)])) {
            boardElements[positionBelowRight(position, dimension)] = FIELD_UNDER_ATTACK_CHAR;
        }
        if (isPossibleToPlaceOnNextLine(boardElements, positionBelowLeft(position, dimension))
                && isPositionInRangeOnTheLeft(position, dimension)
                && isBoardElementEmpty(boardElements[positionBelowLeft(position, dimension)])) {
            boardElements[positionBelowLeft(position, dimension)] = FIELD_UNDER_ATTACK_CHAR;
        }
    }

    private void placeLeft(char[] boardElements, int position, int dimension) {
        if (isPossibleToPlaceLeft(position, dimension)) {
            if (isPossibleToPlaceOnNextLine(boardElements, position + dimension)
                    && isBoardElementEmpty(boardElements[positionLeftBelow(position, dimension)])) {
                boardElements[positionLeftBelow(position, dimension)] = FIELD_UNDER_ATTACK_CHAR;
            }
            if (isPossibleToPlaceOnPreviousLine(position - dimension)
                    && isBoardElementEmpty(boardElements[positionLeftAbove(position, dimension)])) {
                boardElements[positionLeftAbove(position, dimension)] = FIELD_UNDER_ATTACK_CHAR;
            }
        }
    }

    private void placeRight(char[] boardElements, int position, int dimension) {
        if (isPossibleToPlaceRight(position, dimension)) {
            if (isPossibleToPlaceOnNextLine(boardElements, position + dimension)
                    && isBoardElementEmpty(boardElements[positionRightBelow(position, dimension)])) {
                boardElements[positionRightBelow(position, dimension)] = FIELD_UNDER_ATTACK_CHAR;
            }
            if (isPossibleToPlaceOnPreviousLine(position - dimension)
                    && isBoardElementEmpty(boardElements[positionRightAbove(position, dimension)])) {
                boardElements[positionRightAbove(position, dimension)] = FIELD_UNDER_ATTACK_CHAR;
            }
        }
    }

    private boolean isPositionInRangeOnTheLeft(int position, int dimension) {
        return position % dimension - 1 >= 0;
    }

    private boolean isPositionInRangeOnTheRight(int position, int dimension) {
        return position % dimension + 1 < dimension;
    }

    private boolean isPossibleToPlaceLeft(int position, int dimension) {
        return position % dimension - 2 >= 0;
    }

    private boolean isPossibleToPlaceRight(int position, int dimension) {
        return position % dimension + 2 < dimension;
    }

    private int positionLeftAbove(int position, int dimension) {
        return position - dimension - 2;
    }

    private int positionLeftBelow(int position, int dimension) {
        return position + dimension - 2;
    }

    private int positionRightAbove(int position, int dimension) {
        return position - dimension + 2;
    }

    private int positionRightBelow(int position, int dimension) {
        return position + dimension + 2;
    }

    private int positionAboveLeft(int position, int dimension) {
        return position - dimension * 2 - 1;
    }

    private int positionAboveRight(int position, int dimension) {
        return position - dimension * 2 + 1;
    }

    private int positionBelowLeft(int position, int dimension) {
        return position + dimension * 2 - 1;
    }

    private int positionBelowRight(int position, int dimension) {
        return position + dimension * 2 + 1;
    }
}