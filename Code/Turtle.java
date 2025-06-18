package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class Turtle {
    Texture secenimiesheet;
    TextureRegion[] secenimeframe;
    Animation<TextureRegion> secenimeanimation;
    float animationtime =0f;
    float posx = 1400;
    float positiony = 100;

    public void create(){
        secenimiesheet = new Texture("second.png");
        TextureRegion[][] secanimationframes = TextureRegion.split(secenimiesheet, 80,89);
        secenimeframe = new TextureRegion[3];
        System.arraycopy(secanimationframes[0], 0, secenimeframe, 0, 3);
        secenimeanimation = new Animation<>(0.1f, secenimeframe);
    }

    public void update(){
        float delta = Gdx.graphics.getDeltaTime();
        posx -= 150 * delta;  // Enemy movement

        animationtime += delta;
    }

    public void draw(SpriteBatch batch){
        /// Display the current frame of the animation based on the animation time
        TextureRegion seccurrentFrame = secenimeanimation.getKeyFrame(animationtime, true);
        batch.draw(seccurrentFrame, posx, positiony);
    }

    public Rectangle getBounds() {
        return new Rectangle(posx, positiony, 80, 71);
    }

    public void dispose(){
        secenimiesheet.dispose();
    }
}
