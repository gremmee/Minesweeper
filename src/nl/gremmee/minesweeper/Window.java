package nl.gremmee.minesweeper;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class Window extends Canvas {
    private static final long serialVersionUID = 1522602361354624706L;

    public static int mouseX;
    public static int mouseY;
    public static boolean cellClicked = false;
    public static boolean cellMarked = false;

    public Window(final int aWidth, final int aHeight, final String aTitle, final Minesweeper aMain) {
        JFrame frame = new JFrame(aTitle);
        aMain.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        aMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (MouseEvent.BUTTON1 == e.getButton()) {
                    if (Minesweeper.isGameOver()) {
                        Minesweeper.setRestart(true);
                    }
                    cellClicked = true;
                } else if (MouseEvent.BUTTON2 == e.getButton()) {
                    // Nothing
                } else if (MouseEvent.BUTTON3 == e.getButton()) {
                    if (Minesweeper.isGameOver()) {
                        System.exit(0);
                    }
                    cellMarked = true;
                }
            }
        });

        frame.setPreferredSize(new Dimension(aWidth, aHeight));
        frame.setMaximumSize(new Dimension(aWidth, aHeight));
        frame.setMinimumSize(new Dimension(aWidth, aHeight));

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(aMain);
        frame.setVisible(true);

        aMain.start();
    }

    public static boolean isCellClicked() {
        return cellClicked;
    }

    public static boolean isCellMarked() {
        return cellMarked;
    }

    public static void setCellClicked(boolean aValue) {
        cellClicked = aValue;
    }

    public static void setCellMarked(boolean aValue) {
        cellMarked = aValue;
    }

    public static int getMouseX() {
        return mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }

}
