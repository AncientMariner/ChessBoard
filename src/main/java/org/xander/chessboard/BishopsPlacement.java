package org.xander.chessboard;

public class BishopsPlacement extends FiguresPlacement {
    private final Chessboard chessboard;
    private final char figure = 'b';

    public BishopsPlacement(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public String placeOneFigureOnBoardSequentially(String board) {
        return placeFigureOnBoard(figure, board);
    }

    @Override
    public String calculateAttackPlaces(String board) {
        StringBuilder chessBoardWithFigures = new StringBuilder();
        char[] boardElements = board.toCharArray();
        //mind the '\n' character
        int dimension = chessboard.getDimension() + 1;

        for (int i = 0 ; i < boardElements.length; i++) {
            if (boardElements[i] == figure) {
                placeAbove(boardElements, i, dimension);
                placeBelow(boardElements, i, dimension);
            }
        }
        for (char element : boardElements) {
            chessBoardWithFigures.append(element);
        }
        return chessBoardWithFigures.toString();
    }

    private void placeAbove(char[] boardElements, int position, int dimension) {
        if (position - dimension > 0) {
            int positionLeftAbove = 1;
            while (position - dimension * positionLeftAbove - positionLeftAbove >= 0
                    && (position - dimension * positionLeftAbove - positionLeftAbove) % dimension >= 0 ) {
                if (boardElements[position - dimension * positionLeftAbove - positionLeftAbove] != '\n'
                        && boardElements[position - dimension * positionLeftAbove - positionLeftAbove] == '.') {
                    boardElements[position - dimension*positionLeftAbove - positionLeftAbove] = 'x';
                }
                positionLeftAbove++;
            }
            int positionRightAbove = 1;
            while (position - dimension * positionRightAbove + positionRightAbove >= 0
                    && (position - dimension * positionRightAbove + positionRightAbove) % dimension < dimension - 1 ) {
                if (boardElements[position - dimension * positionRightAbove + positionRightAbove] != '\n'
                        && boardElements[position - dimension * positionRightAbove + positionRightAbove] == '.') {
                    boardElements[position - dimension*positionRightAbove + positionRightAbove] = 'x';
                }
                positionRightAbove++;
            }
        }
    }

    private void placeBelow(char[] boardElements, int position, int dimension) {
        if (position + dimension < boardElements.length) {
            int positionLeftBelow = 1;
            while(position + dimension * positionLeftBelow - positionLeftBelow < boardElements.length
                    && (position + dimension * positionLeftBelow - positionLeftBelow) % dimension >= 0) {
                if (boardElements[position + dimension * positionLeftBelow - positionLeftBelow] != '\n'
                        && boardElements[position + dimension * positionLeftBelow - positionLeftBelow] == '.') {
                    boardElements[position + dimension * positionLeftBelow - positionLeftBelow] = 'x';
                }
                positionLeftBelow++;
            }

            int positionRightBelow = 1;
            while(position + dimension * positionRightBelow + positionRightBelow < boardElements.length
                    && (position + dimension * positionRightBelow + positionRightBelow) % dimension < dimension - 1) {
                if (boardElements[position + dimension * positionRightBelow + positionRightBelow] != '\n'
                        && boardElements[position + dimension * positionRightBelow + positionRightBelow] == '.') {
                    boardElements[position + dimension * positionRightBelow + positionRightBelow] = 'x';
                }
                positionRightBelow++;
            }
        }
    }
}
