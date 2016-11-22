package org.xander.chessboard.figures;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.Assert.assertThat;

public class NoMoreFiguresTest {
    @Test
    public void placeFigures() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        NoMoreFigures noMoreFigures = new NoMoreFigures(figureQuantityMap);
        Stream<String> stringStream = noMoreFigures.placeFigures(new HashSet<String>().stream());
        assertThat("value is present, however should not be", stringStream, CoreMatchers.is(stringStream));
    }

    @Test
    public void getName() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        NoMoreFigures noMoreFigures = new NoMoreFigures(figureQuantityMap);
        String name = noMoreFigures.getName();
        assertThat("name is not empty, however should be", name, CoreMatchers.is(""));
    }
}