import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class KeyableMap<V extends Keyable> {
    private final String key;
    private final Map<String, V> map;

    KeyableMap(String key) {
        this.key = key;
        this.map = new HashMap<>();
    }

    public String getKey() {
        return this.key;
    }

    public Map<String, V> getMap() {
        return this.map;
    }

    public Optional<V> get(String key) {
        return Optional.ofNullable(this.map.get(key));
    }

    public KeyableMap<V> put(V item) {
        this.map.put(item.getKey(), item);
        return this;
    }

    @Override
    public String toString() {
        String result = this.getKey() + ":{";

        for (Map.Entry<String, V> e : map.entrySet()) {
            result = result.concat(e.getValue() + ",");
        }
        if (this.map.size() != 0)
            result = result.substring(0, result.length() - 1);

        return result.concat("}");

    }

}
