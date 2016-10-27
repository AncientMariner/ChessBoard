package org.xander.chessboard.figuresPlacement;

import java.util.Set;

import static org.xander.chessboard.figures.Figure.KING;
import static org.xander.chessboard.figuresPlacement.BoardUtils.checkBoard;

public class KingsPlacement extends FiguresPlacement {
    @Override
    public Set<String> placeCertainFigureOnBoard(String board) {
        return placeFigureOnBoard(KING.getFigure(), board);
    }

    @Override
    public String calculateAttackPlaces(String board) {
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = (int) Math.sqrt(board.length()) + 1;
        checkBoard(board, dimension);

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
            if (boardElements[position + 1] != NEXT_LINE_FIELD && boardElements[position + 1] == EMPTY_FIELD_CHAR) {
                boardElements[position + 1] = FIELD_UNDER_ATTACK;
            }
        }
        if (position % dimension - 1 >= 0) {
            if (boardElements[position - 1] != NEXT_LINE_FIELD && boardElements[position - 1] == EMPTY_FIELD_CHAR) {
                boardElements[position - 1] = FIELD_UNDER_ATTACK;
            }
        }
    }

    private void placeVertically(char[] boardElements, int position, int dimension) {
        if (position + dimension < boardElements.length) {
            if (boardElements[position + dimension] != NEXT_LINE_FIELD && boardElements[position + dimension] == EMPTY_FIELD_CHAR) {
                boardElements[position + dimension] = FIELD_UNDER_ATTACK;
            }
        }

        if (position - dimension >= 0) {
            if (boardElements[position - dimension] != NEXT_LINE_FIELD && boardElements[position - dimension] == EMPTY_FIELD_CHAR) {
                boardElements[position - dimension] = FIELD_UNDER_ATTACK;
            }
        }
    }

    private void placeDiagonallyAbove(char[] boardElements, int position, int dimension) {
        if (position - dimension - 1 >= 0 && (position - dimension - 1) % dimension >= 0 ) {
            if (boardElements[position - dimension - 1] != NEXT_LINE_FIELD
                    && boardElements[position - dimension - 1] == EMPTY_FIELD_CHAR) {
                boardElements[position - dimension - 1] = FIELD_UNDER_ATTACK;
            }
        }

        if (position - dimension + 1 >= 0 && (position - dimension + 1) % dimension < dimension - 1) {
            if (boardElements[position - dimension + 1] != NEXT_LINE_FIELD
                    && boardElements[position - dimension + 1] == EMPTY_FIELD_CHAR) {
                boardElements[position - dimension + 1] = FIELD_UNDER_ATTACK;
            }
        }
    }

    private void placeDiagonallyBelow(char[] boardElements, int position, int dimension) {
        if((position + dimension - 1) % dimension < dimension
                && position + dimension - 1 < boardElements.length) {
            if (boardElements[position + dimension - 1] != NEXT_LINE_FIELD
                    && boardElements[position + dimension - 1] == EMPTY_FIELD_CHAR) {
                boardElements[position + dimension - 1] = FIELD_UNDER_ATTACK;
            }
        }

        if(position + dimension + 1 < boardElements.length
                && (position + dimension + 1) % dimension < dimension) {
            if (boardElements[position + dimension + 1] != NEXT_LINE_FIELD
                    && boardElements[position + dimension + 1] == EMPTY_FIELD_CHAR) {
                boardElements[position + dimension + 1] = FIELD_UNDER_ATTACK;
            }
        }
    }
}
