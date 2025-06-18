package com.game.project;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class FinalAttacks {
    Texture kunai;
    float posy = 720;
    Mario mario;
    float posx;

    public FinalAttacks(Mario mario) {
        this.mario = mario;
        this.posx = mario.getX() + MathUtils.random(0, Gdx.graphics.getWidth());
    }


    public void create(){
        kunai = new Texture("kunai.png");
    }

    public void update(){
        float deltaTime = Gdx.graphics.getDeltaTime();
        posy -= deltaTime * 200;
        posx -= deltaTime * 200;
        //System.out.println("Xposition" +posx + "Y position" +posy);

    }

    public void draw(SpriteBatch batch){
        float width = 80;
        float height = 120;
        batch.draw(kunai, posx, posy, width, height );
    }

    public Rectangle getBounds(){
        return new Rectangle(posx, posy, 80, 120);
    }

    public void dispose(){
        kunai.dispose();
    }
}



