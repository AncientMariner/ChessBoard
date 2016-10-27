package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.KnightsPlacement;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Knight extends FiguresChain {
    public Knight(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
        placementBehavior = new KnightsPlacement();
    }

    @Override
    public Set<String> placeFigures(Set<String> boards) {
        Integer numberOfFigures = figureQuantityMap.get(getName());
        Set<String> boardsToReturn = new HashSet<>(boards);

        if (Objects.nonNull(figureQuantityMap.get(getName())) && figureQuantityMap.containsKey(getName())) {
            while (numberOfFigures > 0) {
                boardsToReturn.clear();
                boardsToReturn.addAll(placementBehavior.placeFigureOnBoard(boards));
                numberOfFigures--;
            }
        }
        return boardsToReturn;
    }

    @Override
    public String getName() {
        return Figure.KNIGHT.name();
    }
}
