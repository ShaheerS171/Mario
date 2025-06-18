package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Cactus {

        Texture cactussheet;
        TextureRegion[] cactusframe;
        Animation<TextureRegion> cactusanimation;
        float animationtime =0f;
        float posx = 1100;
        float positiony = 100;

        public void create(){
            cactussheet = new Texture("cactus.png");
            TextureRegion[][] secanimationframes = TextureRegion.split(cactussheet, 58,76);
            cactusframe = new TextureRegion[2];
            System.arraycopy(secanimationframes[0], 0, cactusframe, 0, 2);
            cactusanimation = new Animation<>(0.1f, cactusframe);
        }

        public void update() {
            float delta = Gdx.graphics.getDeltaTime();
            positiony += 50 * delta;
            if (positiony >= 174) {
                positiony -= 150 * delta;//if it works let it be
            }

            animationtime += delta;
        }


        public void draw (SpriteBatch batch){
            /// Display the current frame of the animation based on the animation time
            TextureRegion seccurrentFrame = cactusanimation.getKeyFrame(animationtime, true);
            batch.draw(seccurrentFrame, posx, positiony);
        }
    public Rectangle getBounds() {
        return new Rectangle(posx, positiony, 58, 76);
    }

        public void dispose(){
            cactussheet.dispose();
        }


}


