package com.game.project;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class LoadingScreen implements Screen {
    private final MyGame game;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private Texture background;

    // Button properties
    private Rectangle playButton;
    private Rectangle exitButton;
    private Color playButtonColor = new Color(0.2f, 0.8f, 0.2f, 1); // Green
    private Color exitButtonColor = new Color(0.8f, 0.2f, 0.2f, 1); // Red
    private Color buttonBorderColor = new Color(0.1f, 0.1f, 0.1f, 1); // Dark border
    private float buttonWidth = 200;
    private float buttonHeight = 60;
    private float buttonCornerRadius = 15;

    public LoadingScreen(MyGame game) {
        this.game = game;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        font.getData().setScale(1.5f);
        background = new Texture("load.png");

        // Center buttons horizontally
        float centerX = Gdx.graphics.getWidth() / 2 - buttonWidth / 2;
        float playButtonY = Gdx.graphics.getHeight() / 2;
        float exitButtonY = playButtonY - buttonHeight - 20; // 20px spacing

        playButton = new Rectangle(centerX, playButtonY, buttonWidth, buttonHeight);
        exitButton = new Rectangle(centerX, exitButtonY, buttonWidth, buttonHeight);
    }

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw background
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Draw buttons with ShapeRenderer
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw play button
        shapeRenderer.setColor(playButtonColor);
        shapeRenderer.rect(playButton.x, playButton.y, playButton.width, playButton.height);
        shapeRenderer.circle(playButton.x + buttonCornerRadius, playButton.y + buttonCornerRadius, buttonCornerRadius);
        shapeRenderer.circle(playButton.x + playButton.width - buttonCornerRadius, playButton.y + buttonCornerRadius, buttonCornerRadius);
        shapeRenderer.circle(playButton.x + buttonCornerRadius, playButton.y + playButton.height - buttonCornerRadius, buttonCornerRadius);
        shapeRenderer.circle(playButton.x + playButton.width - buttonCornerRadius, playButton.y + playButton.height - buttonCornerRadius, buttonCornerRadius);

        // Draw exit button
        shapeRenderer.setColor(exitButtonColor);
        shapeRenderer.rect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
        shapeRenderer.circle(exitButton.x + buttonCornerRadius, exitButton.y + buttonCornerRadius, buttonCornerRadius);
        shapeRenderer.circle(exitButton.x + exitButton.width - buttonCornerRadius, exitButton.y + buttonCornerRadius, buttonCornerRadius);
        shapeRenderer.circle(exitButton.x + buttonCornerRadius, exitButton.y + exitButton.height - buttonCornerRadius, buttonCornerRadius);
        shapeRenderer.circle(exitButton.x + exitButton.width - buttonCornerRadius, exitButton.y + exitButton.height - buttonCornerRadius, buttonCornerRadius);

        shapeRenderer.end();

        // Draw button borders
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(buttonBorderColor);
        shapeRenderer.rect(playButton.x, playButton.y, playButton.width, playButton.height);
        shapeRenderer.rect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
        shapeRenderer.end();

        // Draw button text
        batch.begin();
        font.setColor(Color.WHITE);

        // Play button text
        GlyphLayout playLayout = new GlyphLayout(font, "PLAY");
        float playTextX = playButton.x + (playButton.width - playLayout.width) / 2;
        float playTextY = playButton.y + (playButton.height + playLayout.height) / 2;
        font.draw(batch, "PLAY", playTextX, playTextY);

        // Exit button text
        GlyphLayout                                                                                                                                              exitLayout = new GlyphLayout(font, "EXIT");
        float exitTextX = exitButton.x + (exitButton.width - exitLayout.width) / 2;
        float exitTextY = exitButton.y + (exitButton.height + exitLayout.height) / 2;
        font.draw(batch, "EXIT", exitTextX, exitTextY);

        batch.end();

        // Handle touch/click
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.graphics.getHeight() - Gdx.input.getY(); // Flip y-coordinate

            if (playButton.contains(x, y)) {
                game.setScreen((Screen) new Stage2(game.mario));
                dispose();
            } else if (exitButton.contains(x, y)) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen
    }

    @Override
    public void resize(int width, int height) {
        // Handle screen resizing
    }

    @Override
    public void pause() {
        // Called when game is paused
    }

    @Override
    public void resume() {
        // Called when game is resumed
    }

    @Override
    public void hide() {
        // Called when current screen changes
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
        background.dispose();
    }
}
