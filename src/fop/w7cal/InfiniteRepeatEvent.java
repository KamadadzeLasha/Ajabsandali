package fop.w7cal;

public class InfiniteRepeatEvent extends Event {
    private int period;
    public InfiniteRepeatEvent(int day, String description, String place, int period) {
        super(day, description, place);
        this.period = period;
    }
    
    public int getPeriod() {
        return period;
    }
    
    @Override
    public int diff(int day) {
        if (day < getDay())
            return super.diff(day);
        else
            return (super.diff(day)  + period) % period - ((super.diff(day) % period + period) % period);
    }
}