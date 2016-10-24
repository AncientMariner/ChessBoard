package org.xander.chessboard.figures;

import org.xander.chessboard.RooksPlacement;

import java.util.Map;

public class Rook extends FiguresChain {
    public Rook(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
        placementBehavior = new RooksPlacement();
    }

    @Override
    public String getName() {
        return Figure.ROOK.name();
    }
}