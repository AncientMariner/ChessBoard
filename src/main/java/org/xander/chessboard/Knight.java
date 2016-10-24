package org.xander.chessboard;

import java.util.Map;
import java.util.Objects;

import static org.xander.chessboard.Figure.KNIGHT;

public class Knight extends FiguresChain {
    public Knight(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
    }

    @Override
    void placeFigures() {
        if (Objects.nonNull(figureQuantityMap.get(getName())) && figureQuantityMap.containsKey(getName())) {
            Integer number = figureQuantityMap.get(getName());
            System.out.println("placing + " + number + " " + getName());
        }
    }

    @Override
    public String getName() {
        return KNIGHT.name();
    }
}
