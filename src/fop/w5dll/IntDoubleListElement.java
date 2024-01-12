package fop.w5dll;

public class IntDoubleListElement {
    private int info;

    public void setInfo(int inf) {
        info = inf;
    }

    public int getInfo() {
        return info;
    }

    public IntDoubleListElement next;
    public IntDoubleListElement prev;

    public IntDoubleListElement(int info) {
        this.info = info;
        this.next = null;
        this.prev = null;
    }

    public boolean isEqual(IntDoubleListElement other) {
        return other != null && info == other.info;
    }
}
