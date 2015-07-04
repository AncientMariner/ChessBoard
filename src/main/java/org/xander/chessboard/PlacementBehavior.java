package org.xander.chessboard;

public interface PlacementBehavior {
    String placeNumberOfFiguresOnBoard(int numberOfFigures, String board);
    String calculateAttackPlaces(String boardWithFigures);
    String placeOneFigureOnBoardSequentially(String boardWithFigures);
}
