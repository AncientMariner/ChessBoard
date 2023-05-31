package org.xander.chessboard.figures;

import org.junit.jupiter.api.Test;
import org.xander.chessboard.figuresPlacement.KnightsPlacement;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class KnightTest {
    @Test
    public void getName() {
        FiguresChain figuresChain = new Knight(new HashMap<>());
        assertThat("object is null", figuresChain.placementBehavior instanceof KnightsPlacement, is(true));
        assertThat("object name is different", figuresChain.getName().equals(Figure.KNIGHT.toString()), is(true));
    }
}