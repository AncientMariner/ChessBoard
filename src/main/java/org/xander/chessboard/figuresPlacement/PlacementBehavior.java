package org.xander.chessboard.figuresPlacement;

import java.util.Set;
import java.util.stream.Stream;

public interface PlacementBehavior {
    Stream<String> placeFiguresOnBoards(Stream<String> boards);
    String calculateAttackPlaces(String boardWithFigures);
    Set<String> placeCertainFigureOnBoard(String boardWithFigures);
}
