package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Wolf {

    private Texture runTexture;
    private Texture jumpTexture;
    private Texture fireballTexture;
    private TextureRegion[] runFrames;
    private TextureRegion[] jumpFrames;
    private Animation<TextureRegion> runAnimation;
    private Animation<TextureRegion> jumpAnimation;

    private Mario mario;
    public float positionx;
    private float positiony;
    private float jumpTimer = 0;
    private float fireballTimer = 0;
    private boolean isJumping = false;
    private float jumpVelocity = 0;
    private Array<Fireball> fireballs;


     final float JUMP_HEIGHT = 10f;
     final float GRAVITY = -0.5f;
     float MOVEMENT_SPEED = -100f;
     float JUMP_COOLDOWN = 1f;
     float FIREBALL_COOLDOWN = 1f;
     float FIREBALL_SPEED = -150f;
     float stateTime = 0;

    public Wolf(Mario mario) {
        this.mario = mario;
        this.positiony = 100;
        this.positionx = 0;
        this.fireballs = new Array<>();
    }

    public void create() {

        runTexture = new Texture(Gdx.files.internal("wolf.png"));
        TextureRegion[][] runSheet = TextureRegion.split(runTexture, 190, 119);
        runFrames = new TextureRegion[6];
        System.arraycopy(runSheet[0], 0, runFrames, 0, 6);
        runAnimation = new Animation<>(0.1f, runFrames);


        jumpTexture = new Texture(Gdx.files.internal("wolfjump.png"));
        TextureRegion[][] jumpSheet = TextureRegion.split(jumpTexture, 240, 172);
        jumpFrames = new TextureRegion[6];
        System.arraycopy(jumpSheet[0], 0, jumpFrames, 0, 6);
        jumpAnimation = new Animation<>(0.1f, jumpFrames);

        fireballTexture = new Texture(Gdx.files.internal("fireball.png"));
    }

    public void update(float deltaTime) {

        positionx += MOVEMENT_SPEED * deltaTime * 4;

        jumpTimer += deltaTime;
        if (jumpTimer >= JUMP_COOLDOWN && !isJumping) {
            jumpTimer = 0;
            isJumping = true;
            jumpVelocity = JUMP_HEIGHT;
        }

        if (isJumping) {
            positiony += jumpVelocity;
            jumpVelocity += GRAVITY;

            if (positiony <= 100) {
                positiony = 100;
                isJumping = false;
                jumpVelocity = 0;
            }
        }

        if (!isJumping) {
            fireballTimer += deltaTime;
            if (fireballTimer >= FIREBALL_COOLDOWN) {
                fireballTimer = 0;
                shootFireball();
            }
        }

        for (Fireball fireball : fireballs) {
            fireball.update(deltaTime);
        }

        for (int i = fireballs.size - 1; i >= 0; i--) {
            if (fireballs.get(i).isOffScreen()) {
                fireballs.removeIndex(i);
            }
        }
    }

    private void shootFireball() {
        Fireball fireball = new Fireball(positionx, positiony + 50, FIREBALL_SPEED, fireballTexture);
        fireballs.add(fireball);
    }

    public void draw(SpriteBatch batch, float deltaTime) {
        stateTime += deltaTime;

        TextureRegion currentFrame;
        if (isJumping) {
            currentFrame = jumpAnimation.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, positionx, positiony - 20, 240, 172);
        }
        else {
            currentFrame = runAnimation.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, positionx, positiony, 190, 100);
        }

        for (Fireball fireball : fireballs) {
            fireball.draw(batch);
        }
    }

    public Rectangle getBounds() {
        if (isJumping) {
            return new Rectangle(positionx, positiony - 20, 240, 172);
        }
        return new Rectangle(positionx, positiony, 190, 100);
    }

    public Array<Fireball> getFireballs() {
        return fireballs;
    }

    public void dispose() {
        runTexture.dispose();
        jumpTexture.dispose();
        fireballTexture.dispose();
        for (Fireball fireball : fireballs) {
            fireball.dispose();
        }
    }

    protected class Fireball {
        private float x, y;
        private float speed;
        private Texture texture;
        private Rectangle bounds;

        public Fireball(float x, float y, float speed, Texture texture) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.texture = texture;
            this.bounds = new Rectangle(x, y, 32, 32);
        }

        public void update(float deltaTime) {
            x += speed * deltaTime * 3;
            bounds.setPosition(x, y);
        }

        public void draw(SpriteBatch batch) {
            batch.draw(texture, x, y, 50, 40);
        }

        public boolean isOffScreen() {
            return x < -50 || x > Gdx.graphics.getWidth() + 50;
        }

        public Rectangle getBounds() {
            return bounds;
        }

        public Array<Fireball> getFireballs() {
            return fireballs;
        }

        public void dispose() {
            texture.dispose();
        }
    }
}
