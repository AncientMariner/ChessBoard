package org.xander.chessboard;

import org.xander.chessboard.figures.Bishop;
import org.xander.chessboard.figures.FiguresChain;
import org.xander.chessboard.figures.King;
import org.xander.chessboard.figures.Knight;
import org.xander.chessboard.figures.Queen;
import org.xander.chessboard.figures.Rook;

import java.util.*;

import static org.xander.chessboard.figures.Figure.*;

public class Chessboard {
    private final PlacementBehavior knightsPlacement = new KnightsPlacement(this);
//    private final PlacementBehavior rooksPlacement = new RooksPlacement(this);
//    private final PlacementBehavior bishopPlacement = new BishopsPlacement(this);
    private int dimension;
    private int boardSize;
    private Map<String, Integer> figureQuantityMap;
    private FiguresChain figureChain;

    public Chessboard(Map<String, Integer> figureQuantityMap) {
        this.figureQuantityMap = figureQuantityMap;
        this.figureChain = new King(figureQuantityMap);
        FiguresChain queen = new Queen(figureQuantityMap);
        FiguresChain bishop = new Bishop(figureQuantityMap);
        FiguresChain rook = new Rook(figureQuantityMap);
        FiguresChain knight = new Knight(figureQuantityMap);

        figureChain.setNextFigure(queen);
        queen.setNextFigure(bishop);
        bishop.setNextFigure(rook);
        rook.setNextFigure(knight);
    }

    public void placeFigures() {
        figureChain.placeFigures();
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
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

    public String placeFiguresOnBoard(String emptyBoard) {
        int numberOfKings = extractA(KING.toString(), figureQuantityMap);
        int numberOfQueens = extractA(QUEEN.toString(), figureQuantityMap);
        int numberOfBishops = extractA(BISHOP.toString(), figureQuantityMap);
        int numberOfRooks = extractA(ROOK.toString(), figureQuantityMap);
        int numberOfKnights = extractA(KNIGHT.toString(), figureQuantityMap);
        int sumOfAllFigures = numberOfBishops + numberOfKings + numberOfKnights + numberOfQueens + numberOfRooks;
        placeFigures();
//        this.figureQuantityMap = new HashMap<>();
//        this.figureQuantityMap.put(KING.toString(), numberOfKings);
//        this.figureQuantityMap.put(QUEEN.toString(), numberOfQueens);
//        this.figureQuantityMap.put(ROOK.toString(), numberOfRooks);
//        this.figureQuantityMap.put(BISHOP.toString(), numberOfBishops);
//        this.figureQuantityMap.put(KNIGHT.toString(), numberOfKnights);
//
//
        if (sumOfAllFigures > boardSize) {
            throw new IllegalStateException("There are more figures than places to put them");
        }
        String boardWithKnights = knightsPlacement.placeOneFigureOnBoardSequentially(emptyBoard);

//        String boardWithKnightsAndAttackPlaces = knightsPlacement.calculateAttackPlaces(boardWithKnights);

//        String boardWithKnightsAndRooks = rooksPlacement.placeOneFigureOnBoardSequentially(boardWithKnightsAndAttackPlaces);
        //todo: in this situation rook should not be standing at the first line
//        String boardWithKaRaAP = rooksPlacement.calculateAttackPlaces(boardWithKnightsAndRooks);

//        String boardWithKnightsRooksAndBishops = bishopPlacement.placeOneFigureOnBoardSequentially(boardWithKaRaAP);

//        System.out.println(boardWithKnightsRooksAndBishops);
//        String boardWithKnightsRooksBishopsAndQueens = placeQueens(numberOfQueens, boardWithKnightsRooksAndBishops);
//        String boardWithAllFigures = placeKings(numberOfKings, boardWithKnightsRooksBishopsAndQueens);

        return boardWithKnights;
    }

    private int extractA(String figure, Map<String, Integer> figureQuantityMap) {
        if (figureQuantityMap.containsKey(figure)) {
            return figureQuantityMap.get(figure);
        }
        return 0;
    }

    public void checkBoard(String board, int dimension) {
        if (board.isEmpty() || board.length() % dimension != 0) {
            throw new IllegalStateException("There is something wrong with your board");
        }
    }
}
