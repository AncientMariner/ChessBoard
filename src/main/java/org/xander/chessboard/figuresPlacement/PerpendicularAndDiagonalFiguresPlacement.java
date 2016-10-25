package org.xander.chessboard.figuresPlacement;

public abstract class PerpendicularAndDiagonalFiguresPlacement extends FiguresPlacement implements DiagonalPlacement, PerpendicularPlacement {
    public void placeHorizontally(char[] boardElements, int position, int dimension) {
        if (position % dimension < dimension) {
            int rightPosition = 1;
            while (position % dimension + rightPosition < dimension) {
                if (boardElements[position + rightPosition] != NEXT_LINE_FIELD
                        && boardElements[position + rightPosition] == EMPTY_FIELD) {
                    boardElements[position + rightPosition] = FIELD_UNDER_ATTACK;
                }
                rightPosition++;
            }
        }
        if (position % dimension > 0 && position % dimension <= dimension) {
            int leftPosition = 1;
            while (position % dimension - leftPosition >= 0) {
                if (boardElements[position - leftPosition] != NEXT_LINE_FIELD
                        && boardElements[position - leftPosition] == EMPTY_FIELD) {
                    boardElements[position - leftPosition] = FIELD_UNDER_ATTACK;
                }
                leftPosition++;
            }
        }
    }

    public void placeVertically(char[] boardElements, int position, int dimension) {
        if (position + dimension < boardElements.length) {
            int numberOfLinesBelow = 1;
            while (position + dimension * numberOfLinesBelow < boardElements.length) {
                if (boardElements[position + dimension * numberOfLinesBelow] != NEXT_LINE_FIELD
                        && boardElements[position + dimension * numberOfLinesBelow] == EMPTY_FIELD) {
                    boardElements[position + dimension * numberOfLinesBelow] = FIELD_UNDER_ATTACK;
                }
                numberOfLinesBelow++;
            }
        }

        if (position - dimension >= 0) {
            int numberOfLinesAbove = 1;
            while (position - dimension * numberOfLinesAbove >= 0) {
                if (boardElements[position - dimension * numberOfLinesAbove] != NEXT_LINE_FIELD
                        && boardElements[position - dimension * numberOfLinesAbove] == EMPTY_FIELD) {
                    boardElements[position - dimension * numberOfLinesAbove] = FIELD_UNDER_ATTACK;
                }
                numberOfLinesAbove++;
            }
        }
    }

    public void placeDiagonallyAbove(char[] boardElements,int position, int dimension){
        if (position - dimension > 0) {
            int positionLeftAbove = 1;
            int attackPlacesOnTheLeft = position % dimension;
            while(attackPlacesOnTheLeft > 0) {
                if (position - dimension * positionLeftAbove - positionLeftAbove >= 0
                        && (position - dimension * positionLeftAbove - positionLeftAbove) % dimension >= 0 ) {
                    if (boardElements[position - dimension * positionLeftAbove - positionLeftAbove] != NEXT_LINE_FIELD
                            && boardElements[position - dimension * positionLeftAbove - positionLeftAbove] == EMPTY_FIELD) {
                        boardElements[position - dimension * positionLeftAbove - positionLeftAbove] = FIELD_UNDER_ATTACK;
                    }
                }
                positionLeftAbove++;
                attackPlacesOnTheLeft--;
            }

            int positionRightAbove = 1;
            int attackPlacesOnTheRight = dimension - position % dimension - 2;
            while(attackPlacesOnTheRight > 0) {
                if (position - dimension * positionRightAbove + positionRightAbove >= 0
                        && (position - dimension * positionRightAbove + positionRightAbove) % dimension < dimension - 1) {
                    if (boardElements[position - dimension * positionRightAbove + positionRightAbove] != NEXT_LINE_FIELD
                            && boardElements[position - dimension * positionRightAbove + positionRightAbove] == EMPTY_FIELD) {
                        boardElements[position - dimension * positionRightAbove + positionRightAbove] = FIELD_UNDER_ATTACK;
                    }
                }
                positionRightAbove++;
                attackPlacesOnTheRight--;
            }
        }
    }

    public void placeDiagonallyBelow(char[] boardElements,int position, int dimension){
        if (position + dimension < boardElements.length) {
            int positionLeftBelow = 1;
            int attackPlacesOnTheLeft = position % dimension;
            while(attackPlacesOnTheLeft > 0) {
                if((position + dimension * positionLeftBelow - positionLeftBelow) % dimension < dimension
                        && position + dimension * positionLeftBelow - positionLeftBelow < boardElements.length) {
                    if (boardElements[position + dimension * positionLeftBelow - positionLeftBelow] != NEXT_LINE_FIELD
                            && boardElements[position + dimension * positionLeftBelow - positionLeftBelow] == EMPTY_FIELD) {
                        boardElements[position + dimension * positionLeftBelow - positionLeftBelow] = FIELD_UNDER_ATTACK;
                    }
                }
                positionLeftBelow++;
                attackPlacesOnTheLeft--;
            }

            int positionRightBelow = 1;
            int attackPlacesOnTheRight = dimension - position % dimension - 2;
            while(attackPlacesOnTheRight > 0) {
                if(position + dimension * positionRightBelow + positionRightBelow < boardElements.length
                        && (position + dimension * positionRightBelow + positionRightBelow) % dimension < dimension) {
                    if (boardElements[position + dimension * positionRightBelow + positionRightBelow] != NEXT_LINE_FIELD
                            && boardElements[position + dimension * positionRightBelow + positionRightBelow] == EMPTY_FIELD) {
                        boardElements[position + dimension * positionRightBelow + positionRightBelow] = FIELD_UNDER_ATTACK;
                    }
                }
                positionRightBelow++;
                attackPlacesOnTheRight--;
            }
        }
    }
}
