package org.xander.chessboard;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figures.Figure.KING;
import static org.xander.chessboard.figures.Figure.KNIGHT;
import static org.xander.chessboard.figures.Figure.QUEEN;
import static org.xander.chessboard.figures.Figure.ROOK;
import static org.xander.chessboard.figuresPlacement.FiguresPlacement.EMPTY_FIELD_STRING;
import static org.xander.chessboard.figuresPlacement.FiguresPlacement.FIELD_UNDER_ATTACK_STRING;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.DIMENSION_6;
import static org.xander.chessboard.figuresPlacement.FiguresTestUtil.leftOnlyFigures;

public class ChessboardTest {
    @Test
    public void chessBoardBasic() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(ROOK.toString(), 1);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(DIMENSION_6).build();

        assertNotNull(chessboard);
        assertEquals(DIMENSION_6, chessboard.getDimension());
    }

    @Test(expected = IllegalStateException.class)
    public void chessBoardBasicNegative() {
        Chessboard.newBuilder(null).withDimension(DIMENSION_6).build();
    }

    @Test(expected = IllegalStateException.class)
    public void nonExistingFigures() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put("a", 1);
        Chessboard.newBuilder(figureQuantityMap).build().placeFiguresOnBoard("   ");
    }

    @Test(expected = IllegalStateException.class)
    public void nonExistingFigure() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put("PAWN", 1);
        Chessboard.newBuilder(figureQuantityMap).build().placeFiguresOnBoard("......");
    }

    @Test(expected = IllegalStateException.class)
    public void moreThanExpectedFigures() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 3);
        figureQuantityMap.put(BISHOP.toString(), 4);
        figureQuantityMap.put(ROOK.toString(), 5);
        figureQuantityMap.put(KNIGHT.toString(), 6);
        figureQuantityMap.put("PAWN", 8);

        Chessboard.newBuilder(figureQuantityMap).withKing().withQueen().withBishop().withRook().withKnight().build();
    }

    @Test(expected = IllegalStateException.class)
    public void notDesiredFiguresWithPawn() {
        HashMap<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 3);
        figureQuantityMap.put(BISHOP.toString(), 4);
        figureQuantityMap.put(KNIGHT.toString(), 6);
        figureQuantityMap.put("PAWN", 8);

        Chessboard.newBuilder(figureQuantityMap).withKing().withQueen().withBishop().withRook().withKnight().build();
    }

    @Test
    public void chessBoardFigures() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 3);
        figureQuantityMap.put(BISHOP.toString(), 4);
        figureQuantityMap.put(ROOK.toString(), 5);
        figureQuantityMap.put(KNIGHT.toString(), 6);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withKing().withQueen().withBishop().withRook().withKnight().build();
        assertTrue(figureQuantityMap.equals(chessboard.getFigureQuantityMap()));
    }

//    runs too long, for now ignored
    @Ignore
    @Test
    public void placeFiguresOnBoard() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 3);
        figureQuantityMap.put(BISHOP.toString(), 4);
        figureQuantityMap.put(ROOK.toString(), 5);
        figureQuantityMap.put(KNIGHT.toString(), 6);

        int dimension = 8;
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKing().withQueen().withBishop().withRook().withKnight().build();

        Set<String> boards = chessboard.placeFiguresOnEmptyBoard();
        assertThat("more than 1 figure is present",
                boards.contains("kxkxqxxx\n" +
                                "xxxxxxqx\n" +
                                "qxxxxxxx\n" +
                                "xxbbxbxx\n" +
                                "xxxxxbxr\n" +
                                "xxxxxxxx\n" +
                                "xxxxxxxx\n" +
                                "xxxrxxxx\n"), is(true));

        assertTrue("all elements are not present on each board", boards.parallelStream()
                .allMatch(board -> board.contains(KING.getFigureAsString())
                                && board.contains(QUEEN.getFigureAsString())
                                && board.contains(BISHOP.getFigureAsString())
                                && board.contains(ROOK.getFigureAsString())
                                && board.contains(KNIGHT.getFigureAsString())
//                        && board.contains("x")
//                        && board.contains(".")
                                && leftOnlyFigures(board).length() == 20
//                        && boards.size() == 26133
                ));
    }

    @Test
    public void placeFiguresOnBoardSize7() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 3);
        figureQuantityMap.put(BISHOP.toString(), 4);
        figureQuantityMap.put(ROOK.toString(), 5);
        figureQuantityMap.put(KNIGHT.toString(), 6);

        int dimension = 7;
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKing().withQueen().withBishop().withRook().withKnight().build();

        Set<String> boards = chessboard.placeFiguresOnEmptyBoard();
        assertThat("more than 1 figure is present",
                boards.contains("xxxxxqx\n" +
                                "xxqxxxx\n" +
                                "bxxxxxb\n" +
                                "xxxrxxx\n" +
                                "bxxxxxk\n" +
                                "xxxxqxx\n" +
                                "nbxxxxk\n"), is(true));

        assertTrue("all elements are not present on each board", boards.stream()
                .allMatch(board -> board.contains(KING.getFigureAsString())
                                && board.contains(QUEEN.getFigureAsString())
                                && board.contains(BISHOP.getFigureAsString())
                                && board.contains(ROOK.getFigureAsString())
                                && board.contains(KNIGHT.getFigureAsString())
                                && board.contains(FIELD_UNDER_ATTACK_STRING)
                                && leftOnlyFigures(board).length() > 10
                                && boards.size() == 32
                ));

    }

// todo make different combinations
    @Test
    public void readmeRequirement() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 2);
        figureQuantityMap.put(BISHOP.toString(), 2);
        figureQuantityMap.put(KNIGHT.toString(), 1);

        int dimension = 7;
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKing().withQueen().withBishop().withKnight().build();

        Set<String> boards = chessboard.placeFiguresOnEmptyBoard();

        assertTrue("all elements are not present on each board", boards.parallelStream()
                .allMatch(board -> board.contains(KING.getFigureAsString())
                    && board.contains(QUEEN.getFigureAsString())
                    && board.contains(BISHOP.getFigureAsString())
                    && !board.contains(ROOK.getFigureAsString())
                    && board.contains(KNIGHT.getFigureAsString())
                    && board.contains(FIELD_UNDER_ATTACK_STRING)
                    && leftOnlyFigures(board).length() == 7
                    && boards.size() == 3063828));
    }

    @Test(expected = IllegalStateException.class)
    public void placeMoreFiguresOnBoardThanBoardSize() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 22);
        figureQuantityMap.put(QUEEN.toString(), 23);
        figureQuantityMap.put(BISHOP.toString(), 24);
        figureQuantityMap.put(ROOK.toString(), 25);
        figureQuantityMap.put(KNIGHT.toString(), 26);

        int dimension = 8;
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKing().withQueen().withBishop().withRook().withKnight().build();

        chessboard.placeFiguresOnEmptyBoard();
    }

    @Test(expected = IllegalStateException.class)
    public void placeAFigureOnBoardNegative() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withKing().build();

        chessboard.placeFiguresOnBoard("");
    }

    @Test
    public void kingAndRook() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(ROOK.toString(), 2);
        int dimension = 7;
        Set<String> boards = prepareBoardsWithKingAndRook(figureQuantityMap, dimension);

        assertThat("there is no boards", boards.size() > 0, is(true));
        assertTrue("all elements are not present on each board", boards.parallelStream()
                .allMatch(board -> board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && !board.contains(BISHOP.getFigureAsString())
                        && board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 4
                        && boards.size() == 153064
                ));
    }

    Set<String> prepareBoardsWithKingAndRook(Map<String, Integer> figureQuantityMap, int dimension) {
        Chessboard chessboard1 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKing().withRook().build();
        Chessboard chessboard2 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withRook().withKing().build();

        Set<String> boards = new HashSet<>();
        boards.addAll(chessboard1.placeFiguresOnEmptyBoard());
        boards.addAll(chessboard2.placeFiguresOnEmptyBoard());

        return boards;
    }

    //takes at learst 1,5 minutes
    @Ignore
    @Test
    public void kingRookAndBishop() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(ROOK.toString(), 2);
        figureQuantityMap.put(BISHOP.toString(), 2);
        int dimension = 7;
        Set<String> boards = prepareBoardsWithKingRookAndBishop(figureQuantityMap, dimension);

        assertThat("there is no boards", boards.size() > 0, is(true));
        assertTrue("all elements are not present on each board", boards.parallelStream()
                .allMatch(board -> board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && board.contains(BISHOP.getFigureAsString())
                        && board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 6
                        && boards.size() == 3613364
                ));
    }

    Set<String> prepareBoardsWithKingRookAndBishop(Map<String, Integer> figureQuantityMap, int dimension) {
        Chessboard chessboard1 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKing().withRook().withBishop().build();
//        Chessboard chessboard2 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKing().withBishop().withRook().build();

//        Chessboard chessboard3 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withBishop().withKing().withRook().build();
//        Chessboard chessboard4 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withBishop().withRook().withKing().build();

//        Chessboard chessboard5 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withRook().withBishop().withKing().build();
//        Chessboard chessboard6 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withRook().withKing().withBishop().build();

        Set<String> boards = new HashSet<>();
        boards.addAll(chessboard1.placeFiguresOnEmptyBoard());
//        boards.addAll(chessboard2.placeFiguresOnEmptyBoard());
//        boards.addAll(chessboard3.placeFiguresOnEmptyBoard());
//        boards.addAll(chessboard4.placeFiguresOnEmptyBoard());
//        boards.addAll(chessboard5.placeFiguresOnEmptyBoard());
//        boards.addAll(chessboard6.placeFiguresOnEmptyBoard());
        return boards;
    }

    @Test
    public void boardSize8KingAndRook() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(ROOK.toString(), 2);
        int dimension = 8;
        Set<String> boards = prepareBoardsWithKingAndRook(figureQuantityMap, dimension);

        assertThat("there is no boards", boards.size() > 0, is(true));

        assertTrue("all elements are not present on each board", boards.parallelStream()
                .allMatch(board -> board.contains(KING.getFigureAsString())
                                && !board.contains(QUEEN.getFigureAsString())
                                && !board.contains(BISHOP.getFigureAsString())
                                && board.contains(ROOK.getFigureAsString())
                                && !board.contains(KNIGHT.getFigureAsString())
                                && board.contains(FIELD_UNDER_ATTACK_STRING)
                                && leftOnlyFigures(board).length() == 4
                                && boards.size() == 657390
                ));
    }

    @Test
    public void onlyBishopOnFilledBoard() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 2);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(6).withBishop().build();

        Set<String> boards = chessboard.placeFiguresOnBoard("bbbbbb\n" +
                                                                       "b....b\n" +
                                                                       "b....b\n" +
                                                                       "b....b\n" +
                                                                       "b....b\n" +
                                                                       "bbbbbb\n");
        assertThat("there is no boards", boards.size() == 1, is(true));

        assertTrue("all elements are not present on each board", boards.stream()
                .allMatch(board -> !board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && board.contains(BISHOP.getFigureAsString())
                        && !board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && !board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 20
                        && boards.size() == 1
                ));
    }

    @Test
    public void onlyBishopOnEmptyBoard() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 2);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(6).withBishop().build();

        Set<String> boards = chessboard.placeFiguresOnEmptyBoard();
        assertThat("there is no boards", boards.size() > 0, is(true));
        assertTrue("all elements are not present on each board", boards.stream()
                .allMatch(board -> !board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && board.contains(BISHOP.getFigureAsString())
                        && !board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 2
                        && boards.size() == 520
                ));
    }

    @Test
    public void bishopOnFullBoard() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 2);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(6).withBishop().build();

        Set<String> boards = chessboard.placeFiguresOnBoard("bbbbbb\n" +
                                                                       "bbbbbb\n" +
                                                                       "bbbbbb\n" +
                                                                       "bbbbbb\n" +
                                                                       "bbbbbb\n" +
                                                                       "bbbbbb\n");
        assertThat("there is no boards", boards.size() == 1, is(true));

        assertTrue("all elements are not present on each board", boards.stream()
                .allMatch(board -> !board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && board.contains(BISHOP.getFigureAsString())
                        && !board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && !board.contains(FIELD_UNDER_ATTACK_STRING)
                        && !board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 36
                        && boards.size() == 1
                ));
    }

    @Test
    public void smallBoardWithKingAndQueen() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 1);
        figureQuantityMap.put(QUEEN.toString(), 1);

        int dimension = 2;
        Set<String> boards = prepareBoardsWithKingAndQueen(figureQuantityMap, dimension);
        assertThat("more than 1 figure is present",
                boards.contains("kx\n" + "xx\n"), is(true));

        assertTrue("all elements are not present on each board", boards.stream()
                .allMatch(board -> !board.contains(BISHOP.getFigureAsString())
                        && !board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && !board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 1
                        && boards.size() == 8
                ));
    }

    Set<String> prepareBoardsWithKingAndQueen(Map<String, Integer> figureQuantityMap, int dimension) {
        Chessboard chessboard1 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKing().withQueen().build();
        Chessboard chessboard2 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withQueen().withKing().build();

        Set<String> boards = new HashSet<>();
        boards.addAll(chessboard1.placeFiguresOnEmptyBoard());
        boards.addAll(chessboard2.placeFiguresOnEmptyBoard());

        return boards;
    }

    @Test
    public void smallBoardSize2WithBishopAndRook() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 1);
        figureQuantityMap.put(ROOK.toString(), 1);

        int dimension = 2;
        Set<String> boards = prepareBoardsWithBishopAndRook(figureQuantityMap, dimension);

        assertThat("more than 1 figure is present", boards.contains("rx\n" + "x.\n"), is(true));
        assertThat("more than 1 figure is present", boards.contains("xr\n" + ".x\n"), is(true));
        assertThat("more than 1 figure is present", boards.contains(".b\n" + "x.\n"), is(true));
        assertThat("more than 1 figure is present", boards.contains("b.\n" + ".x\n"), is(true));

        assertTrue("all elements are not present on each board", boards.stream()
                .allMatch(board -> !board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 1
                        && boards.size() == 8
                ));
    }

    Set<String> prepareBoardsWithBishopAndRook(Map<String, Integer> figureQuantityMap, int dimension) {
        Chessboard chessboard1 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withRook().withBishop().build();
        Chessboard chessboard2 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withBishop().withRook().build();

        Set<String> boards = new HashSet<>();
        boards.addAll(chessboard1.placeFiguresOnEmptyBoard());
        boards.addAll(chessboard2.placeFiguresOnEmptyBoard());
        return boards;
    }

    @Test
    public void smallBoardSize3WithBishopAndRook() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 1);
        figureQuantityMap.put(ROOK.toString(), 1);

        int dimension = 3;
        Set<String> boards = prepareBoardsWithBishopAndRook(figureQuantityMap, dimension);

        assertThat("more than 1 figure is present", boards.contains("bx.\n" +
                                                                           ".x.\n" +
                                                                           "xrx\n"), is(true));
        assertThat("more than 1 figure is present", boards.contains(".bx\n" +
                                                                           "xxr\n" +
                                                                           "..x\n"), is(false));
        assertThat("more than 1 figure is present", boards.contains(".xx\n" +
                                                                           "b.x\n" +
                                                                           "xxr\n"), is(true));
        assertTrue("all elements are not present on each board", boards.stream()
                .allMatch(board -> !board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && board.contains(BISHOP.getFigureAsString())
                        && board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 2
                        && boards.size() == 16
                ));
    }
}
