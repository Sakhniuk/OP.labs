
import java.util.HashMap;
import java.util.Map;

public class TimeBasedCache<Key, Value> implements EvictionPolicy<Key, Value> {
    private final Map<Key, CacheEntry<Value>> cache = new HashMap<>();
    private final long expiryTimeMillis;

    private static class CacheEntry<Value> {
        Value value;
        long timestamp;

        CacheEntry(Value value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    public TimeBasedCache(long expiryTimeMillis) {
        this.expiryTimeMillis = expiryTimeMillis;
    }

    public Value get(Key key) {
        if (containsKey(key)) {
            return cache.get(key).value;
        }
        return null;
    }

    public void put(Key key, Value value) {
        cache.put(key, new CacheEntry<>(value, System.currentTimeMillis()));
    }

    public boolean containsKey(Key key) {
        if (!cache.containsKey(key)) return false;
        long currentTime = System.currentTimeMillis();
        CacheEntry<Value> entry = cache.get(key);
        if (currentTime - entry.timestamp > expiryTimeMillis) {
            cache.remove(key);
            return false;
        }
        return true;
    }
}

