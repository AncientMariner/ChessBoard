package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.BishopsPlacement;

import java.util.Map;

public class Bishop extends FiguresChain {
    public Bishop(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
        placementBehavior = new BishopsPlacement();
    }

    @Override
    public String getName() {
        return Figure.BISHOP.name();
    }
}
