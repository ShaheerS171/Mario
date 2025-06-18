package com.game.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Mario {

    /// Elements that are used in the game

    Texture walkSheet;
    Texture jumpsheet;
    Texture out;
    Texture life;
    TextureRegion[] jumpframe;
    Animation<TextureRegion> jumpAnimation;
    TextureRegion[] walkFrames;
    Animation<TextureRegion> walkAnimation;
    float marioTime = 0f; // controls which frame to show
    public boolean jump = false;
    public final float ground = 100;
    public  float GRAVITY = -20f;
    public float  up = 0;
    float positionx = 80;
    float positiony = ground;
    boolean isright = true;
    boolean isflip = false;
    boolean isout = false;
    float lifeposx;
    float lifeposy = 650;
    MyGame game;

    public Mario(MyGame game) {
        this.game = game;
    }

    public void create() {


        walkSheet = new Texture("mario.png");
        jumpsheet = new Texture("jump.png");
        out = new Texture("out.png");
        life = new Texture("life.png");

        /// Setting the frames of the sprite sheet
        TextureRegion[][] animationframes = TextureRegion.split(walkSheet, 98, 108);
        walkFrames = new TextureRegion[6]; // Giving the number of the images
        System.arraycopy(animationframes[0], 0, walkFrames, 0, 6); // make a copy array

        ///for the jump variable
        TextureRegion[][] jumpframes = TextureRegion.split(jumpsheet, 147, 162);
        jumpframe = new TextureRegion[3];
        System.arraycopy(jumpframes[0], 0, jumpframe, 0, 3);


        /// Create an animation using the pictures in the frame
        walkAnimation = new Animation<>(0.1f, walkFrames);

        ///now creating it for the jump
        jumpAnimation = new Animation<>(0.1f, jumpframe);
    }

    public void update() {

        up += GRAVITY*Gdx.graphics.getDeltaTime();
        positiony += up*Gdx.graphics.getDeltaTime();
        lifeposx = positionx - 430;

        ///logic for the lifeline
        if(MyGame.life == 0){
            isout = true;
        }

        boolean move = false;
        float time = Gdx.graphics.getDeltaTime();

        /// Move to the left
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            positionx -= 200 * time;
            move = true;



            if (!isflip) {
                /// for making the mario face toward the left
                for (TextureRegion frame : walkFrames) {
                    frame.flip(true, false);
                }

                /// for making the mario face toward the left while jumping
                for (TextureRegion frame : jumpframe) {
                    frame.flip(true, false);
                }
                isflip = true;
            }


        }

        /// Move to the right
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            positionx += 200 * time;
            move = true;

            if(isflip){

                /// if mario is currently facing left
                for(TextureRegion frame : walkFrames){
                    frame.flip(true, false);
                }

                for(TextureRegion frame : jumpframe){
                    frame.flip(true, false);
                }
                isflip = false;
            }
        }

        /// Set the jumping movement of Mario
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && positiony <= ground + 1) {
            up = 700;
            jump = true;
        }

        up += GRAVITY * Gdx.graphics.getDeltaTime() * 100;
        positiony += up*Gdx.graphics.getDeltaTime();

        if(!isout){
        if (positiony <= ground) {
            positiony = ground;
            up = 0;
            jump = false;
        }
        }

        /// Update animation time only if moving
        if (move || jump) {
            marioTime += time*2;
        }

        ///getting the death condition for the mario
        if(isout){
            positiony -= 50 * time;
        }

    }

    /// Draw Mario with animation
    public void draw(SpriteBatch batch) {

        if(isout){
            batch.draw(out, positionx, positiony,90,100);
        }

        /// Draw the current frame based on the animation state this line is from gpt
        else if (jump) {
            TextureRegion currentJumpFrame = jumpAnimation.getKeyFrame(marioTime, false);
            batch.draw(currentJumpFrame, positionx, positiony, currentJumpFrame.getRegionWidth() * 0.7f, currentJumpFrame.getRegionHeight() * 0.7f);

        }
        else if(!jump) {
            batch.draw(walkAnimation.getKeyFrame(marioTime, true), positionx, positiony);
        }

        ///drawing the life lines
        for (int i = 0; i < MyGame.life; i++) {
            batch.draw(life, lifeposx - i * 45, lifeposy, 40, 50);
        }



    }

    ///getting the X position of the mario
    public float getX(){
        return positionx;
    }



    public Rectangle getBounds() {
        if (jump) {

            return new Rectangle(positionx, positiony, 147 * 0.7f, 162 * 0.7f);
        } else {

            return new Rectangle(positionx, positiony, 98 * 0.7f, 108 * 0.7f);
        }
    }

    public Camera getCamera() {
        return game.camera;
    }




    public void dispose() {
        walkSheet.dispose();
        jumpsheet.dispose();
        life.dispose();
        out.dispose();
    }

    public boolean isAttacking() {
        return true;
    }
}
