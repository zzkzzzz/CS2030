package cs2030.simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * To schedule all the events base on the number of servers and the list of
 * arrival time of customers.
 */
public class Schedule {
    private final int numOfServers;
    private final int numOfCustomers;
    private final int numsOfSelfCheckCounters;
    private final double probabilityOfGreedy;
    private final double probabilityOfResting;
    private final List<Double> arrivalTime;
    private final List<Customer> customersList;
    private final List<Server> serversList;
    private final List<Number> statistics;
    private final PriorityQueue<Event> eventsList;
    private final RandomGenerator randomGenerator;

    /**
     * initialize the schedule according to user input.
     * 
     * @param args user input
     * @return Schedule instance
     */
    public static Schedule initializeSchedule(String[] args) {

        if (args.length == 5) { // Level 3
            RandomGenerator r = new RandomGenerator(Integer.parseInt(args[0]), 
                    Double.parseDouble(args[3]), Double.parseDouble(args[4]), 0);
            return new Schedule(r, args[1], args[2], "1", "0", "0", "0");
        } else if (args.length == 6) { // Level 4
            RandomGenerator r = new RandomGenerator(Integer.parseInt(args[0]), 
                    Double.parseDouble(args[4]), Double.parseDouble(args[5]), 0);
            return new Schedule(r, args[1], args[3], args[2], "0", "0", "0");
        } else if (args.length == 8) { // Level 5
            RandomGenerator r = new RandomGenerator(Integer.parseInt(args[0]), 
                    Double.parseDouble(args[4]), Double.parseDouble(args[5]), 
                    Double.parseDouble(args[6]));
            return new Schedule(r, args[1], args[3], args[2], args[7], "0", "0");
        } else if (args.length == 9) { // Level 6
            RandomGenerator r = new RandomGenerator(Integer.parseInt(args[0]), 
                    Double.parseDouble(args[5]), Double.parseDouble(args[6]), 
                    Double.parseDouble(args[7]));
            return new Schedule(r, args[1], args[4], args[3], args[8], args[2], "0");
        } else if (args.length == 10) { // Level 7
            RandomGenerator r = new RandomGenerator(Integer.parseInt(args[0]), 
                    Double.parseDouble(args[5]), Double.parseDouble(args[6]), 
                    Double.parseDouble(args[7]));
            return new Schedule(r, args[1], args[4], args[3], args[8], args[2], args[9]);
        } else {
            throw new IllegalArgumentException("Invalid Input");
        }
    }

    /**
     * Schedule Constructor.
     * 
     * @param r                       RandomGenerator
     * @param numOfServers            number of servers
     * @param numOfCustomers          number of customers to simulate
     * @param queueCapacity           maximum queue length, Qmax
     * @param probabilityOfResting    probability of resting, Pr
     * @param numsOfSelfCheckCounters number of self-checkout counters, Nself
     * @param probabilityOfGreedy     probability of a greedy customer occurring
     * 
     */
    public Schedule(RandomGenerator r, String numOfServers, String numOfCustomers, 
            String queueCapacity, String probabilityOfResting, String numsOfSelfCheckCounters, 
            String probabilityOfGreedy) {

        this.randomGenerator = r;
        this.numOfServers = Integer.parseInt(numOfServers);
        this.numOfCustomers = Integer.parseInt(numOfCustomers);
        this.probabilityOfGreedy = Double.parseDouble(probabilityOfGreedy);
        this.probabilityOfResting = Double.parseDouble(probabilityOfResting);
        this.numsOfSelfCheckCounters = Integer.parseInt(numsOfSelfCheckCounters);

        // create all human servers
        int capacity = Integer.parseInt(queueCapacity);
        this.serversList = new ArrayList<Server>();
        for (int i = 0; i < this.numOfServers; i++) {
            serversList.add(new Server(i + 1, true, false, Lazy.of(0.0), capacity, 
                new LinkedList<>(), null, false));
        }

        // create self-checked counter
        for (int i = this.numOfServers; i < this.numsOfSelfCheckCounters + this.numOfServers; i++) {
            serversList.add(new Server(i + 1, true, false, Lazy.of(0.0), capacity, 
                new LinkedList<>(), null, true));
        }

        // create customers list and arrival time list
        this.customersList = new ArrayList<>();
        this.arrivalTime = new ArrayList<>();

        // first customer arrival event with timestamp 0
        customersList.add(new Customer(1, 0, () -> this.randomGenerator.genServiceTime(),
                this.randomGenerator.genCustomerType() < this.probabilityOfGreedy));
        arrivalTime.add(0.0);

        double currentTime = 0.0;
        // the rest typical customers arrive time generated by RandomGenerator
        for (int i = 1; i < this.numOfCustomers; i++) {
            currentTime = currentTime + this.randomGenerator.genInterArrivalTime();
            arrivalTime.add(currentTime);
            customersList.add(new Customer(i + 1, currentTime, 
                () -> this.randomGenerator.genServiceTime(),
                this.randomGenerator.genCustomerType() < this.probabilityOfGreedy));
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
        return this.numOfCustomers;
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
                double nextAvailableTime = newEvent.getCurrentServer().getNextAvailableTime().get();
                double customerArrivalTime = newEvent.getCustomer().getArrivalTime();
                double startTime = this.eventsList.stream()
                        .filter(x -> (x instanceof ServeEvent)
                                && (x.getCustomer().getId() == newEvent.getCustomer().getId()))
                        .findFirst().get().getStartTime();
                double waitTime = startTime - customerArrivalTime;
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

    private PriorityQueue<Event> setEventList() {
        int size = this.getNumOfCustomers();
        // initialize the tempEventQueue with EventComparator
        PriorityQueue<Event> tempEventQueue = new PriorityQueue<Event>(size, new EventComparator());
        // initialize the allEventsQueue with EventComparator
        PriorityQueue<Event> allEventsQueue = new PriorityQueue<Event>(size, new EventComparator());
        // initialize the currentServerList
        List<Server> currenServerList = this.getServerList();
        // initialize the waitingCustomerQueue with empty queue
        List<LinkedList<Customer>> waitingCustomerQueue = new ArrayList<>();
        // initialize the waitingCustomerQueue for all selfCheckOutCounters
        LinkedList<Customer> selfCheckedCounterQueue = new LinkedList<>();

        for (int i = 0; i < currenServerList.size(); i++) {
            waitingCustomerQueue.add(new LinkedList<>());
        }

        // add all arriveEvent into the queue
        for (int i = 0; i < size; i++) {
            Event newEvent = new ArriveEvent(customersList.get(i));
            tempEventQueue.add(newEvent);
        }

        // loop through all the events in the tempEventQueue
        while (!tempEventQueue.isEmpty()) {

            // get the new event from the head of the queue
            Event newEvent = tempEventQueue.poll();

            
            // only when the server is available, next wait event will be poll
            if (newEvent instanceof WaitEvent) {
                int indexOfServer = newEvent.getCurrentServer().getIdentifier() - 1;
                Server server = currenServerList.get(indexOfServer);

                List<Event> events = new ArrayList<>();
                while (newEvent instanceof WaitEvent 
                        && ((!server.isSelfChecked() && !server.isAvailable())
                            || (server.isSelfChecked() 
                                && currenServerList.stream().filter(x -> x.isSelfChecked())
                                .allMatch(x -> !x.isAvailable())))) {

                    events.add(newEvent);
                    newEvent = tempEventQueue.poll();
                    if (newEvent instanceof WaitEvent) {
                        indexOfServer = newEvent.getCurrentServer().getIdentifier() - 1;
                        server = currenServerList.get(indexOfServer);
                    }

                }

                tempEventQueue.addAll(events);
                events.clear();
            }

            // if the server is resting after doneEvent, push back the serve event
            if (newEvent instanceof ServeEvent) {
                Server server = newEvent.getCurrentServer();
                if (!server.isSelfChecked()) {
                    while (newEvent instanceof ServeEvent && newEvent.getStartTime() < 
                    currenServerList.get(server.getIdentifier() - 1).getNextAvailableTime().get()) {
                        Event newWaitEvent = new WaitEvent(newEvent.getCustomer(),
                                currenServerList.get(server.getIdentifier() - 1));
                        Event newServeEvent = newWaitEvent.execute(
                                new Shop(currenServerList, waitingCustomerQueue)).second();
                        tempEventQueue.add(newServeEvent);

                        newEvent = tempEventQueue.poll();
                    }
                }

            }

            // update the serversList base on current event
            if (newEvent.getCurrentServer() != null) {
                if (newEvent instanceof ServeEvent) {
                    LinkedList<Customer> list = waitingCustomerQueue
                            .get(newEvent.getCurrentServer().getIdentifier() - 1);
                    list.poll();
                    waitingCustomerQueue.set(newEvent.getCurrentServer().getIdentifier() - 1, list);

                    if (newEvent.getCurrentServer().isSelfChecked()) {
                        selfCheckedCounterQueue = new LinkedList<>(list);

                        for (int i = this.numOfServers; i < waitingCustomerQueue.size(); i++) {
                            waitingCustomerQueue.set(i, selfCheckedCounterQueue);
                        }

                    }
                }
                if (!(newEvent instanceof WaitEvent)) {
                    currenServerList.set(newEvent.getCurrentServer().getIdentifier() - 1, 
                        newEvent.getCurrentServer());
                }
            }

            Event nextEvent = newEvent.execute(new Shop(currenServerList)).second();

            // if current event is ArriveEvent,
            // need to update the servers by currentServer list
            if (newEvent instanceof ArriveEvent) {

                Event arriveEvent = new ArriveEvent(newEvent.getCustomer());
                nextEvent = arriveEvent.execute(
                        new Shop(currenServerList, waitingCustomerQueue)).second();

                // if nextEvent is WaitEvent, add to waitingCustomerQueue
                if (nextEvent instanceof WaitEvent) {
                    Customer temp = newEvent.getCustomer();
                    LinkedList<Customer> list = waitingCustomerQueue
                            .get(nextEvent.getCurrentServer().getIdentifier() - 1);
                    list.add(temp);

                    waitingCustomerQueue
                            .set(nextEvent.getCurrentServer().getIdentifier() - 1, list);

                    // if the customer wait in self-checked counter
                    // update all queues of self-checked counter
                    if (nextEvent.getCurrentServer().isSelfChecked()) {
                        selfCheckedCounterQueue = new LinkedList<>(list);
                        for (int i = this.numOfServers; i < waitingCustomerQueue.size(); i++) {
                            waitingCustomerQueue.set(i, selfCheckedCounterQueue);
                        }
                    }
                }

            }

            // if current event is DoneEvent, next event should be Resting Event
            // need to set the resting time of the server
            if (newEvent instanceof DoneEvent && !newEvent.getCurrentServer().isSelfChecked()) {
                // If the value returned is less than probabilityOfResting,
                // update the server with the resting time
                if (this.randomGenerator.genRandomRest() < this.probabilityOfResting) {

                    Event doneEvent = new DoneEvent(newEvent.getCustomer(),
                            new Server(newEvent.getCurrentServer(), 
                                    this.randomGenerator.genRestPeriod()));

                    nextEvent = doneEvent.execute(
                            new Shop(currenServerList, waitingCustomerQueue)).second();

                    currenServerList.set(nextEvent.getCurrentServer().getIdentifier() - 1,
                            nextEvent.getCurrentServer());
                } else {
                    // the server is not resting
                    // since the default resting time is 0, no need update the server status
                    Event doneEvent = new DoneEvent(newEvent.getCustomer(), 
                                                new Server(newEvent.getCurrentServer(), 0));
                    nextEvent = doneEvent.execute(
                            new Shop(currenServerList, waitingCustomerQueue)).second();
                }
            }

            // if current event is WaitEvent,
            // need to update the servers by currentServer list
            if (newEvent instanceof WaitEvent) {
                Server server = newEvent.getCurrentServer();
                if (!server.isSelfChecked()) {
                    int index = server.getIdentifier() - 1;
                    Event waitEvent = new WaitEvent(newEvent.getCustomer(),
                            new Server(currenServerList.get(index), 
                                    waitingCustomerQueue.get(index)));
                    nextEvent = waitEvent.execute(
                            new Shop(currenServerList, waitingCustomerQueue)).second();
                    // update the serverlist since the server will have a new waiting customer

                    currenServerList.set(nextEvent.getCurrentServer().getIdentifier() - 1, 
                                    newEvent.getCurrentServer());
                } else {
                    Server selfChekedServer = currenServerList.stream()
                            .filter(x -> x.isSelfChecked())
                            .filter(x -> x.isAvailable()).collect(Collectors.toList()).get(0);
                    int index = selfChekedServer.getIdentifier() - 1;
                    Event waitEvent = new WaitEvent(newEvent.getCustomer(),
                        new Server(currenServerList.get(index), waitingCustomerQueue.get(index)));

                    nextEvent = waitEvent.execute(
                            new Shop(currenServerList, waitingCustomerQueue)).second();

                    // update the serverlist since the server will have a new waiting customer

                    currenServerList.set(nextEvent.getCurrentServer().getIdentifier() - 1,
                            nextEvent.getCurrentServer().goBusyNeedWait(
                                    newEvent.getCurrentServer().getNextAvailableTime(), 
                                            newEvent.getCustomer()));

                }

            }

            // if next event is not null, add the event to queue
            // LeaveEvent -> null and DoneEvent -> null
            if (nextEvent != null) {
                tempEventQueue.add(nextEvent);
            }
            allEventsQueue.add(newEvent);

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
            if (!(event instanceof ServerRestEvent || event instanceof ServerBackEvent)) {
                System.out.println(event);
            }

        }
    }

    @Override
    public String toString() {
        return serversList.toString().concat("\n").concat(customersList.toString());
    }
}