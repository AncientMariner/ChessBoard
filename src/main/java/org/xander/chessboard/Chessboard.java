package org.xander.chessboard;

import org.xander.chessboard.figures.Bishop;
import org.xander.chessboard.figures.FiguresChain;
import org.xander.chessboard.figures.King;
import org.xander.chessboard.figures.Knight;
import org.xander.chessboard.figures.Queen;
import org.xander.chessboard.figures.Rook;

import java.util.*;

import static org.xander.chessboard.figures.Figure.*;
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
        calculateBoardSize(xDimension, yDimension);

        StringBuilder chessBoard = new StringBuilder();
        for (int y = 0; y < yDimension; y++) {
            for (int x = 0; x < xDimension; x++) {
                chessBoard.append(".");
            }
            chessBoard.append("\n");
        }
        return chessBoard.toString();
    }

    private void calculateBoardSize(int xDimension, int yDimension) {
        boardSize = xDimension * yDimension;
    }

    public Set<String> placeFiguresOnBoard(String emptyBoard) {
        int numberOfKings = extractA(KING.toString(), figureQuantityMap);
        int numberOfQueens = extractA(QUEEN.toString(), figureQuantityMap);
        int numberOfBishops = extractA(BISHOP.toString(), figureQuantityMap);
        int numberOfRooks = extractA(ROOK.toString(), figureQuantityMap);
        int numberOfKnights = extractA(KNIGHT.toString(), figureQuantityMap);
        int sumOfAllFigures = numberOfBishops + numberOfKings + numberOfKnights + numberOfQueens + numberOfRooks;

        if (sumOfAllFigures > boardSize) {
            throw new IllegalStateException("There are more figures than places to put them");
        }

        checkBoard(emptyBoard, dimension);
        HashSet<String> initialBoards = new HashSet<>();
        initialBoards.add(drawEmptyBoard());
        Set<String> boards = placeFigures(initialBoards);

//        String boardWithKnights = knightsPlacement.placeOneFigureOnBoardSequentially(emptyBoard);

//        String boardWithKnightsAndAttackPlaces = knightsPlacement.calculateAttackPlaces(boardWithKnights);
//        String boardWithKnightsAndRooks = rooksPlacement.placeOneFigureOnBoardSequentially(boardWithKnightsAndAttackPlaces);
        //todo: in this situation rook should not be standing at the first line
//        String boardWithKaRaAP = rooksPlacement.calculateAttackPlaces(boardWithKnightsAndRooks);

//        String boardWithKnightsRooksAndBishops = bishopPlacement.placeOneFigureOnBoardSequentially(boardWithKaRaAP);

//        System.out.println(boardWithKnightsRooksAndBishops);
//        String boardWithKnightsRooksBishopsAndQueens = placeQueens(numberOfQueens, boardWithKnightsRooksAndBishops);
//        String boardWithAllFigures = placeKings(numberOfKings, boardWithKnightsRooksBishopsAndQueens);
        return boards;
    }

    private int extractA(String figure, Map<String, Integer> figureQuantityMap) {
        if (figureQuantityMap.containsKey(figure)) {
            return figureQuantityMap.get(figure);
        }
        return 0;
    }

    public static Builder newBuilder(Map<String, Integer> figureQuantityMap) {
        return new Chessboard().new Builder(figureQuantityMap); }

    public class Builder {
        private Builder(Map<String, Integer> figureQuantityMap) {
            Chessboard.this.figureQuantityMap = figureQuantityMap;
        }

        public Builder withDimension(int dimension) {
            Chessboard.this.dimension = dimension;
            return this;
        }

        public Builder withKing() {
            Chessboard.this.figureChain = new King(figureQuantityMap);
            return this;
        }

        public Builder withQueen() {
            FiguresChain queen = new Queen(figureQuantityMap);
            Chessboard.this.figureChain.setNextFigure(queen);
            return this;
        }

        public Builder withBishop() {
            FiguresChain bishop = new Bishop(figureQuantityMap);
            Chessboard.this.figureChain.setNextFigure(bishop);
            return this;
        }

        public Builder withRook() {
            FiguresChain rook = new Rook(figureQuantityMap);
            Chessboard.this.figureChain.setNextFigure(rook);
            return this;
        }

        public Builder withKnight() {
            FiguresChain knight = new Knight(figureQuantityMap);
            Chessboard.this.figureChain.setNextFigure(knight);
            return this;
        }

        public Chessboard build() {
            return Chessboard.this;
        }
    }
}
