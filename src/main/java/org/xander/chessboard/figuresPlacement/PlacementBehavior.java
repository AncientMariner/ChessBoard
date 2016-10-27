package org.xander.chessboard.figuresPlacement;

import java.util.Set;

public interface PlacementBehavior {
    Set<String> placeFigureOnBoard(Set<String> boards);
    String calculateAttackPlaces(String boardWithFigures);
    Set<String> placeOneFigureOnBoard(String boardWithFigures);
}
