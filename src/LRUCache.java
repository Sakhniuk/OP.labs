
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<Key, Value> implements EvictionPolicy<Key, Value> {
    private final int maxSize;
    private final Map<Key, Value> cache;

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new LinkedHashMap<>(maxSize, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<Key, Value> eldest) {
                return size() > LRUCache.this.maxSize;
            }
        };
    }

    public Value get(Key key) {
        return cache.get(key);
    }

    public void put(Key key, Value value) {
        cache.put(key, value);
    }

    public boolean containsKey(Key key) {
        return cache.containsKey(key);
    }
}

