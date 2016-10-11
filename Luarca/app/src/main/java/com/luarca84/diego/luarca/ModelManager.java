package com.luarca84.diego.luarca;

/**
 * Created by USUARIO on 11/10/2016.
 */
public class ModelManager {
    private static ModelManager instance = new ModelManager();
    private boolean newGame = false;

    private ModelManager(){}

    public static ModelManager getInstance() {
        return instance;
    }

    public boolean isNewGame() {
        return newGame;
    }

    public void setNewGame(boolean newGame) {
        this.newGame = newGame;
    }
}
