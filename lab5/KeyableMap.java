import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class KeyableMap<V extends Keyable> {
    private final String key;
    private final Map<String, V> map;

    /**
     * KeyableMap Constructor
     * 
     * @param key key
     */
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

    /**
     * get the value according to the key give
     * 
     * @param key module id
     * @return the Optional value to which the specified key is mapped, or
     *         Optional.empty if this map contains no mapping for the key
     */
    public Optional<V> get(String key) {
        return Optional.ofNullable(this.map.get(key));
    }

    /**
     * Associates the specified value with the specified key in this map
     * 
     * @param V value to be associated with the specified key
     * @return the previous value associated with key
     *
     */
    public KeyableMap<V> put(V item) {
        this.map.put(item.getKey(), item);
        return this;
    }

    /**
     * tostring format: Tony: {CS2040: {{Lab1: B}}}
     */
    @Override
    public String toString() {
        String result = this.getKey() + ": {";

        for (Map.Entry<String, V> e : map.entrySet()) {
            result = result.concat(e.getValue() + ", ");
        }
        if (this.map.size() != 0)
            result = result.substring(0, result.length() - 2);

        return result.concat("}");

    }

}
