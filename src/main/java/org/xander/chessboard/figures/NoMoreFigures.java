package org.xander.chessboard.figures;

import java.util.Map;
import java.util.Set;

/**class made to avoid cyclic dependency while building a chain
 * set this class as a last in a builder
 */
public class NoMoreFigures extends FiguresChain {
    public NoMoreFigures(Map<String, Integer> figureQuantityMap) {
        super(figureQuantityMap);
    }

    @Override
    public Set<String> placeFigures(Set<String> boards) {
        //this is finishing element to avoid cycling in figures
        return boards;
    }

    @Override
    public String getName() {
        return "";
    }
}
