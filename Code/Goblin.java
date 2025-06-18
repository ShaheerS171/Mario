package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Goblin {
    Texture enimiesheet;
    TextureRegion[] enimeframe;
    Animation<TextureRegion> enimeanimation;
    float animationtime =0f;
    float posx ;
    float positiony = 100 ;

    public void create(){
        enimiesheet = new Texture("goblin.png");
        TextureRegion[][] animationframes = TextureRegion.split(enimiesheet, 202,232);
        enimeframe = new TextureRegion[4];
        System.arraycopy(animationframes[0], 0, enimeframe, 0, 4);
        enimeanimation = new Animation<>(0.1f, enimeframe);
    }

    public void update(){
        float delta = Gdx.graphics.getDeltaTime();
        posx -= 900 * delta;  // Enemy movement
        animationtime += delta;
    }

    public void draw(SpriteBatch batch){
        /// Display the current frame of the animation based on the animation time
        TextureRegion onecurrentFrame = enimeanimation.getKeyFrame(animationtime, true);
        batch.draw(onecurrentFrame, posx, positiony,90,151);
    }
    public Rectangle getBounds() {
        return new Rectangle(posx, positiony, 90, 151);
    }


    public void dispose(){
        enimiesheet.dispose();
    }
}
