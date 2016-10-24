package org.xander.chessboard;

import static org.xander.chessboard.figures.Figure.*;

public class KingsPlacement extends FiguresPlacement {
    @Override
    public String placeOneFigureOnBoardSequentially(String board) {
        return placeFigureOnBoard(KING.getFigure(), board);
    }

    @Override
    public String calculateAttackPlaces(String board) {
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = (int) Math.sqrt(board.length()) + 1;
        Chessboard.checkBoard(board, dimension);

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
            if (boardElements[position + 1] != '\n' && boardElements[position + 1] == EMPTY_FIELD) {
                boardElements[position + 1] = FIELD_UNDER_ATTACK;
            }
        }
        if (position % dimension - 1 >= 0) {
            if (boardElements[position - 1] != '\n' && boardElements[position - 1] == EMPTY_FIELD) {
                boardElements[position - 1] = FIELD_UNDER_ATTACK;
            }
        }
    }

    private void placeVertically(char[] boardElements, int position, int dimension) {
        if (position + dimension < boardElements.length) {
            if (boardElements[position + dimension] != '\n' && boardElements[position + dimension] == EMPTY_FIELD) {
                boardElements[position + dimension] = FIELD_UNDER_ATTACK;
            }
        }

        if (position - dimension >= 0) {
            if (boardElements[position - dimension] != '\n' && boardElements[position - dimension] == EMPTY_FIELD) {
                boardElements[position - dimension] = FIELD_UNDER_ATTACK;
            }
        }
    }

    private void placeDiagonallyAbove(char[] boardElements, int position, int dimension) {
        if (position - dimension - 1 >= 0 && (position - dimension - 1) % dimension >= 0 ) {
            if (boardElements[position - dimension - 1] != '\n'
                    && boardElements[position - dimension - 1] == EMPTY_FIELD) {
                boardElements[position - dimension - 1] = FIELD_UNDER_ATTACK;
            }
        }

        if (position - dimension + 1 >= 0 && (position - dimension + 1) % dimension < dimension - 1) {
            if (boardElements[position - dimension + 1] != '\n'
                    && boardElements[position - dimension + 1] == EMPTY_FIELD) {
                boardElements[position - dimension + 1] = FIELD_UNDER_ATTACK;
            }
        }
    }

    private void placeDiagonallyBelow(char[] boardElements, int position, int dimension) {
        if((position + dimension - 1) % dimension < dimension
                && position + dimension - 1 < boardElements.length) {
            if (boardElements[position + dimension - 1] != '\n'
                    && boardElements[position + dimension - 1] == EMPTY_FIELD) {
                boardElements[position + dimension - 1] = FIELD_UNDER_ATTACK;
            }
        }

        if(position + dimension + 1 < boardElements.length
                && (position + dimension + 1) % dimension < dimension) {
            if (boardElements[position + dimension + 1] != '\n'
                    && boardElements[position + dimension + 1] == EMPTY_FIELD) {
                boardElements[position + dimension + 1] = FIELD_UNDER_ATTACK;
            }
        }
    }
}
