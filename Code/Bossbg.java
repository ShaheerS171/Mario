package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bossbg {
    Texture background1; // alihassan.png (shown for first 2 seconds)
    Texture background2; // bossbg.png (default background)
    float displayTime = 0f;
     final float initialdisplay = 2f; // 2 seconds

    public Bossbg(Mario mario) {

            background1 = new Texture(Gdx.files.internal("finalbg.png"));
            background2 = new Texture(Gdx.files.internal("bossbg.png"));

    }

    public void draw(SpriteBatch batch, OrthographicCamera camera) {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();


        int width1 = background1.getWidth() + 950;
        int width2 = background2.getWidth() + 950;


        float X = camera.position.x - camera.viewportWidth / 2;
        float parallaxOffset = (camera.position.x - camera.viewportWidth / 2) * 0.5f;
        X -= parallaxOffset * 2;


        displayTime += Gdx.graphics.getDeltaTime();

        if (displayTime < initialdisplay) {

            batch.draw(background1, X, 0, width1, screenHeight);


            for (int i = 1; i < 1000; i++) {
                batch.draw(background2, X + i * width2, 0, width2, screenHeight);
            }
        }
        else {

            for (int i = 0; i < 1000; i++) {
                batch.draw(background2, X + i * width2, 0, width2, screenHeight);
            }
        }
    }

    public void dispose() {
        if (background1 != null) background1.dispose();
        if (background2 != null) background2.dispose();
    }
}
