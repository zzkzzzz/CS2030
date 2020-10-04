package cs2030.simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * To schedule all the events base on the number of servers and the list of
 * arrival time of customers.
 */
public class Schedule {
    private final int numOfServers;
    private final List<Double> arrivalTime;
    private final List<Customer> customersList;
    private final List<Server> serversList;
    private final PriorityQueue<Event> eventsList;
    private final List<Number> statistics;

    /**
     * Schedule Constructor.
     *
     * @param numOfServers the numbers of servers
     * @param arrivalTime  the list of the arrival time of customer
     */
    public Schedule(int numOfServers, List<Double> arrivalTime) {
        this.numOfServers = numOfServers;
        this.arrivalTime = arrivalTime;

        // create all the servers
        this.serversList = new ArrayList<Server>();
        for (int i = 0; i < numOfServers; i++) {
            serversList.add(new Server(i + 1, true, false, 0));
        }
        // create all the customers
        this.customersList = new ArrayList<Customer>();
        for (int i = 0; i < arrivalTime.size(); i++) {
            customersList.add(new Customer(i + 1, arrivalTime.get(i)));
        }

        this.eventsList = this.setEventList();
        this.statistics = this.setStatistics();
    }

    /**
     * get the numbers of Servers.
     * 
     * @return return the numbers of Servers
     */
    public int getNumOfServers() {
        return numOfServers;
    }

    /**
     * get the list of arrivalTime of all Customers.
     * 
     * @return return the list of arrivalTime of all Customers
     */
    public List<Double> getArrivalTime() {
        return new ArrayList<Double>(arrivalTime);
    }

    /**
     * get the numbers of Customers.
     * 
     * @return return the numbers of Customers
     */
    public int getNumOfCustomers() {
        return arrivalTime.size();
    }

    /**
     * get the list of all Servers.
     * 
     * @return return the list of all Servers
     */
    public List<Server> getServerList() {
        return new ArrayList<Server>(serversList);
    }

    /**
     * get the list of all Customers.
     * 
     * @return return the list of all Customers
     */
    public List<Customer> getCustomerList() {
        return new ArrayList<Customer>(customersList);
    }

    /**
     * get all Event which store in PriorityQueue.
     * 
     * @return return the list of all Event
     */
    public PriorityQueue<Event> getEventList() {
        return new PriorityQueue<Event>(eventsList);
    }

    /**
     * get the list of Statistics.
     * 
     * @return return the list of Statistics
     */
    public List<Number> getStatistics() {
        return statistics;
    }

    /**
     * calculate the Statistics base on all the events.
     * 
     * @return return the list of Statistics
     */
    private List<Number> setStatistics() {
        // the total waiting time for customers who have been served
        double totalWatingTime = 0;
        // the average waiting time for customers who have been served
        double aveWaitingTime = 0;
        // the number of customers served
        int numOfCusServed = 0;
        // the number of customers who left without being served
        int numOfCusLeft = 0;

        for (Event newEvent : this.eventsList) {
            // update number Of customer served
            if (newEvent instanceof ServeEvent) {
                numOfCusServed++;
            }
            // update the total waiting time
            if (newEvent instanceof WaitEvent) {
                int id = newEvent.getCurrentServerId();
                Server currentServer = newEvent.getServerList().get(id);
                double nextAvailableTime = currentServer.getNextAvailableTime();
                double customerArrivalTime = newEvent.getCustomer().getArrivalTime();
                double waitTime = nextAvailableTime - customerArrivalTime;
                totalWatingTime += waitTime;
            }
            // update the number of customer left
            if (newEvent instanceof LeaveEvent) {
                numOfCusLeft++;
            }
        }

        // calculate the average waiting time
        if (numOfCusServed != 0) {
            aveWaitingTime = totalWatingTime / numOfCusServed;
        }

        return new ArrayList<>(Arrays.asList(aveWaitingTime, numOfCusServed, numOfCusLeft));
    }

    /**
     * get all the events base on the servers and customer given.
     * 
     * @return return the PriorityQueue contains all events
     */
    private PriorityQueue<Event> setEventList() {
        int size = this.getNumOfCustomers();
        // initialize the tempEventQueue with EventComparator
        PriorityQueue<Event> tempEventQueue = new PriorityQueue<Event>(size, new EventComparator());
        // initialize the allEventsQueue with EventComparator
        PriorityQueue<Event> allEventsQueue = new PriorityQueue<Event>(size, new EventComparator());
        List<Server> currenServerList = this.getServerList();

        // add all arriveEvent into the queue
        for (int i = 0; i < size; i++) {
            Event newEvent = new ArriveEvent(customersList.get(i), serversList);
            tempEventQueue.add(newEvent);
        }

        // loop through all the events in the tempEventQueue
        while (!tempEventQueue.isEmpty()) {
            // get the new event from the head of the queue
            Event newEvent = tempEventQueue.poll();
            // get the next event after executing the new event
            Event nextEvent = newEvent.execute();
            allEventsQueue.add(newEvent);

            // update the serversList base on current event
            if (newEvent.getCurrentServerId() >= 0) {
                currenServerList.set(newEvent.getCurrentServerId(),
                        newEvent.getServerList().get(newEvent.getCurrentServerId()));
            }

            // if the event is ArriveEvent,
            // need to update the serversList by current Server list
            if (newEvent instanceof ArriveEvent) {
                Event arriveEvent = new ArriveEvent(newEvent.getCustomer(), currenServerList);
                nextEvent = arriveEvent.execute();
            }

            // if next event is not null, add the event to queue
            // LeaveEvent -> null and DoneEvent -> null
            if (nextEvent != null) {
                tempEventQueue.add(nextEvent);
            }
        }

        return allEventsQueue;
    }

    /**
     * print the Statistics.
     */
    public void printStatistics() {
        // convert the type from <Number> to double and int
        double var1 = (double) statistics.get(0);
        int var2 = (int) statistics.get(1);
        int var3 = (int) statistics.get(2);
        String stat = String.format("[%.3f %d %d]", var1, var2, var3);
        System.out.println(stat);
    }

    /**
     * print the Event list.
     */
    public void printEeventList() {
        PriorityQueue<Event> events = this.getEventList();
        while (!events.isEmpty()) {
            Event event = events.poll();
            System.out.println(event);
        }
    }

    @Override
    public String toString() {
        return serversList.toString().concat("\n").concat(customersList.toString());
    }
}