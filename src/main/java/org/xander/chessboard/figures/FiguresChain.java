package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.PlacementBehavior;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class FiguresChain {
    final Map<String, Integer> figureQuantityMap;
    PlacementBehavior placementBehavior;
    FiguresChain chain;

    FiguresChain(Map<String, Integer> figureQuantityMap) {
        this.figureQuantityMap = figureQuantityMap;
    }

    public void setNextFigure(FiguresChain nextChain) {
        this.chain = nextChain;
    }

    public Set<String> placeFigures(Set<String> boards) {
        Set<String> boardsToReturn = placePartOfChain(boards);
        if (chain != null) {
            return this.chain.placeFigures(boardsToReturn);
        }
        return boardsToReturn;
    }

    Set<String> placePartOfChain(Set<String> boards) {
        Integer numberOfFigures = figureQuantityMap.get(getName());
        Set<String> boardsToReturn = new HashSet<>();
        if (Objects.nonNull(numberOfFigures) && figureQuantityMap.containsKey(getName())) {
            while (numberOfFigures > 0) {
                boardsToReturn.addAll(placementBehavior.placeFiguresOnBoards(boards));
                boards.clear();
                boards.addAll(boardsToReturn);
                boardsToReturn.clear();
                numberOfFigures--;
            }
        }
        return boards;
    }

    abstract String getName();
}
