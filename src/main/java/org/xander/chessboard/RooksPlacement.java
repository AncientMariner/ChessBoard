package org.xander.chessboard;

public class RooksPlacement {
    Chessboard chessboard;

    public RooksPlacement(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public String placeNumberOfRooksOnBoard(int numberOfRooks, String board) {
        String boardWithRooks = board;
        while (numberOfRooks > 0) {
            boardWithRooks = placeOneRookSequentially(boardWithRooks);
            boardWithRooks = calculateRookAttackPlaces(boardWithRooks);
            numberOfRooks--;
        }
        return boardWithRooks;
    }

    public String placeOneRookSequentially(String board) {
        StringBuilder chessboardWithFigures = new StringBuilder();
        char[] boardElements = board.toCharArray();
        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] != '\n' && boardElements[i] == '.') {
                boardElements[i] = 'r';
                break;
            }
        }
        for (char element : boardElements) {
            chessboardWithFigures.append(element);
        }
        return chessboardWithFigures.toString();
    }

    public String calculateRookAttackPlaces(String board) {
        StringBuilder chessBoardWithFigures = new StringBuilder();
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = chessboard.getDimension() + 1;

        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] == 'r') {
                placeHorizontally(boardElements, i, dimension);
                placeVertically(boardElements, i, dimension);
            }
        }
        for (char element : boardElements) {
            chessBoardWithFigures.append(element);
        }
        return chessBoardWithFigures.toString();
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
