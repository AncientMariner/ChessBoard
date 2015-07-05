package org.xander.chessboard;

public abstract class PerpendicularAndDiagonalFiguresPlacement extends FiguresPlacement implements DiagonalPlacement, PerpendicularPlacement {
    public void placeHorizontally(char[] boardElements, int position, int dimension) {
        if (position % dimension < dimension) {
            int rightPosition = 1;
            while (position % dimension + rightPosition < dimension) {
                if (boardElements[position + rightPosition] != '\n'
                        && boardElements[position + rightPosition] == '.') {
                    boardElements[position + rightPosition] = 'x';
                }
                rightPosition++;
            }
        }
        if (position % dimension > 0 && position % dimension <= dimension) {
            int leftPosition = 1;
            while (position % dimension - leftPosition >= 0) {
                if (boardElements[position - leftPosition] != '\n'
                        && boardElements[position - leftPosition] == '.') {
                    boardElements[position - leftPosition] = 'x';
                }
                leftPosition++;
            }
        }
    }

    public void placeVertically(char[] boardElements, int position, int dimension) {
        if (position + dimension < boardElements.length) {
            int numberOfLinesBelow = 1;
            while (position + dimension * numberOfLinesBelow < boardElements.length) {
                if (boardElements[position + dimension * numberOfLinesBelow] != '\n'
                        && boardElements[position + dimension * numberOfLinesBelow] == '.') {
                    boardElements[position + dimension * numberOfLinesBelow] = 'x';
                }
                numberOfLinesBelow++;
            }
        }

        if (position - dimension >= 0) {
            int numberOfLinesAbove = 1;
            while (position - dimension * numberOfLinesAbove >= 0) {
                if (boardElements[position - dimension * numberOfLinesAbove] != '\n'
                        && boardElements[position - dimension * numberOfLinesAbove] == '.') {
                    boardElements[position - dimension * numberOfLinesAbove] = 'x';
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
                    if (boardElements[position - dimension * positionLeftAbove - positionLeftAbove] != '\n'
                            && boardElements[position - dimension * positionLeftAbove - positionLeftAbove] == '.') {
                        boardElements[position - dimension * positionLeftAbove - positionLeftAbove] = 'x';
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
                    if (boardElements[position - dimension * positionRightAbove + positionRightAbove] != '\n'
                            && boardElements[position - dimension * positionRightAbove + positionRightAbove] == '.') {
                        boardElements[position - dimension * positionRightAbove + positionRightAbove] = 'x';
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
                    if (boardElements[position + dimension * positionLeftBelow - positionLeftBelow] != '\n'
                            && boardElements[position + dimension * positionLeftBelow - positionLeftBelow] == '.') {
                        boardElements[position + dimension * positionLeftBelow - positionLeftBelow] = 'x';
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
                    if (boardElements[position + dimension * positionRightBelow + positionRightBelow] != '\n'
                            && boardElements[position + dimension * positionRightBelow + positionRightBelow] == '.') {
                        boardElements[position + dimension * positionRightBelow + positionRightBelow] = 'x';
                    }
                }
                positionRightBelow++;
                attackPlacesOnTheRight--;
            }
        }
    }
}
