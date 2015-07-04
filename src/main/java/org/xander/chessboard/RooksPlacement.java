package org.xander.chessboard;

import static org.xander.chessboard.Figure.ROOK;

public class RooksPlacement extends FiguresPlacement{
    private final Chessboard chessboard;

    public RooksPlacement(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public String placeOneFigureOnBoardSequentially(String board) {
        return placeFigureOnBoard(ROOK.getFigure(), board);
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
            if (boardElements[i] == ROOK.getFigure()) {
                placeHorizontally(boardElements, i, dimension);
                placeVertically(boardElements, i, dimension);
            }
        }

        return transformArrayToStringBuilder(boardElements);
    }

    private void placeHorizontally(char[] boardElements, int position, int dimension) {
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

    private void placeVertically(char[] boardElements, int position, int dimension) {
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
}
