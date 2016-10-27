package org.xander.chessboard.figuresPlacement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class FiguresPlacement implements PlacementBehavior {
    public static final char EMPTY_FIELD = '.';
    public static final char FIELD_UNDER_ATTACK = 'x';
    public static final char NEXT_LINE_FIELD = '\n';

    protected final BoardUtils boardUtils = new BoardUtils();

    public Set<String> placeFigureOnBoard(Set<String> boards) {
        Set<String> boardsRepresentation = boards.parallelStream().filter(e -> e.contains("."))
                    .map(this::placeOneFigureOnBoard)
                    .flatMap(Set::stream)
                    .map(this::calculateAttackPlaces)
                    .collect(Collectors.toSet());


        if (boardsRepresentation.size() > 0) {
            boards.clear();
            boards.addAll(boardsRepresentation);
        }
        return boards;
    }


    public Set<String> placeFigureOnBoard(char figure, String board) {
        List<Integer> numberOfEmptyPlaces = IntStream.range(0, board.length())
                .filter(i -> board.charAt(i) == '.')
                .boxed()
                .collect(Collectors.toList());

        Set<String> setOfPossibleBoards = new HashSet<>(numberOfEmptyPlaces.size());

        for (int i = 0; i < board.length(); i++) {
            char[] boardArray = board.toCharArray();
            if (boardArray[i] != NEXT_LINE_FIELD && boardArray[i] == EMPTY_FIELD) {
                boardArray[i] = figure;
                StringBuilder chessboardWithFigures = new StringBuilder();
                for (char element : boardArray) {
                    chessboardWithFigures.append(element);
                }
                setOfPossibleBoards.add(chessboardWithFigures.toString());
            }
        }
        return setOfPossibleBoards;
    }
}
