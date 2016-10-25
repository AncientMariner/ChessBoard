package org.xander.chessboard.figuresPlacement;

public interface DiagonalPlacement {
    void placeDiagonallyAbove(char[] boardElements, int position, int dimension);
    void placeDiagonallyBelow(char[] boardElements, int position, int dimension);
}
