package nl.gremmee.minesweeper;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
    LinkedList<Cell> object = new LinkedList<>();
    public Object clearObjects;

    public void addObject(final Cell aObject) {
        this.object.add(aObject);
    }

    public Cell getGameObject(final Cell aObject) {
        int index = this.object.indexOf(aObject);
        if (index != -1) {
            return getGameObject(index);
        }
        return null;
    }

    public void clearObjects() {
        object.clear();
    }

    public Cell getGameObject(final int aIndex) {
        if (aIndex < 0) {
            return null;
        }
        return this.object.get(aIndex);
    }

    public int getCells() {
        int result = 0;
        for (Cell cell : object) {
            if (cell instanceof Cell) {
                result++;
            }
        }
        return result;
    }

    public void removeObject(final Cell aObject) {
        this.object.remove(aObject);
    }

    public void render(final Graphics aGraphics) {
        for (int i = 0; i < object.size(); i++) {
            Cell tempObject = object.get(i);
            tempObject.render(aGraphics);
        }
    }

    public void update() {
        for (int i = 0; i < object.size(); i++) {
            Cell tempObject = object.get(i);
            tempObject.update();
        }
    }

    public void mousePressed(final int aX, final int aY) {
        for (int i = 0; i < object.size(); i++) {
            Cell tempObject = object.get(i);
            if (tempObject.contains(aX, aY)) {
                if (!tempObject.isMarked()) {
                    tempObject.reveal();
                    if (tempObject.isMine()) {
                        gameOver();
                    }
                }
            }
        }
    }

    private void gameOver() {
        Minesweeper.setGameOver(true);
        for (int i = 0; i < object.size(); i++) {
            Cell tempObject = object.get(i);
            tempObject.setRevealed(true);
        }
    }

    public void setMarker(int aX, int aY) {
        for (int i = 0; i < object.size(); i++) {
            Cell tempObject = object.get(i);
            if (tempObject.contains(aX, aY)) {
                tempObject.mark();
            }
        }
    }

}
