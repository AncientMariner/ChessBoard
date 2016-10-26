package org.xander.chessboard.figures;

import org.junit.Test;
import org.xander.chessboard.figuresPlacement.BishopsPlacement;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BishopTest {
    @Test
    public void getName() throws Exception {
        FiguresChain figuresChain = new Bishop(new HashMap<>());

        assertThat("object is null", figuresChain != null, is(true));
        assertThat("object is null", figuresChain.placementBehavior instanceof BishopsPlacement, is(true));
        assertThat("object name is different", figuresChain.getName().equals(Figure.BISHOP.name()) , is(true));
    }
}