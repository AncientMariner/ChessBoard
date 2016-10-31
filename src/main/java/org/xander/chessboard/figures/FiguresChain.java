package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.PlacementBehavior;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

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
        if (numberOfFigures != null) {
            IntStream.range(0, numberOfFigures)
                    .filter(e -> figureQuantityMap.containsKey(getName()))
                    .forEach(e -> {
                                    Set<String> boardsToReturn = new HashSet<>();

                                    boardsToReturn.addAll(placementBehavior.placeFiguresOnBoards(boards));

                                    boards.clear();
                                    boards.addAll(boardsToReturn);

                                    boardsToReturn.clear();
                    });
        }
        return boards;
    }

    abstract String getName();
}
