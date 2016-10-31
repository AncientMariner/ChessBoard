package org.xander.chessboard.figures;

import org.xander.chessboard.figuresPlacement.KnightsPlacement;

import java.util.Map;
import java.util.Set;

public class Knight extends FiguresChain {
    public Knight(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
        placementBehavior = new KnightsPlacement();
    }

    @Override
    public Set<String> placeFigures(Set<String> boards) {
        //knight is finishing figure to avoid cycling in figures
        return placePartOfChain(boards);
    }

    @Override
    public String getName() {
        return Figure.KNIGHT.name();
    }
}
