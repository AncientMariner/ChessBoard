package org.xander.chessboard.figuresPlacement;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figures.Figure.KING;
import static org.xander.chessboard.figures.Figure.KNIGHT;
import static org.xander.chessboard.figures.Figure.QUEEN;
import static org.xander.chessboard.figures.Figure.ROOK;
import static org.xander.chessboard.figuresPlacement.BoardUtils.checkBoard;

public abstract class FiguresPlacement implements PlacementBehavior {
    static final char EMPTY_FIELD_CHAR = '.';
    public static final String EMPTY_FIELD_STRING = ".";
    static final char FIELD_UNDER_ATTACK_CHAR = 'x';
    public static final String FIELD_UNDER_ATTACK_STRING = "x";
    static final char NEXT_LINE_FIELD_CHAR = '\n';
    public static final String NEXT_LINE_FIELD_STRING = "\n";

    public Set<String> placeFiguresOnBoards(Set<String> boards) {
        Set<String> initialBoardsWithoutAttackPlaces = boards.parallelStream()
                .filter(board -> board.contains(KING.getFigureAsString())
                              || board.contains(QUEEN.getFigureAsString())
                              || board.contains(BISHOP.getFigureAsString())
                              || board.contains(ROOK.getFigureAsString())
                              || board.contains(KNIGHT.getFigureAsString()))
                .filter(e -> !e.contains(FIELD_UNDER_ATTACK_STRING))
                .filter(e -> e.contains(EMPTY_FIELD_STRING))
                .map(this::calculateAttackPlaces)
                .collect(Collectors.toSet());

        if (initialBoardsWithoutAttackPlaces.isEmpty()) {
            initialBoardsWithoutAttackPlaces.addAll(boards);
        }
        Set<String> boardsWithNewFigureAndAttackPlaces = initialBoardsWithoutAttackPlaces
                .parallelStream()
                .filter(e -> e.contains(EMPTY_FIELD_STRING))
                .map(this::placeCertainFigureOnBoard)
                .flatMap(Set::stream)
                .map(this::calculateAttackPlaces)
                .collect(Collectors.toSet());

        if (boardsWithNewFigureAndAttackPlaces.isEmpty()) {
            boardsWithNewFigureAndAttackPlaces.addAll(initialBoardsWithoutAttackPlaces);
        }
        return boardsWithNewFigureAndAttackPlaces;
    }

    @Override
    public String calculateAttackPlaces(String board) {
        //mind the '\n' character
        int dimension = (int) Math.sqrt(board.length()) + 1;
        checkBoard(board, dimension);
        char[] boardElements = board.toCharArray();

        calculateAttackPlaces(dimension, boardElements);
        return BoardUtils.transformArrayToString(boardElements);
    }

    protected void calculateAttackPlaces(int dimension, char[] boardElements) {
        IntStream.range(0, boardElements.length)
                .filter(e -> boardElements[e] == getFigure())
                .forEach(position -> attackPlaceForPosition(dimension, boardElements, position));
    }

    protected abstract void attackPlaceForPosition(int dimension, char[] boardElements, int position);

    @Override
    public Set<String> placeCertainFigureOnBoard(String board) {
        return placeFigureOnBoard(getFigure(), board);
    }

    protected abstract char getFigure();

    Set<String> placeFigureOnBoard(char figure, String board) {
        return Stream.iterate(0, index -> index + 1)
                .limit(board.length())
                .map(index -> placeFigureAtPositionOnBoard(figure, index, board))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    private Set<String> placeFigureAtPositionOnBoard(char figure, int position, String board) {
        Set<String> setOfPossibleBoards = new HashSet<>((int) IntStream
                                                        .range(0, board.length())
                                                        .filter(i -> board.charAt(i) == EMPTY_FIELD_CHAR)
                                                        .boxed()
                                                        .count());
        char[] boardArray = board.toCharArray();

        if (boardArray[position] == EMPTY_FIELD_CHAR) {
            boardArray[position] = figure;
            setOfPossibleBoards.add(BoardUtils.transformArrayToString(boardArray));
        }
        return setOfPossibleBoards;
    }
}
