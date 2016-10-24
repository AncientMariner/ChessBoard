package org.xander.chessboard;

import static org.xander.chessboard.figures.Figure.ROOK;

public class RooksPlacement extends PerpendicularFiguresPlacement {
    @Override
    public String placeOneFigureOnBoardSequentially(String board) {
        return placeFigureOnBoard(ROOK.getFigure(), board);
    }

    @Override
    public String calculateAttackPlaces(String board) {
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = (int) Math.sqrt(board.length()) + 1;

        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] == ROOK.getFigure()) {
                placeHorizontally(boardElements, i, dimension);
                placeVertically(boardElements, i, dimension);
            }
        }
        return boardUtils.transformArrayToStringBuilder(boardElements);
    }
}
