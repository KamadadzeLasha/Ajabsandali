package fop.w7cal;

public class Calendar {
    EventList events;
    
    public void addNewEvent(Event event) {
        if (events == null) {
            events = new EventList(event);
        }
        else {
            events.add(event);
        }
    }
    
    public Event[] eventsAt(int day) {
        if (events == null) {
            return new Event[0];
        }
        int count = 0;
        EventList eventList = events;
        while (eventList != null) {
            if (eventList.getEvent().diff(day) == 0) {
                count++;
            }
            eventList = eventList.getNext();
        }
        Event[] result = new Event[count];
        int index = 0;
        for (EventList tl = events; tl != null; tl = tl.getNext()) {
            if (tl.getEvent().diff(day) == 0) {
                result[index] = tl.getEvent();
                index++;
            }
        }
        return result;
    }
    
    public Event nextEvent(int day) {
        if (events == null)
            return null;
        
        Event nextEvent = null;
        int minDays = Integer.MAX_VALUE;
        for (EventList current = events; current != null; current = current.getNext()) {
            if (current.getEvent().diff(day) >= 0 && current.getEvent().diff(day) < minDays) {
                nextEvent = current.getEvent();
                minDays = current.getEvent().diff(day);
            }
        }
        return nextEvent;
    }
}
