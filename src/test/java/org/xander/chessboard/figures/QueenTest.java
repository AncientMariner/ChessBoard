package org.xander.chessboard.figures;

import org.junit.jupiter.api.Test;
import org.xander.chessboard.figuresPlacement.QueensPlacement;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QueenTest {
    @Test
    public void getName() {
        FiguresChain figuresChain = new Queen(new HashMap<>());
        assertThat("object is null", figuresChain.placementBehavior instanceof QueensPlacement, is(true));
        assertThat("object name is different", figuresChain.getName().equals(Figure.QUEEN.toString()), is(true));
    }
}