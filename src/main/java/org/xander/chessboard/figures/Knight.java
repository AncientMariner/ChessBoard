package org.xander.chessboard.figures;

import org.xander.chessboard.KnightsPlacement;

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
        if (Objects.nonNull(figureQuantityMap.get(getName())) && figureQuantityMap.containsKey(getName())) {
            boards = placementBehavior.placeNumberOfFiguresOnBoard(figureQuantityMap.get(getName()), boards);
        }
        return boards;
    }

    @Override
    public String getName() {
        return Figure.KNIGHT.name();
    }
}
