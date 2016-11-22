package org.xander.chessboard;

import org.xander.chessboard.figures.Bishop;
import org.xander.chessboard.figures.FiguresChain;
import org.xander.chessboard.figures.King;
import org.xander.chessboard.figures.Knight;
import org.xander.chessboard.figures.NoMoreFigures;
import org.xander.chessboard.figures.Queen;
import org.xander.chessboard.figures.Rook;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figures.Figure.KING;
import static org.xander.chessboard.figures.Figure.KNIGHT;
import static org.xander.chessboard.figures.Figure.QUEEN;
import static org.xander.chessboard.figures.Figure.ROOK;
import static org.xander.chessboard.figuresPlacement.BoardUtils.checkBoard;
import static org.xander.chessboard.figuresPlacement.FiguresPlacement.EMPTY_FIELD_STRING;
import static org.xander.chessboard.figuresPlacement.FiguresPlacement.NEXT_LINE_FIELD_STRING;

public class Chessboard {
    private int dimension;
    private FiguresChain figureChain;

    private Chessboard() {
    }

    public int getDimension() {
        return dimension;
    }

    public Map<String, Integer> getFigureQuantityMap() {
        return figureChain.getFigureQuantityMap();
    }

    private String drawEmptyBoard() {
        StringBuilder chessBoard = new StringBuilder();

        IntStream.range(0, dimension).forEach((y) -> {
            IntStream.range(0, dimension).forEach((x) -> chessBoard.append(EMPTY_FIELD_STRING));
            chessBoard.append(NEXT_LINE_FIELD_STRING);
        });
        return chessBoard.toString();
    }

    public Stream<String> placeFiguresOnEmptyBoard() {
        return placeFiguresOnBoard(drawEmptyBoard());
    }

    public Stream<String> placeFiguresOnBoard(String initialBoard) {
        int numberOfKings = figureChain.extractA(KING.toString());
        int numberOfQueens = figureChain.extractA(QUEEN.toString());
        int numberOfBishops = figureChain.extractA(BISHOP.toString());
        int numberOfRooks = figureChain.extractA(ROOK.toString());
        int numberOfKnights = figureChain.extractA(KNIGHT.toString());
        int sumOfAllFigures = numberOfBishops + numberOfKings + numberOfKnights + numberOfQueens + numberOfRooks;

        if (Objects.nonNull(initialBoard) && !initialBoard.isEmpty() && sumOfAllFigures > initialBoard.length()) {
            throw new IllegalStateException("There are more figures than places to put them");
        }

        checkBoard(initialBoard, dimension);
        HashSet<String> initialBoards = new HashSet<>();
        initialBoards.add(initialBoard);

        return figureChain.placeFigures(initialBoards.stream());
    }

    public static Builder newBuilder(Map<String, Integer> figureQuantityMap) {
        return new Chessboard().new Builder(figureQuantityMap);
    }

    public class Builder {
        private FiguresChain previousFiguresChain;
        private Map<String, Integer> figureQuantityMap;

        private Builder(Map<String, Integer> figureQuantityMap) {
            if (Objects.isNull(figureQuantityMap) || figureQuantityMap.isEmpty()) {
                throw new IllegalStateException("please provide the figures to put on the board");
            }
            Set<String> possibleFigures = new HashSet<>();
            possibleFigures.add(KING.toString());
            possibleFigures.add(QUEEN.toString());
            possibleFigures.add(BISHOP.toString());
            possibleFigures.add(ROOK.toString());
            possibleFigures.add(KNIGHT.toString());

            Set<String> givenFigures = figureQuantityMap.keySet();

            if (givenFigures.size() > 0 && givenFigures.stream().anyMatch(e -> !possibleFigures.contains(e))) {
                throw new IllegalStateException("not desired figure is present");
            }
            this.figureQuantityMap = figureQuantityMap;
        }

        Builder withDimension(int dimension) {
            Chessboard.this.dimension = dimension;
            return this;
        }

        Builder withKing() {
            prepareFiguresChain(new King(figureQuantityMap));
            return this;
        }

        Builder withQueen() {
            prepareFiguresChain(new Queen(figureQuantityMap));
            return this;
        }

        Builder withBishop() {
            prepareFiguresChain(new Bishop(figureQuantityMap));
            return this;
        }

        Builder withRook() {
            prepareFiguresChain(new Rook(figureQuantityMap));
            return this;
        }

        Builder withKnight() {
            prepareFiguresChain(new Knight(figureQuantityMap));
            return this;
        }

        private void noMoreFigures() {
            prepareFiguresChain(new NoMoreFigures(figureQuantityMap));
        }

        Chessboard build() {
            noMoreFigures();
            return Chessboard.this;
        }

        private void prepareFiguresChain(FiguresChain figure) {
            if (Objects.isNull(Chessboard.this.figureChain)) {
                Chessboard.this.figureChain = figure;
                previousFiguresChain = figure;
            } else {
                previousFiguresChain.setNextFigure(figure);
                previousFiguresChain = figure;
            }
        }
    }
}
