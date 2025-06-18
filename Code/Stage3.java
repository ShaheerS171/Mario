package com.game.project;

/// this code is taken from the deepseek after we failed to load ali hassan for 2 sec

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Stage3 extends Bossbg {
    Texture background;
    Texture background2;
    public float time = 0;

    public Stage3(Mario mario) {
        super(mario);
        // Load both textures in constructor
        background = new Texture("stage3.png");
        background2 = new Texture("adan.png");
    }

    @Override
    public void draw(SpriteBatch batch, OrthographicCamera camera) {
        // Update time each frame
        time += Gdx.graphics.getDeltaTime();

        // Choose which texture to use based on current time
        Texture currentBackground = (time <= 2) ? background2 : background;

        ///getting the width and the height of the screen
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        ///getting the width of the background
        int Width = currentBackground.getWidth() + 950;

        float X = camera.position.x - camera.viewportWidth / 2;
        float parallaxOffset = (camera.position.x - camera.viewportWidth / 2) * 0.5f;
        X -= parallaxOffset * 2;

        ///making the for loop so the background will import the require images
        for (int i = 0; i < 1000; i++) {
            batch.draw(currentBackground, X + i * Width, 0, Width, screenHeight);
        }
    }

    @Override
    public void dispose() {
        background.dispose();
        background2.dispose();
    }
}
