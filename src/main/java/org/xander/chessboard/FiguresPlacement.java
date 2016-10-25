package org.xander.chessboard;

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

    public Set<String> placeNumberOfFiguresOnBoard(int numberOfFigures, Set<String> boards) {
        for (String board : boards) {
            if (board.contains(".")) {
                while (numberOfFigures > 0) {
                    Set<String> boardRepresentation;
                    if (boards.isEmpty()) {
                        boardRepresentation = placeOneFigureOnBoardSequentially(board).stream()
                                .map(this::calculateAttackPlaces)
                                .collect(Collectors.toSet());
                    } else {
                        boardRepresentation = boards.stream()
                                .map(this::placeOneFigureOnBoardSequentially)
                                .flatMap(Set::stream)
                                .map(this::calculateAttackPlaces)
                                .collect(Collectors.toSet());
                        boards.clear();
                    }
                    boardRepresentation.forEach(boards::add);

                    numberOfFigures--;
                }
            }
            return boards;
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
