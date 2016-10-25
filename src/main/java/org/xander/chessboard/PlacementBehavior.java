package org.xander.chessboard;

import java.util.Set;

public interface PlacementBehavior {
    String placeNumberOfFiguresOnBoard(int numberOfFigures, String board);
    Set<String> placeNumberOfFiguresOnBoardAll(int numberOfFigures, String board);
    String calculateAttackPlaces(String boardWithFigures);
    Set<String> placeOneFigureOnBoardSequentially(String boardWithFigures);
}
