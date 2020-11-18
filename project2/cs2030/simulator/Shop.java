package cs2030.simulator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Shop {

    List<Server> servers = new ArrayList<Server>();

    /**
     * Shop Constructor.
     * 
     * @param size the number of servers in the Shop
     */
    Shop(int size) {
        this.servers = Stream.iterate(new Server(1, true, false, 0), x -> x.next()).limit(size)
                .collect(Collectors.toList());
    }

    /**
     * Shop Constructor.
     * 
     * @param servers the servers list
     */
    Shop(List<Server> servers) {
        this.servers = servers;
    }

    /**
     * Shop Constructor. Merge given server and waiting customer queue togrther.
     * 
     * @param servers   the servers list
     * @param customers the waiting customers queue
     */
    Shop(List<Server> servers, List<LinkedList<Customer>> customers) {
        List<Server> tempServers = servers.stream()
                    .map(x -> new Server(x, customers.get(servers.indexOf(x))))
                    .collect(Collectors.toList());
        this.servers = tempServers;
    }

    /**
     * returns an Optional describing the first element that match the given
     * predicate, or an empty Optional if the stream is empty.
     * 
     * @param predicate predicate to apply to each element to determine if it should
     *                  be included
     * @return the first server that match the predicate
     */
    public Optional<Server> find(Predicate<Server> predicate) {
        return this.servers.stream().filter(x -> predicate.test(x)).findFirst();
    }

    /**
     * replace the server in the list with given server.
     * 
     * @param server the new server
     * @return new Shop
     */
    public Shop replace(Server server) {
        return new Shop(this.servers.stream()
                    .map(x -> x.getIdentifier() == server.getIdentifier() ? server : x)
                    .collect(Collectors.toList()));
    }

    /**
     * find first server in the Shop who is available now.
     * 
     * @return the first available server
     */
    public Optional<Server> findAvailableServer() {
        if (isAllFullQueued()) {
            return Optional.empty();
        }
        return this.find(x -> x.isAvailable());
    }

    /**
     * find first server match that the waiting queue is not full.
     * 
     * @return the first server that waiting queue is not full
     */
    public Optional<Server> findAvailableQueue() {
        if (isAllFullQueued()) {
            return Optional.empty();
        }
        return this.find(x -> !x.isFull());
    }

    /**
     * For greedy customer, find the queue with the fewest customers.
     * 
     * @return the server with the fewest customers queue
     */
    public Optional<Server> findFewestQueuedServer() {
        if (isAllFullQueued()) {
            return Optional.empty();
        }
        return this.servers.stream()
                .min((Server s1, Server s2) -> s1.getWaitingQueues().size() 
                                                        - s2.getWaitingQueues().size());

    }

    /**
     * find the suitable server for different type of customer.
     * 
     * @param customer the customer waiting to be serve
     * @return the server that serve thje customer
     */
    public Optional<Server> findServerQueue(Customer customer) {
        // for greedy customer, they will find the queue with the fewest customers
        // for typical customer, they will find the fisrt server whose queue not full
        return customer.isGreedy() ? findFewestQueuedServer() : findAvailableQueue();
    }

    /**
     * check all the servers in the Shop is full queued or not.
     * 
     * @return is all full queued or not
     */
    public boolean isAllFullQueued() {
        return this.servers.stream()
                .allMatch(server -> server.getWaitingQueues().size() == server.getCapacity());
    }

    @Override
    public String toString() {
        String result = "[";
        result = result.concat(servers.stream().map(n -> n.toString())
                       .collect(Collectors.joining(", ")));
        return result.concat("]");
    }

}
