package org.xander.chessboard;

import static org.xander.chessboard.Figure.*;

public class KingsPlacement extends FiguresPlacement {
    private final Chessboard chessboard;

    public KingsPlacement(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public String placeOneFigureOnBoardSequentially(String board) {
        return placeFigureOnBoard(KING.getFigure(), board);
    }

    @Override
    public String calculateAttackPlaces(String board) {
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = chessboard.getDimension() + 1;
        if (board.isEmpty() || board.length() % dimension != 0) {
            throw new IllegalStateException("There is something wrong with your board");
        }

        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] == KING.getFigure()) {
                placeHorizontally(boardElements, i, dimension);
                placeVertically(boardElements, i, dimension);
                placeDiagonallyAbove(boardElements, i, dimension);
                placeDiagonallyBelow(boardElements, i, dimension);
            }
        }
        return boardUtils.transformArrayToStringBuilder(boardElements);
    }

    private void placeHorizontally(char[] boardElements, int position, int dimension) {
        if (position % dimension + 1 < dimension) {
            if (boardElements[position + 1] != '\n' && boardElements[position + 1] == '.') {
                boardElements[position + 1] = 'x';
            }
        }
        if (position % dimension - 1 >= 0) {
            if (boardElements[position - 1] != '\n' && boardElements[position - 1] == '.') {
                boardElements[position - 1] = 'x';
            }
        }
    }

    private void placeVertically(char[] boardElements, int position, int dimension) {
        if (position + dimension < boardElements.length) {
            if (boardElements[position + dimension] != '\n' && boardElements[position + dimension] == '.') {
                boardElements[position + dimension] = 'x';
            }
        }

        if (position - dimension >= 0) {
            if (boardElements[position - dimension] != '\n' && boardElements[position - dimension] == '.') {
                boardElements[position - dimension] = 'x';
            }
        }
    }

    private void placeDiagonallyAbove(char[] boardElements, int position, int dimension) {
        if (position - dimension - 1 >= 0 && (position - dimension - 1) % dimension >= 0 ) {
            if (boardElements[position - dimension - 1] != '\n'
                    && boardElements[position - dimension - 1] == '.') {
                boardElements[position - dimension - 1] = 'x';
            }
        }

        if (position - dimension + 1 >= 0 && (position - dimension + 1) % dimension < dimension - 1) {
            if (boardElements[position - dimension + 1] != '\n'
                    && boardElements[position - dimension + 1] == '.') {
                boardElements[position - dimension + 1] = 'x';
            }
        }
    }

    private void placeDiagonallyBelow(char[] boardElements, int position, int dimension) {
        if((position + dimension - 1) % dimension < dimension
                && position + dimension - 1 < boardElements.length) {
            if (boardElements[position + dimension - 1] != '\n'
                    && boardElements[position + dimension - 1] == '.') {
                boardElements[position + dimension - 1] = 'x';
            }
        }

        if(position + dimension + 1 < boardElements.length
                && (position + dimension + 1) % dimension < dimension) {
            if (boardElements[position + dimension + 1] != '\n'
                    && boardElements[position + dimension + 1] == '.') {
                boardElements[position + dimension + 1] = 'x';
            }
        }
    }
}
