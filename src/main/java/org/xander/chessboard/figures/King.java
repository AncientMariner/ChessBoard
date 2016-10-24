package org.xander.chessboard.figures;

import java.util.Map;

public class King extends FiguresChain {
    public King(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
    }

    @Override
    public String getName() {
        return Figure.KING.name();
    }
}
