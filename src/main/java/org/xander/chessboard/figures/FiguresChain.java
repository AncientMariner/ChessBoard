package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.PlacementBehavior;

import java.util.HashSet;
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
        Integer numberOfFigures = figureQuantityMap.get(getName());
        Set<String> boardsToReturn = new HashSet<>(boards);
        if (Objects.nonNull(numberOfFigures) && figureQuantityMap.containsKey(getName())) {

            while (numberOfFigures > 0) {
                boardsToReturn.clear();
                boardsToReturn.addAll(placementBehavior.placeFigureOnBoard(boards));
                numberOfFigures--;
            }
        }
        if (chain != null) {
            return this.chain.placeFigures(boardsToReturn);
        }
        return boardsToReturn;
    }

    abstract String getName();
}
