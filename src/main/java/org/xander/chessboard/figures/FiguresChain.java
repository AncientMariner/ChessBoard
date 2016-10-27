package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.PlacementBehavior;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
        Set<String> boardsToReturn = placePartOfChain(boards);
        if (chain != null) {
            return this.chain.placeFigures(boardsToReturn);
        }
        return boardsToReturn;
    }

    Set<String> placePartOfChain(Set<String> boards) {
        Integer numberOfFigures = figureQuantityMap.get(getName());
        Set<String> boardsToReturn = boards.stream().collect(Collectors.toSet());
        if (Objects.nonNull(numberOfFigures) && figureQuantityMap.containsKey(getName())) {
            while (numberOfFigures > 0) {
                boardsToReturn.clear();
                boardsToReturn.addAll(placementBehavior.placeFigureOnBoard(boards));
                numberOfFigures--;
            }
        }
        return boardsToReturn;
    }

    abstract String getName();
}
