package org.xander.chessboard.figuresPlacement;

public interface PerpendicularPlacement {
    void placeHorizontally(char[] boardElements, int position, int dimension);
    void placeVertically(char[] boardElements, int position, int dimension);
}
