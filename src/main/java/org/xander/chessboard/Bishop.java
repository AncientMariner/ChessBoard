package org.xander.chessboard;

import java.util.Map;

public class Bishop extends FiguresChain {
    public Bishop(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
    }

    @Override
    public String getName() {
        return Figure.BISHOP.name();
    }
}
