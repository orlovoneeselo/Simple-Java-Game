package com.game.src.main;

import java.awt.*;

public class GameObject {
    public double x,y;

    public GameObject(double x , double y){
        this.x = x;
        this.y = y;
    }
    public Rectangle getBounds (int widht, int height) {
        return  new Rectangle((int)x ,(int) y , widht ,height );
    }
}
