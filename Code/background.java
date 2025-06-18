package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class background {
    Texture bg;

    public void create() {
        bg = new Texture("background.png");
    }

    public void draw(SpriteBatch batch, OrthographicCamera camera) {

            ///getting the width and the height of the screen
            float screenWidth = Gdx.graphics.getWidth();
            float screenHeight = Gdx.graphics.getHeight();


            ///getting the width of the background
            int Width = bg.getWidth();

            ///chatgpt logic for getting the number of background to fit
           ///int numTiles = (int)Math.ceil(camera.viewportWidth / Width) + 2;
            float X  = camera.position.x - camera.viewportWidth / 2;

            ///gpt too tired to write more
            float parallaxOffset = (camera.position.x - camera.viewportWidth / 2) * 0.5f;
            X -= parallaxOffset*2;



         ///making the for loop so the background will import the require images
            for (int i = 0; i < 1000; i++) {
                batch.draw(bg, X+ i * Width, 0, Width,screenHeight);
            }
    }

    public void dispose() {
        bg.dispose();
    }
}
