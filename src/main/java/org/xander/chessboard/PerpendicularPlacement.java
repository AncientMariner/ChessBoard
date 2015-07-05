package org.xander.chessboard;

public interface PerpendicularPlacement {
    void placeHorizontally(char[] boardElements, int position, int dimension);
    void placeVertically(char[] boardElements, int position, int dimension);
}
