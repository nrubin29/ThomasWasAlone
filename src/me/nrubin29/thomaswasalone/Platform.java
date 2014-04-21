package me.nrubin29.thomaswasalone;

import java.awt.*;

class Platform extends Entity {

    public Platform(int x, int y, int width, int height) {
        super(Color.BLACK, width, height);
        setX(x);
        setY(y);
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}