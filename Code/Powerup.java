package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Powerup {
    Texture powerup;
    Texture flameTexture;
    Sprite acc2;
    Mario mario;
    Array<Sprite> flames;

    public void create(Mario mario) {
        this.mario = mario;
        powerup = new Texture("flower.png");
        flameTexture = new Texture("flame.png");
        acc2 = new Sprite(powerup);
        flames = new Array<>();

        acc2.setSize(40, 66);
        acc2.setPosition(mario.getX() + 2150, 250);
    }

    public void update(){
        float time  = Gdx.graphics.getDeltaTime();
        float posy = acc2.getY();
        posy += 50*time;
        if(posy >= 356) {
            posy -= 150 * time;
        }
        acc2.setY(posy);

        /// updating flame position
        for(int i = flames.size - 1; i >= 0; i--){
            Sprite flame = flames.get(i);
            flame.setX(flame.getX() + 400 * time);
            if(flame.getX() > mario.getX() + 400){
                flames.removeIndex(i);
            }
        }
    }

    public void draw(SpriteBatch batch){
        acc2.draw(batch);

        /// draw all flames
        for(Sprite flame : flames){
            flame.draw(batch);
        }
    }

    public void shootFlame(){
        Sprite flame = new Sprite(flameTexture);
        flame.setSize(30, 30);
        flame.setPosition(mario.getX() + 50, mario.positiony + 30);
        flames.add(flame);
    }

    public Rectangle getBounds() {
        return new Rectangle(acc2.getX(), acc2.getY(), acc2.getWidth(), acc2.getHeight());
    }


    public Array<Sprite> getFlames(){
        return flames;
    }

    public void dispose(){
        powerup.dispose();
        flameTexture.dispose();
    }
}
