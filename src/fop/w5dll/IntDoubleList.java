package fop.w5dll;

public class IntDoubleList {
    private IntDoubleListElement head;
    private IntDoubleListElement tail;

    public IntDoubleList() {
        head = null;
        tail = null;
    }

    public IntDoubleListElement getFirstElement() {
        return head;
    }

    public IntDoubleListElement getLastElement() {
        return tail;
    }

    public void append(int info) {
        IntDoubleListElement newEle = new IntDoubleListElement(info);
        if (head == null) {
            head = newEle;
        } else {
            tail.next = newEle;
            newEle.prev = tail;
        }
        tail = newEle;
    }

    public int size() {
        IntDoubleListElement head = this.head;
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    public int get(int pos) {
        if (pos < 0 || pos >= this.size()) {
            System.out.println("Postion exceeds sizes.");
            return -1;
        }
        IntDoubleListElement ele = this.head;
        for (int i = 0; i < pos; i++) {
            ele = ele.next;
        }
        return ele.getInfo();
    }


    public void remove(int pos) {
        IntDoubleListElement temp = head;
        if (this.size() <= pos || pos < 0) {
            System.out.println("Position is out of bounds.");
        } else if (pos == 0) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
        } else {
            int j = 0;
            while (j < pos - 1) {
                temp = temp.next;
                j++;
            }
            temp.next = temp.next.next;
            if (temp.next != null) temp.next.prev = temp;
            else tail = temp;
        }
    }

    @Override
    public String toString() {
        IntDoubleListElement temp = head;
        StringBuilder stringBuilder = new StringBuilder();
        while (temp != null) {
            stringBuilder.append(temp.getInfo());
            if (temp.next != null) stringBuilder.append(',');
            temp = temp.next;
        }
        return stringBuilder.toString();
    }


    public boolean isEqual(IntDoubleList other) {
        IntDoubleListElement thisEle = head;
        IntDoubleListElement otherEle = other.head;
        while (thisEle != null) {
            if (!thisEle.isEqual(otherEle)) return false;
            thisEle = thisEle.next;
            otherEle = otherEle.next;
        }
        return otherEle == null;
    }


    public int sum() {
        IntDoubleListElement head = this.head;
        int sum = 0;
        while (head != null) {
            sum += head.getInfo();
            head = head.next;
        }
        return sum;
    }


    // copying internal elements
    public IntDoubleList copy() {
        IntDoubleListElement head = this.head;
        IntDoubleList copy = new IntDoubleList();
        while (head != null) {
            copy.append(head.getInfo());
            head = head.next;
        }
        return copy;
    }


    public IntDoubleListElement[] search(int intValue) {
        IntDoubleListElement head = this.head;
        int length = 0;
        while (head != null) {
            if (head.getInfo() == intValue) {
                length++;
            }
            head = head.next;
        }
        head = this.head;
        IntDoubleListElement[] elements = new IntDoubleListElement[length];
        int i = 0;
        while (head != null) {
            if (head.getInfo() == intValue) {
                elements[i] = head;
                i++;
            }
            head = head.next;
        }
        return elements;
    }

}
