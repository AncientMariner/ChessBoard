package org.xander.chessboard.figuresPlacement;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class FiguresPlacement implements PlacementBehavior {
    static final char EMPTY_FIELD_CHAR = '.';
    private static final String EMPTY_FIELD_STRING = ".";
    static final char FIELD_UNDER_ATTACK = 'x';
    static final char NEXT_LINE_FIELD = '\n';

    final BoardUtils boardUtils = new BoardUtils();

    public Set<String> placeFiguresOnBoards(Set<String> boards) {
        Set<String> boardsRepresentation = boards.parallelStream()
                    .filter(e -> e.contains(EMPTY_FIELD_STRING))
                    .map(this::placeCertainFigureOnBoard)
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
        int numberOfEmptyPlaces = (int) IntStream
                .range(0, board.length())
                .filter(i -> board.charAt(i) == EMPTY_FIELD_CHAR)
                .boxed().count();

        Set<String> setOfPossibleBoards = new HashSet<>(numberOfEmptyPlaces);
        StringBuilder chessboardWithFigures;

        for (int i = 0; i < board.length(); i++) {
            char[] boardArray = board.toCharArray();

            if (boardArray[i] == EMPTY_FIELD_CHAR) {
                boardArray[i] = figure;
                chessboardWithFigures = new StringBuilder();
                for (char element : boardArray) {
                    chessboardWithFigures.append(element);
                }
                setOfPossibleBoards.add(chessboardWithFigures.toString());
            }
        }
        return setOfPossibleBoards;
    }
}
