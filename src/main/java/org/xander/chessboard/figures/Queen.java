package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.QueensPlacement;

import java.util.Map;

public class Queen extends FiguresChain {
    public Queen(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
        placementBehavior = new QueensPlacement();
    }

    @Override
    public String getName() {
        // todo getFigureAsString
        return Figure.QUEEN.name();
    }
}
