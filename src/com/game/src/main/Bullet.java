package com.game.src.main;


import EntityPack.EntityA;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject implements EntityA {


    private Textures tex;
    private Game game;

    Animation anim;

    public Bullet(double x, double y,Textures tex , Game game){
        super(x,y);
        this.tex = tex;
        this.game = game;

        anim = new Animation(2, tex.bullets[0],tex.bullets[1],tex.bullets[2]);
    }
    public void tick(){
        y -= 8;

        anim.runAnimation();
    }
    public Rectangle getBounds (){
        return  new Rectangle((int)x ,(int) y , 32 ,32);
    }
    public void render (Graphics g){
        anim.drawAnimation(g,x,y,0);
    }

    @Override
    public double getX() {
        return y;
    }

    public double getY(){
        return y;
    }
}
