package com.game.project;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.audio.Music;

public class
MyGame extends Game {

    /// using the composition to make the object of the different classes
    SpriteBatch batch;
    Texture shieldTexture;
    background bg;
    Bossbg boss;
    Stage2 stage2;
    Mario mario;
    Powerup powerup;
    Accessories accessories;
    Finale finale;
    Stage3 stage3;
    Array<Enimies> enimieslist;
    Array<Turtle> turtlelist;
    Array<Bird> birdlist;
    Array<Pipe> pipelist;
    Array<Cactus> cactuslist;
    Array<Accessories> accessorieslist;
    Array<FinalAttacks> finalattackslist;
    Array<Fireballs> fireballslist;
    Array<Pillar> pillarlist;
    Array<Goblin> goblinlist;
    Array<ROLLER> rollerlist;
    Array<Wolf> wolflist;
    Array<Wolf.Fireball> wolffireballlist;
    Array<Treeenemy> treelist;
    OrthographicCamera camera;
    public int attacks = 50;
    BitmapFont font;
    BitmapFont font2;
    BitmapFont font3;
    BitmapFont font4;
    public  int killCount = 0;
    Music backgroundMusic;
    Music killSound;
    boolean isdead = false;
    public boolean shield = false;
    public boolean isfinalboss = false;
    public  float delay = 0;
    public boolean bossdefeated = false;
    float lastflamespawn = 0;
    Array<flameSpirit> flameSpirits;
    float lastFlameSpiritSpawn = 0;
    public float shieldtime = 0;
    public float shieldduration = 0;
    public boolean shieldavailable = false;
    int canuseshield = 10;
    Texture menuBackground;
    Texture start;
    Texture quit;


    ///  variable for the enemy
    float lastenemy = 0;
    float lastbird = 0;
    float lastturtle = 0;
    float lastcactus = 0;
    float lastpipe = 0;
    float lastaccessories = 0;
    float lastgoblin = 0;
    float lastroller = 0;
    public static int life = 50;
    float kunaitimer = 0f;
    float firetimer = 0f;
    float pillertimer = 0;
    float shoottimer = 0;
    boolean isstage2 = false;
    boolean isstage3 = false;
    float lasstwolf = 0;
    float lasttree = 0;
    boolean showMenu = true;
    BitmapFont menuFont;
    boolean gameOver = false;
    boolean playerWon = false;
    BitmapFont endScreenFont;
    Texture endScreenBackground;


    boolean canShootFlame = false; // Flag to track if Mario can shoot flames

    @Override
    public void create() {


        /// making the objects
        // Add this in create()
        endScreenFont = new BitmapFont();
        endScreenFont.getData().setScale(3);
        endScreenBackground = new Texture("end.png");
        menuFont = new BitmapFont();
        menuFont.getData().setScale(3);
        menuBackground = new Texture("load.png");
        start = new Texture("play.png");
        quit = new Texture("exit.png");
        menuFont = new BitmapFont();
        menuFont.getData().setScale(3);
        batch = new SpriteBatch();
        bg = new background();
        mario = new Mario(this);
        boss = new Bossbg(mario);
        powerup = new Powerup();
        finalattackslist = new Array<>();
        fireballslist = new Array<>();
        pillarlist = new Array<>();
        stage2 = new Stage2(mario);
        stage3 = new Stage3(mario);
        finale = new Finale(mario,this);
        shieldTexture = new Texture( "shieldTexture.png");


        /// making the arrays of the element
        enimieslist = new Array<>();
        turtlelist = new Array<>();
        birdlist = new Array<>();
        pipelist = new Array<>();
        cactuslist = new Array<>();
        goblinlist = new Array<>();
        rollerlist = new Array<>();
        wolflist = new Array<>();
        flameSpirits = new Array<>();
        wolffireballlist = new Array<>();
        accessorieslist = new Array<>();
        treelist = new Array<>();
        font = new BitmapFont();
        font.getData().setScale(2f);
        font.setColor(Color.WHITE);
        font2 = new BitmapFont();
        font2.getData().setScale(4f);
        font2.setColor(Color.BLACK);
        font3 = new BitmapFont();
        font3.getData().setScale(4f);
        font3.setColor(Color.BLACK);
        font4 = new BitmapFont();
        font4.getData().setScale(4f);
        font4.setColor(Color.BLACK);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("bg-music.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(2f);
        backgroundMusic.play();
        killSound = Gdx.audio.newMusic(Gdx.files.internal("kill.wav"));
        killSound.setLooping(false);
        killSound.setVolume(1f);
        shieldduration = 3f;



        /// setting up the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        /// creating the elements
        bg.create();
        mario.create();
        powerup.create(mario);
        finalattackslist = new Array<>();
        fireballslist = new Array<>();
        pillarlist = new Array<>();
        finale.create();
    }


    @Override
    public void render() {


        if (showMenu) {

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();

            batch.draw(menuBackground, 0, 0, Gdx.graphics.getWidth() + 100, Gdx.graphics.getHeight());
            batch.draw(start, 300, 100, 210, 210);
            batch.draw(quit, 800, 100, 240, 230);

            batch.end();

            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                showMenu = false;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
                Gdx.app.exit();
            }

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                float mouseX = Gdx.input.getX();
                float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
                if (mouseX >= 300 && mouseX <= 300 + 210 && mouseY >= 100 && mouseY <= 100 + 210)
                {
                    showMenu = false;
                }
                else if (mouseX >= 800 && mouseX <= 800+ 240 && mouseY >= 100 && mouseY <= 100 + 230)
                {
                    Gdx.app.exit();
                }
            }
            return;
        }


        // NEW: Game over check
        if (life <= 0 && !gameOver) {
            gameOver = true;
            playerWon = false;
        } else if (bossdefeated && !gameOver) {
            gameOver = true;
            playerWon = true;
        }

        // NEW: End screen
        if (gameOver) {
            renderEndScreen();
            return;
        }

        float time = Gdx.graphics.getDeltaTime();

        if(isstage2){
            enimieslist.clear();
            birdlist.clear();
            turtlelist.clear();
        }

        if(isstage3){
            goblinlist.clear();
            flameSpirits.clear();
            enimieslist.clear();
            birdlist.clear();
            turtlelist.clear();
        }

        /// condition for the final boss
        if(isfinalboss){
            enimieslist.clear();
            birdlist.clear();
            pipelist.clear();
            cactuslist.clear();
            accessorieslist.clear();
            turtlelist.clear();
        }

        if(shieldavailable && Gdx.input.isKeyJustPressed(Input.Keys.X)){
            canuseshield--;
        }

        if(shieldavailable && Gdx.input.isKeyJustPressed(Input.Keys.X) && !shield && canuseshield >= 0) {
            shield = true;
            shieldtime = 0;
        }


        if(shield) {
            shieldtime += Gdx.graphics.getDeltaTime();
            if(shieldtime >= shieldduration) {
                shield = false;
            }
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


             if(killCount >= 45){
            isfinalboss = true;
            isstage2 = false;
            isstage3 = false;
            attacks = 100;
            canuseshield = 10;

        }
        else if(killCount >= 30){
            isstage3 = true;
            isstage2 = false;
            shieldavailable = true;

            if(mario.getX() - lastroller >= 500 ){
                ROLLER roller = new ROLLER(mario);
                roller.create();
                roller.positionx = mario.getX() + 500;
                rollerlist.add(roller);
                lastroller = mario.getX();
            }

            if(mario.getX() - lasstwolf >= 800){
                Wolf wolf = new Wolf(mario);
                wolf.create();
                wolf.positionx = mario.getX() + 800;
                wolflist.add(wolf);
                lasstwolf = mario.getX();
            }


            if (mario.getX() - lasttree >= 1500) {
                Treeenemy treeenemy = new Treeenemy(mario.getX() + 1200, 100, mario);
                treeenemy.create();
                treeenemy.posX = mario.getX() + 1200;
                treelist.add(treeenemy);
                lasttree = mario.getX();
            }
        }

        /// stage 2 code
        else if(killCount >= 15){
            isstage2 = true;
            shieldavailable = true;

            if(mario.getX() - lastgoblin >= 800 ){

                Goblin goblin = new Goblin();
                goblin.create();
                goblin.posx = mario.getX() + 2500 ;
                goblinlist.add(goblin);
                lastgoblin = mario.getX();
            }


            if (mario.getX() - lastFlameSpiritSpawn >= 1500 ) {
                flameSpirit newSpirit = new flameSpirit(
                    mario.getX() + 1000,
                    MathUtils.random(200, 400)
                );
                flameSpirits.add(newSpirit);
                lastFlameSpiritSpawn = mario.getX();
            }
        }



        /// code for the final boss and its attack
        if(isfinalboss && !bossdefeated) {

            kunaitimer += time;
            firetimer += time;
            pillertimer += time;

            if(kunaitimer >= 3f) {
                for (int i = 0; i < 5; i++) {
                    FinalAttacks attack = new FinalAttacks(mario);
                    attack.create();
                    finalattackslist.add(attack);
                    kunaitimer = 0f;
                }
            }

            if(firetimer >= 5f) {
                for (int i = 0; i < 5; i++) {
                    Fireballs fireball = new Fireballs(mario);
                    fireball.create();
                    fireball.posX += i*50;
                    fireballslist.add(fireball);
                }
                firetimer = 0f;
            }

            if(pillertimer >= 5f) {
                for(int i = 0; i < 5; i++) {
                    Pillar pillar = new Pillar(mario);
                    pillar.create();
                    pillarlist.add(pillar);
                }
                pillertimer = 0f;
            }

        }

        /// code for the generation of the enemy
        if (mario.getX() - lastenemy >= 500) {
            Enimies enimies = new Enimies();
            enimies.create();
            enimies.posx = mario.getX() + 800; // Spawn ahead of Mario
            enimieslist.add(enimies);
            lastenemy = mario.getX();
        }

        /// for the generation of the bird
        if (mario.getX() - lastbird >= 1500) {
            Bird bird = new Bird();
            bird.create();
            bird.posx = mario.getX() + 1000; // Spawn ahead of Mario
            birdlist.add(bird);
            lastbird = mario.getX();
        }

        /// for the generation of the turtle
        if (mario.getX() - lastturtle >= 1000) {
            Turtle turtle = new Turtle();
            turtle.create();
            turtle.posx = mario.getX() + 1200;
            turtlelist.add(turtle);
            lastturtle = mario.getX();
        }

        /// for the generation of the pipe and the cactus
        if (mario.getX() - lastcactus >= 1800) {
            Cactus cactus = new Cactus();
            cactus.create();
            cactus.posx = mario.getX() + 1100;
            cactuslist.add(cactus);
            lastcactus = mario.getX();
        }

        if (mario.getX() - lastpipe >= 1800) {
            Pipe pipe = new Pipe();
            pipe.create();
            pipe.acc1.setPosition(mario.getX() + 1073, 100);
            pipelist.add(pipe);
            lastpipe = mario.getX();
        }

        /// for the generation of the bricks
        if (mario.getX() - lastaccessories >= 1200) {
            Accessories accessories = new Accessories();
            accessories.create();
            accessories.acc1.setPosition(mario.getX() + 950, 300);
            accessorieslist.add(accessories);
            lastaccessories = mario.getX();
        }

        /// updating the position
        mario.update();
        powerup.update();
        finale.update();
        if(isfinalboss ) {
            for(FinalAttacks f : finalattackslist){
                f.update();
            }

            for(Fireballs f : fireballslist){
                f.update();
            }

            for(Pillar p : pillarlist){
                p.update();
            }
        }


        /// Check for Mario hitting the power-up
        if (mario.getBounds().overlaps(powerup.getBounds())) {
            canShootFlame = true; // Activate flame shooting when Mario hits the power-up
            powerup.acc2.setX(-powerup.acc2.getX()); // Hide the power-up after Mario collects it
        }


        /// removing the enemies from the arraylist so they can regenerate and also updating the position
        for (int i = enimieslist.size - 1; i >= 0; i--) {
            Enimies enimies = enimieslist.get(i);
            enimies.update();
            if (enimies.posx < -100) {
                enimieslist.removeIndex(i);
            }
        }

        /// This is for the turtle
        for (int i = turtlelist.size - 1; i >= 0; i--) {
            Turtle turtle = turtlelist.get(i);
            turtle.update();
            if (turtle.posx < -100) {
                turtlelist.removeIndex(i);
            }
        }

        /// This is for the bird
        for (int i = birdlist.size - 1; i >= 0; i--) {
            Bird bird = birdlist.get(i);
            bird.update();
            if (bird.posx < -100) {
                birdlist.removeIndex(i);
            }
        }

        /// This is for the cactus
        for (int h = cactuslist.size - 1; h >= 0; h--) {
            Cactus cactus = cactuslist.get(h);
            cactus.update();
            if (cactus.posx < -100) {
                cactuslist.removeIndex(h);
            }
        }


        for (int i = treelist.size - 1; i >= 0; i--) {
            Treeenemy tree = treelist.get(i);
            tree.update();


            for (Fireballs fireball : tree.getActiveFireballs()) {
                if (mario.getBounds().overlaps(fireball.getBounds()) && !shield) {
                    mario.positionx -= 50;
                    life--;
                    shield = true;
                    tree.getActiveFireballs().removeValue(fireball, true);
                    fireball.dispose();
                    break;
                }

                for (Sprite flame : powerup.getFlames()) {
                    if (flame.getBoundingRectangle().overlaps(fireball.getBounds())) {
                        tree.getActiveFireballs().removeValue(fireball, true);
                        fireball.dispose();
                        break;
                    }
                }
            }


            if (tree.posX < mario.getX() - 500) {
                tree.dispose();
                treelist.removeIndex(i);
            }
        }


        for(int i = goblinlist.size - 1; i >= 0; i--) {
            Goblin goblin = goblinlist.get(i);
            goblin.update();


            if(mario.getBounds().overlaps(goblin.getBounds()) && !shield) {

                mario.positionx -= 400;
                life--;
                shield = true;
            }


            for (Sprite flame : powerup.getFlames()) {
                if (flame.getBoundingRectangle().overlaps(goblin.getBounds())) {
                    goblinlist.removeIndex(i);
                    killCount++;
                    isdead = true;
                    break;
                }
            }

            if(goblin.posx < -100) {
                goblinlist.removeIndex(i);
            }
        }

        for (Wolf wolf : wolflist) {

            for (Wolf.Fireball fireball : wolf.getFireballs()) {
                if (mario.getBounds().overlaps(fireball.getBounds()) && !shield) {
                    mario.positionx -= 200;
                    life--;
                    shield = true;
                    wolf.getFireballs().removeValue(fireball, true);
                    break;
                }


                for (Sprite flame : powerup.getFlames()) {
                    if (flame.getBoundingRectangle().overlaps(fireball.getBounds())) {
                        wolf.getFireballs().removeValue(fireball, true);
                        break;
                    }
                }
            }
        }

        for(int i = rollerlist.size - 1; i >= 0; i--) {
            ROLLER roller = rollerlist.get(i);
            roller.update(time);


            if(mario.getBounds().overlaps(roller.getBounds()) && !shield) {

                mario.positionx -= 400;
                life--;
                shield = true;
            }


            for (Sprite flame : powerup.getFlames()) {
                if (flame.getBoundingRectangle().overlaps(roller.getBounds())) {
                    rollerlist.removeIndex(i);
                    killCount++;
                    isdead = true;
                    break;
                }
            }

            if(roller.positionx < -100) {
                rollerlist.removeIndex(i);
            }
        }

        for(int i = wolflist.size - 1; i >= 0; i--) {
            Wolf wolf = wolflist.get(i);
            wolf.update(time);


            if(mario.getBounds().overlaps(wolf.getBounds()) && !shield) {

                mario.positionx -= 400;
                life--;
                shield = true;
            }


            for (Sprite flame : powerup.getFlames()) {
                if (flame.getBoundingRectangle().overlaps(wolf.getBounds())) {
                    wolflist.removeIndex(i);
                    killCount++;
                    isdead = true;
                    break;
                }
            }

            if(wolf.positionx < -100) {
                wolflist.removeIndex(i);
            }
        }



        camera.position.x = Math.max(camera.viewportWidth / 2, mario.getX());
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        if(canShootFlame){
            shoottimer += time;
        }


        /// drawing the other elements
        batch.begin();

        if(bossdefeated) {
            font4.draw(batch, "You Won", camera.position.x - 130, camera.viewportHeight - 300);
        }

        if(isstage2){
            isstage3 = false;
            isfinalboss = false;
            stage2.draw(batch,camera);


            for(Goblin goblin : goblinlist){
                goblin.draw(batch);
            }

            // Update and draw flame spirits
            for (int i = flameSpirits.size - 1; i >= 0; i--) {
                flameSpirit spirit = flameSpirits.get(i);
                spirit.update(time, mario);

                // Remove if off screen
                if (spirit.positionX < mario.getX() - 500) {
                    flameSpirits.removeIndex(i);
                    continue;
                }

                // Draw the spirit
                spirit.draw(batch);

                // Check for collision with Mario
                if (mario.getBounds().overlaps(spirit.getBounds()) && !shield) {
                    mario.positionx -= 100;
                    life--;
                    shield = true;
                    flameSpirits.removeIndex(i);
                    continue;
                }


                for (Sprite flame : powerup.getFlames()) {
                    if (flame.getBoundingRectangle().overlaps(spirit.getBounds())) {
                        flameSpirits.removeIndex(i);
                        killCount++;
                        isdead = true;
                        break;
                    }
                }


                for (flameSpirit.fireBall fireball : spirit.getFireBalls()) {
                    if (mario.getBounds().overlaps(fireball.getBounds())) {
                        if (!shield) {
                            mario.positionx -= 50;
                            life--;
                            shield = true;
                        }

                        spirit.getFireBalls().removeValue(fireball, true);
                        break;
                    }
                }
            }


        }

        else if(isstage3){
            isstage2 = false;
            isfinalboss = false;

            stage3.draw(batch,camera);

            for(ROLLER roller : rollerlist){
                roller.draw(batch,time);
            }

            for(Wolf wolf : wolflist){
                wolf.draw(batch,time);
            }

            for(Treeenemy treeenemy : treelist){
                treeenemy.draw(batch);
            }

        }

        ///total logic for the final boss
        else if(isfinalboss) {

            isstage3 = false;
            isstage2 = false;

            /// for drawing the kunai
            delay += Gdx.graphics.getDeltaTime();
            boss.draw(batch,camera);
            finale.draw(batch);
            for(FinalAttacks f : finalattackslist){
                f.draw(batch);
            }

            /// for drawing the fireballs
            for(Fireballs f : fireballslist){
                f.draw(batch);
            }

            for(Pillar p : pillarlist){
                p.draw(batch);
            }

            /// for cehcking the collision of the kunai
            for(FinalAttacks f : finalattackslist){
                if(mario.getBounds().overlaps(f.getBounds()) && !shield) {
                    mario.positionx -= 50;
                    life--;
                    shield = true;
                    finalattackslist.removeValue(f,true);
                }
            }

            /// for checking the collision of the fireballs
            for(Fireballs f : fireballslist){
                if(mario.getBounds().overlaps(f.getBounds()) && !shield) {
                    mario.positionx -= 50;
                    life--;
                    shield = true;
                    fireballslist.removeValue(f,true);
                }
            }

            for(Pillar p : pillarlist){
                if(mario.getBounds().overlaps(p.getBounds())) {
                    mario.positionx -= 50;
                    life--;
                    shield = true;
                    pillarlist.removeValue(p,true);
                }
            }


            if (isfinalboss && !finale.isdefeated) {

                for (Sprite flame : powerup.getFlames()) {
                    if (flame.getBoundingRectangle().overlaps(finale.getBounds())) {
                        finale.hitcount++;
                        flame.setPosition(-1000,-51);
                        break;
                    }
                }

                /// Check for defeat
                if (finale.hitcount >= 50) {
                    finale.isdefeated = true;
                    bossdefeated = true;
                    finalattackslist.clear();
                    fireballslist.clear();
                    pillarlist.clear();

                }

                /// Drawing the hit counter
                font.draw(batch, "Boss Hits: " + finale.hitcount, finale.getBounds().x, finale.getBounds().y + 300);
            }



        }



        else {
            bg.draw(batch, camera);
        }
        mario.draw(batch);
        powerup.draw(batch);

        /// Check if the space bar is pressed to shoot the flame
        if (canShootFlame && Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE) && attacks > 0 && shoottimer >= 1) {
            powerup.shootFlame();
            attacks--;
            shoottimer = 0;
        }

        /// Draw flames
        for (Sprite flame : powerup.getFlames()) {
            flame.draw(batch);
        }

        /// Check for enemy collisions with Mario and update life
        for (Enimies enimies : enimieslist) {
            boolean hitByFlame = false;
            for (Sprite flame : powerup.getFlames()) {
                if (flame.getBoundingRectangle().overlaps(enimies.getBounds())) {
                    enimies.posx = -enimies.posx;
                    hitByFlame = true;
                    killCount++;
                    isdead = true;
                    break;
                }
            }

            if (!hitByFlame) {
                if (mario.jump && mario.getBounds().overlaps(enimies.getBounds())) {
                    enimies.posx = -enimies.posx;
                    killCount++;
                    isdead = true;
                }

                else if (mario.getBounds().overlaps(enimies.getBounds())) {
                    mario.positionx -= 400;
                    life--;
                    shield = true;
                }
            }

            enimies.draw(batch);

        }


        /// Check for turtle collisions
        for (Turtle turtle : turtlelist) {

            boolean hitByTurtle = false;
            for (Sprite flame : powerup.getFlames()) {
                if (flame.getBoundingRectangle().overlaps(turtle.getBounds())) {
                    turtle.posx = -turtle.posx;
                    hitByTurtle = true;
                    killCount++;
                    isdead = true;
                    break;
                }
            }
            if (!hitByTurtle) {
                if (mario.jump && mario.getBounds().overlaps(turtle.getBounds())) {
                    turtle.posx = -turtle.posx;
                    killCount++;
                    isdead = true;
                }

                else if (mario.getBounds().overlaps(turtle.getBounds())) {
                    mario.positionx -= 400;
                    life--;
                    shield = true;
                }
            }
            turtle.draw(batch);
        }


        /// Check for bird collisions
        for (Bird bird : birdlist) {
            boolean hitByBird = false;
            for (Sprite flame : powerup.getFlames()) {
                if (flame.getBoundingRectangle().overlaps(bird.getBounds())) {
                    bird.posx = -bird.posx;
                    hitByBird = true;
                    killCount++;
                    isdead = true;
                    break;
                }
            }
            if (!hitByBird) {
                if (mario.jump && mario.getBounds().overlaps(bird.getBounds())) {
                    bird.posx = -bird.posx;
                    killCount++;
                    isdead = true;
                }

                else if (mario.getBounds().overlaps(bird.getBounds())) {
                    mario.positionx -= 400;
                    life--;
                    shield = true;
                }
            }
            bird.draw(batch);
        }

        /// Check for cactus collisions
        for (Cactus cactus : cactuslist) {

            boolean hitByCactus = false;
            for (Sprite flame : powerup.getFlames()) {
                if (flame.getBoundingRectangle().overlaps(cactus.getBounds())) {
                    cactus.posx = -cactus.posx;
                    hitByCactus = true;
                    killCount++;
                    isdead = true;
                    break;
                }
            }
            if (!hitByCactus) {
                if (mario.jump && mario.getBounds().overlaps(cactus.getBounds())) {
                    cactus.posx = -cactus.posx;
                    killCount++;
                    isdead = true;
                }

                else if (mario.getBounds().overlaps(cactus.getBounds())) {
                    mario.positionx -= 400;
                    life--;
                    shield = true;
                }
            }
            cactus.draw(batch);
        }



        /// Draw pipes and accessories
        for (Pipe pipe : pipelist) {
            pipe.draw(batch);
        }

        for (Accessories accessories : accessorieslist) {
            boolean isOnPlatform = false;

            if (mario.getBounds().overlaps(accessories.getTopBounds())) {
                mario.positiony = accessories.acc1.getY() + 62;
                mario.GRAVITY = 0;
                mario.jump = false;
                isOnPlatform = true;
            }

            accessories.draw(batch);

            if (!isOnPlatform) {
                mario.GRAVITY = -20;
            }

            isOnPlatform = false;
        }

        for (Accessories accessories : accessorieslist) {

            boolean brick = false;

            ///condition for mario hitting the bottom of the brick
            if (mario.getBounds().overlaps(accessories.getBottomBounds())) {
                brick = true;
                if (brick) {
                    mario.GRAVITY = -100f;
                }
                brick = false;
            }
        }


        if(!isfinalboss) {
            font.draw(batch, "Enimies Killed: " + killCount, camera.position.x - camera.viewportWidth / 2 + 40, camera.viewportHeight - 50);
        }

        ///Game overr condition
        if(life == 0) {
            font2.draw(batch,"Game over", camera.position.x - 130, camera.viewportHeight - 300);
        }

        if (bossdefeated) {
            font4.draw(batch, "You Won", camera.position.x - 130, camera.viewportHeight - 300);
        }


        if(shield) {

            batch.setColor(0, 0.5f, 1f, 0.5f);
            batch.draw(shieldTexture, mario.getX()-10, mario.positiony-10,
                118, 128);
            batch.setColor(Color.WHITE); // Reset color

        }


        batch.end();

        if(isdead) {
            killSound.play();
        }

        isdead = false;
    }

    private void renderEndScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update camera position to center on Mario
        camera.position.x = mario.getX();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        // Draw background centered around Mario's position
        float bgWidth = Gdx.graphics.getWidth();
        float bgHeight = Gdx.graphics.getHeight();
        batch.draw(endScreenBackground,
            mario.getX() - bgWidth/2,  // X position (centered on Mario)
            0,                         // Y position (bottom of screen)
            bgWidth,                   // Width
            bgHeight);                 // Height

        // Draw result message relative to Mario's position
        if (playerWon) {
            endScreenFont.draw(batch, "YOU WON!",
                mario.getX() - 100,    // X position
                camera.viewportHeight - 200); // Y position
        } else {
            endScreenFont.draw(batch, "YOU LOST",
                mario.getX() - 100,
                camera.viewportHeight - 200);
        }

        // Draw options relative to Mario's position
        endScreenFont.draw(batch, "PRESS R TO RESTART",
            mario.getX() - 200,
            camera.viewportHeight/2);
        endScreenFont.draw(batch, "PRESS Q TO QUIT",
            mario.getX() - 180,
            camera.viewportHeight/2 - 100);

        batch.end();

        // Handle input
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            restartGame();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            Gdx.app.exit();
        }
    }

    private void restartGame() {
        // Reset all game state variables
        gameOver = false;
        playerWon = false;
        life = 5;
        killCount = 0;
        isstage2 = false;
        isstage3 = false;
        isfinalboss = false;
        bossdefeated = false;
        canShootFlame = false;
        attacks = 50;
        shield = false;
        shieldavailable = false;
        canuseshield = 10;

        // Clear all enemy lists
        enimieslist.clear();
        turtlelist.clear();
        birdlist.clear();
        pipelist.clear();
        cactuslist.clear();
        accessorieslist.clear();
        finalattackslist.clear();
        fireballslist.clear();
        pillarlist.clear();
        goblinlist.clear();
        rollerlist.clear();
        wolflist.clear();
        wolffireballlist.clear();
        treelist.clear();
        flameSpirits.clear();

        // Reset timers
        lastenemy = 0;
        lastbird = 0;
        lastturtle = 0;
        lastcactus = 0;
        lastpipe = 0;
        lastaccessories = 0;
        lastgoblin = 0;
        lastroller = 0;
        lasstwolf = 0;
        lasttree = 0;
        lastFlameSpiritSpawn = 0;

        // Reset Mario
        mario.positionx = 100;
        mario.positiony = 100;
        mario.GRAVITY = -20;



        // Reset boss
        if (finale != null) {
            finale.hitcount = 0;
            finale.isdefeated = false;
        }
    }



    @Override
    public void dispose() {

        /// disposing each and every thing
        menuFont.dispose();
        bg.dispose();
        menuBackground.dispose();
        mario.dispose();
        powerup.dispose();
        for (Enimies enimies : enimieslist) {
            enimies.dispose();
        }
        for (Turtle turtle : turtlelist) {
            turtle.dispose();
        }
        for (Bird bird : birdlist) {
            bird.dispose();
        }
        for (Cactus cactus : cactuslist) {
            cactus.dispose();
        }
        for (Pipe pipe : pipelist) {
            pipe.dispose();
        }
        for (Accessories accessories : accessorieslist) {
            accessories.dispose();
        }
        for(Fireballs fireballs : fireballslist){
            fireballs.dispose();
        }
        for(FinalAttacks finalattacks : finalattackslist){
            finalattacks.dispose();
        }
        for(Pillar pillar : pillarlist) {
            pillar.dispose();
        }
        for(Goblin goblin : goblinlist) {
            goblin.dispose();
        }
        for(ROLLER roller : rollerlist) {
            roller.dispose();
        }
        for(Wolf wolf : wolflist) {
            wolf.dispose();
        }
        for (Wolf wolf : wolflist) {
            for (Wolf.Fireball fireball : wolf.getFireballs()) {
                fireball.dispose();
            }
        }
        for(Treeenemy treeenemy : treelist){
            treeenemy.dispose();
        }


        batch.dispose();
        font.dispose();
        killSound.dispose();
        boss.dispose();
        backgroundMusic.dispose();
        finale.dispose();
        endScreenFont.dispose();
        endScreenBackground.dispose();

    }

    public void setScreen(Stage2 stage2) {
    }
}
