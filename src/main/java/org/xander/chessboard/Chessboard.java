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
import java.util.Set;

import static org.xander.chessboard.figures.Figure.BISHOP;
import static org.xander.chessboard.figures.Figure.KING;
import static org.xander.chessboard.figures.Figure.KNIGHT;
import static org.xander.chessboard.figures.Figure.QUEEN;
import static org.xander.chessboard.figures.Figure.ROOK;
import static org.xander.chessboard.figuresPlacement.BoardUtils.isBoardLegal;
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

        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                chessBoard.append(EMPTY_FIELD_STRING);
            }
            chessBoard.append(NEXT_LINE_FIELD_STRING);
        }
        return chessBoard.toString();
    }

    public Set<String> placeFiguresOnEmptyBoard() {
        return placeFiguresOnBoard(drawEmptyBoard());
    }

    public Set<String> placeFiguresOnBoard(String initialBoard) {
        int numberOfKings = figureChain.extractA(KING.toString());
        int numberOfQueens = figureChain.extractA(QUEEN.toString());
        int numberOfBishops = figureChain.extractA(BISHOP.toString());
        int numberOfRooks = figureChain.extractA(ROOK.toString());
        int numberOfKnights = figureChain.extractA(KNIGHT.toString());
        int sumOfAllFigures = numberOfBishops + numberOfKings + numberOfKnights + numberOfQueens + numberOfRooks;

        if (initialBoard != null && !initialBoard.isEmpty() && sumOfAllFigures > initialBoard.length()) {
            throw new IllegalStateException("There are more figures than places to put them");
        }

        isBoardLegal(initialBoard, dimension);
        HashSet<String> initialBoards = new HashSet<>();
        initialBoards.add(initialBoard);

        return figureChain.placeFigures(initialBoards);
    }

    public static Builder newBuilder(Map<String, Integer> figureQuantityMap) {
        return new Chessboard().new Builder(figureQuantityMap);
    }

    public class Builder {
        private FiguresChain previousFiguresChain;
        private Map<String, Integer> figureQuantityMap;

        private Builder(Map<String, Integer> figureQuantityMap) {
            if (figureQuantityMap == null || figureQuantityMap.isEmpty()) {
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
            return prepareFiguresChain(new King(figureQuantityMap));
        }

        Builder withQueen() {
            return prepareFiguresChain(new Queen(figureQuantityMap));
        }

        Builder withBishop() {
            return prepareFiguresChain(new Bishop(figureQuantityMap));
        }

        Builder withRook() {
            return prepareFiguresChain(new Rook(figureQuantityMap));
        }

        Builder withKnight() {
            return prepareFiguresChain(new Knight(figureQuantityMap));
        }

        private void noMoreFigures() {
            prepareFiguresChain(new NoMoreFigures(figureQuantityMap));
        }

        Chessboard build() {
            noMoreFigures();
            return Chessboard.this;
        }

        private Builder prepareFiguresChain(FiguresChain figuresChain) {
            if (Chessboard.this.figureChain == null) {
                Chessboard.this.figureChain = figuresChain;
                previousFiguresChain = figuresChain;
            } else {
                previousFiguresChain.setNextFigure(figuresChain);
                previousFiguresChain = figuresChain;
            }
            return this;
        }
    }
}
