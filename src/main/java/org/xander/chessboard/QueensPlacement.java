package org.xander.chessboard;

import static org.xander.chessboard.Figure.QUEEN;

public class QueensPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    private final Chessboard chessboard;

    public QueensPlacement(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public String placeOneFigureOnBoardSequentially(String board) {
        return placeFigureOnBoard(QUEEN.getFigure(), board);
    }

    @Override
    public String calculateAttackPlaces(String board) {
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = chessboard.getDimension() + 1;
        chessboard.checkBoard(board, dimension);

        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] == QUEEN.getFigure()) {
                placeHorizontally(boardElements, i, dimension);
                placeVertically(boardElements, i, dimension);
                placeDiagonallyAbove(boardElements, i, dimension);
                placeDiagonallyBelow(boardElements, i, dimension);
            }
        }
        return boardUtils.transformArrayToStringBuilder(boardElements);
    }
}
