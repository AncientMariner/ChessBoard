package org.xander.chessboard.figures;

import org.junit.jupiter.api.Test;
import org.xander.chessboard.figuresPlacement.KingsPlacement;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.xander.chessboard.figures.Figure.KING;

public class KingTest {
    @Test
    public void getName() {
        FiguresChain figuresChain = new King(new HashMap<>());
        assertThat("object is null", figuresChain.placementBehavior instanceof KingsPlacement, is(true));
        assertThat("object name is different", figuresChain.getName().equals(KING.toString()), is(true));
    }
}