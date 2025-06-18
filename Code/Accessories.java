package com.game.project;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Accessories {
        Texture brick;
        Sprite acc1;

        public void create() {
            brick = new Texture("brick.png");
            acc1 = new Sprite(brick);

            /// size of the image
            acc1.setSize(249, 66);

            ///position of the brick
            acc1.setPosition(200, 250);




        }

        public void update() {


        }

        public void draw(SpriteBatch batch) {
            acc1.draw(batch);
        }

    public Rectangle getTopBounds() {
        return new Rectangle(acc1.getX(), acc1.getY() + acc1.getHeight() - 10, acc1.getWidth(), 10);
    }

    public Rectangle getBottomBounds() {
            return new Rectangle(acc1.getX(), acc1.getY() + acc1.getHeight() - 64, acc1.getWidth(), 10);
    }

    public void dispose() {
            brick.dispose();
        }
    }


