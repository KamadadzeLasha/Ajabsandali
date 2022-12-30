package fop.w8trees;
import java.util.Comparator;

public class PenguinTrees {
    public static Tree<Penguin> getAgeTree() {
        return new Tree<Penguin>(new Comparator<Penguin>() {
            @Override
            public int compare(Penguin o1, Penguin o2) {
                return o1.getAge() - o2.getAge();
            }
        });
    }

    public static Tree<Penguin> getHeightTree() {
        return new Tree<Penguin>(new Comparator<>() {
            @Override
            public int compare(Penguin o1, Penguin o2) {
                return (int) Math.signum(o2.getHeight() - o1.getHeight());
            }
        });
    }

    public static int getMinorsAmount(Tree<Penguin> tree) {
        return tree.countMatches(p -> p.getAge() < 5);
    }

    public static Object[] getEvenNames(Tree<Penguin> tree) {
        return tree.getAll(p -> (p.getName().length() % 2) == 0);
    }
}
