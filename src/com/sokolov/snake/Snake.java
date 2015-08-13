package com.sokolov.snake;

import java.util.ArrayList;

public class Snake {
    private SnakeDirection direction;
    private boolean isAlive;
    private ArrayList<SnakeSection> sections = new ArrayList<SnakeSection>();

    public Snake(int x, int y) {
        sections = new ArrayList<SnakeSection>();
        sections.add(new SnakeSection(x, y));
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getX() {
        return sections.get(0).getX();
    }

    public int getY() {
        return sections.get(0).getY();
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public ArrayList<SnakeSection> getSections() {
        return sections;
    }

    public void move() {
        if (!isAlive) return;

        if (direction == SnakeDirection.UP)
            move(0, -1);
        else if (direction == SnakeDirection.RIGHT)
            move(1, 0);
        else if (direction == SnakeDirection.DOWN)
            move(0, 1);
        else if (direction == SnakeDirection.LEFT)
            move(-1, 0);
    }

    private void move(int dx, int dy) {
        int x = sections.get(0).getX() + dx;
        int y = sections.get(0).getY() + dy;
        SnakeSection head = new SnakeSection(x, y);
        checkBorders(head);
        checkBody(head);
        if (isAlive) {
            sections.add(0, head);
            if (head.getX() != Room.game.getMouse().getX() | head.getY() != Room.game.getMouse().getY())
                sections.remove(sections.size() - 1);
            else Room.game.eatMouse();
        }

    }

    private void checkBorders(SnakeSection head) {
        if (head.getX() < 0 || head.getX() > Room.game.getWidth() - 1 || head.getY() < 0 || head.getY() > Room.game.getHeight() - 1)
            isAlive = false;
    }

    private void checkBody(SnakeSection head) {
        if (sections.contains(head)) isAlive = false;
    }
}
