package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.PlacementBehavior;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class FiguresChain {
    private Map<String, Integer> figureQuantityMap;
    PlacementBehavior placementBehavior;
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
        Objects.requireNonNull(getFigureQuantityMap(), "figure quantity map is null");
        if (getFigureQuantityMap().containsKey(figure)) {
            return getFigureQuantityMap().get(figure);
        }
        return 0;
    }

    public void setNextFigure(FiguresChain nextChain) {
        this.chain = nextChain;
    }

    public Stream<String> placeFigures(Stream<String> boards) {
        Stream<String> boardsToReturn = placePartOfChain(boards);
        if (!Objects.isNull(chain)) {
            return this.chain.placeFigures(boardsToReturn);
        }
        return boardsToReturn;
    }

    private Stream<String> placePartOfChain(Stream<String> boardsStream) {
        Set<String> boards = boardsStream.collect(Collectors.toSet());
        Integer numberOfFigures = figureQuantityMap.get(getName());
        if (!Objects.isNull(numberOfFigures)) {
            IntStream.range(0, numberOfFigures)
                    .filter(e -> figureQuantityMap.containsKey(getName()))
                    .forEach(e -> {
                        Set<String> boardsToReturn = new HashSet<>();

                        boardsToReturn.addAll(placementBehavior.placeFiguresOnBoards(boards.stream()).collect(Collectors.toSet()));

                        boards.clear();
                        boards.addAll(boardsToReturn);

                        boardsToReturn.clear();
                    });
        }
        return boards.stream();
    }

    abstract String getName();
}
