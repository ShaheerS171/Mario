package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Bird {
    Texture thienimiesheet;
    TextureRegion[] thienimeframe;
    Animation<TextureRegion> thienimeanimation;
    float animationtime =0f;
    float posx = 1500;
    float positiony = 200;
    float basey;
    float time = 0f;

    public void create(){
        thienimiesheet = new Texture("bird.png");
        TextureRegion[][] secanimationframes = TextureRegion.split(thienimiesheet, 80,62);
        thienimeframe = new TextureRegion[3];
        System.arraycopy(secanimationframes[0], 0, thienimeframe, 0, 3);
        thienimeanimation = new Animation<>(0.1f, thienimeframe);
        basey = positiony;
    }

    public void update(){
        float delta = Gdx.graphics.getDeltaTime();
        posx -= 150 * delta;

        time += delta;
        positiony = basey + MathUtils.sin(time * 4f) * 50;
        animationtime += delta;
    }

    public void draw(SpriteBatch batch){

        /// Display the current frame of the animation based on the animation time
        TextureRegion seccurrentFrame = thienimeanimation.getKeyFrame(animationtime, true);
        batch.draw(seccurrentFrame, posx, positiony);
    }
    public Rectangle getBounds() {
        return new Rectangle(posx, positiony, 80, 62);
    }

    public void dispose(){
        thienimiesheet.dispose();
    }
}
