package org.xander.chessboard;

import java.util.Set;

import static org.xander.chessboard.figures.Figure.QUEEN;

public class QueensPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    @Override
    public Set<String> placeOneFigureOnBoardSequentially(String board) {
        return placeFigureOnBoardRandomly(QUEEN.getFigure(), board);
    }

    @Override
    public String calculateAttackPlaces(String board) {
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = (int) Math.sqrt(board.length()) + 1;
        Chessboard.checkBoard(board, dimension);


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
