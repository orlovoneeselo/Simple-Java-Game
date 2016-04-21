package com.game.src.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.game.src.main.Game.State;

public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //public Rectangle playButton = new Rectangle(Game.WIDHT / 4 + 120,150,100,50);
       // public Rectangle helpButton = new Rectangle(Game.WIDHT / 4 + 120,250,100,50);
       // public Rectangle quitButton = new Rectangle(Game.WIDHT / 4 + 120,350,100,50);

        if(State == Game.STATE.MENU){
        if(mx >= Game.WIDHT / 4 + 120 && mx <= Game.WIDHT / 4 + 220){
            if(my >= 150 && my <= 200){
                State = Game.STATE.GAME;
            }
        }

        /*if(mx >= Game.WIDHT / 4 + 120 && mx <= Game.WIDHT / 4 + 220){
            if(my >= 250 && my <= 300){
                Game game = new Game();
                Graphics g = null;
                game.setPreferredSize(new Dimension( 300,100));
                game.setMaximumSize(new Dimension(300,100));
                game.setMinimumSize(new Dimension(300,100));

                JFrame frame = new JFrame("HELP");
                frame.add(game);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame .EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }*/
        }
        if(mx >= Game.WIDHT / 4 + 120 && mx <= Game.WIDHT / 4 + 220){
            if(my >= 350 && my <= 400){
                System.exit(1);
            }
        }
    }



    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
