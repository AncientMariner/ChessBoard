package org.xander.chessboard.figures;

import org.xander.chessboard.PlacementBehavior;

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

    public String placeFigures(String board) {
        if (Objects.nonNull(figureQuantityMap.get(getName())) && figureQuantityMap.containsKey(getName())) {
            Set<String> boards = placementBehavior.placeNumberOfFiguresOnBoardAll(figureQuantityMap.get(getName()), board);
            System.out.println();
        }
        if (chain != null) {
            return this.chain.placeFigures(board);
        }
        return board;
    }

    abstract String getName();
}
