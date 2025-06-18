package com.game.project;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pipe {
    Texture pipe;
    Sprite acc1;

    public void create() {
        pipe = new Texture("pipe.png");
        acc1 = new Sprite(pipe);

        /// size of the image
        acc1.setSize(100, 100);

        /// position of the brick
        acc1.setPosition(1076, 100);
    }



    public void draw(SpriteBatch batch) {
        acc1.draw(batch);
    }

    public void dispose() {
        pipe.dispose();
    }
}


