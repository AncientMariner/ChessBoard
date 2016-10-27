package org.xander.chessboard.figuresPlacement;

import java.util.Set;

import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figuresPlacement.BoardUtils.checkBoard;

public class BishopsPlacement extends DiagonalFiguresPlacement {
    @Override
    public Set<String> placeCertainFigureOnBoard(String board) {
        return placeFigureOnBoard(BISHOP.getFigure(), board);
    }

    @Override
    public String calculateAttackPlaces(String board) {
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = (int) Math.sqrt(board.length()) + 1;
        checkBoard(board, dimension);

        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] == BISHOP.getFigure()) {
                placeDiagonallyAbove(boardElements, i, dimension);
                placeDiagonallyBelow(boardElements, i, dimension);
            }
        }
        return boardUtils.transformArrayToStringBuilder(boardElements);
    }
}
