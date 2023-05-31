package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.PlacementBehavior;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public abstract class FiguresChain {
    private final Map<String, Integer> figureQuantityMap;
    protected PlacementBehavior placementBehavior;
    private FiguresChain chain;

    FiguresChain(Map<String, Integer> figureQuantityMap) {
        if (Objects.isNull(figureQuantityMap)) {
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
        if (Objects.isNull(nextChain)) {
            throw new IllegalStateException("next part of the chain is null, please provide non-null chain");
        }
        this.chain = nextChain;
    }

    public int extractA(String figure) {
        if (Objects.nonNull(figure)
                && Objects.nonNull(getFigureQuantityMap())
                && getFigureQuantityMap().containsKey(figure)) {
            return getFigureQuantityMap().get(figure);
        }
        return 0;
    }

    public Set<String> placeFigures(Set<String> boards) {
        if (Objects.isNull(boards)) {
            throw new IllegalStateException("boars are null");
        }
        Set<String> boardsToReturn = placePartOfChain(boards);
        if (Objects.nonNull(chain)) {
            return this.chain.placeFigures(boardsToReturn);
        }
        return boardsToReturn;
    }

    private Set<String> placePartOfChain(Set<String> boards) {
        Integer numberOfFigures = figureQuantityMap.get(getName());
        if (Objects.nonNull(numberOfFigures)) {
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
