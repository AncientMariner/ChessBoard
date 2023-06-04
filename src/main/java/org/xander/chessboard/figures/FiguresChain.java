package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.PlacementBehavior;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class FiguresChain {
    private final Map<String, Integer> figureQuantityMap;
    protected PlacementBehavior placementBehavior;
    private FiguresChain chain;

    FiguresChain(Map<String, Integer> figureQuantityMap) {
        if (figureQuantityMap == null) {
            throw new IllegalStateException("figureQuantity map is null, however should not be");
        }
        this.figureQuantityMap = figureQuantityMap;
    }

    public Map<String, Integer> getFigureQuantityMap() {
        return figureQuantityMap;
    }

    public FiguresChain getChain() {
        return chain;
    }

    public void setNextFigure(FiguresChain nextChain) {
        if (nextChain == null) {
            throw new IllegalStateException("next part of the chain is null, please provide non-null chain");
        }
        this.chain = nextChain;
    }

    public int extractA(String figure) {
        if (figure != null
                && getFigureQuantityMap() != null
                && getFigureQuantityMap().containsKey(figure)) {
            return getFigureQuantityMap().get(figure);
        }
        return 0;
    }

    public Set<String> placeFigures(Set<String> boards) {
        if (boards == null) {
            throw new IllegalStateException("boars are null");
        }
        Set<String> boardsToReturn = placePartOfChain(boards);
        if (chain != null) {
            return this.chain.placeFigures(boardsToReturn);
        }
        return boardsToReturn;
    }

    private Set<String> placePartOfChain(Set<String> boards) {
        for (int i = 0; i < figureQuantityMap.getOrDefault(getName(), 0); i++) {
               if (figureQuantityMap.containsKey(getName())) {
                    Set<String> boardsToReturn = new HashSet<>(placementBehavior.placeFiguresOnBoards(boards));
                    boards.clear();
                    boards.addAll(boardsToReturn);

                    boardsToReturn.clear();
                }
            }
        return boards;
    }

    abstract String getName();
}
