package org.xander.chessboard.figures;

import org.xander.chessboard.KnightsPlacement;

import java.util.Map;
import java.util.Objects;

public class Knight extends FiguresChain {
    public Knight(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
        placementBehavior = new KnightsPlacement();
    }

    @Override
    public String placeFigures(String board) {
        if (Objects.nonNull(figureQuantityMap.get(getName())) && figureQuantityMap.containsKey(getName())) {
            board = placementBehavior.placeNumberOfFiguresOnBoard(figureQuantityMap.get(getName()), board);
        }
        return board;
    }

    @Override
    public String getName() {
        return Figure.KNIGHT.name();
    }
}
