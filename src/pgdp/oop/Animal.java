package pgdp.oop;

import java.awt.*;
import java.io.File;

public abstract class Animal {
    protected int x, y;
    static String filename;
    protected File f;
    protected Image image;
    protected boolean alive;

    protected static Animal[][] antarktis;

    public Animal(int x, int y) {
        this.x = x;
        this.y = y;
        this.alive = true;
    }

    protected boolean isSafe(int x, int y) {
        if ((antarktis[(x + 1) % 41][y] != null) && (antarktis[(x + 1) % 41][y].canEat(this))) {
            return false;
        }
        if ((antarktis[((x - 1) + 41) % 41][y] != null) && (antarktis[((x - 1) + 41) % 41][y].canEat(this))) {
            return false;
        }
        if ((antarktis[x][(y + 1) % 41] != null) && (antarktis[x][(y + 1) % 41].canEat(this))) {
            return false;
        }
        return (antarktis[x][((y - 1) + 41) % 41] == null) || (!antarktis[x][((y - 1) + 41) % 41].canEat(this));
    }

    private int foodDirection(int x, int y) {
        if ((antarktis[((x - 1) + 41) % 41][y] != null) && this.canEat(antarktis[((x - 1) + 41) % 41][y]) && isSafe(((x - 1) + 41) % 41, y)) {
            return 1;
        } else if ((antarktis[x][((y - 1) + 41) % 41] != null) && (this.canEat(antarktis[x][((y - 1) + 41) % 41]) && isSafe(x, ((y - 1) + 41) % 41))) {
                return 2;
        } else if ((antarktis[(x + 1) % 41][y] != null) && (this.canEat(antarktis[(x + 1) % 41][y]) && isSafe((x + 1) % 41, y))) {
                return 3;
        } else if ((antarktis[x][(y + 1) % 41] != null) && (this.canEat(antarktis[x][((y + 1) % 41)]) && isSafe(x, (y + 1) % 41))) {
                return 4;
        }
        return 0;
    }

    public void move() {
        if (this.alive) {
            switch (foodDirection(this.x, this.y)) {
                case 1 -> {
                    antarktis[((x - 1) + 41) % 41][y].alive = false;
                    antarktis[((x - 1) + 41) % 41][y] = this;
                    antarktis[x][y] = null;
                    this.x = ((x - 1) + 41) % 41;
                }
                case 2 -> {
                    antarktis[x][((y - 1) + 41) % 41].alive = false;
                    antarktis[x][((y - 1) + 41) % 41] = this;
                    antarktis[x][y] = null;
                    this.y = ((y - 1) + 41) % 41;
                }
                case 3 -> {
                    antarktis[(x + 1) % 41][y].alive = false;
                    antarktis[(x + 1) % 41][y] = this;
                    antarktis[x][y] = null;
                    this.x = (x + 1) % 41;
                }
                case 4 -> {
                    antarktis[x][(y + 1) % 41].alive = false;
                    antarktis[x][(y + 1) % 41] = this;
                    antarktis[x][y] = null;
                    this.y = (y + 1) % 41;
                }
                case 0 -> {
                    if (antarktis[((x - 1) + 41) % 41][y] == null && isSafe(((x - 1) + 41) % 41, y)) {
                        antarktis[((x - 1) + 41) % 41][y] = this;
                        antarktis[x][y] = null;
                        this.x = ((x - 1) + 41) % 41;
                    } else if (antarktis[x][((y - 1) + 41) % 41] == null && isSafe(x, ((y - 1) + 41) % 41)) {
                        antarktis[x][((y - 1) + 41) % 41] = this;
                        antarktis[x][y] = null;
                        this.y = ((y - 1) + 41) % 41;
                    } else if (antarktis[(x + 1) % 41][y] == null && isSafe((x + 1) % 41, y)) {
                        antarktis[(x + 1) % 41][y] = this;
                        antarktis[x][y] = null;
                        this.x = (x + 1) % 41;
                    } else if (antarktis[x][(y + 1) % 41] == null && isSafe(x, (y + 1) % 41)) {
                        antarktis[x][(y + 1) % 41] = this;
                        antarktis[x][y] = null;
                        this.y = (y + 1) % 41;
                    }
                }
            }
        }
    }

    public abstract boolean canEat(Animal animal);

    protected abstract boolean eatenBy(Penguin penguin);

    protected abstract boolean eatenBy(PlayerPenguin playerPenguin);

    protected abstract boolean eatenBy(Whale whale);

    protected abstract boolean eatenBy(LeopardSeal leopardSeal);

    protected abstract boolean eatenBy(Fish fish);

    public static void setAntarktis(Animal[][] antarktis) {
        Animal.antarktis = antarktis;
    }
    // Graphics Stuff - You don't have to do anything here

    private void paintSymbol(Graphics g, Color c, int height, int width) {
        GradientPaint gradient = new GradientPaint(15, 0, c, width, 0, Color.black);
        ((Graphics2D) g).setPaint(gradient);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillOval((int) (width * 0.3), (int) (height * 0.3), (int) (width * 0.5),
                (int) (height * 0.5));
    }

    public void draw(Graphics g, int height, int width) {
        if (image == null) {
            paintSymbol(g, Color.YELLOW, height, width);
            return;
        }
        g.drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(null),
                image.getHeight(null), null);
    }
}
