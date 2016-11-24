package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.KNIGHT;
import static org.xander.chessboard.figuresPlacement.BoardUtils.isBoardElementAnotherFigure;
import static org.xander.chessboard.figuresPlacement.BoardUtils.isBoardElementEmpty;

public class KnightsPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    @Override
    public char getFigure() {
        return KNIGHT.getFigure();
    }

    @Override
    public void attackPlaceForPosition(int position, char[] boardElements, int dimension) {
        attackPlaceRight(position, boardElements, dimension);
        attackPlaceBelow(position, boardElements, dimension);
        attackPlaceLeft(position, boardElements, dimension);
        attackPlaceAbove(position, boardElements, dimension);
    }

    @Override
    protected boolean isAttackPlacesForPositionNotHarmingToAnotherFigures(int position, char[] boardElements, int dimension) {
        return isAttackPlaceAboveNotHarming(position, boardElements, dimension)
                && isAttackPlaceBelowNotHarming(position, boardElements, dimension)
                && isAttackPlaceLeftNotHarming(position, boardElements, dimension)
                && isAttackPlaceRightNotHarming(position, boardElements, dimension);
    }

    private void attackPlaceAbove(int position, char[] boardElements, int dimension) {
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

    private boolean isAttackPlaceAboveNotHarming(int position, char[] boardElements, int dimension) {
        if (isPossibleToPlaceOnPreviousLine(positionAboveRight(position, dimension))
                && isPositionInRangeOnTheRight(position, dimension)) {
            if (isBoardElementAnotherFigure(boardElements[positionAboveRight(position, dimension)])) {
                return false;
            }
        }
        if (isPossibleToPlaceOnPreviousLine(positionAboveLeft(position, dimension))
                && isPositionInRangeOnTheLeft(position, dimension)) {
            if (isBoardElementAnotherFigure(boardElements[positionAboveLeft(position, dimension)])) {
                return false;
            }
        }
        return true;
    }

    private void attackPlaceBelow(int position, char[] boardElements, int dimension) {
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

    private boolean isAttackPlaceBelowNotHarming(int position, char[] boardElements, int dimension) {
        if (isPossibleToPlaceOnNextLine(boardElements, positionBelowRight(position, dimension))
                && isPositionInRangeOnTheRight(position, dimension)) {
            if (isBoardElementAnotherFigure(boardElements[positionBelowRight(position, dimension)])) {
                return false;
            }
        }
        if (isPossibleToPlaceOnNextLine(boardElements, positionBelowLeft(position, dimension))
                && isPositionInRangeOnTheLeft(position, dimension)) {
            if (isBoardElementAnotherFigure(boardElements[positionBelowLeft(position, dimension)])) {
                return false;
            }
        }
        return true;
    }

    private void attackPlaceLeft(int position, char[] boardElements, int dimension) {
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

    private boolean isAttackPlaceLeftNotHarming(int position, char[] boardElements, int dimension) {
        if (isPossibleToPlaceLeft(position, dimension)) {
            if (isPossibleToPlaceOnNextLine(boardElements, position + dimension)
                    && isBoardElementAnotherFigure(boardElements[positionLeftBelow(position, dimension)])) {
                return false;
            }
            if (isPossibleToPlaceOnPreviousLine(position - dimension)
                    && isBoardElementAnotherFigure(boardElements[positionLeftAbove(position, dimension)])) {
                return false;
            }
        }
        return true;
    }

    private void attackPlaceRight(int position, char[] boardElements, int dimension) {
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

    private boolean isAttackPlaceRightNotHarming(int position, char[] boardElements, int dimension) {
        if (isPossibleToPlaceRight(position, dimension)) {
            if (isPossibleToPlaceOnNextLine(boardElements, position + dimension)
                    && isBoardElementAnotherFigure(boardElements[positionRightBelow(position, dimension)])) {
                return false;
            }
            if (isPossibleToPlaceOnPreviousLine(position - dimension)
                    && isBoardElementAnotherFigure(boardElements[positionRightAbove(position, dimension)])) {
                return false;
            }
        }
        return true;
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