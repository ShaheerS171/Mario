package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class flameSpirit {

    Sprite sprite;
    float positionX, positionY;
    float velocityX, velocityY;
    float hoverAngle = 0;
    float fireCooldownTimer = 0;
    Array<fireBall> fireBalls = new Array<>();
    Texture fireballTexture;
    Texture smokeTexture;
    Array<smokeParticle> smokeTrail = new Array<>();

    float hoverRadius = 50;
    float hoverSpeed = 1.5f;
    float dodgeSpeed = 200;
    float shootCooldown = 1;

    public flameSpirit(float startX, float startY) {
        sprite = new Sprite(new Texture(Gdx.files.internal("flamespirit.png")));
        sprite.setSize(90,91);
        fireballTexture = new Texture(Gdx.files.internal("fireball.png"));
        smokeTexture = new Texture(Gdx.files.internal("smoke.png"));

        positionX = startX;
        positionY = startY;

        velocityX = 0;
        velocityY = 0;

        hoverAngle = MathUtils.random(0f, 6.28f); // 2π
    }

    public void update(float deltaTime, Mario mario) {
        hoverAngle += deltaTime * hoverSpeed;

        float targetX = positionX + MathUtils.cos(hoverAngle) * hoverRadius;
        float targetY = positionY + MathUtils.sin(hoverAngle) * hoverRadius;

        velocityX = (targetX - positionX) * 6;
        velocityY = (targetY - positionY) * 6;

        float deltaX = positionX - mario.getX();
        float deltaY = positionY - mario.positiony;
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (mario.isAttacking() && distance < 300) {
            float normalX = deltaX / distance;
            float normalY = deltaY / distance;
            velocityX += normalX * dodgeSpeed * deltaTime;
            velocityY += normalY * dodgeSpeed * deltaTime;
        }

        positionX += velocityX * deltaTime;
        positionY += velocityY * deltaTime;
        sprite.setPosition(positionX, positionY);

        updateSmoke(deltaTime);

        fireCooldownTimer += deltaTime;
        if (fireCooldownTimer >= shootCooldown) {
            shootAt(mario);
            fireCooldownTimer = 0;
        }

        for (int i = 0; i < fireBalls.size; i++) {
            fireBalls.get(i).update(deltaTime);
        }
    }

    void updateSmoke(float deltaTime) {
        if (MathUtils.random() < 0.3f) {
            smokeTrail.add(new smokeParticle(positionX + MathUtils.random(-5, 5),
                positionY + MathUtils.random(-5, 5), smokeTexture));
        }

        for (int i = smokeTrail.size - 1; i >= 0; i--) {
            smokeParticle smoke = smokeTrail.get(i);
            smoke.update(deltaTime);
            if (smoke.isDead()) {
                smokeTrail.removeIndex(i);
            }
        }
    }

    void shootAt(Mario marioRef) {
        float dx = marioRef.getX() - positionX;
        float dy = marioRef.positiony - positionY;
        float dist = (float) Math.sqrt(dx * dx + dy * dy);

        if (dist < 500) {
            float normX = dx / dist;
            float normY = dy / dist;
            fireBalls.add(new fireBall(positionX, positionY, normX, normY, fireballTexture));
        }
    }

    public void draw(SpriteBatch batch) {
        for (smokeParticle s : smokeTrail) {
            s.draw(batch);
        }

        sprite.draw(batch);

        for (fireBall fb : fireBalls) {
            fb.draw(batch);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(positionX,positionY,90,91);
    }

    public Array<fireBall> getFireBalls() {
        return fireBalls;
    }

    public void dispose() {
        sprite.getTexture().dispose();
        fireballTexture.dispose();
        smokeTexture.dispose();
        for (smokeParticle s : smokeTrail) {
            s.dispose();
        }
    }

    // fireBall class
    class fireBall {
        Sprite sprite;
        float posX, posY;
        float velX, velY;
        float speed = 150;

        fireBall(float startX, float startY, float dirX, float dirY, Texture texture) {
            sprite = new Sprite(texture);
            sprite.setSize(70,71);
            posX = startX;
            posY = startY;
            velX = dirX * speed;
            velY = dirY * speed;
            sprite.setPosition(posX, posY);
        }

        void update(float deltaTime) {
            posX += velX * deltaTime * 3;
            posY += velY * deltaTime * 3;
            sprite.setPosition(posX, posY);
        }

        void draw(SpriteBatch batch) {
            sprite.draw(batch);
        }

        Rectangle getBounds() {
            return sprite.getBoundingRectangle();
        }
    }

    // smoke particle class
    class smokeParticle {
        Sprite sprite;
        float posX, posY;
        float lifetime = 0;
        float maxLifetime = MathUtils.random(0.5f, 1.2f);
        float scale = MathUtils.random(0.3f, 0.7f);

        smokeParticle(float x, float y, Texture texture) {
            sprite = new Sprite(texture);
            posX = x - 500;
            posY = y ;
            sprite.setScale(scale);
            sprite.setPosition(posX, posY);
            sprite.setAlpha(0.7f);
        }

        void update(float deltaTime) {
            lifetime += deltaTime;
            posY += 10f * deltaTime;
            sprite.setPosition(posX, posY);
            float progress = lifetime / maxLifetime;
            sprite.setAlpha(0.7f * (1 - progress));
            sprite.setScale(scale * (1 + progress * 0.5f));
        }

        void draw(SpriteBatch batch) {
            sprite.draw(batch);
        }

        boolean isDead() {
            return lifetime >= maxLifetime;
        }

        void dispose() {
            sprite.getTexture().dispose();
        }
    }
}
