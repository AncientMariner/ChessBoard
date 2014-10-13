package org.xander.chessboard;

public class KnightsPlacement {
    private final Chessboard chessboard;

    public KnightsPlacement(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public String placeNumberOfKnightsOnBoard(int numberOfKnights, String board) {
        String boardWithKnights = board;
        while (numberOfKnights > 0) {
            boardWithKnights = placeOneKnightOnBoardSequentially(boardWithKnights);
            boardWithKnights = calculateKnightAttackPlaces(boardWithKnights);
            numberOfKnights--;
        }
        return boardWithKnights;
    }

    public String placeOneKnightOnBoardSequentially(String emptyBoard) {
        StringBuilder chessBoardWithFigures = new StringBuilder();
        char[] boardElements = emptyBoard.toCharArray();
        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] != '\n' && boardElements[i] == '.') {
                boardElements[i] = 'n';
                break;
            }
        }
        for (char element : boardElements) {
            chessBoardWithFigures.append(element);
        }
        return chessBoardWithFigures.toString();
    }

    public String calculateKnightAttackPlaces(String board) {
        StringBuilder chessBoardWithFigures = new StringBuilder();
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = chessboard.getDimension() + 1;

        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] == 'n') {
                placeRight(boardElements, i, dimension);
                placeBelow(boardElements, i, dimension);
                placeLeft(boardElements, i, dimension);
                placeTop(boardElements, i, dimension);
            }
        }
        for (char element : boardElements) {
            chessBoardWithFigures.append(element);
        }
        return chessBoardWithFigures.toString();
    }

    private void placeTop(char[] boardElements, int position, int dimension) {
        if (position - dimension * 2 >= 0) {
            if (position % dimension + 1 < dimension)
                if (boardElements[position - dimension * 2 + 1] == '.')
                    boardElements[position - dimension * 2 + 1] = 'x';
            if (position % dimension - 1 >= 0)
                if (boardElements[position - dimension * 2 - 1] == '.')
                    boardElements[position - dimension * 2 - 1] = 'x';
        }
    }

    private void placeLeft(char[] boardElements, int position, int dimension) {
        if (position % dimension - 2 >= 0) {
            if (position + dimension < boardElements.length)
                if (boardElements[position + dimension - 2] == '.')
                    boardElements[position + dimension - 2] = 'x';
            if (position - dimension >= 0)
                if (boardElements[position - dimension - 2] == '.')
                    boardElements[position - dimension - 2] = 'x';
        }
    }

    private void placeBelow(char[] boardElements, int position, int dimension) {
        if (position + dimension * 2 < boardElements.length) {
            if (position % dimension + 1 < dimension)
                if (boardElements[position + dimension * 2 + 1] == '.')
                    boardElements[position + dimension * 2 + 1] = 'x';
            if (position % dimension - 1 >= 0)
                if (boardElements[position + dimension * 2 - 1] == '.')
                    boardElements[position + dimension * 2 - 1] = 'x';
        }
    }

    private void placeRight(char[] boardElements, int position, int dimension) {
        if (position % dimension + 2 < dimension) {
            if (position + dimension < boardElements.length)
                if (boardElements[position + dimension + 2] == '.')
                    boardElements[position + dimension + 2] = 'x';
            if (position - dimension >= 0)
                if (boardElements[position - dimension + 2] == '.')
                    boardElements[position - dimension + 2] = 'x';
        }
    }
}