package org.xander.chessboard.figuresPlacement;

import org.xander.chessboard.Chessboard;

import java.util.Set;

import static org.xander.chessboard.figures.Figure.KNIGHT;

public class KnightsPlacement extends FiguresPlacement {
    @Override
    public Set<String> placeOneFigureOnBoardSequentially(String board) {
        return placeFigureOnBoard(KNIGHT.getFigure(), board);
    }

    @Override
    public String calculateAttackPlaces(String board) {
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = (int) Math.sqrt(board.length()) + 1;
        Chessboard.checkBoard(board, dimension);

        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] == KNIGHT.getFigure()) {
                placeRight(boardElements, i, dimension);
                placeBelow(boardElements, i, dimension);
                placeLeft(boardElements, i, dimension);
                placeTop(boardElements, i, dimension);
            }
        }
        return boardUtils.transformArrayToStringBuilder(boardElements);
    }

    private void placeTop(char[] boardElements, int position, int dimension) {
        if (position - dimension * 2 + 1 >= 0) {
            if (position % dimension + 1 < dimension)
                if (boardElements[position - dimension * 2 + 1] == EMPTY_FIELD)
                    boardElements[position - dimension * 2 + 1] = FIELD_UNDER_ATTACK;
        }
            if (position - dimension * 2 - 1 >= 0) {
                if (position % dimension - 1 >= 0)
                if (boardElements[position - dimension * 2 - 1] == EMPTY_FIELD)
                    boardElements[position - dimension * 2 - 1] = FIELD_UNDER_ATTACK;
            }
    }

    private void placeLeft(char[] boardElements, int position, int dimension) {
        if (position % dimension - 2 >= 0) {
            if (position + dimension < boardElements.length)
                if (boardElements[position + dimension - 2] == EMPTY_FIELD)
                    boardElements[position + dimension - 2] = FIELD_UNDER_ATTACK;
            if (position - dimension >= 0)
                if (boardElements[position - dimension - 2] == EMPTY_FIELD)
                    boardElements[position - dimension - 2] = FIELD_UNDER_ATTACK;
        }
    }

    private void placeBelow(char[] boardElements, int position, int dimension) {
        if (position + dimension * 2 + 1 < boardElements.length) {
            if (position % dimension + 1 < dimension)
                if (boardElements[position + dimension * 2 + 1] == EMPTY_FIELD)
                    boardElements[position + dimension * 2 + 1] = FIELD_UNDER_ATTACK;
        }
        if (position + dimension * 2 - 1 < boardElements.length){
            if (position % dimension - 1 >= 0) {
                if (boardElements[position + dimension * 2 - 1] == EMPTY_FIELD) {
                    boardElements[position + dimension * 2 - 1] = FIELD_UNDER_ATTACK;
                }
            }
        }
    }

    private void placeRight(char[] boardElements, int position, int dimension) {
        if (position % dimension + 2 < dimension) {
            if (position + dimension < boardElements.length)
                if (boardElements[position + dimension + 2] == EMPTY_FIELD)
                    boardElements[position + dimension + 2] = FIELD_UNDER_ATTACK;
            if (position - dimension >= 0)
                if (boardElements[position - dimension + 2] == EMPTY_FIELD)
                    boardElements[position - dimension + 2] = FIELD_UNDER_ATTACK;
        }
    }
}