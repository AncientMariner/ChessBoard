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

public abstract class FiguresPlacement implements PlacementBehavior {
    static final char EMPTY_FIELD_CHAR = '.';
    private static final String EMPTY_FIELD_STRING = ".";
    static final char FIELD_UNDER_ATTACK_CHAR = 'x';
    private static final String FIELD_UNDER_ATTACK_STRING = "x";
    static final char NEXT_LINE_FIELD_CHAR = '\n';

    final BoardUtils boardUtils = new BoardUtils();

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

        Set<String> boardsWithNewFigure = initialBoardsWithoutAttackPlaces.parallelStream()
                .filter(e -> e.contains(EMPTY_FIELD_STRING))
                .map(this::placeCertainFigureOnBoard)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        Set<String> boardsWithNewFigureAndAttackPlaces = boardsWithNewFigure.parallelStream()
                .filter(e -> e.contains(EMPTY_FIELD_STRING))
                .map(this::calculateAttackPlaces)
                .collect(Collectors.toSet());

        if (boardsWithNewFigureAndAttackPlaces.isEmpty()) {
            boardsWithNewFigureAndAttackPlaces.addAll(initialBoardsWithoutAttackPlaces);
        }
        return boardsWithNewFigureAndAttackPlaces;
    }

    public Set<String> placeFigureOnBoard(char figure, String board) {
        return Stream.iterate(0, index -> index + 1)
                .limit(board.length())
                .map(index -> place(index, figure, board))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    private Set<String> place(int index, char figure, String board) {
        int numberOfEmptyPlaces = (int) IntStream
                .range(0, board.length())
                .filter(i -> board.charAt(i) == EMPTY_FIELD_CHAR)
                .boxed()
                .count();
        Set<String> setOfPossibleBoards = new HashSet<>(numberOfEmptyPlaces);

        StringBuilder chessboardWithFigures;
        char[] boardArray = board.toCharArray();
        if (boardArray[index] == EMPTY_FIELD_CHAR) {
            boardArray[index] = figure;
            chessboardWithFigures = new StringBuilder();
            for (char element : boardArray) {
                chessboardWithFigures.append(element);
            }
            setOfPossibleBoards.add(chessboardWithFigures.toString());
        }
        return setOfPossibleBoards;
    }
}
