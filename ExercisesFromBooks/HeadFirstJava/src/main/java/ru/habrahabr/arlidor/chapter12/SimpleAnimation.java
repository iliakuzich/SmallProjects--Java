package ru.habrahabr.arlidor.chapter12;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleAnimation {

    private int x = 70;
    private int y = 70;

    public static void main(String[] args) {
        SimpleAnimation gui = new SimpleAnimation();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.getContentPane().add(drawPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);

        for (int i = 0; i < 130; i++) {
            try {
                x++;
                y++;
                drawPanel.repaint();
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(SimpleAnimation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class MyDrawPanel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.green);
            g.fillOval(x, y, 40, 40);
        }
    }
}
