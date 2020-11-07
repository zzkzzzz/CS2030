import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

public class Room {
    private final String name;
    private final List<Item> roomList;

    public Room(String name) {
        this.name = name;
        this.roomList = new ArrayList<>();
    }

    public Room(String name, List<Item> roomList) {
        this.name = name;
        this.roomList = new ArrayList<>(roomList);

    }

    public Room add(Item item) {
        List<Item> newList = new ArrayList<>(this.roomList);
        newList.add(item);
        return new Room(this.name, newList);
    }

    public Room go(Function<? super Room, ? extends Room> f) {
        return f.apply(this);
    }

    public Room back(Function<? super Room, ? extends Room> f) {
        return f.apply(this);
    }

    public Room tick() {
        List<Item> newList = new ArrayList<>(this.roomList);
        return new Room(this.name, newList.stream().map(x -> x.change()).collect(Collectors.toList()));
    }

    public Room tick(Function<? super Item, ? extends Item> s) {
        List<Item> newList = new ArrayList<>(this.roomList);
        return new Room(this.name, newList.stream().map(x -> s.apply(x.change())).collect(Collectors.toList()));

    }

    public String toString() {
        String result = String.format("@%s", this.name);
        result = result.concat("\n");
        for (int i = 0; i < this.roomList.size(); i++) {
            result = result.concat(roomList.get(i).toString());
            if (i < this.roomList.size() - 1)
                result = result.concat("\n");
        }

        return result;
    }

}
