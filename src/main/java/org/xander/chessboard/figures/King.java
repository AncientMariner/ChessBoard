package org.xander.chessboard.figures;

import org.xander.chessboard.KingsPlacement;

import java.util.Map;

public class King extends FiguresChain {
    public King(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
        placementBehavior = new KingsPlacement();
    }

    @Override
    public String getName() {
        return Figure.KING.name();
    }
}
