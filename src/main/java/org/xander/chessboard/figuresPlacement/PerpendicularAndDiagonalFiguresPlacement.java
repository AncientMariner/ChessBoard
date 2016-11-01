package org.xander.chessboard.figuresPlacement;

public abstract class PerpendicularAndDiagonalFiguresPlacement extends FiguresPlacement implements DiagonalPlacement, PerpendicularPlacement {


    void placeDiagonallyBelowLeft(int dimension, char[] boardElements, int position, int positionLeftBelow, int attackPlacesOnTheLeft) {
        while (attackPlacesOnTheLeft > 0) {
            if (isPossibleToPlaceDiagLeftBelow(boardElements.length, position, dimension, positionLeftBelow)) {
                left(boardElements, position + dimension * positionLeftBelow, positionLeftBelow);
            }
            positionLeftBelow++;
            attackPlacesOnTheLeft--;
        }
    }

    void placeDiagonallyBelowRIght(int dimension, char[] boardElements, int position, int positionRightBelow, int attackPlacesOnTheRight) {
        while (attackPlacesOnTheRight > 0) {
            if (isPossibleToPlaceDiagRightBelow(boardElements.length, position, dimension, positionRightBelow)) {
                if (boardElements[position + dimension * positionRightBelow + positionRightBelow] != NEXT_LINE_FIELD_CHAR
                        && boardElements[position + dimension * positionRightBelow + positionRightBelow] == EMPTY_FIELD_CHAR) {
                    boardElements[position + dimension * positionRightBelow + positionRightBelow] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            positionRightBelow++;
            attackPlacesOnTheRight--;
        }
    }

    void placeDiagonallyAboveRight(int dimension, char[] boardElements, int position, int positionRightAbove, int attackPlacesOnTheRight) {
        while (attackPlacesOnTheRight > 0) {
            if (isPossibleToPlaceDiagRightAbove(position, dimension, positionRightAbove)) {
                right(boardElements, position - dimension * positionRightAbove, positionRightAbove);
            }

            positionRightAbove++;
            attackPlacesOnTheRight--;
        }
    }

    void placeDiagonallyAboveLeft(int dimension, char[] boardElements, int position, int positionLeftAbove, int attackPlacesOnTheLeft) {
        while (attackPlacesOnTheLeft > 0) {

            if (isPossibleToPlaceDiagLeftAbove(position, dimension, positionLeftAbove)) {
                if (boardElements[position - dimension * positionLeftAbove - positionLeftAbove] != NEXT_LINE_FIELD_CHAR
                        && boardElements[position - dimension * positionLeftAbove - positionLeftAbove] == EMPTY_FIELD_CHAR) {
                    boardElements[position - dimension * positionLeftAbove - positionLeftAbove] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            positionLeftAbove++;
            attackPlacesOnTheLeft--;
        }
    }


    void pHorizontallyRight(int dimension, char[] boardElements, int position, int rightPosition) {
        while (isPossibleToPlaceRight(position % dimension + rightPosition, dimension)) {
            placeHorizontallyRight(boardElements, position, dimension, rightPosition);
            rightPosition++;
        }
    }
    void pVerticallyAbove(int dimension, char[] boardElements, int position, int numberOfLinesAbove) {
        while (isPossibleToPlaceOnPreviousLine(position - dimension * numberOfLinesAbove)) {

            placeVerticallyAbove(boardElements, position, dimension, numberOfLinesAbove);
            numberOfLinesAbove++;
        }
    }

    void pVerticallyBelow(int dimension, char[] boardElements, int position, int numberOfLinesBelow) {
        while (isPossibleToPlaceOnNextLine(boardElements, position + dimension * numberOfLinesBelow)) {

            placeVerticallyBelow(boardElements, position, dimension, numberOfLinesBelow);
            numberOfLinesBelow++;
        }
    }

    void pHorizontallyLeft(int dimension, char[] boardElements, int position, int leftPosition) {
        while (isPossibleToPlaceLeft(position % dimension - leftPosition)) {
            placeHorizontallyLeft(boardElements, position, dimension, leftPosition);
            leftPosition++;
        }
    }


    public void placeHorizontallyLeft(char[] boardElements, int position, int dimension, int leftPosition) {
        left(boardElements, position, leftPosition);
    }

    public void placeHorizontallyRight(char[] boardElements, int position, int dimension, int rightPosition) {
        right(boardElements, position, rightPosition);
    }

    private void left(char[] boardElements, int position, int leftPosition) {
        if (boardElements[position - leftPosition] != NEXT_LINE_FIELD_CHAR
                && boardElements[position - leftPosition] == EMPTY_FIELD_CHAR) {
            boardElements[position - leftPosition] = FIELD_UNDER_ATTACK_CHAR;
        }
    }

    private void right(char[] boardElements, int position, int rightPosition) {
        if (boardElements[position + rightPosition] != NEXT_LINE_FIELD_CHAR
                && boardElements[position + rightPosition] == EMPTY_FIELD_CHAR) {
            boardElements[position + rightPosition] = FIELD_UNDER_ATTACK_CHAR;
        }
    }

    public void placeVerticallyBelow(char[] boardElements, int position, int dimension, int numberOfLinesBelow) {
        right(boardElements, position, dimension * numberOfLinesBelow);
    }

    public void placeVerticallyAbove(char[] boardElements, int position, int dimension, int numberOfLinesAbove) {
        left(boardElements, position, dimension * numberOfLinesAbove);
    }

    protected boolean isPossibleToPlaceOnNextLine(char[] boardElements, int position) {
        return position < boardElements.length;
    }

    protected boolean isPossibleToPlaceOnPreviousLine(int position) {
        return position >= 0;
    }

    protected boolean isPossibleToPlaceRight(int position, int dimension) {
        return position < dimension;
    }

    protected boolean isPossibleToPlaceLeft(int position) {
        return position >= 0;
    }

    protected boolean isPossibleToPlaceDiagLeftAbove(int position, int dimension, int positionLeftAbove) {
        return position - dimension * positionLeftAbove - positionLeftAbove >= 0
                && (position - dimension * positionLeftAbove - positionLeftAbove) % dimension >= 0;
    }

    protected boolean isPossibleToPlaceDiagRightAbove(int position, int dimension, int positionRightAbove) {
        return position - dimension * positionRightAbove + positionRightAbove >= 0
                && (position - dimension * positionRightAbove + positionRightAbove) % dimension < dimension - 1;
    }

    protected boolean isPossibleToPlaceDiagLeftBelow(int boardElementsLength, int position, int dimension, int positionLeftBelow) {
        return (position + dimension * positionLeftBelow - positionLeftBelow) % dimension < dimension
                && position + dimension * positionLeftBelow - positionLeftBelow < boardElementsLength;
    }

    protected boolean isPossibleToPlaceDiagRightBelow(int boardElementsLength, int position, int dimension, int positionRightBelow) {
        return position + dimension * positionRightBelow + positionRightBelow < boardElementsLength
                && (position + dimension * positionRightBelow + positionRightBelow) % dimension < dimension;
    }
}
