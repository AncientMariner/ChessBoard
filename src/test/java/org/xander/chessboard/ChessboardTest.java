package org.xander.chessboard;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap)
                                        .withKing()
                                        .withQueen()
                                        .withBishop()
                                        .withRook()
                                        .withKnight()
                                        .build();
        assertTrue(figureQuantityMap.equals(chessboard.getFigureQuantityMap()));
    }

    //runs too long, for now ignored
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
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withKing()
                                        .withQueen()
                                        .withBishop()
                                        .withRook()
                                        .withKnight()
                                        .build();

        Set<String> boards = chessboard.placeFiguresOnEmptyBoard().collect(Collectors.toSet());
        assertThat("more than 1 figure is present",
                boards.contains("kxkxqxxx\n" +
                                "xxxxxxqx\n" +
                                "qxxxxxxx\n" +
                                "xxbbxbxx\n" +
                                "xxxxxbxr\n" +
                                "xxxxxxxx\n" +
                                "xxxxxxxx\n" +
                                "xxxrxxxx\n"), is(true));


        assertThat("all elements are not present on each board",
                boards.parallelStream()
                .filter(board -> board.contains(KING.getFigureAsString())
                                && board.contains(QUEEN.getFigureAsString())
                                && board.contains(BISHOP.getFigureAsString())
                                && board.contains(ROOK.getFigureAsString())
                                && board.contains(KNIGHT.getFigureAsString())
//                        && board.contains("x")
//                        && board.contains(".")
                                && leftOnlyFigures(board).length() == 20
                )
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(26133));
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
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withKing()
                                        .withQueen()
                                        .withBishop()
                                        .withRook()
                                        .withKnight()
                                        .build();

        Set<String> boards = chessboard.placeFiguresOnEmptyBoard().collect(Collectors.toSet());
        assertThat("more than 1 figure is present",
                boards.contains("xxxxxqx\n" +
                                "xxqxxxx\n" +
                                "bxxxxxb\n" +
                                "xxxrxxx\n" +
                                "bxxxxxk\n" +
                                "xxxxqxx\n" +
                                "nbxxxxk\n"), is(true));


        assertThat("all elements are not present on each board",
                boards.stream()
                .filter(board -> board.contains(KING.getFigureAsString())
                        && board.contains(QUEEN.getFigureAsString())
                        && board.contains(BISHOP.getFigureAsString())
                        && board.contains(ROOK.getFigureAsString())
                        && board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && leftOnlyFigures(board).length() > 10
                )
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(32));
    }

    @Test
    public void readmeRequirement() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(QUEEN.toString(), 2);
        figureQuantityMap.put(BISHOP.toString(), 2);
        figureQuantityMap.put(KNIGHT.toString(), 1);

        int dimension = 7;
        Chessboard chessboard1 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withKing()
                                        .withQueen()
                                        .withBishop()
                                        .withKnight()
                                        .build();
        Chessboard chessboard2 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withQueen()
                                        .withBishop()
                                        .withKing()
                                        .withKnight()
                                        .build();
        //todo uncommenting highly affects performance
//        Chessboard chessboard3 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKnight().withBishop().withKing().withQueen().build();

        Set<String> collect1 = chessboard1.placeFiguresOnEmptyBoard().collect(Collectors.toSet());
        Set<String> collect2 = chessboard2.placeFiguresOnEmptyBoard().collect(Collectors.toSet());
        Set<String> collect = new HashSet<>();
        collect.addAll(collect1);
        collect.addAll(collect2);

        assertThat("all elements are not present on each board",
                collect
                .parallelStream()
                .filter(board -> board.contains(KING.getFigureAsString())
                        && board.contains(QUEEN.getFigureAsString())
                        && board.contains(BISHOP.getFigureAsString())
                        && !board.contains(ROOK.getFigureAsString())
                        && board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && leftOnlyFigures(board).length() == 7)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(3063828));
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
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withKing()
                                        .withQueen()
                                        .withBishop()
                                        .withRook()
                                        .withKnight()
                                        .build();

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

        assertThat("all elements are not present on each board",
                prepareBoardsWithKingAndRook(figureQuantityMap, dimension)
                .parallel()
                .filter(board -> board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && !board.contains(BISHOP.getFigureAsString())
                        && board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 4)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(153064));
    }

    private Stream<String> prepareBoardsWithKingAndRook(Map<String, Integer> figureQuantityMap, int dimension) {
        Chessboard chessboard1 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withKing()
                                        .withRook()
                                        .build();
        Chessboard chessboard2 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withRook()
                                        .withKing()
                                        .build();

        Set<String> boards = new HashSet<>();
        boards.addAll(chessboard1.placeFiguresOnEmptyBoard().collect(Collectors.toSet()));
        boards.addAll(chessboard2.placeFiguresOnEmptyBoard().collect(Collectors.toSet()));

        return boards.stream();
    }

    //takes at least 1,5 minutes
    @Ignore
    @Test
    public void kingRookAndBishop() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(ROOK.toString(), 2);
        figureQuantityMap.put(BISHOP.toString(), 2);
        int dimension = 7;

        assertThat("all elements are not present on each board",
                prepareBoardsWithKingRookAndBishop(figureQuantityMap, dimension)
                .parallel()
                .filter(board -> board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && board.contains(BISHOP.getFigureAsString())
                        && board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 6)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(3613364));
    }

    private Stream<String> prepareBoardsWithKingRookAndBishop(Map<String, Integer> figureQuantityMap, int dimension) {
        Chessboard chessboard1 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withKing()
                                        .withRook()
                                        .withBishop()
                                        .build();
//        Chessboard chessboard2 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKing().withBishop().withRook().build();

//        Chessboard chessboard3 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withBishop().withKing().withRook().build();
//        Chessboard chessboard4 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withBishop().withRook().withKing().build();

//        Chessboard chessboard5 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withRook().withBishop().withKing().build();
//        Chessboard chessboard6 = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withRook().withKing().withBishop().build();

        Set<String> boards = new HashSet<>();
        boards.addAll(chessboard1.placeFiguresOnEmptyBoard().collect(Collectors.toSet()));
//        boards.addAll(chessboard2.placeFiguresOnEmptyBoard());
//        boards.addAll(chessboard3.placeFiguresOnEmptyBoard());
//        boards.addAll(chessboard4.placeFiguresOnEmptyBoard());
//        boards.addAll(chessboard5.placeFiguresOnEmptyBoard());
//        boards.addAll(chessboard6.placeFiguresOnEmptyBoard());
        return boards.stream();
    }

    @Test
    public void kingRookAndKnight() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(ROOK.toString(), 2);
        figureQuantityMap.put(KNIGHT.toString(), 2);
        int dimension = 5;

        assertThat("all elements are not present on each board",
                prepareBoardsWithKingRookAndKnight(figureQuantityMap, dimension)
                .filter(board ->
                        !board.contains(QUEEN.getFigureAsString())
                                && !board.contains(BISHOP.getFigureAsString())
                                && board.contains(KNIGHT.getFigureAsString())
                                && board.contains(FIELD_UNDER_ATTACK_STRING)
                                && leftOnlyFigures(board).length() > 5)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(1156));
    }

    private Stream<String> prepareBoardsWithKingRookAndKnight(Map<String, Integer> figureQuantityMap, int dimension) {
        Chessboard chessboard1 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withKing()
                                        .withRook()
                                        .withKnight()
                                        .build();
        Chessboard chessboard2 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withKing()
                                        .withKnight()
                                        .withRook()
                                        .build();
        Chessboard chessboard3 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withRook()
                                        .withKing()
                                        .withKnight()
                                        .build();
        Chessboard chessboard4 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withRook()
                                        .withKnight()
                                        .withKing()
                                        .build();
        Chessboard chessboard5 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withKnight()
                                        .withRook()
                                        .withKing()
                                        .build();
        Chessboard chessboard6 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withKnight()
                                        .withKing()
                                        .withRook()
                                        .build();

        Set<String> boards = new HashSet<>();
        boards.addAll(chessboard1.placeFiguresOnEmptyBoard().collect(Collectors.toSet()));
        boards.addAll(chessboard2.placeFiguresOnEmptyBoard().collect(Collectors.toSet()));
        boards.addAll(chessboard3.placeFiguresOnEmptyBoard().collect(Collectors.toSet()));
        boards.addAll(chessboard4.placeFiguresOnEmptyBoard().collect(Collectors.toSet()));
        boards.addAll(chessboard5.placeFiguresOnEmptyBoard().collect(Collectors.toSet()));
        boards.addAll(chessboard6.placeFiguresOnEmptyBoard().collect(Collectors.toSet()));
        return boards.stream();
    }

    @Test
    public void boardSize8KingAndRook() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 2);
        figureQuantityMap.put(ROOK.toString(), 2);
        int dimension = 8;

        assertThat("all elements are not present on each board",
                prepareBoardsWithKingAndRook(figureQuantityMap, dimension)
                .parallel()
                .filter(board -> board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && !board.contains(BISHOP.getFigureAsString())
                        && board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && leftOnlyFigures(board).length() == 4)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(657390));
    }

    @Test
    public void onlyBishopOnFilledBoard() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 2);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(DIMENSION_6).withBishop().build();

        assertThat("all elements are not present on each board",
                chessboard.placeFiguresOnBoard("bbbbbb\n" +
                                                          "b....b\n" +
                                                          "b....b\n" +
                                                          "b....b\n" +
                                                          "b....b\n" +
                                                          "bbbbbb\n")
                .parallel()
                .filter(board -> !board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && board.contains(BISHOP.getFigureAsString())
                        && !board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && !board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 20)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(1));
    }

    @Test
    public void onlyRookOnFilledBoard() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(ROOK.toString(), 2);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(DIMENSION_6).withRook().build();

        Set<String> boards = chessboard.placeFiguresOnBoard("rrrrrr\n" +
                                                                       "r....r\n" +
                                                                       "r....r\n" +
                                                                       "r....r\n" +
                                                                       "r....r\n" +
                                                                       "rrrrrr\n").collect(Collectors.toSet());
        assertThat("there is no boards", boards.size() == 1, is(true));

    }

    @Test
    public void onlyBishopOnEmptyBoard() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 2);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(DIMENSION_6)
                                        .withBishop()
                                        .build();

        Set<String> boards = chessboard.placeFiguresOnEmptyBoard().collect(Collectors.toSet());

        assertThat("all elements are not present on each board",
                boards.stream()
                .filter(board -> !board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && board.contains(BISHOP.getFigureAsString())
                        && !board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 2)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(520));
    }

    @Test
    public void bishopOnFullBoard() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 2);
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(DIMENSION_6)
                                        .withBishop()
                                        .build();

        assertThat("all elements are not present on each board",
                chessboard.placeFiguresOnBoard("bbbbbb\n" +
                                                          "bbbbbb\n" +
                                                          "bbbbbb\n" +
                                                          "bbbbbb\n" +
                                                          "bbbbbb\n" +
                                                          "bbbbbb\n")
                .filter(board -> !board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && board.contains(BISHOP.getFigureAsString())
                        && !board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && !board.contains(FIELD_UNDER_ATTACK_STRING)
                        && !board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 36)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(1));
    }

    @Test
    public void smallBoardWithKingAndQueen() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KING.toString(), 1);
        figureQuantityMap.put(QUEEN.toString(), 1);

        int dimension = 2;
        Set<String> boards = prepareBoardsWithKingAndQueen(figureQuantityMap, dimension);
        assertThat("more than 1 figure is present", boards.contains("kx\n" + "xx\n"), is(true));

        assertThat("all elements are not present on each board",
                boards.stream()
                .filter(board -> !board.contains(BISHOP.getFigureAsString())
                        && !board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && !board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 1)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(8));
    }

    private Set<String> prepareBoardsWithKingAndQueen(Map<String, Integer> figureQuantityMap, int dimension) {
        Chessboard chessboard1 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withKing()
                                        .withQueen()
                                        .build();
        Chessboard chessboard2 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withQueen()
                                        .withKing()
                                        .build();

        Set<String> boards = new HashSet<>();
        boards.addAll(chessboard1.placeFiguresOnEmptyBoard().collect(Collectors.toSet()));
        boards.addAll(chessboard2.placeFiguresOnEmptyBoard().collect(Collectors.toSet()));

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


        assertThat("all elements are not present on each board",
                boards.stream()
                .filter(board -> !board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 1)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(8));
    }

    private Set<String> prepareBoardsWithBishopAndRook(Map<String, Integer> figureQuantityMap, int dimension) {
        Chessboard chessboard1 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withRook()
                                        .withBishop()
                                        .build();
        Chessboard chessboard2 = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withBishop()
                                        .withRook()
                                        .build();

        Set<String> boards = new HashSet<>();
        boards.addAll(chessboard1.placeFiguresOnEmptyBoard().collect(Collectors.toSet()));
        boards.addAll(chessboard2.placeFiguresOnEmptyBoard().collect(Collectors.toSet()));
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

        assertThat("all elements are not present on each board",
                boards.stream()
                .filter(board -> !board.contains(KING.getFigureAsString())
                        && !board.contains(QUEEN.getFigureAsString())
                        && board.contains(BISHOP.getFigureAsString())
                        && board.contains(ROOK.getFigureAsString())
                        && !board.contains(KNIGHT.getFigureAsString())
                        && board.contains(FIELD_UNDER_ATTACK_STRING)
                        && board.contains(EMPTY_FIELD_STRING)
                        && leftOnlyFigures(board).length() == 2)
                .map(e -> 1)
                .reduce(0, (x, y) -> x + y), is(16));
    }

    /**
     * The following examples with queens are taken from:
     * https://en.wikipedia.org/wiki/Eight_queens_puzzle
     * */
    @Test
    public void fiveQueens() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        int size5 = 5;

        assertBoardsSize(size5, buildBoardWithQueens(figureQuantityMap, size5), 10);
    }

    @Test
    public void sixQueens() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        int size6 = 6;

        assertBoardsSize(size6, buildBoardWithQueens(figureQuantityMap, size6), 4);
    }

    @Test
    public void sevenQueens() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        int size7 = 7;

        assertBoardsSize(size7, buildBoardWithQueens(figureQuantityMap, size7), 40);
    }

    @Test
    public void eightQueens() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        int size8 = 8;

        assertBoardsSize(size8, buildBoardWithQueens(figureQuantityMap, size8), 92);
    }

    @Test
    public void nineQueens() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        int size9 = 9;

        assertBoardsSize(size9, buildBoardWithQueens(figureQuantityMap, size9), 352);
    }

    //takes too much time
    @Ignore
    @Test
    public void tenQueens() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        int size10 = 10;

        assertBoardsSize(size10, buildBoardWithQueens(figureQuantityMap, size10), 724);
    }

    private Chessboard buildBoardWithQueens(Map<String, Integer> figureQuantityMap, int dimension) {
        figureQuantityMap.put(QUEEN.toString(), dimension);

        return Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withQueen().build();
    }

    private void assertBoardsSize(int size8, Chessboard chessboard, int expectedBoardsSize) {
        assertThat("all elements are not present on each board",
                chessboard.placeFiguresOnEmptyBoard()
                        .parallel()
                        .filter(board -> !board.contains(KING.getFigureAsString())
                                && board.contains(QUEEN.getFigureAsString())
                                && !board.contains(BISHOP.getFigureAsString())
                                && !board.contains(ROOK.getFigureAsString())
                                && !board.contains(KNIGHT.getFigureAsString())
                                && board.contains(FIELD_UNDER_ATTACK_STRING)
                                && leftOnlyFigures(board).length() == size8)
                        .map(e -> 1)
                        .reduce(0, (x, y) -> x + y), is(expectedBoardsSize));
    }

    /**
     * On an 8×8 board one can place
     * 32 knights,
     * or 14 bishops, 16 kings
     * or eight rooks,
     * so that no two pieces attack each other
     *
     * The following examples with rooks are taken from:
     * https://en.wikipedia.org/wiki/Rook_polynomial
     * */
    @Test
    public void eightRooks() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        int size8 = 8;
        figureQuantityMap.put(ROOK.toString(), size8);

        int dimension = size8;
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withRook().build();

        assertThat("all elements are not present on each board",
                chessboard.placeFiguresOnEmptyBoard()
                        .parallel()
                        .filter(board -> !board.contains(KING.getFigureAsString())
                                && !board.contains(QUEEN.getFigureAsString())
                                && !board.contains(BISHOP.getFigureAsString())
                                && board.contains(ROOK.getFigureAsString())
                                && !board.contains(KNIGHT.getFigureAsString())
                                && board.contains(FIELD_UNDER_ATTACK_STRING)
                                && leftOnlyFigures(board).length() == size8)
                        .map(e -> 1)
                        .reduce(0, (x, y) -> x + y), is(40320));
    }

    @Ignore
    @Test
    public void statementOfMaxFiguresOnBoard32Knights() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(KNIGHT.toString(), 32);

        int dimension = 8;
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap).withDimension(dimension).withKnight().build();
        assertThat("all elements are not present on each board",
                chessboard.placeFiguresOnEmptyBoard()
                        .parallel()
                        .filter(board -> !board.contains(KING.getFigureAsString())
                                && !board.contains(QUEEN.getFigureAsString())
                                && !board.contains(BISHOP.getFigureAsString())
                                && !board.contains(ROOK.getFigureAsString())
                                && board.contains(KNIGHT.getFigureAsString())
                                && board.contains(FIELD_UNDER_ATTACK_STRING)
                                && leftOnlyFigures(board).length() == 8)
                        .map(e -> 1)
                        .reduce(0, (x, y) -> x + y), is(3063828));
    }

    @Ignore
    @Test
    public void statementOfMaxFiguresOnBoard14Bishops16Kings() {
        Map<String, Integer> figureQuantityMap = new HashMap<>();
        figureQuantityMap.put(BISHOP.toString(), 14);
        figureQuantityMap.put(KING.toString(), 16);

        int dimension = 8;
        Chessboard chessboard = Chessboard.newBuilder(figureQuantityMap)
                                        .withDimension(dimension)
                                        .withBishop()
                                        .withKing()
                                        .build();

        assertThat("all elements are not present on each board",
                chessboard.placeFiguresOnEmptyBoard()
                        .parallel()
                        .filter(board -> board.contains(KING.getFigureAsString())
                                && !board.contains(QUEEN.getFigureAsString())
                                && board.contains(BISHOP.getFigureAsString())
                                && !board.contains(ROOK.getFigureAsString())
                                && !board.contains(KNIGHT.getFigureAsString())
                                && board.contains(FIELD_UNDER_ATTACK_STRING)
                                && leftOnlyFigures(board).length() == 8)
                        .map(e -> 1)
                        .reduce(0, (x, y) -> x + y), is(3063828));
    }
}
