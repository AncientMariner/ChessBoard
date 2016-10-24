package org.xander.chessboard;

import java.util.Map;

import static org.xander.chessboard.Figure.KING;

public class King extends FiguresChain {
    public King(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
    }

    @Override
    public String getName() {
        return KING.name();
    }
}
