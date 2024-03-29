package org.xander.chessboard.figuresPlacement;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figures.Figure.KING;
import static org.xander.chessboard.figures.Figure.KNIGHT;
import static org.xander.chessboard.figures.Figure.QUEEN;
import static org.xander.chessboard.figures.Figure.ROOK;
import static org.xander.chessboard.figuresPlacement.BoardUtils.isBoardLegal;

public abstract class FiguresPlacement implements PlacementBehavior {
    static final char EMPTY_FIELD_CHAR = '.';
    public static final String EMPTY_FIELD_STRING = ".";
    static final char FIELD_UNDER_ATTACK_CHAR = 'x';
    public static final String FIELD_UNDER_ATTACK_STRING = "x";
    static final char NEXT_LINE_FIELD_CHAR = '\n';
    public static final String NEXT_LINE_FIELD_STRING = "\n";

    public Set<String> placeFiguresOnBoards(Set<String> boards) {

        Set<String> initialBoardsWithAttackPlaces = boards
                .parallelStream()
                .filter(board -> board.contains(KING.getFigureAsString())
                              || board.contains(QUEEN.getFigureAsString())
                              || board.contains(BISHOP.getFigureAsString())
                              || board.contains(ROOK.getFigureAsString())
                              || board.contains(KNIGHT.getFigureAsString()))
                .filter(e -> !e.contains(FIELD_UNDER_ATTACK_STRING))
                .filter(e -> e.contains(EMPTY_FIELD_STRING))
                .map(this::calculateAttackPlaces)
                .collect(Collectors.collectingAndThen(Collectors.toSet(),
                        set -> set.isEmpty() ? boards : set));

        Set<String> boardsWithNewFigureAndAttackPlaces = initialBoardsWithAttackPlaces
                .parallelStream()
                .filter(e -> e.contains(EMPTY_FIELD_STRING))
                .map(this::placeCertainFigureOnBoard)
                .flatMap(Set::stream)
                .map(this::calculateAttackPlaces)
                .collect(Collectors.collectingAndThen(Collectors.toSet(),
                        set -> set.isEmpty() ? initialBoardsWithAttackPlaces : set));

        return boardsWithNewFigureAndAttackPlaces;
    }

    @Override
    public String calculateAttackPlaces(String board) {
        if (board == null) {
            throw new IllegalStateException("board is null");
        }
        //mind the '\n' character
        int dimension = (int) Math.sqrt(board.length()) + 1;
        isBoardLegal(board, dimension);
        char[] boardElements = board.toCharArray();

        calculateAttackPlaces(dimension, boardElements);
        return new String(boardElements);
    }

    private void calculateAttackPlaces(int dimension, char[] boardElements) {
        for (int i = 0; i < boardElements.length; i++) {
            if (boardElements[i] == getFigure()) {
                attackPlaceForPosition(i, boardElements, dimension);
            }
        }
    }

    private boolean isFigurePlacementOnPositionPossible(int position, char[] boardElements, int dimension) {
        return isAttackPlacesForPositionNotHarmingToAnotherFigures(position, boardElements, dimension);
    }

    protected abstract void attackPlaceForPosition(int position, char[] boardElements, int dimension);
    protected abstract boolean isAttackPlacesForPositionNotHarmingToAnotherFigures(int position, char[] boardElements, int dimension);
    protected abstract char getFigure();

    @Override
    public Set<String> placeCertainFigureOnBoard(String board) {
        return placeFigureOnBoard(getFigure(), board);
    }

    Set<String> placeFigureOnBoard(char figure, String board) {
        return Stream.iterate(0, index -> index + 1)
                .limit(board.length())
                .map(index -> placeFigureAtPositionOnBoard(figure, index, board))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    private Set<String> placeFigureAtPositionOnBoard(char figure, int position, String board) {
        int dimension = (int) Math.sqrt(board.length()) + 1;
        isBoardLegal(board, dimension);

        long count = 0L;
        for (int i = 0; i < board.length(); i++) {
            if (board.charAt(i) == EMPTY_FIELD_CHAR) {
                count++;
            }
        }
        Set<String> setOfPossibleBoards = new HashSet<>((int) count);
        char[] boardArray = board.toCharArray();
        if (boardArray[position] == EMPTY_FIELD_CHAR) {
            if (isFigurePlacementOnPositionPossible(position, boardArray, dimension)) {
                boardArray[position] = figure;
                setOfPossibleBoards.add(new String(boardArray));
            }
        }
        return setOfPossibleBoards;
    }
}
