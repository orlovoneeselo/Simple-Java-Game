package com.game.src.main;

import com.sun.prism.paint.*;
import com.sun.prism.paint.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(Game.WIDHT / 4 + 120,150,100,50);
    public Rectangle helpButton = new Rectangle(Game.WIDHT / 4 + 120,250,100,50);
    public Rectangle quitButton = new Rectangle(Game.WIDHT / 4 + 120,350,100,50);

    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial",Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(java.awt.Color.white);
        g.drawString("SPACE SHOOTER" ,Game.WIDHT / 4 - 30, 100);

        Font fnt1 = new Font("arial" ,Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 20,playButton.y + 35);

        g.setFont(fnt1);
        g.drawString("Quit", quitButton.x + 20,quitButton.y + 35);


        g2d.draw(playButton);

        g2d.draw(quitButton);

    }
}
