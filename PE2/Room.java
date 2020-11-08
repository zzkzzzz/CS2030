import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

public class Room {
    private final String name;
    private final List<Item> roomList;
    private final Room previousRoom;

    public Room(String name) {
        this.name = name;
        this.roomList = new ArrayList<>();
        this.previousRoom = this;
    }

    public Room(String name, List<Item> roomList) {
        this.name = name;
        this.roomList = new ArrayList<>(roomList);
        this.previousRoom = this;
    }

    public Room(String name, Room other) {
        this.name = name;
        this.roomList = new ArrayList<>(other.getRoomList());
        this.previousRoom = other;
    }

    public Room(String name, List<Item> roomList, Room previousRoom) {
        this.name = name;
        this.roomList = new ArrayList<>(roomList);
        this.previousRoom = previousRoom;
    }

    public String getName() {
        return name;
    }

    public List<Item> getRoomList() {
        return roomList;
    }

    public Room getPreviousRoom() {
        return previousRoom;
    }

    public Room add(Item item) {
        List<Item> newList = new ArrayList<>(this.roomList);
        newList.add(item);
        return new Room(this.name, newList);
    }

    public Room go(Function<? super Room, ? extends Room> f) {
        List<Item> newList = this.roomList.stream().filter(item -> item instanceof Sword).collect(Collectors.toList());
        if (!newList.isEmpty() && (newList.stream().anyMatch(item -> ((Sword) item).isTaken()))) {
            List<Item> temp = f.apply(this).getRoomList().stream().filter(item -> !(item instanceof Sword))
                    .collect(Collectors.toList());
            temp.add(0, new Sword(0, newList.get(0).getStatusList(), true));
            List<Item> list = this.getRoomList();
            list.removeIf((item) -> (item instanceof Sword));
            return new Room(f.apply(this).getName(), temp, this);
        }
        Room room = f.apply(this);
        return new Room(room.getName(), room.getRoomList(), this);
    }

    public Room back() {
        List<Item> newList = this.roomList.stream().filter(item -> item instanceof Sword).collect(Collectors.toList());
        if (!newList.isEmpty() && (newList.stream().anyMatch(item -> ((Sword) item).isTaken()))) {
            List<Item> temp = this.previousRoom.getRoomList().stream().filter(item -> !(item instanceof Sword))
                    .collect(Collectors.toList());
            temp.add(new Sword(0, newList.get(0).getStatusList(), true));
            return new Room(this.previousRoom.getName(), temp, this.previousRoom.getPreviousRoom()).tick();
        }
        return new Room(this.previousRoom.getName(), this.previousRoom.getRoomList(),
                this.previousRoom.getPreviousRoom()).tick();
    }

    public Room tick() {
        List<Item> newList = new ArrayList<>(this.roomList);
        return new Room(this.name, newList.stream().map(x -> x.change()).collect(Collectors.toList()),
                this.previousRoom);
    }

    public Room tick(Function<List<Item>, List<Item>> s) {
        List<Item> newList = new ArrayList<>(this.roomList);
        return new Room(this.name, s.apply(newList.stream().map(x -> x.change()).collect(Collectors.toList())),
                this.previousRoom);
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
