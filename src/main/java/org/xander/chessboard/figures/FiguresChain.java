package org.xander.chessboard.figures;

import org.xander.chessboard.PlacementBehavior;

import java.util.Map;
import java.util.Objects;

public abstract class FiguresChain {
    Map<String, Integer> figureQuantityMap;
    PlacementBehavior placementBehavior;
    FiguresChain chain;

    public FiguresChain(Map<String, Integer> figureQuantityMap) {
        this.figureQuantityMap = figureQuantityMap;
    }

    public void setNextFigure(FiguresChain nextChain) {
        this.chain = nextChain;
    }

    public String placeFigures(String board) {
        if (Objects.nonNull(figureQuantityMap.get(getName())) && figureQuantityMap.containsKey(getName())) {
            board = placementBehavior.placeNumberOfFiguresOnBoard(figureQuantityMap.get(getName()), board);
        }
        return this.chain.placeFigures(board);
    }

    abstract String getName();
}
