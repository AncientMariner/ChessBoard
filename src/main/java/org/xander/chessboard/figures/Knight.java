package org.xander.chessboard.figures;

import java.util.Map;
import java.util.Objects;

public class Knight extends FiguresChain {
    public Knight(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
    }

    @Override
    public void placeFigures() {
        if (Objects.nonNull(figureQuantityMap.get(getName())) && figureQuantityMap.containsKey(getName())) {
            Integer number = figureQuantityMap.get(getName());
            System.out.println("placing + " + number + " " + getName());
        }
    }

    @Override
    public String getName() {
        return Figure.KNIGHT.name();
    }
}
