package org.xander.chessboard;

import java.util.Set;

public interface PlacementBehavior {
    Set<String> placeNumberOfFiguresOnBoard(int numberOfFigures, Set<String> boards);
    String calculateAttackPlaces(String boardWithFigures);
    Set<String> placeOneFigureOnBoardSequentially(String boardWithFigures);
}
