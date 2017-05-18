
public class Cell {

    private int i;
    private int j;
    private int w;
    private int x;
    private int y;

    public Cell(final int aI, final int aJ, final int aW) {
        this.i = aI;
        this.j = aJ;
        this.w = aW;
        this.setX(this.i * this.w);
        this.setY(this.j * this.w);
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
}
