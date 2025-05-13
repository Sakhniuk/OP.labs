
public interface EvictionPolicy<Key, Value> {
    Value get(Key key);
    void put(Key key, Value value);
    boolean containsKey(Key key);
}

