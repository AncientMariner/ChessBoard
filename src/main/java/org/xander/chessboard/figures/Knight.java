package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.KnightsPlacement;

import java.util.Map;

public class Knight extends FiguresChain {
    public Knight(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
        placementBehavior = new KnightsPlacement();
    }

    @Override
    public String getName() {
        return Figure.KNIGHT.toString();
    }
}
