package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Treeenemy {
    Texture texture;
    TextureRegion[] textureRegion;
    Animation<TextureRegion> animation;
    float posX;
    float posY;
    float fireballTimer = 0;
    Array<Fireballs> activeFireballs;
    Mario mario;
    float animationTimer = 0;
    private static final float FIREBALL_SPEED = 300f;

    public Treeenemy(float x, float y, Mario mario) {
        this.posX = x;
        this.posY = y;
        this.mario = mario;
        this.activeFireballs = new Array<>();
    }

    public void create() {
        texture = new Texture("tree.png");
        TextureRegion[][] tex = TextureRegion.split(texture, 231, 269);

        textureRegion = new TextureRegion[4];
        System.arraycopy(tex[0], 0, textureRegion, 0, 4);
        animation = new Animation<>(0.2f, textureRegion);
    }

    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        fireballTimer += deltaTime;
        if (fireballTimer >= 1) {
            fireballTimer = 0;
            throwFireball();
        }

        animationTimer += deltaTime;

        // Update active fireballs
        for (Fireballs fireball : activeFireballs) {
            fireball.update();
        }

        removeOffscreenFireballs();
    }

    private void throwFireball() {
        // Calculate initial direction to Mario at time of firing
        final Vector2 initialDirection = new Vector2(mario.getX() - (this.posX + 150), mario.positiony - (this.posY + 450)).nor();

        Fireballs newFireball = new Fireballs(mario) {

            Vector2 direction = new Vector2(initialDirection);

            @Override
            public void update() {

                float deltaTime = Gdx.graphics.getDeltaTime();
                this.posX += direction.x * FIREBALL_SPEED * deltaTime;
                this.posY += direction.y * FIREBALL_SPEED * deltaTime;
            }
        };

        newFireball.create();

        newFireball.posX = this.posX + 150;
        newFireball.posY = this.posY + 450;
        activeFireballs.add(newFireball);
    }

    private void removeOffscreenFireballs() {
        Array<Fireballs> toRemove = new Array<>();
        for (Fireballs fireball : activeFireballs) {

            if (fireball.posX < mario.getX() - 500 ||
                fireball.posX > mario.getX() + Gdx.graphics.getWidth() ||
                fireball.posY < 0 ||
                fireball.posY > Gdx.graphics.getHeight()) {
                toRemove.add(fireball);
                fireball.dispose();
            }
        }
        activeFireballs.removeAll(toRemove, true);
    }

    public void draw(SpriteBatch batch) {
        TextureRegion currentFrame = animation.getKeyFrame(animationTimer, true);
        batch.draw(currentFrame, posX, posY, 300, 500);

        for (Fireballs fireball : activeFireballs) {
            fireball.draw(batch);
        }
    }

    public Array<Fireballs> getActiveFireballs() {
        return activeFireballs;
    }

    public Rectangle getBounds() {
        return new Rectangle(posX, posY, 300, 500);
    }

    public void dispose() {
        texture.dispose();
        for (Fireballs fireball : activeFireballs) {
            fireball.dispose();
        }
        activeFireballs.clear();
    }
}
