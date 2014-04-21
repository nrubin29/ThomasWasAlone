package me.nrubin29.thomaswasalone;

import java.awt.*;

class Player extends Entity {

    private int jump;
    private boolean grounded;

    public Player(int width, int height) {
        super(Color.GREEN, width, height);
    }

    public int getJump() {
        return jump;
    }

    public void setJump(int jump) {
        this.jump = jump;
    }

    public void modifyJump(int jump) {
        setJump(getJump() + jump);
        setGrounded(false);
    }

    public boolean isGrounded() {
        return grounded;
    }

    void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }

    public void modifyLocation(int x, int y) {
        if (getX() + x > 640 - getWidth()) setX(640 - getWidth());
        else if (getX() + x < 0) setX(0);
        else setX(getX() + x);

        if (getY() + y > 480 - getHeight()) setY(480 - getHeight());
        else if (getY() + y < 0) setY(0);
        else setY(getY() + y);
    }
}