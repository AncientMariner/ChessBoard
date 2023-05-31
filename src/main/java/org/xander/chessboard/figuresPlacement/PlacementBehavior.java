package org.xander.chessboard.figuresPlacement;

import java.util.Set;

public interface PlacementBehavior {
    Set<String> placeFiguresOnBoards(Set<String> boards);
    String calculateAttackPlaces(String boardWithFigures);
    Set<String> placeCertainFigureOnBoard(String boardWithFigures);
}
