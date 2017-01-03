package com.kiran.chess.model;

/**
 * Created by Kiran Kolli on 07-10-2016.
 */
public class BoardPosition {

    private final int x;
    private final int y;

    public BoardPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    BoardPosition moveRight() {
        return null;
    }

    BoardPosition moveLeft() {
        return null;
    }

    public BoardPosition moveUp() {
        return null;
    }

    public BoardPosition moveDown() {
        return null;
    }

    public BoardPosition moveUpLeft() {
        return null;
    }

    public BoardPosition moveUpRight() {
        return null;
    }

    public BoardPosition moveDownLeft() {
        return null;
    }

    public BoardPosition moveDownRight() {
        return null;
    }
}
