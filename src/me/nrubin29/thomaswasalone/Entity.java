package me.nrubin29.thomaswasalone;

import java.awt.*;

class Entity {

    private final Color color;
    private final int width, height;
    private int x, y;

    Entity(Color color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }
}