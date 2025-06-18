package com.game.project;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
    private final MyGame game;

    public GameScreen(MyGame game) {
        this.game = game;
    }

    @Override
    public void show() {
       // Ensure initialization
    }

    @Override
    public void render(float delta) {
        if (game != null) {
            game.render();
        }
    }

    @Override
    public void resize(int width, int height) {
        // Delegate if needed
    }

    @Override
    public void pause() {
        // Delegate if needed
    }

    @Override
    public void resume() {
        // Delegate if needed
    }

    @Override
    public void hide() {
        // Cleanup if needed
    }

    @Override
    public void dispose() {
        // MyGame handles its own disposal
    }
}
