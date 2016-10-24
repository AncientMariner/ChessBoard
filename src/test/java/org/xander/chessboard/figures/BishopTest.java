package org.xander.chessboard.figures;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BishopTest {
    @Test
    public void getName() throws Exception {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put("1", 1);
        FiguresChain figuresChain = new Bishop(figureQuantityMap);

        assertThat("object is null", figuresChain != null, is(true));
        assertThat("object name is different", figuresChain.getName().equals(Figure.BISHOP.name()) , is(true));
    }
}