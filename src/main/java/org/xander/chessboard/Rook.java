package org.xander.chessboard;

import java.util.Map;

import static org.xander.chessboard.Figure.ROOK;

public class Rook extends FiguresChain {
    public Rook(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
    }

    @Override
    public String getName() {
        return ROOK.name();
    }
}
