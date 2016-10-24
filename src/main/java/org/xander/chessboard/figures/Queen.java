package org.xander.chessboard.figures;

import java.util.Map;

public class Queen extends FiguresChain {
    public Queen(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
    }

    @Override
    public String getName() {
        return Figure.QUEEN.name();
    }
}
