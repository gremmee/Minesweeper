package nl.gremmee.minesweeper;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.Stack;

public class Minesweeper extends Canvas implements Runnable {

    public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static int cols;
    public static int rows;
    public static Handler handler;
    public static int defaultCell = 50;
    private Cell current;
    private Cell[] grid;
    private Stack<Cell> stack;
    private boolean running = false;
    private int frames = 0;
    private Thread thread;

    public Minesweeper() {
        handler = new Handler();
        cols = Math.floorDiv(WIDTH, defaultCell);
        rows = Math.floorDiv(HEIGHT, defaultCell);
        grid = new Cell[cols * rows];
        stack = new Stack<>();

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                handler.addObject(new Cell(i, j, defaultCell));
            }
        }

        // current = handler.getGameObject(0);

        new Window(WIDTH, HEIGHT, "Minesweeper", this);
    }

    public static void main(String[] args) {
        new Minesweeper();

    }

    public void make2DArray(final int aCols, final int aRows) {

    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        frames = 0;
        while (running) {
            if (Window.getPrintMaze()) {
                int mouseX = Window.x;
                int mouseY = Window.y;
                mousePressed(mouseX, mouseY);
                Window.setPrintMaze(false);
            }
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if ((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
                int cells = handler.getCells();
                System.out.println("W x H : " + WIDTH + " x " + HEIGHT + " FPS: " + frames + " : Cells " + cells + "|"
                        + cols + " " + rows + "|");
                frames = 0;
            }
        }
        stop();

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(255, 255, 255, 15));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        handler.render(g);

        g.dispose();
        bs.show();
    }

    private void update() {
        handler.update();
    }

    private void mousePressed(int aX, int aY) {
        handler.mousePressed(aX, aY);
    }

}
