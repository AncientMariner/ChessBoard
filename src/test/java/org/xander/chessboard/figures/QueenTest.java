package org.xander.chessboard.figures;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QueenTest {
    @Test
    public void getName() {
        FiguresChain figuresChain = new Queen(new HashMap<String, Integer>());
        assertThat("object is null", figuresChain != null, is(true));
        assertThat("object name is different", figuresChain.getName().equals(Figure.QUEEN.name()) , is(true));
    }
}