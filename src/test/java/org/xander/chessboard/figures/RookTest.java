package org.xander.chessboard.figures;

import org.junit.jupiter.api.Test;
import org.xander.chessboard.figuresPlacement.RooksPlacement;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RookTest {
    @Test
    public void getName() {
        FiguresChain figuresChain = new Rook(new HashMap<>());
        assertThat("object is null", figuresChain.placementBehavior instanceof RooksPlacement, is(true));
        assertThat("object name is different", figuresChain.getName().equals(Figure.ROOK.toString()), is(true));
    }
}