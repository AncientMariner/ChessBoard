package org.xander.chessboard;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.xander.chessboard.figures.Figure.KING;

public abstract class FiguresPlacement implements PlacementBehavior {
    public static final char EMPTY_FIELD = '.';
    public static final char FIELD_UNDER_ATTACK = 'x';
    public static final char NEXT_LINE_FIELD = '\n';

    protected final BoardUtils boardUtils = new BoardUtils();

    public String placeNumberOfFiguresOnBoard(int numberOfFigures, String board) {
        if (board.contains(".")) {
            String boardWithFigures = board;
            while (numberOfFigures > 0) {
                boardWithFigures = placeOneFigureOnBoardSequentially(boardWithFigures);
                boardWithFigures = calculateAttackPlaces(boardWithFigures);
                numberOfFigures--;
            }
            return boardWithFigures;
        }
        return board;
    }

    public String placeFigureOnBoard(char figure, String board) {
        StringBuilder chessboardWithFigures = new StringBuilder();
        char[] boardElements = board.toCharArray();
        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] != NEXT_LINE_FIELD && boardElements[i] == EMPTY_FIELD) {
                boardElements[i] = figure;
                break;
            }
        }
        for (char element : boardElements) {
            chessboardWithFigures.append(element);
        }
        return chessboardWithFigures.toString();
    }


    public String placeNumberOfFiguresOnBoardAll(int numberOfFigures, String board) {
        if (board.contains(".")) {
            String boardWithFigures = board;
            Set<String> boards = new HashSet<>();
            Set<String> boardsWithFiguresAndAttackPlaces = new HashSet<>();

            while (numberOfFigures > 0) {
                if (boards.isEmpty()) {
                    boards = placeFigureOnBoardRandomly('k', board);
                    for (String boardWith1Figure : boards) {
                        boardsWithFiguresAndAttackPlaces.add(calculateAttackPlaces1(boardWith1Figure));
                    }
//                    boards.forEach(e -> boardsWithFiguresAndAttackPlaces.add(e));

                } else {
                    Set<String> strings = new HashSet<>();

                    for (String boardToPlace : boardsWithFiguresAndAttackPlaces) {
                        Set<String> boardWithMoreFigures = placeFigureOnBoardRandomly('k', boardToPlace);
                        strings.addAll(boardWithMoreFigures);
                    }
                    for (String boardWith1FigureAttackAndAnotherFigure : strings) {
                        boardsWithFiguresAndAttackPlaces.add(calculateAttackPlaces1(boardWith1FigureAttackAndAnotherFigure));
                    }
                }
                numberOfFigures--;
            }
            return boardWithFigures;
        }
        return board;
    }


    public String calculateAttackPlaces1(String board) {
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = (int) Math.sqrt(board.length()) + 1;
        Chessboard.checkBoard(board, dimension);

        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] == KING.getFigure()) {
                placeHorizontally(boardElements, i, dimension);
                placeVertically(boardElements, i, dimension);
                placeDiagonallyAbove(boardElements, i, dimension);
                placeDiagonallyBelow(boardElements, i, dimension);
            }
        }
        return boardUtils.transformArrayToStringBuilder(boardElements);
    }

    private void placeHorizontally(char[] boardElements, int position, int dimension) {
        if (position % dimension + 1 < dimension) {
            if (boardElements[position + 1] != '\n' && boardElements[position + 1] == EMPTY_FIELD) {
                boardElements[position + 1] = FIELD_UNDER_ATTACK;
            }
        }
        if (position % dimension - 1 >= 0) {
            if (boardElements[position - 1] != '\n' && boardElements[position - 1] == EMPTY_FIELD) {
                boardElements[position - 1] = FIELD_UNDER_ATTACK;
            }
        }
    }

    private void placeVertically(char[] boardElements, int position, int dimension) {
        if (position + dimension < boardElements.length) {
            if (boardElements[position + dimension] != '\n' && boardElements[position + dimension] == EMPTY_FIELD) {
                boardElements[position + dimension] = FIELD_UNDER_ATTACK;
            }
        }

        if (position - dimension >= 0) {
            if (boardElements[position - dimension] != '\n' && boardElements[position - dimension] == EMPTY_FIELD) {
                boardElements[position - dimension] = FIELD_UNDER_ATTACK;
            }
        }
    }

    private void placeDiagonallyAbove(char[] boardElements, int position, int dimension) {
        if (position - dimension - 1 >= 0 && (position - dimension - 1) % dimension >= 0 ) {
            if (boardElements[position - dimension - 1] != '\n'
                    && boardElements[position - dimension - 1] == EMPTY_FIELD) {
                boardElements[position - dimension - 1] = FIELD_UNDER_ATTACK;
            }
        }

        if (position - dimension + 1 >= 0 && (position - dimension + 1) % dimension < dimension - 1) {
            if (boardElements[position - dimension + 1] != '\n'
                    && boardElements[position - dimension + 1] == EMPTY_FIELD) {
                boardElements[position - dimension + 1] = FIELD_UNDER_ATTACK;
            }
        }
    }

    private void placeDiagonallyBelow(char[] boardElements, int position, int dimension) {
        if((position + dimension - 1) % dimension < dimension
                && position + dimension - 1 < boardElements.length) {
            if (boardElements[position + dimension - 1] != '\n'
                    && boardElements[position + dimension - 1] == EMPTY_FIELD) {
                boardElements[position + dimension - 1] = FIELD_UNDER_ATTACK;
            }
        }

        if(position + dimension + 1 < boardElements.length
                && (position + dimension + 1) % dimension < dimension) {
            if (boardElements[position + dimension + 1] != '\n'
                    && boardElements[position + dimension + 1] == EMPTY_FIELD) {
                boardElements[position + dimension + 1] = FIELD_UNDER_ATTACK;
            }
        }
    }

    public Set<String> placeFigureOnBoardRandomly(char figure, String board) {
        List<Integer> numberOfEmptyPlaces = IntStream.range(0, board.length())
                .filter(i -> board.charAt(i) == '.')
                .boxed()
                .collect(Collectors.toList());

        Set<String> setOfPossibleBoards = new HashSet<>(numberOfEmptyPlaces.size());

        for (int i = 0; i < board.length(); i++) {
            char[] boardArray = board.toCharArray();
            if(boardArray[i] != NEXT_LINE_FIELD && boardArray[i] == EMPTY_FIELD){
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
