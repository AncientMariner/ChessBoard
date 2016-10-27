package org.xander.chessboard.figuresPlacement;

public abstract class DiagonalFiguresPlacement extends PerpendicularAndDiagonalFiguresPlacement {
    public void placeDiagonallyAbove(char[] boardElements, int position, int dimension){
        super.placeDiagonallyAbove(boardElements, position, dimension);
    }

    public void placeDiagonallyBelow(char[] boardElements, int position, int dimension){
        super.placeDiagonallyBelow(boardElements, position, dimension);
    }
}
