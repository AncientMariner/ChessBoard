package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figuresPlacement.BoardUtils.isBoardElementAnotherFigure;
import static org.xander.chessboard.figuresPlacement.BoardUtils.isBoardElementEmpty;
public abstract class PerpendicularAndDiagonalFiguresPlacement extends FiguresPlacement implements DiagonalPlacement, PerpendicularPlacement {
    void perpendicularAttackPlacement(int position, int dimension, char[] boardElements) {
        attackPlaceHorizontallyRight(position, boardElements, dimension, attackPlacesOnTheRight(position, dimension));
        attackPlaceHorizontallyLeft(position, boardElements, dimension, attackPlacesOnTheLeft(position, dimension));
        attackPlaceVerticallyAbove(position, boardElements, dimension, numberOfLinesAbove(position, dimension));
        attackPlaceVerticallyBelow(position, boardElements, dimension, numberOfLinesBelow(position, dimension, boardElements));
    }

    boolean isPerpendicularAttackPlacementNotHarming(int position, int dimension, char[] boardElements) {
        return isAttackPlaceHorizontallyRightNotHarming(position, boardElements, dimension, attackPlacesOnTheRight(position, dimension))
                && isAttackPlaceHorizontallyLeftNotHarming(position, boardElements, dimension, attackPlacesOnTheLeft(position, dimension))
                && isAttackPlaceVerticallyAboveNotHarming(position, boardElements, dimension, numberOfLinesAbove(position, dimension))
                && isAttackPlaceVerticallyBelowNotHarming(position, boardElements, dimension, numberOfLinesBelow(position, dimension, boardElements));
    }

    void diagonalAttackPlacement(int position, int dimension, char[] boardElements) {
        attaclkPlaceDiagonallyAboveLeft(position, boardElements, dimension, attackPlacesOnTheLeft(position, dimension));
        attackPlaceDiagonallyAboveRight(position, boardElements, dimension, attackPlacesOnTheRight(position, dimension));
        attackPlaceDiagonallyBelowLeft(position, boardElements, dimension, attackPlacesOnTheLeft(position, dimension));
        attackPlaceDiagonallyBelowRight(position, boardElements, dimension, attackPlacesOnTheRight(position, dimension));
    }

    boolean isDiagonalAttackPlacementNotHarming(int position, char[] boardElements, int dimension) {
        return isAttackPlaceDiagonallyAboveLeftNotHarming(position, boardElements, dimension, attackPlacesOnTheLeft(position, dimension))
            && isAttackPlaceDiagonallyAboveRightNotHarming(position, boardElements, dimension, attackPlacesOnTheRight(position, dimension))
            && isAttackPlaceDiagonallyBelowLeftNotHarming(position, boardElements, dimension, attackPlacesOnTheLeft(position, dimension))
            && isAttackPlaceDiagonallyBelowRightNotHarming(position, boardElements, dimension, attackPlacesOnTheRight(position, dimension));
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

    void attackPlaceDiagonallyBelowLeft(int position, char[] boardElements, int dimension, int attackPlacesOnTheLeft) {
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

  boolean isAttackPlaceDiagonallyBelowLeftNotHarming(int position, char[] boardElements, int dimension, int attackPlacesOnTheLeft) {
        int positionLeftBelow = 1;

        while (attackPlacesOnTheLeft > 0) {
            if (isPossibleToPlaceDiagLeftBelow(boardElements.length, position, dimension, positionLeftBelow)) {
                if (isBoardElementAnotherFigure(boardElements[elementDiagonallyLeftBelow(dimension, position, positionLeftBelow)])) {
                    return false;
                }
            }
            positionLeftBelow++;
            attackPlacesOnTheLeft--;
        }
        return true;
    }

    void attackPlaceDiagonallyBelowRight(int position, char[] boardElements, int dimension, int attackPlacesOnTheRight) {
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

    boolean isAttackPlaceDiagonallyBelowRightNotHarming(int position, char[] boardElements, int dimension, int attackPlacesOnTheRight) {
        int positionRightBelow = 1;

        while (attackPlacesOnTheRight > 0) {
            if (isPossibleToPlaceDiagRightBelow(boardElements.length, position, dimension, positionRightBelow)) {
                if (isBoardElementAnotherFigure(boardElements[elementDiagonallyRightBelow(dimension, position, positionRightBelow)])) {
                    return false;
                }
            }
            positionRightBelow++;
            attackPlacesOnTheRight--;
        }
        return true;
    }

    void attackPlaceDiagonallyAboveRight(int position, char[] boardElements, int dimension, int attackPlacesOnTheRight) {
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

    boolean isAttackPlaceDiagonallyAboveRightNotHarming(int position, char[] boardElements, int dimension, int attackPlacesOnTheRight) {
        int positionRightAbove = 1;

        while (attackPlacesOnTheRight > 0) {
            if (isPossibleToPlaceDiagRightAbove(position, dimension, positionRightAbove)) {
                if (isBoardElementAnotherFigure(boardElements[elementDiagonallyRightAbove(dimension, position, positionRightAbove)])) {
                    return false;
                }
            }
            positionRightAbove++;
            attackPlacesOnTheRight--;
        }
        return true;
    }

    void attaclkPlaceDiagonallyAboveLeft(int position, char[] boardElements, int dimension, int attackPlacesOnTheLeft) {
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

    boolean isAttackPlaceDiagonallyAboveLeftNotHarming(int position, char[] boardElements, int dimension, int attackPlacesOnTheLeft) {
        int positionLeftAbove = 1;

        while (attackPlacesOnTheLeft > 0) {
            if (isPossibleToPlaceDiagLeftAbove(position, dimension, positionLeftAbove)) {
                char boardElement = boardElements[elementDiagonallyLeftAbove(dimension, position, positionLeftAbove)];
                if (isBoardElementAnotherFigure(boardElement)) {
                    return false;
                }
            }
            positionLeftAbove++;
            attackPlacesOnTheLeft--;
        }
        return true;
    }

    void attackPlaceHorizontallyRight(int position, char[] boardElements, int dimension, int rightPosition) {
        while (rightPosition > 0) {
            if (isPossibleToPlaceRight(position, dimension, rightPosition)) {
                if (isBoardElementEmpty(boardElements[position + rightPosition])) {
                    boardElements[position + rightPosition] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            rightPosition--;
        }
    }

    boolean isAttackPlaceHorizontallyRightNotHarming(int position, char[] boardElements, int dimension, int rightPosition) {
        while (rightPosition > 0) {
            if (isPossibleToPlaceRight(position, dimension, rightPosition)) {
                if (isBoardElementAnotherFigure(boardElements[position + rightPosition])) {
                    return false;
                }
            }
            rightPosition--;
        }
        return true;
    }

    void attackPlaceHorizontallyLeft(int position, char[] boardElements, int dimension, int leftPosition) {
        while (leftPosition > 0) {
            if (isPossibleToPlaceLeft(position, dimension, leftPosition)) {
                if (isBoardElementEmpty(boardElements[position - leftPosition])) {
                    boardElements[position - leftPosition] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            leftPosition--;
        }
    }

    boolean isAttackPlaceHorizontallyLeftNotHarming(int position, char[] boardElements, int dimension, int leftPosition) {
        while (leftPosition > 0) {
            if (isPossibleToPlaceLeft(position, dimension, leftPosition)) {
                if (isBoardElementAnotherFigure(boardElements[position - leftPosition])) {
                    return false;
                }
            }
            leftPosition--;
        }
        return true;
    }

    void attackPlaceVerticallyAbove(int position, char[] boardElements, int dimension, int numberOfLinesAbove) {
        while (numberOfLinesAbove > 0) {
            if (isPossibleToPlaceOnPreviousLine(elementVerticallyAbove(dimension, position, numberOfLinesAbove))) {
                if (isBoardElementEmpty(boardElements[elementVerticallyAbove(dimension, position, numberOfLinesAbove)])) {
                    boardElements[elementVerticallyAbove(dimension, position, numberOfLinesAbove)] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            numberOfLinesAbove--;
        }
    }

    boolean isAttackPlaceVerticallyAboveNotHarming(int position, char[] boardElements, int dimension, int numberOfLinesAbove) {
        while (numberOfLinesAbove > 0) {
            if (isPossibleToPlaceOnPreviousLine(elementVerticallyAbove(dimension, position, numberOfLinesAbove))) {
                if (isBoardElementAnotherFigure(boardElements[elementVerticallyAbove(dimension, position, numberOfLinesAbove)])) {
                    return false;
                }
            }
            numberOfLinesAbove--;
        }
        return true;
    }

    void attackPlaceVerticallyBelow(int position, char[] boardElements, int dimension, int numberOfLinesBelow) {
        while (numberOfLinesBelow > 0) {
            if (isPossibleToPlaceOnNextLine(boardElements, elementVerticallyBelow(dimension, position, numberOfLinesBelow))) {
                if (isBoardElementEmpty(boardElements[elementVerticallyBelow(dimension, position, numberOfLinesBelow)])) {
                    boardElements[elementVerticallyBelow(dimension, position, numberOfLinesBelow)] = FIELD_UNDER_ATTACK_CHAR;
                }
            }
            numberOfLinesBelow--;
        }
    }

    boolean isAttackPlaceVerticallyBelowNotHarming(int position, char[] boardElements, int dimension, int numberOfLinesBelow) {
        while (numberOfLinesBelow > 0) {
            if (isPossibleToPlaceOnNextLine(boardElements, elementVerticallyBelow(dimension, position, numberOfLinesBelow))) {
                if (isBoardElementAnotherFigure(boardElements[elementVerticallyBelow(dimension, position, numberOfLinesBelow)])) {
                    return false;
                }
            }
            numberOfLinesBelow--;
        }
        return true;
    }

    boolean isPossibleToPlaceOnNextLine(char[] boardElements, int position) {
        return position < boardElements.length;
    }

    boolean isPossibleToPlaceOnPreviousLine(int position) {
        return position >= 0;
    }

    private boolean isPossibleToPlaceRight(int position, int dimension, int rightPosition) {
        return position % dimension + rightPosition < dimension;
    }

    private boolean isPossibleToPlaceLeft(int position, int dimension, int leftPosition) {
        return position % dimension - leftPosition >= 0;
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

    private int elementVerticallyBelow(int dimension, int position, int numberOfLinesBelow) {
        return position + dimension * numberOfLinesBelow;
    }
}
