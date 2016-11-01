package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.PlacementBehavior;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public abstract class FiguresChain {
    private Map<String, Integer> figureQuantityMap;
    protected PlacementBehavior placementBehavior;
    private FiguresChain chain;

    FiguresChain(Map<String, Integer> figureQuantityMap) {
        this.figureQuantityMap = figureQuantityMap;
    }

    public Map<String, Integer> getFigureQuantityMap() {
        return figureQuantityMap;
    }

    public FiguresChain getChain() {
        return chain;
    }

    public int extractA(String figure) {
        if (getFigureQuantityMap() != null && getFigureQuantityMap().containsKey(figure)) {
            return getFigureQuantityMap().get(figure);
        }
        return 0;
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
