package fop.w5dll;

public class IntDoubleList {
    private IntDoubleListElement head;
    private IntDoubleListElement tail;
    
    public IntDoubleList() {
        head = null;
        tail = null;
    }
    
    public void append(int info) {
        IntDoubleListElement newElement = new IntDoubleListElement(info);
        if (head == null) {
            head = newElement;
        }
        else {
            tail.next = newElement;
            newElement.prev = tail;
        }
        tail = newElement;
    }
    
    public int size() {
        IntDoubleListElement temp = head;
        int j = 0;
        while (temp != null) {
            temp = temp.next;
            j++;
        }
        return j;
    }
    
    public int get(int pos) {
        if (pos < 0 || this.size() <= pos) {
            System.out.println("Position exceeds list!");
            return 0;
        }
        
        IntDoubleListElement temp = head;
        for (int i = 0; i < pos; i++)
            temp = temp.next;
        
        return temp.getInfo();
    }
    
    
    public void remove(int pos) {
        IntDoubleListElement temp = head;
        if (this.size() <= pos || pos < 0) {
            System.out.println("Position ist groesser als Liste!");
        }
        else if (pos == 0) {
            head = head.next;
            if (head != null)
                head.prev = null;
            else
                tail = null;
        }
        else {
            int j = 0;
            while (j < pos - 1) {
                temp = temp.next;
                j++;
            }
            
            temp.next = temp.next.next;
            if (temp.next != null)
                temp.next.prev = temp;
            else
                // If temp.next == null, the end of the list has been reached
                tail = temp;
        }
    }
    
    @Override
    public String toString() {
        IntDoubleListElement temp = head;
        StringBuilder ret = new StringBuilder();
        while (temp != null) {
            ret.append(temp.getInfo());
            if (temp.next != null)
                ret.append(',');
            temp = temp.next;
        }
        return ret.toString();
    }
    
    
    public boolean isEqual(IntDoubleList other) {
        IntDoubleListElement mytemp = head;
        IntDoubleListElement othertemp = other.head;
        while (mytemp != null) {
            if (!mytemp.isEqual(othertemp))
                return false;
            mytemp = mytemp.next;
            othertemp = othertemp.next;
        }
        return othertemp == null;
    }
    
    
    public int sum() {
        IntDoubleListElement temp = head;
        int ret = 0;
        while (temp != null) {
            ret = ret + temp.getInfo();
            temp = temp.next;
        }
        return ret;
    }
    
    
    public IntDoubleListElement getFirstElement() {
        return head;
    }
    
    public IntDoubleListElement getLastElement() {
        return tail;
    }
    
    
    // copying internal elements
    public IntDoubleList copy() {
        IntDoubleList ret = new IntDoubleList();
        IntDoubleListElement temp = head;
        while (temp != null) {
            ret.append(temp.getInfo());
            temp = temp.next;
        }
        return ret;
    }
    
    
    public int[] search(int intValue) {
        int[] results = new int[this.size()];
        int j = 0;
        int i = 0;
        IntDoubleListElement temp = head;
        while (temp != null) {
            if (temp.getInfo() == intValue) {
                results[j] = temp.getInfo();
                j++;
            }
            temp = temp.next;
            i++;
        }
        int[] ret = new int[j];
        System.arraycopy(results, 0, ret, 0, j);
        return ret;
    }
}
