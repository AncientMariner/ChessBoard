package org.xander.chessboard;

import java.util.Map;

import static org.xander.chessboard.Figure.QUEEN;

public class Queen extends FiguresChain {
    public Queen(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
    }

    @Override
    public String getName() {
        return QUEEN.name();
    }
}
