package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Finale {
    Texture tex;
    TextureRegion[] region;
    Animation<TextureRegion> anim;
    Texture jump;
    TextureRegion[] jumpRegion;
    Animation<TextureRegion> jumpAnim;
    public Rectangle bounds;
    float animationTime = 0.0f;
    float posy = 100;
    Mario mario;
    MyGame game;
    public float time = 0;
    public int hitcount = 0;
    public boolean isdefeated = false;

    /// Constants for dimensions
    private static final float WIDTH = 302;
    private static final float HEIGHT = 255;

    public Finale(Mario mario, MyGame game) {
        this.mario = mario;
        this.game = game;
        /// Initialize with dummy values, will be updated in update()
        this.bounds = new Rectangle(0, posy, WIDTH, HEIGHT);
    }

    public void create() {
        tex = new Texture("final.png");
        TextureRegion[][] temp = TextureRegion.split(tex, 302, 205);
        region = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            region[i] = temp[0][i];
        }
        anim = new Animation<>(0.1f, region);

        jump = new Texture("finaljump.png");
        TextureRegion[][] jum = TextureRegion.split(jump, 302, 255);
        jumpRegion = new TextureRegion[2];
        for (int i = 0; i < 2; i++) {
            jumpRegion[i] = jum[0][i];
        }
        jumpAnim = new Animation<>(120f, jumpRegion);
    }

    public void update() {
        time += Gdx.graphics.getDeltaTime();
        animationTime += Gdx.graphics.getDeltaTime();

        /// Calculate current position
        float cameraX = mario.getCamera().position.x;
        float screenRightEdge = cameraX + Gdx.graphics.getWidth() / 2f;
        float currentX = screenRightEdge - 300;

        if (isdefeated) {
            posy -= 5f;
        }

        // Update collision bounds
        bounds.setPosition(currentX, posy);
    }

    public void draw(SpriteBatch batch) {
        float cameraX = mario.getCamera().position.x;
        float screenRightEdge = cameraX + Gdx.graphics.getWidth() / 2f;
        float drawX = screenRightEdge - 300;

        if(time >= 0 && time < 2){
            batch.draw(anim.getKeyFrame(animationTime, true), drawX, posy);
        }
        else if(time >= 2){
            batch.draw(jumpAnim.getKeyFrame(animationTime,false), drawX, posy);
            time = 0;
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        tex.dispose();
        jump.dispose();
    }
}
