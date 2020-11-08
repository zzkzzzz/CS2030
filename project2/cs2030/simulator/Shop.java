package cs2030.simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Shop {
    List<Server> servers = new ArrayList<Server>();

    Shop(int size) {
        this.servers = Stream.iterate(new Server(1, true, false, 0), x -> x.next()).limit(size)
                .collect(Collectors.toList());
    }

    Shop(List<Server> servers) {
        this.servers = servers;
    }

    public Optional<Server> find(Predicate<Server> predicate) {
        return this.servers.stream().filter(x -> predicate.test(x)).findFirst();
    }

    public Shop replace(Server server) {
        return new Shop(this.servers.stream().map(x -> x.getIdentifier() == server.getIdentifier() ? server : x)
                .collect(Collectors.toList()));
    }

    public String toString() {
        String result = "[";
        result = result.concat(servers.stream().map(n -> n.toString()).collect(Collectors.joining(", ")));
        return result.concat("]");
    }

}
