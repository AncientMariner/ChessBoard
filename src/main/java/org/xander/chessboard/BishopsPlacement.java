package org.xander.chessboard;

public class BishopsPlacement extends FiguresPlacement {
    private final Chessboard chessboard;

    public BishopsPlacement(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public String placeOneFigureOnBoardSequentially(String board) {
        return placeFigureOnBoard(bishop, board);
    }

    @Override
    public String calculateAttackPlaces(String board) {
        StringBuilder chessBoardWithFigures = new StringBuilder();
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = chessboard.getDimension() + 1;
        if (board.isEmpty() || board.length() % dimension != 0) {
            throw new IllegalStateException("There is something wrong with your board");
        }

        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] == bishop) {
                placeAbove(boardElements, i, dimension);
                placeBelow(boardElements, i, dimension);
            }
        }
        for (char element : boardElements) {
            chessBoardWithFigures.append(element);
        }
        return chessBoardWithFigures.toString();
    }

    private void placeAbove(char[] boardElements, int position, int dimension) {
        if (position - dimension > 0) {
            int positionLeftAbove = 1;
            int attackPlacesOnTheLeft = position % dimension;
            while(attackPlacesOnTheLeft > 0) {
                if (position - dimension * positionLeftAbove - positionLeftAbove >= 0
                        && (position - dimension * positionLeftAbove - positionLeftAbove) % dimension >= 0 ) {
                    if (boardElements[position - dimension * positionLeftAbove - positionLeftAbove] != '\n'
                            && boardElements[position - dimension * positionLeftAbove - positionLeftAbove] == '.') {
                        boardElements[position - dimension*positionLeftAbove - positionLeftAbove] = 'x';
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
                        boardElements[position - dimension*positionRightAbove + positionRightAbove] = 'x';
                    }
                }
                positionRightAbove++;
                attackPlacesOnTheRight--;
            }
        }
    }

    private void placeBelow(char[] boardElements, int position, int dimension) {
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
