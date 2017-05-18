package nl.gremmee.minesweeper;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Cell {

    private int i;
    private int j;
    private int w;
    private int x;
    private int y;
    private boolean mine;
    private boolean revealed;

    public Cell(final int aI, final int aJ, final int aW) {
        this.i = aI;
        this.j = aJ;
        this.w = aW;
        Random rnd = new Random();
        if (rnd.nextInt(100) < 50) {
            this.setMine(true);
        }
        this.setX(this.i * this.w);
        this.setY(this.j * this.w);
        this.setRevealed(false);
    }

    public boolean contains(final int aX, final int aY) {
        return (aX > this.x && //
                aX < this.x + this.w && //
                aY > this.y && //
                aY < this.y + this.w);
    }

    public void doRender(Graphics aGraphics) {
        aGraphics.setColor(Color.black);
        aGraphics.drawRect(this.x, this.y, this.w, this.w);
        if (this.revealed) {
            if (this.mine) {
                aGraphics.setColor(Color.gray);
                aGraphics.fillOval(this.x + (int) (this.w * 0.25), this.y + (int) (this.w * 0.25), (int) (this.w * 0.5),
                        (int) (this.w * 0.5));
            } else {
                aGraphics.setColor(Color.gray);
                aGraphics.fillRect(this.x, this.y, this.w, this.w);

            }
        }
    }

    public void doUpdate() {
    }

    public void render(Graphics aGraphics) {
        doRender(aGraphics);
    }

    public void update() {
        doUpdate();
    }

    public int getI() {
        return this.i;
    }

    public void setI(final int aI) {
        this.i = aI;
    }

    public int getJ() {
        return this.j;
    }

    public void setJ(final int aJ) {
        this.j = aJ;
    }

    public int getW() {
        return this.w;
    }

    public void setW(final int aW) {
        this.w = aW;
    }

    public int getY() {
        return this.y;
    }

    public void setY(final int aY) {
        this.y = aY;
    }

    public int getX() {
        return this.x;
    }

    public void setX(final int aX) {
        this.x = aX;
    }

    public boolean isMine() {
        return this.mine;
    }

    public void setMine(final boolean aMine) {
        this.mine = aMine;
    }

    public boolean isRevealed() {
        return this.revealed;
    }

    public void setRevealed(final boolean aRevealed) {
        this.revealed = aRevealed;
    }
}
