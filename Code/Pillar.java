package com.game.project;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Pillar {
    Texture pillar;
    float posy = 720;
    Mario mario;
    float posx;

    public Pillar(Mario mario) {
        this.mario = mario;
        this.posx = mario.getX() + MathUtils.random(0, Gdx.graphics.getWidth());
    }


    public void create(){
        pillar = new Texture("pillar.png");
    }

    public void update(){
        float deltaTime = Gdx.graphics.getDeltaTime();
        posy -= deltaTime * 400;
        //posx -= deltaTime * 200;  // Optional: move diagonally
        //System.out.println("Xposition" +posx + "Y position" +posy);

    }

    public void draw(SpriteBatch batch){
        float width = 120;
        float height = 300;
        batch.draw(pillar, posx, posy, width, height );
    }

    public Rectangle getBounds(){
        return new Rectangle(posx, posy, 120, 300);
    }

    public void dispose(){
        pillar.dispose();
    }
}



