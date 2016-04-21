package com.game.src.main;


import EntityPack.EntityA;
import EntityPack.EntityB;
import com.sun.xml.internal.ws.api.server.LazyMOMProvider;

import java.awt.*;
import java.util.Random;

import static com.game.src.main.Game.SCORE;
import static com.game.src.main.Game.State;

public class Enemy extends GameObject implements EntityB{


    Random r = new Random();
    private Textures tex;
    Animation anim;
    private Game game;
    private Controller c;
    private int speed = r.nextInt(3) + 1;

    public Enemy (double x, double y,Textures tex ,Controller c , Game game){
        super(x,y);
        this.tex = tex;
        this.c = c;
        this.game = game;

        anim = new Animation(5 ,tex.enemy[0],tex.enemy[1],tex.enemy[2]);
    }
    public void tick(){
       if(State == Game.STATE.GAME) {
            y += speed;

            if (y > Game.WIDHT - 200) {
                speed = r.nextInt(8) + 1;
                y = 0;
                x = r.nextInt(Game.WIDHT);

            }
      }

        for (int i = 0; i < game.ea.size(); i++) {
            EntityA tempEnt = game.ea.get(i);
            if(Physics.Collision(this , tempEnt)){
                c.removeEntity(tempEnt);
                c.removeEntity(this);
                game.setEnemy_killed(game.getEnemy_killed() + 1);
                SCORE+=10;


            }
        }
        if(State == Game.STATE.END){

        }

        anim.runAnimation();
    }
    public void render(Graphics g){
            anim.drawAnimation(g,x,y,0);
    }
    public Rectangle getBounds (){
        return  new Rectangle((int)x ,(int) y , 32 ,32);
    }

    @Override
    public double getX() {
        return x;
    }

    public double getY(){
        return y;
    }
    public void setY(double y){
        this.y = y;
    }



}
