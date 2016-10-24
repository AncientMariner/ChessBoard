package org.xander.chessboard.figures;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RookTest {
    @Test
    public void getName() {
        FiguresChain figuresChain = new Rook(new HashMap<String, Integer>());
        assertThat("object is null", figuresChain != null, is(true));
        assertThat("object name is different", figuresChain.getName().equals(Figure.ROOK.name()) , is(true));
    }
}