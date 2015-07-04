package org.xander.chessboard;

public enum Figure {
    // to avoid a conflict with king in names - knight is taken by 'n' char
    KNIGHT('n'),
    ROOK('r'),
    BISHOP('b'),
    QUEEN('q'),
    KING('k');

    private final char figure;

    Figure(char figure) {
        this.figure = figure;
    }

    public char getFigure() {
        return figure;
    }
}
