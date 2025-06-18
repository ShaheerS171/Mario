package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Fireballs {
    public Texture fireball;
    public float posX;
    public float posY = 180;
    public Mario mario;
    public float speed = 300f;
    public float width = 50f;
    public float height = 50f;

    public Fireballs(Mario mario) {
        this.mario = mario;
        /// Start at right edge of screen
        float cameraX = mario.getCamera().position.x;
        this.posX = cameraX + Gdx.graphics.getWidth()/2f - 250;
    }

    public void create() {
        fireball = new Texture("fireball.png");
    }

    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        posX -= deltaTime * speed;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(fireball, posX - 220, posY, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(posX , posY, width, height);
    }


    public void dispose() {
        fireball.dispose();
    }
}
