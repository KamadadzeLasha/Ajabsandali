package pgdp.oop;

public class PlayerPenguin extends Penguin {
    public PlayerPenguin(int x, int y) {
        super(x, y);
    }

     boolean move(int newX, int newY) {
        if (antarktis[newX][newY] == null) {
            antarktis[newX][newY] = this;
            antarktis[this.x][this.y] = null;
            this.x = newX;
            this.y = newY;
            return false;
        } else if (this.canEat(antarktis[newX][newY])) {
            antarktis[newX][newY].alive = false;
            antarktis[newX][newY] = this;
            antarktis[this.x][this.y] = null;
            this.x = newX;
            this.y = newY;
            return false;
        } else {
            antarktis[this.x][this.y] = null;
            this.x = newX;
            this.y = newY;
            return true;
        }
    }
    public boolean canEat(Animal animal) {
        return animal.eatenBy(this);
    }
}
