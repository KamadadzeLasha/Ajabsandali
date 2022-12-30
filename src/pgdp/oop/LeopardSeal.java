package pgdp.oop;

import java.awt.Toolkit;
import java.io.File;

public class LeopardSeal extends Animal {
  static String filename = "leopard.png";

  public LeopardSeal(int x, int y) {
    super(x, y);

    f = new File(filename);
    image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
  }

  public boolean canEat(Animal animal) {
    return animal.eatenBy(this);
  }

  @Override
  protected boolean eatenBy(Penguin penguin) {
    return false;
  }

  @Override
  protected boolean eatenBy(PlayerPenguin playerPenguin) {
    return false;
  }

  @Override
  protected boolean eatenBy(Whale whale) {
    return true;
  }

  @Override
  protected boolean eatenBy(LeopardSeal leopardSeal) {
    return false;
  }

  @Override
  protected boolean eatenBy(Fish fish) {
    return false;
  }
}
