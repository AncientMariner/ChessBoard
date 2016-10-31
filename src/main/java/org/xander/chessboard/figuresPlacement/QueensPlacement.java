package org.xander.chessboard.figuresPlacement;

import java.util.Set;

import static org.xander.chessboard.figures.Figure.QUEEN;
import static org.xander.chessboard.figuresPlacement.BoardUtils.checkBoard;

public class QueensPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    @Override
    public Set<String> placeCertainFigureOnBoard(String board) {
        return placeFigureOnBoard(QUEEN.getFigure(), board);
    }

    @Override
    public String calculateAttackPlaces(String board) {
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = (int) Math.sqrt(board.length()) + 1;
        checkBoard(board, dimension);


        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] == QUEEN.getFigure()) {
                placeHorizontally(boardElements, i, dimension);
                placeVertically(boardElements, i, dimension);
                placeDiagonallyAbove(boardElements, i, dimension);
                placeDiagonallyBelow(boardElements, i, dimension);
            }
        }
        return BoardUtils.transformArrayToString(boardElements);
    }
}
