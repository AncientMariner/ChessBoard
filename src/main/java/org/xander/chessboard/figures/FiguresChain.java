package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.PlacementBehavior;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

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

    public Set<String> placeFigures(Set<String> boards) {
        if (Objects.nonNull(figureQuantityMap.get(getName())) && figureQuantityMap.containsKey(getName())) {
            boards = placementBehavior.placeNumberOfFiguresOnBoard(figureQuantityMap.get(getName()), boards);
        }
        if (chain != null) {
            return this.chain.placeFigures(boards);
        }
        return boards;
    }

    abstract String getName();
}
