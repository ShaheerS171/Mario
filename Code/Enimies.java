package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Enimies {
    Texture enimiesheet;
    TextureRegion[] enimeframe;
    Animation<TextureRegion> enimeanimation;
    float animationtime =0f;
    float posx ;
    float positiony = 100 ;

    public void create(){
        enimiesheet = new Texture("enimiesheet.png");
        TextureRegion[][] animationframes = TextureRegion.split(enimiesheet, 80,71);
        enimeframe = new TextureRegion[4];
        System.arraycopy(animationframes[0], 0, enimeframe, 0, 4);
        enimeanimation = new Animation<>(0.1f, enimeframe);
    }

    public void update(){
        float delta = Gdx.graphics.getDeltaTime();
        posx -= 150 * delta;  // Enemy movement
        animationtime += delta;
    }

    public void draw(SpriteBatch batch){
        /// Display the current frame of the animation based on the animation time
        TextureRegion onecurrentFrame = enimeanimation.getKeyFrame(animationtime, true);
        batch.draw(onecurrentFrame, posx, positiony);
    }
    public Rectangle getBounds() {
        return new Rectangle(posx, positiony, 80, 71);
    }


    public void dispose(){
        enimiesheet.dispose();
    }
}
