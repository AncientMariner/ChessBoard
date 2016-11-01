package org.xander.chessboard.figuresPlacement;

public abstract class PerpendicularAndDiagonalFiguresPlacement extends FiguresPlacement implements DiagonalPlacement, PerpendicularPlacement {
    void perpendicularPlacement(int position, int dimension, char[] boardElements) {
        placeHorizontallyRight(dimension, boardElements, position, attackPlacesOnTheRight(position, dimension));
        placeHorizontallyLeft(dimension, boardElements, position, attackPlacesOnTheLeft(position, dimension));

        placeVerticallyAbove(dimension, boardElements, position, numberOfLinesAbove(position, dimension));
        placeVerticallyBelow(dimension, boardElements, position, numberOfLinesBelow(position, dimension, boardElements));
    }

    void diagonalPlacement(int position, int dimension, char[] boardElements) {
        placeDiagonallyAboveLeft(dimension, boardElements, position, attackPlacesOnTheLeft(position, dimension));
        placeDiagonallyAboveRight(dimension, boardElements, position, attackPlacesOnTheRight(position, dimension));

        placeDiagonallyBelowLeft(dimension, boardElements, position, attackPlacesOnTheLeft(position, dimension));
        placeDiagonallyBelowRight(dimension, boardElements, position, attackPlacesOnTheRight(position, dimension));
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

    void placeDiagonallyBelowLeft(int dimension, char[] boardElements, int position, int attackPlacesOnTheLeft) {
        int positionLeftBelow = 1;

        while (attackPlacesOnTheLeft > 0) {
            if (isPossibleToPlaceDiagLeftBelow(boardElements.length, position, dimension, positionLeftBelow)) {
                if (isBoardElementEmpty(boardElements[elementDiagonallyLeftBelow(dimension, position, positionLeftBelow)])) {
                    boardElements[elementDiagonallyLeftBelow(dimension, position, positionLeftBelow)] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            positionLeftBelow++;
            attackPlacesOnTheLeft--;
        }
    }

    void placeDiagonallyBelowRight(int dimension, char[] boardElements, int position, int attackPlacesOnTheRight) {
        int positionRightBelow = 1;

        while (attackPlacesOnTheRight > 0) {
            if (isPossibleToPlaceDiagRightBelow(boardElements.length, position, dimension, positionRightBelow)) {
                if (isBoardElementEmpty(boardElements[elementDiagonallyRightBelow(dimension, position, positionRightBelow)])) {
                    boardElements[elementDiagonallyRightBelow(dimension, position, positionRightBelow)] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            positionRightBelow++;
            attackPlacesOnTheRight--;
        }
    }

    void placeDiagonallyAboveRight(int dimension, char[] boardElements, int position, int attackPlacesOnTheRight) {
        int positionRightAbove = 1;

        while (attackPlacesOnTheRight > 0) {
            if (isPossibleToPlaceDiagRightAbove(position, dimension, positionRightAbove)) {
                if (isBoardElementEmpty(boardElements[elementDiagonallyRightAbove(dimension, position, positionRightAbove)])) {
                    boardElements[elementDiagonallyRightAbove(dimension, position, positionRightAbove)] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            positionRightAbove++;
            attackPlacesOnTheRight--;
        }
    }

    void placeDiagonallyAboveLeft(int dimension, char[] boardElements, int position, int attackPlacesOnTheLeft) {
        int positionLeftAbove = 1;

        while (attackPlacesOnTheLeft > 0) {
            if (isPossibleToPlaceDiagLeftAbove(position, dimension, positionLeftAbove)) {
                if (isBoardElementEmpty(boardElements[elementDiagonallyLeftAbove(dimension, position, positionLeftAbove)])) {
                    boardElements[elementDiagonallyLeftAbove(dimension, position, positionLeftAbove)] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            positionLeftAbove++;
            attackPlacesOnTheLeft--;
        }
    }

    void placeHorizontallyRight(int dimension, char[] boardElements, int position, int rightPosition) {
        while (rightPosition > 0) {
            if (isPossibleToPlaceRight(position % dimension + rightPosition, dimension)) {
                if (isBoardElementEmpty(boardElements[position + rightPosition])) {
                    boardElements[position + rightPosition] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            rightPosition--;
        }
    }

    void placeHorizontallyLeft(int dimension, char[] boardElements, int position, int leftPosition) {
        while (leftPosition > 0) {
            if (isPossibleToPlaceLeft(position % dimension - leftPosition)) {
                if (isBoardElementEmpty(boardElements[position - leftPosition])) {
                    boardElements[position - leftPosition] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            leftPosition--;
        }
    }

    void placeVerticallyAbove(int dimension, char[] boardElements, int position, int numberOfLinesAbove) {
        while (numberOfLinesAbove > 0) {
            if (isPossibleToPlaceOnPreviousLine(elementVerticallyAbove(dimension, position, numberOfLinesAbove))) {

                if (isBoardElementEmpty(boardElements[elementVerticallyAbove(dimension, position, numberOfLinesAbove)])) {
                    boardElements[elementVerticallyAbove(dimension, position, numberOfLinesAbove)] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            numberOfLinesAbove--;
        }
    }

    void placeVerticallyBelow(int dimension, char[] boardElements, int position, int numberOfLinesBelow) {
        while (numberOfLinesBelow > 0) {
            if (isPossibleToPlaceOnNextLine(boardElements, elementVerticallyBelow(dimension, position, numberOfLinesBelow))) {
                if (isBoardElementEmpty(boardElements[elementVerticallyBelow(dimension, position, numberOfLinesBelow)])) {
                    boardElements[elementVerticallyBelow(dimension, position, numberOfLinesBelow)] = FIELD_UNDER_ATTACK_CHAR;
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
        return elementDiagonallyLeftAbove(dimension, position, positionLeftAbove) >= 0
                && (elementDiagonallyLeftAbove(dimension, position, positionLeftAbove)) % dimension >= 0;
    }

    private boolean isPossibleToPlaceDiagRightAbove(int position, int dimension, int positionRightAbove) {
        return elementDiagonallyRightAbove(dimension, position, positionRightAbove) >= 0
                && (elementDiagonallyRightAbove(dimension, position, positionRightAbove)) % dimension < dimension - 1;
    }

    private boolean isPossibleToPlaceDiagLeftBelow(int boardElementsLength, int position, int dimension, int positionLeftBelow) {
        return (elementDiagonallyLeftBelow(dimension, position, positionLeftBelow)) % dimension < dimension
                && elementDiagonallyLeftBelow(dimension, position, positionLeftBelow) < boardElementsLength;
    }

    private boolean isPossibleToPlaceDiagRightBelow(int boardElementsLength, int position, int dimension, int positionRightBelow) {
        return elementDiagonallyRightBelow(dimension, position, positionRightBelow) < boardElementsLength
                && (elementDiagonallyRightBelow(dimension, position, positionRightBelow)) % dimension < dimension;
    }

    private int elementDiagonallyLeftBelow(int dimension, int position, int positionLeftBelow) {
        return elementVerticallyBelow(dimension, position, positionLeftBelow) - positionLeftBelow;
    }

    private int elementDiagonallyRightBelow(int dimension, int position, int positionRightBelow) {
        return position + dimension * positionRightBelow + positionRightBelow;
    }

    private int elementDiagonallyRightAbove(int dimension, int position, int positionRightAbove) {
        return elementVerticallyAbove(dimension, position, positionRightAbove) + positionRightAbove;
    }

    private int elementDiagonallyLeftAbove(int dimension, int position, int positionLeftAbove) {
        return position - dimension * positionLeftAbove - positionLeftAbove;
    }

    private int elementVerticallyAbove(int dimension, int position, int numberOfLinesAbove) {
        return position - dimension * numberOfLinesAbove;
    }

    private boolean isBoardElementEmpty(char boardElement) {
        return boardElement != NEXT_LINE_FIELD_CHAR && boardElement == EMPTY_FIELD_CHAR;
    }

    private int elementVerticallyBelow(int dimension, int position, int numberOfLinesBelow) {
        return position + dimension * numberOfLinesBelow;
    }
}
