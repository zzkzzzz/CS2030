package cs2030.simulator;

import java.util.Comparator;

/**
 * The Comparator for Events.
 * 
 */
public class EventComparator implements Comparator<Event> {

    /**
     * Compare two events for order.
     * 
     * @param e1 the first event
     * @param e2 the second event
     * @return Returns a negative integer, zero, or a positive integer as the first
     *         event is less than, equal to, or greater than the second event.
     * 
     */
    @Override
    public int compare(Event e1, Event e2) {
        int id1 = e1.getCustomer().getId();
        int id2 = e2.getCustomer().getId();
        double time1 = e1.getStartTime();
        double time2 = e2.getStartTime();

        // if two events are from same customer
        // then sort base on the Event Priority
        if (Integer.compare(id1, id2) == 0) {
            return e1.getPriority() - e2.getPriority();
        } else {
            // else if two events are from different customers
            if (Math.abs(time1 - time2) < 0.000001) {
                // if the start time are the same
                // then sort by the id of customer
                return Integer.compare(id1, id2);
            } else {
                // if start time are different
                // then sort by start time
                return Double.compare(time1, time2);
            }
        }

    }
}
