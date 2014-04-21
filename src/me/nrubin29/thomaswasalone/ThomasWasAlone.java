package me.nrubin29.thomaswasalone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

class ThomasWasAlone extends JComponent {

    private final Player player;
    private final ArrayList<Platform> platforms;

    private int keyPressed = -1, arrowPressed = -1;

    private ThomasWasAlone() {
        player = new Player(25, 40);
        platforms = new ArrayList<Platform>();

        platforms.add(new Platform(0, 400, 640, 480));
        platforms.add(new Platform(100, 350, 100, 50));
        platforms.add(new Platform(200, 200, 200, 50));

        JFrame frame = new JFrame("Thomas Was Alone");

        frame.add(this);

        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isArrow(e)) arrowPressed = e.getKeyCode();
                else keyPressed = e.getKeyCode();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (isArrow(e)) arrowPressed = -1;
                else keyPressed = -1;
            }

            private boolean isArrow(KeyEvent e) {
                return e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT;
            }
        });

        new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        }).start();
    }

    public static void main(String[] args) {
        new ThomasWasAlone();
    }

    private void tick() {
        if (player.getJump() != 0) {
            if (
                    !isBlocked(player.getX(), player.getY()) &&
                            !isBlocked(player.getX() + player.getWidth(), player.getY())
                    ) {
                player.modifyLocation(0, -(player.getJump() / 2));
                player.modifyJump(-1);
            } else {
                player.setJump(0);
            }
        } else {
            if (
                    !isBlocked(player.getX() + player.getWidth(), player.getY() + player.getHeight()) &&
                            !isBlocked(player.getX(), player.getY() + player.getHeight())
                    ) {
                player.modifyLocation(0, 5);
            } else {
                player.setGrounded(true);
            }
        }

        if (arrowPressed != -1) {
            if (arrowPressed == KeyEvent.VK_LEFT && !isBlocked(player.getX() - 5, player.getY())) {
                player.modifyLocation(-5, 0);
            } else if (arrowPressed == KeyEvent.VK_RIGHT && !isBlocked(player.getX() + player.getWidth(), player.getY())) {
                player.modifyLocation(5, 0);
            }
        }

        if (keyPressed != -1) {
            if (keyPressed == KeyEvent.VK_SPACE && player.isGrounded()) {
                player.modifyJump(20);
                keyPressed = -1;
            }
        }

        repaint();
    }

    private boolean isBlocked(int x, int y) {
        for (Platform p : platforms) {
            if (p.getBounds().contains(x, y)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        for (Platform p : platforms) {
            g.fillRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
        }

        g.setColor(player.getColor());
        g.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }
}