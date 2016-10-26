package org.xander.chessboard.figures;

public enum Figure {
    // to avoid a conflict with king in names - knight is taken by "n" char
    KNIGHT("n"),
    ROOK("r"),
    BISHOP("b"),
    QUEEN("q"),
    KING("k");

    private final String figure;

    Figure(String figure) {
        this.figure = figure;
    }

    public char getFigure() {
        return figure.charAt(0);
    }

    public String getFigureAsString() {
        return figure;
    }
}
