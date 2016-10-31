package org.xander.chessboard.figuresPlacement;

import java.util.Set;

import static org.xander.chessboard.figures.Figure.ROOK;
import static org.xander.chessboard.figuresPlacement.BoardUtils.checkBoard;

public class RooksPlacement extends PerpendicularFiguresPlacement {
    @Override
    public Set<String> placeCertainFigureOnBoard(String board) {
        return placeFigureOnBoard(ROOK.getFigure(), board);
    }

    @Override
    public String calculateAttackPlaces(String board) {
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = (int) Math.sqrt(board.length()) + 1;
        checkBoard(board, dimension);

        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] == ROOK.getFigure()) {
                placeHorizontally(boardElements, i, dimension);
                placeVertically(boardElements, i, dimension);
            }
        }
        return BoardUtils.transformArrayToString(boardElements);
    }
}
