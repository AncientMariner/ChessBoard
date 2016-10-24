package org.xander.chessboard.figures;

import java.util.Map;
import java.util.Objects;

public abstract class FiguresChain {
    Map<String, Integer> figureQuantityMap;
    FiguresChain chain;

    public FiguresChain(Map<String, Integer> figureQuantityMap) {
        this.figureQuantityMap = figureQuantityMap;
    }

    public void setNextFigure(FiguresChain nextChain) {
        this.chain = nextChain;
    }

    public void placeFigures() {
        if (Objects.nonNull(figureQuantityMap.get(getName())) && figureQuantityMap.containsKey(getName())) {
            Integer number = figureQuantityMap.get(getName());
            System.out.println("placing + " + number + " " + getName());
        }
        this.chain.placeFigures();
    }

    abstract String getName();
}
