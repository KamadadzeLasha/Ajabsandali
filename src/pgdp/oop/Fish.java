package pgdp.oop;

import java.awt.*;
import java.io.File;

public class Fish extends Animal {
    static String filename = "fish.png";

    public Fish(int x, int y) {
        super(x, y);

        f = new File(filename);
        image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
    }
    @Override
    public void move() {
        if (this.alive) {
            if (antarktis[x][((y - 1) + 41) % 41] == null && isSafe(x, ((y - 1) + 41) % 41)) {
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
            } else if (antarktis[((x - 1) + 41) % 41][y] == null && isSafe(((x - 1) + 41) % 41, y)) {
                antarktis[((x - 1) + 41) % 41][y] = this;
                antarktis[x][y] = null;
                this.x = ((x - 1) + 41) % 41;
            }
        }
    }

    public boolean canEat(Animal animal) {
        return animal.eatenBy(this);
    }

    @Override
    protected boolean eatenBy(Penguin penguin) {
        return true;
    }

    @Override
    protected boolean eatenBy(PlayerPenguin playerPenguin) {
        return true;
    }

    @Override
    protected boolean eatenBy(Whale whale) {
        return false;
    }

    @Override
    protected boolean eatenBy(LeopardSeal leopardSeal) {
        return true;
    }

    @Override
    protected boolean eatenBy(Fish fish) {
        return false;
    }
}
