package com.game.src.main;

import com.sun.prism.paint.*;
import com.sun.prism.paint.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.*;

import static com.game.src.main.Game.SCORE;

public class EndMenu {

    public Rectangle playButton = new Rectangle(Game.WIDHT / 4 + 120,150,100,50);

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial",Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(java.awt.Color.red);
        g.drawString("GAME OVER" ,Game.WIDHT / 4 - 30, 100);
        g.drawString("Score :" + SCORE   ,Game.WIDHT / 4 - 30,450);



    }
}
