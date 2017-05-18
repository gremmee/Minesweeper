package nl.gremmee.minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Cell {

    private int i;
    private int j;
    private int w;
    private int x;
    private int y;
    private boolean mine;
    private boolean revealed;
    private Handler handler;
    private int neighborCount;
    private int cols;
    private int rows;

    public Cell(final int aI, final int aJ, final int aW, final int aCols, final int aRows) {
        this.i = aI;
        this.j = aJ;
        this.w = aW;
        this.cols = aCols;
        this.rows = aRows;
        this.neighborCount = 0;
        Random rnd = new Random();
        if (rnd.nextInt(100) < 50) {
            this.setMine(true);
        }
        this.setX(this.i * this.w);
        this.setY(this.j * this.w);
        this.setRevealed(true);
    }

    public boolean contains(final int aX, final int aY) {
        return (aX > this.x && //
                aX < this.x + this.w && //
                aY > this.y && //
                aY < this.y + this.w);
    }

    public int countNeighbors() {
        if (this.mine) {
            return -1;
        }
        int total = 0;
        for (int xoff = -1; xoff <= 1; xoff++) {
            for (int yoff = -1; yoff <= 1; yoff++) {
                int i = this.i + xoff;
                int j = this.j + yoff;
                if (i > -1 && i < this.cols && j > -1 && j < this.rows) {

                    Cell neighbor = this.handler.getGameObject(i + (j * this.cols));
                    if (neighbor.isMine()) {
                        total++;
                    }
                }
            }
        }
        this.neighborCount = total;
        return total;
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
                aGraphics.setColor(Color.black);
                drawCenteredString(aGraphics, "" + this.neighborCount, new Rectangle(this.x, this.y, this.w, this.w),
                        new Font("Arial", Font.BOLD, 24));
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

    public void setHandler(final Handler aHandler) {
        this.handler = aHandler;
    }

    protected void drawCenteredString(Graphics aGraphics, String aText, Rectangle aRectangle, Font aFont) {
        // Get the FontMetrics
        FontMetrics metrics = aGraphics.getFontMetrics(aFont);
        // Determine the X coordinate for the text
        int x = (aRectangle.width - metrics.stringWidth(aText)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as
        // in java 2d 0 is top of the screen)
        int y = ((aRectangle.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        aGraphics.setFont(aFont);
        // Draw the String
        aGraphics.drawString(aText, x + aRectangle.x, y + aRectangle.y);
    }

}
