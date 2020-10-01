package swingy.view.game;

import swingy.model.Gameplay;

public interface GameView {
    void start();

    void update(Gameplay game);

    void printMap(boolean[][] map, int x, int y);

    boolean replaceArtifact();
    
    void showInfo(String message);

    void enemyLocation();

    // void switchGameView();
}
