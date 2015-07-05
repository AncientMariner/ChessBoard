package org.xander.chessboard;

import static org.xander.chessboard.Figure.ROOK;

public class RooksPlacement extends PerpendicularFiguresPlacement {
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
        chessboard.checkBoard(board, dimension);

        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] == ROOK.getFigure()) {
                placeHorizontally(boardElements, i, dimension);
                placeVertically(boardElements, i, dimension);
            }
        }
        return boardUtils.transformArrayToStringBuilder(boardElements);
    }
}
