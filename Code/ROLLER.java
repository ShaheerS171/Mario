package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class ROLLER {
    Texture texture;
    TextureRegion[] textureRegion;
    Animation<TextureRegion> animation;
    Mario mario;
    float distance;
    float positionx;
    float positiony;
    float jumpTimer = 0;
    boolean isJumping = false;
    float jump = 0;
    final float jumpheight = 10f;
    final float GRAVITY = -0.5f;
    private static final float MOVEMENT_SPEED = -100f;

    public ROLLER(Mario mario) {  // Add Mario parameter
        this.mario = mario;
        this.positiony = 100;  // Initialize ground position
    }

    public void create() {
        texture = new Texture("roller.png");
        TextureRegion [][] tex = TextureRegion.split(texture, 145, 144);
        textureRegion = new TextureRegion[6];
        System.arraycopy(tex[0], 0, textureRegion, 0, 6);
        animation = new Animation<>(0.1f, textureRegion);
    }

    public void update(float time) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        jumpTimer += deltaTime;

        positionx += MOVEMENT_SPEED * deltaTime * 7;

        if (jumpTimer >= 0.25f && !isJumping) {
            jumpTimer = 0;
            isJumping = true;
            jump = jumpheight;
        }


        if (isJumping) {
            positiony += jump;
            jump += GRAVITY;


            if (positiony <= 100) {
                positiony = 100;
                isJumping = false;
                jump = 0;
            }
        }

        distance = mario.getX() - positionx;
    }

    private float stateTime = 0;

    public void draw(SpriteBatch batch, float deltaTime) {

        stateTime += deltaTime;
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, positionx, positiony,100, 100);
    }

    public Rectangle getBounds() {
        return new Rectangle(positionx, positiony, 100, 100);
    }

    public void dispose() {
        texture.dispose();
    }

}
