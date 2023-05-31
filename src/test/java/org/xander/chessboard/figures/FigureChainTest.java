package org.xander.chessboard.figures;

import org.junit.jupiter.api.Test;
import org.xander.chessboard.figuresPlacement.BishopsPlacement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figures.Figure.KING;
import static org.xander.chessboard.figures.Figure.KNIGHT;
import static org.xander.chessboard.figures.Figure.QUEEN;
import static org.xander.chessboard.figures.Figure.ROOK;
import static org.xander.chessboard.figuresPlacement.FiguresPlacement.EMPTY_FIELD_STRING;
import static org.xander.chessboard.figuresPlacement.FiguresPlacement.FIELD_UNDER_ATTACK_STRING;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.EMPTY_BOARD_SIZE_6;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.leftOnlyFigures;

public class FigureChainTest {
    @Test
    public void setNextFigure() {

        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.getFigureAsString(), 1);
        FiguresChain figuresChain = new Bishop(figureQuantityMap);
        FiguresChain figuresChain1 = new Queen(figureQuantityMap);

        figuresChain.setNextFigure(figuresChain1);
        assertThat("object is null", Objects.nonNull(figuresChain.getChain()), is(true));
        assertThat("object is null", Objects.nonNull(figuresChain.getChain()), is(true));

        if (!Objects.isNull(figuresChain.getFigureQuantityMap())) {
            assertThat("key is not present", figuresChain.getFigureQuantityMap().containsKey(KING.getFigureAsString()), is(true));
            assertThat("value is not present", figuresChain.getFigureQuantityMap().containsValue(1), is(true));
        }
        assertThat("object is of different type", figuresChain.placementBehavior instanceof BishopsPlacement, is(true));
    }

    @Test
    public void figureQuantityMapIsNull() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> new Bishop(null));
        assertEquals("figureQuantity map is null, however should not be", ex.getMessage());
    }

    @Test
    public void figureChainIsNull() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.getFigureAsString(), 1);
        FiguresChain figuresChain = new Bishop(figureQuantityMap);
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> figuresChain.setNextFigure(null));
        assertEquals("next part of the chain is null, please provide non-null chain", ex.getMessage());
    }

    @Test
    public void extractANullFigure() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(null, null);
        FiguresChain figuresChain = new Bishop(figureQuantityMap);
        assertThat("null figure is present", figuresChain.extractA(null), is(0));
    }

    @Test
    public void placeFiguresNullBoardsNegative() {
        FiguresChain figuresChain = new Bishop(new HashMap<>());
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> figuresChain.placeFigures(null));
        assertEquals("boars are null", ex.getMessage());
    }

    @Test
    public void placeFiguresBishopNegative() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 4);
        FiguresChain figuresChain = new Bishop(figureQuantityMap);
        FiguresChain figuresChain1 = new Queen(figureQuantityMap);
        figuresChain.setNextFigure(figuresChain1);

        Set<String> objects = new HashSet<>();
        objects.add("....\n");

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> figuresChain.placeFigures(objects));
        assertEquals("There is something wrong with your board", ex.getMessage());
    }

    @Test
    public void placeFigures() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 4);
        FiguresChain figuresChain = new Bishop(figureQuantityMap);
        FiguresChain figuresChain1 = new Queen(figureQuantityMap);
        figuresChain.setNextFigure(figuresChain1);

        Set<String> objects = new HashSet<>();
        objects.add(EMPTY_BOARD_SIZE_6);
        Set<String> boards = figuresChain.placeFigures(objects);
        assertThat("figures are standing on different places", boards.contains("bbbb..\n" +
                                                                                      "xxxxx.\n" +
                                                                                      "xxxxxx\n" +
                                                                                      "x..xxx\n" +
                                                                                      "....xx\n" +
                                                                                      ".....x\n"),
                is(true));

        assertThat("all elements are not present on each board", boards
                .parallelStream()
                .filter(board -> !board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && !board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && board.contains(EMPTY_FIELD_STRING)
                        && board.contains(BISHOP.getFigureAsString())
                        && leftOnlyFigures(board).length() == 4)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(16428));
    }

    @Test
    public void placeFiguresKnightAndKing() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KNIGHT.toString(), 2);
        figureQuantityMap.put(KING.toString(), 2);

        FiguresChain figuresChain = new King(figureQuantityMap);
        figuresChain.setNextFigure(new Knight(figureQuantityMap));

        Set<String> objects = new HashSet<>();
        objects.add(EMPTY_BOARD_SIZE_6);
        Set<String> boards = figuresChain.placeFigures(objects);
        assertThat("figures are standing on different places", boards.contains("kxkxnn\n" +
                                                                                      "xxxx..\n" +
                                                                                      "...xxx\n" +
                                                                                      "......\n" +
                                                                                      "......\n" +
                                                                                      "......\n"),
                is(true));

        assertThat("all elements are not present on each board", boards
                .parallelStream()
                .filter(board -> board.contains(KING.getFigureAsString())
                        && board.contains(KNIGHT.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && !board.contains(ROOK.getFigureAsString())
                        && !board.contains(BISHOP.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 4)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(59392));
    }

    @Test
    public void placeFiguresKnight() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KNIGHT.toString(), 4);

        FiguresChain figuresChain = new Knight(figureQuantityMap);

        Set<String> objects = new HashSet<>();
        objects.add(EMPTY_BOARD_SIZE_6);
        Set<String> boards = figuresChain.placeFigures(objects);
        assertThat("figures are standing on different places", boards.contains("nnnn..\n" +
                                                                                      "xxxxxx\n" +
                                                                                      "xxxxx.\n" +
                                                                                      "......\n" +
                                                                                      "......\n" +
                                                                                      "......\n"), is(true));

        assertThat("all elements are not present on each board", boards
                .parallelStream()
                .filter(board -> board.contains(KNIGHT.getFigureAsString())
                        && !board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && !board.contains(ROOK.getFigureAsString())
                        && !board.contains(BISHOP.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 4
                )
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(26133));
    }

    @Test
    public void placeFiguresKnightNegative() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KNIGHT.toString(), 4);

        Set<String> objects = new HashSet<>();
        objects.add("....\n");

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> new Knight(figureQuantityMap).placeFigures(objects));
        assertEquals("There is something wrong with your board", ex.getMessage());
    }

    @Test
    public void placeFiguresKingQueenBishop() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 1);
        figureQuantityMap.put(QUEEN.toString(), 1);
        figureQuantityMap.put(BISHOP.toString(), 1);

        FiguresChain kingChain = new King(figureQuantityMap);
        FiguresChain queenChain = new Queen(figureQuantityMap);
        FiguresChain bishopChain = new Bishop(figureQuantityMap);
        kingChain.setNextFigure(queenChain);
        queenChain.setNextFigure(bishopChain);

        Set<String> strings = new HashSet<>();
        strings.add(EMPTY_BOARD_SIZE_6);
//        int sum = kingChain.placeFigures(strings.stream()).parallel().map(e -> 1).mapToInt(Integer::new).sum();
        assertThat("all elements are not present on each board", kingChain.placeFigures(strings)
                .parallelStream()
                .filter(board -> board.contains(KING.getFigureAsString())
                            && board.contains(QUEEN.getFigureAsString())
                            && board.contains(BISHOP.getFigureAsString())
                            && !board.contains(ROOK.getFigureAsString())
                            && !board.contains(KNIGHT.getFigureAsString())
                            && board.contains(FIELD_UNDER_ATTACK_STRING)
                            && board.contains(EMPTY_FIELD_STRING)
                            && leftOnlyFigures(board).length() == 3)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(8768));
    }
}
