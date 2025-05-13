
import java.util.*;

public class LFUCache<Key, Value> implements EvictionPolicy<Key, Value> {
    private final int maxSize;
    private final Map<Key, Value> cache = new HashMap<>();
    private final Map<Key, Integer> frequencies = new HashMap<>();

    public LFUCache(int maxSize) {
        this.maxSize = maxSize;
    }

    public Value get(Key key) {
        if (cache.containsKey(key)) {
            frequencies.put(key, frequencies.getOrDefault(key, 0) + 1);
            return cache.get(key);
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (cache.size() >= maxSize && !cache.containsKey(key)) {
            Key leastUsed = Collections.min(frequencies.entrySet(), Map.Entry.comparingByValue()).getKey();
            cache.remove(leastUsed);
            frequencies.remove(leastUsed);
        }
        cache.put(key, value);
        frequencies.put(key, frequencies.getOrDefault(key, 0) + 1);
    }

    public boolean containsKey(Key key) {
        return cache.containsKey(key);
    }
}

