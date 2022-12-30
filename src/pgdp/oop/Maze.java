package pgdp.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;

public class Maze extends MiniJava {
    protected static final int NOTHING = -1;
    protected static final int LEFT = 1;
    protected static final int UP = 2;
    protected static final int RIGHT = 3;
    protected static final int DOWN = 4;
    public static boolean draw = true;
    protected static int currentEvent = NOTHING;
    static Animal[][] antarktis;
    private static JFrame frame = null;
    private static final Random r = new Random();

    private static void setup() {

        JPanel content = new JPanel(new GridLayout(41, 41));

        for (int y = 0; y < antarktis[0].length; y++)
            for (int x = 0; x < antarktis.length; x++) {
                content.add(new Field(x, y));
            }

        frame = new JFrame("A-Maze-Ing");
        frame.getContentPane().add(content);
        frame.setSize(400, 400 * (antarktis[0].length) / (antarktis.length));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frame.repaint();
            }
        });

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                switch (ke.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> currentEvent = LEFT;
                    case KeyEvent.VK_RIGHT -> currentEvent = RIGHT;
                    case KeyEvent.VK_UP -> currentEvent = UP;
                    case KeyEvent.VK_DOWN -> currentEvent = DOWN;
                    default -> {
                    }
                }
            }
        });

        frame.setVisible(true);
    }

    public static void draw() {
        if (!draw || antarktis.length == 0)
            return;
        if (frame == null)
            setup();

        try {
            update();
            int wannaSleep = 10;
            Thread.sleep(wannaSleep);
        } catch (InterruptedException ie) {
            /* Intentionally left blank */
        }
    }

    private static void update() {
        frame.repaint();
    }

    public static Animal[][] generateMaze(int width, int height) {
        Animal[][] maze = new Animal[width][height];

        for (Animal[] animals : maze) {
            Arrays.fill(animals, null);
        }

        return maze;
    }

    public static int[] getRandomEmptyField() {
        int x, y;
        do {
            x = r.nextInt(41);
            y = r.nextInt(41);
        } while (antarktis[x][y] != null);
        return new int[]{x, y};
    }

    public static void closeFrame() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    private static class Field extends JPanel {
        Point p;
        int x, y;

        public Field(int x, int y) {
            this.x = x;
            this.y = y;
            p = getLocation();
        }

        public void paint(Graphics g) {
            super.paint(g);
            GradientPaint gradient = new GradientPaint(0, 50, Color.WHITE, getWidth(), 0, Color.GRAY);
            ((Graphics2D) g).setPaint(gradient);


            g.fillRect(p.getLocation().x, p.getLocation().y, getWidth() * 2, getHeight());

            if (antarktis[x][y] != null) {
                antarktis[x][y].draw(g, getWidth(), getHeight());
            }
        }

    }
}
