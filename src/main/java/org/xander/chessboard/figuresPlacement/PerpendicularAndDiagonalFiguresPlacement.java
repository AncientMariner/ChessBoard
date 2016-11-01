package org.xander.chessboard.figuresPlacement;

public abstract class PerpendicularAndDiagonalFiguresPlacement extends FiguresPlacement implements DiagonalPlacement, PerpendicularPlacement {
    void perpendicularPlacement(int position, int dimension, char[] boardElements) {
        placeHorizontallyRight(dimension, boardElements, position, attackPlacesOnTheRight(position, dimension));
        placeHorizontallyLeft(dimension, boardElements, position, attackPlacesOnTheLeft(position, dimension));

        placeVerticallyAbove(dimension, boardElements, position, numberOfLinesAbove(position, dimension));
        placeVerticallyBelow(dimension, boardElements, position, numberOfLinesBelow(position, dimension, boardElements));
    }

    void diagonalPlacement(int position, int dimension, char[] boardElements) {
        int diagonalStartingCounter = 1;

        placeDiagonallyAboveLeft(dimension, boardElements, position, diagonalStartingCounter, attackPlacesOnTheLeft(position, dimension));
        placeDiagonallyAboveRight(dimension, boardElements, position, diagonalStartingCounter, attackPlacesOnTheRight(position, dimension));

        placeDiagonallyBelowLeft(dimension, boardElements, position, diagonalStartingCounter, attackPlacesOnTheLeft(position, dimension));
        placeDiagonallyBelowRight(dimension, boardElements, position, diagonalStartingCounter, attackPlacesOnTheRight(position, dimension));
    }

    private int numberOfLinesBelow(int position, int dimension, char[] boardElements) {
        return numberOfLinesAbove(boardElements.length - position, dimension);
    }

    private int numberOfLinesAbove(int position, int dimension) {
        return position / dimension;
    }

    private int attackPlacesOnTheLeft(int position, int dimension) {
        return position % dimension;
    }

    private int attackPlacesOnTheRight(int position, int dimension) {
        //mind the \n character and counting from 0
        return dimension - position % dimension - 1 - 1;
    }

    protected void placeDiagonallyBelowLeft(int dimension, char[] boardElements, int position, int positionLeftBelow, int attackPlacesOnTheLeft) {
        while (attackPlacesOnTheLeft > 0) {
            if (isPossibleToPlaceDiagLeftBelow(boardElements.length, position, dimension, positionLeftBelow)) {
                if (isBoardElementEmpty(boardElements[position + dimension * positionLeftBelow - positionLeftBelow])) {
                    boardElements[position + dimension * positionLeftBelow - positionLeftBelow] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            positionLeftBelow++;
            attackPlacesOnTheLeft--;
        }
    }

    protected void placeDiagonallyBelowRight(int dimension, char[] boardElements, int position, int positionRightBelow, int attackPlacesOnTheRight) {
        while (attackPlacesOnTheRight > 0) {
            if (isPossibleToPlaceDiagRightBelow(boardElements.length, position, dimension, positionRightBelow)) {
                if (isBoardElementEmpty(boardElements[position + dimension * positionRightBelow + positionRightBelow])) {
                    boardElements[position + dimension * positionRightBelow + positionRightBelow] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            positionRightBelow++;
            attackPlacesOnTheRight--;
        }
    }

    protected void placeDiagonallyAboveRight(int dimension, char[] boardElements, int position, int positionRightAbove, int attackPlacesOnTheRight) {
        while (attackPlacesOnTheRight > 0) {
            if (isPossibleToPlaceDiagRightAbove(position, dimension, positionRightAbove)) {
                if (isBoardElementEmpty(boardElements[position - dimension * positionRightAbove + positionRightAbove])) {
                    boardElements[position - dimension * positionRightAbove + positionRightAbove] = FIELD_UNDER_ATTACK_CHAR;
                }
            }

            positionRightAbove++;
            attackPlacesOnTheRight--;
        }
    }

    protected void placeDiagonallyAboveLeft(int dimension, char[] boardElements, int position, int positionLeftAbove, int attackPlacesOnTheLeft) {
        while (attackPlacesOnTheLeft > 0) {

            if (isPossibleToPlaceDiagLeftAbove(position, dimension, positionLeftAbove)) {
                if (isBoardElementEmpty(boardElements[position - dimension * positionLeftAbove - positionLeftAbove])) {
                    boardElements[position - dimension * positionLeftAbove - positionLeftAbove] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            positionLeftAbove++;
            attackPlacesOnTheLeft--;
        }
    }

    protected void placeHorizontallyRight(int dimension, char[] boardElements, int position, int rightPosition) {
        while (rightPosition > 0) {
            if (isPossibleToPlaceRight(position % dimension + rightPosition, dimension)) {
                if (isBoardElementEmpty(boardElements[position + rightPosition])) {
                    boardElements[position + rightPosition] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            rightPosition--;
        }
    }

    protected void placeHorizontallyLeft(int dimension, char[] boardElements, int position, int leftPosition) {
        while (leftPosition > 0) {
            if (isPossibleToPlaceLeft(position % dimension - leftPosition)) {
                if (isBoardElementEmpty(boardElements[position - leftPosition])) {
                    boardElements[position - leftPosition] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            leftPosition--;
        }
    }

    private boolean isBoardElementEmpty(char boardElement) {
        return boardElement != NEXT_LINE_FIELD_CHAR && boardElement == EMPTY_FIELD_CHAR;
    }

    protected void placeVerticallyAbove(int dimension, char[] boardElements, int position, int numberOfLinesAbove) {
        while (numberOfLinesAbove > 0) {
            if (isPossibleToPlaceOnPreviousLine(position - dimension * numberOfLinesAbove)) {

                if (isBoardElementEmpty(boardElements[position - dimension * numberOfLinesAbove])) {
                    boardElements[position - dimension * numberOfLinesAbove] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            numberOfLinesAbove--;
        }
    }

    protected void placeVerticallyBelow(int dimension, char[] boardElements, int position, int numberOfLinesBelow) {
        while (numberOfLinesBelow > 0) {
            if (isPossibleToPlaceOnNextLine(boardElements, position + dimension * numberOfLinesBelow)) {
                if (isBoardElementEmpty(boardElements[position + dimension * numberOfLinesBelow])) {
                    boardElements[position + dimension * numberOfLinesBelow] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            numberOfLinesBelow--;
        }
    }

    boolean isPossibleToPlaceOnNextLine(char[] boardElements, int position) {
        return position < boardElements.length;
    }

    boolean isPossibleToPlaceOnPreviousLine(int position) {
        return position >= 0;
    }

    private boolean isPossibleToPlaceRight(int position, int dimension) {
        return position < dimension;
    }

    private boolean isPossibleToPlaceLeft(int position) {
        return position >= 0;
    }

    private boolean isPossibleToPlaceDiagLeftAbove(int position, int dimension, int positionLeftAbove) {
        return position - dimension * positionLeftAbove - positionLeftAbove >= 0
                && (position - dimension * positionLeftAbove - positionLeftAbove) % dimension >= 0;
    }

    private boolean isPossibleToPlaceDiagRightAbove(int position, int dimension, int positionRightAbove) {
        return position - dimension * positionRightAbove + positionRightAbove >= 0
                && (position - dimension * positionRightAbove + positionRightAbove) % dimension < dimension - 1;
    }

    private boolean isPossibleToPlaceDiagLeftBelow(int boardElementsLength, int position, int dimension, int positionLeftBelow) {
        return (position + dimension * positionLeftBelow - positionLeftBelow) % dimension < dimension
                && position + dimension * positionLeftBelow - positionLeftBelow < boardElementsLength;
    }

    private boolean isPossibleToPlaceDiagRightBelow(int boardElementsLength, int position, int dimension, int positionRightBelow) {
        return position + dimension * positionRightBelow + positionRightBelow < boardElementsLength
                && (position + dimension * positionRightBelow + positionRightBelow) % dimension < dimension;
    }
}
