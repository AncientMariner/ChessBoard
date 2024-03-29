package org.xander.chessboard.figuresPlacement;

import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figures.Figure.KING;
import static org.xander.chessboard.figures.Figure.KNIGHT;
import static org.xander.chessboard.figures.Figure.QUEEN;
import static org.xander.chessboard.figures.Figure.ROOK;
import static org.xander.chessboard.figuresPlacement.FiguresPlacement.EMPTY_FIELD_CHAR;
import static org.xander.chessboard.figuresPlacement.FiguresPlacement.NEXT_LINE_FIELD_CHAR;

public class BoardUtils {
    public static void isBoardLegal(String board, int dimension) {
        if (board == null || board.isEmpty() || board.length() % dimension != 0) {
            throw new IllegalStateException("There is something wrong with your board");
        }
    }

    static boolean isBoardElementEmpty(char boardElement) {
        return boardElement != NEXT_LINE_FIELD_CHAR && boardElement == EMPTY_FIELD_CHAR;
    }

    static boolean isBoardElementAnotherFigure(char boardElement) {
        return !isBoardElementEmpty(boardElement)
                && (boardElement == BISHOP.getFigure()
                || boardElement == ROOK.getFigure()
                || boardElement == KING.getFigure()
                || boardElement == KNIGHT.getFigure()
                || boardElement == QUEEN.getFigure());
    }
}