package org.xander.chessboard.figures;

import java.util.Map;

public class Rook extends FiguresChain {
    public Rook(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
    }

    @Override
    public String getName() {
        return Figure.ROOK.name();
    }
}
