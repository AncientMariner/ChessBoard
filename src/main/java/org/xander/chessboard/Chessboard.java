package org.xander.chessboard;

import org.xander.chessboard.figures.Bishop;
import org.xander.chessboard.figures.FiguresChain;
import org.xander.chessboard.figures.King;
import org.xander.chessboard.figures.Knight;
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
import static org.xander.chessboard.figuresPlacement.BoardUtils.checkBoard;

public class Chessboard {
    private int dimension;
    private int boardSize;
    private Map<String, Integer> figureQuantityMap;
    private FiguresChain figureChain;

    private Chessboard() {
    }

    private Set<String> placeFigures(Set<String> boards) {
        return figureChain.placeFigures(boards);
    }

    public int getDimension() {
        return dimension;
    }

    public Map<String, Integer> getFigureQuantityMap() {
        return figureQuantityMap;
    }

    public String drawEmptyBoard() {
        int xDimension = dimension;
        int yDimension = dimension;
        calculateBoardSizeBasedOn(xDimension, yDimension);

        StringBuilder chessBoard = new StringBuilder();
        for (int y = 0; y < yDimension; y++) {
            for (int x = 0; x < xDimension; x++) {
                chessBoard.append(".");
            }
            chessBoard.append("\n");
        }
        return chessBoard.toString();
    }

    private void calculateBoardSizeBasedOn(int xDimension, int yDimension) {
        boardSize = xDimension * yDimension;
    }

    public Set<String> placeFiguresOnBoard(String initialBoard) {
        if (initialBoard != null && !initialBoard.isEmpty()) {
            int length = (int) Math.sqrt(initialBoard.length()) + 1;
            calculateBoardSizeBasedOn(length, length);
        }
        int numberOfKings = extractA(KING.toString());
        int numberOfQueens = extractA(QUEEN.toString());
        int numberOfBishops = extractA(BISHOP.toString());
        int numberOfRooks = extractA(ROOK.toString());
        int numberOfKnights = extractA(KNIGHT.toString());
        int sumOfAllFigures = numberOfBishops + numberOfKings + numberOfKnights + numberOfQueens + numberOfRooks;

        if (sumOfAllFigures > boardSize) {
            throw new IllegalStateException("There are more figures than places to put them");
        }

        checkBoard(initialBoard, dimension);
        HashSet<String> initialBoards = new HashSet<>();
        initialBoards.add(initialBoard);
        Set<String> boards = placeFigures(initialBoards);

//        String boardWithKnights = knightsPlacement.placeOneFigureOnBoard(emptyBoard);

//        String boardWithKnightsAndAttackPlaces = knightsPlacement.calculateAttackPlaces(boardWithKnights);
//        String boardWithKnightsAndRooks = rooksPlacement.placeOneFigureOnBoard(boardWithKnightsAndAttackPlaces);
        //todo: in this situation rook should not be standing at the first line
//        String boardWithKaRaAP = rooksPlacement.calculateAttackPlaces(boardWithKnightsAndRooks);

//        String boardWithKnightsRooksAndBishops = bishopPlacement.placeOneFigureOnBoard(boardWithKaRaAP);

//        System.out.println(boardWithKnightsRooksAndBishops);
//        String boardWithKnightsRooksBishopsAndQueens = placeQueens(numberOfQueens, boardWithKnightsRooksAndBishops);
//        String boardWithAllFigures = placeKings(numberOfKings, boardWithKnightsRooksBishopsAndQueens);
        return boards;
    }

    private int extractA(String figure) {
        if (figureQuantityMap.containsKey(figure)) {
            return figureQuantityMap.get(figure);
        }
        return 0;
    }

    public static Builder newBuilder(Map<String, Integer> figureQuantityMap) {
        return new Chessboard().new Builder(figureQuantityMap);
    }

    public class Builder {
        private FiguresChain previousFiguresChain;
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
            Chessboard.this.figureQuantityMap = figureQuantityMap;
        }

        public Builder withDimension(int dimension) {
            Chessboard.this.dimension = dimension;
            return this;
        }

        public Builder withKing() {
            prepareFiguresChain(new King(figureQuantityMap));
            return this;
        }

        public Builder withQueen() {
            prepareFiguresChain(new Queen(figureQuantityMap));
            return this;
        }

        public Builder withBishop() {
            prepareFiguresChain(new Bishop(figureQuantityMap));
            return this;
        }

        public Builder withRook() {
            prepareFiguresChain(new Rook(figureQuantityMap));
            return this;
        }

        public Builder withKnight() {
            prepareFiguresChain(new Knight(figureQuantityMap));
            return this;
        }

        public Chessboard build() {
            return Chessboard.this;
        }

        private void prepareFiguresChain(FiguresChain figure) {
            if (Chessboard.this.figureChain == null) {
                Chessboard.this.figureChain = figure;
                previousFiguresChain = figure;
            } else {
                previousFiguresChain.setNextFigure(figure);
                previousFiguresChain = figure;
            }
        }
    }
}
