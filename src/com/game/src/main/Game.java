package com.game.src.main;

import EntityPack.EntityA;
import EntityPack.EntityB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;
    public static final int WIDHT = 640;
    public static final int HEIGHT = 480;
    public static final int SCALE = 2;
    public final String TITLE = "Sky Fight";

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH ,HEIGHT , BufferedImage.TYPE_3BYTE_BGR);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;
    private BufferedImage gameOver = null;

    private boolean isShooting = false;

    private int enemy_count = 5;
    private int enemy_killed = 0;

    private Player p;
    private Controller c;
    private Textures tex;
    private Menu menu;
    private EndMenu endMenu;

    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;

    public static int HEALTH = 200;
    public static int SCORE = 0;

    public static enum STATE{
        MENU,
        GAME,
        END,
    };

    public static STATE State = STATE.MENU;


    public void init(){
        this.requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet =loader.loadImage("/spriteSheetReal.png");
            background = loader.loadImage("/233.jpg");
            gameOver = loader.loadImage("/game-over.png");
        }catch (IOException e){
            e.printStackTrace();
        }



        tex =  new Textures(this);

        c = new Controller(tex,this);
        p = new Player(200,200,tex,this,c);
        menu = new Menu();
        endMenu = new EndMenu();
        ea = c.getEntityA();
        eb = c.getEntityB();

        addKeyListener(new KeyInput(this));

        this.addMouseListener(new MouseInput());

        c.createEnemy(enemy_count);
    }


    private synchronized void start(){
        if(running){
            return;
        }
        running =true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if(!running){
            return;
        }
        running =false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void run(){
        init();
        long lastTime = System.nanoTime();
        final  double amountOFTicks = 60.0;
        double ns = 1000000000 /amountOFTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                updates = 0;
                frames = 0;
            }
        }
        stop();

    }
    public void tick(){
        if(HEALTH <= 0){
            State = STATE.END;

        }

        if(State == STATE.GAME);
        {
            p.tick();
            c.tick();
        }
        if(enemy_killed >= enemy_count){
            enemy_count +=2;
            enemy_killed = 0;
            c.createEnemy(enemy_count);
        }

    }
    public void render(){
        BufferStrategy bs =this.getBufferStrategy();
        if(bs ==null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //////////////////////

        g.drawImage(background,-80 , -30 , null);
        if (State == STATE.GAME) {
            p.render(g);
            c.render(g);

            g.setColor(Color.gray);
            g.fillRect(5,5,200,50);
            if(HEALTH <= 200 && HEALTH > 120) {
                g.setColor(Color.green);
            }
            else if(HEALTH <= 120 && HEALTH >= 80){
                g.setColor(Color.orange);
            }
            else if(HEALTH >= 0 && HEALTH < 80){
                g.setColor(Color.red);
            }
            g.fillRect(5,5,HEALTH,50);
            g.setColor(Color.gray);
            g.drawRect(5,5,200,50);
        }else if ( State  == STATE.MENU){
            menu.render(g);
        }
        else if (State == STATE.END){
            endMenu.render(g);
          //  g.drawImage(gameOver,150,-200,this);
            HEALTH = 200;
        }
        //////////////////////

        g.dispose();
        bs.show();
    }

    public void keyPressed(KeyEvent e){
        if(State == STATE.GAME) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_RIGHT) {
                p.setVelX(9);
            }
            if (key == KeyEvent.VK_LEFT) {
                p.setVelX(-9);
            }
            if (key == KeyEvent.VK_DOWN) {
                p.setVelY(6);
            }
            if (key == KeyEvent.VK_UP) {
                p.setVelY(-6);
            }
            if (key == KeyEvent.VK_SPACE && !isShooting) {
                isShooting = true;
                c.addEntity(new Bullet(p.getX(), p.getY() - 20, tex, this));
            }
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(0);
        }
        if (key == KeyEvent.VK_LEFT) {
            p.setVelX(0);
        }
        if (key == KeyEvent.VK_DOWN) {
            p.setVelY(0);
        }
        if (key == KeyEvent.VK_UP) {
            p.setVelY(0);
        }
        if (key == KeyEvent.VK_SPACE) {
            isShooting = false;
        }
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDHT ,HEIGHT));
        game.setMaximumSize(new Dimension(WIDHT  ,HEIGHT ));
        game.setMinimumSize(new Dimension(WIDHT ,HEIGHT ));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();

    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }

    public int getEnemy_count(){
        return enemy_count;
    }
    public void setEnemy_count(int enemy_count){
        this.enemy_count = enemy_count;
    }
    public int getEnemy_killed(){
        return enemy_killed;
    }
    public void setEnemy_killed(int enemy_killed){
        this.enemy_killed = enemy_killed;
    }
}
