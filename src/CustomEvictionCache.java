
import java.util.function.BiPredicate;
import java.util.HashMap;
import java.util.Map;

public class CustomEvictionCache<Key, Value> implements EvictionPolicy<Key, Value> {
    private final Map<Key, Value> cache = new HashMap<>();
    private final int maxSize;
    private final BiPredicate<Map<Key, Value>, Key> evictionRule;

    public CustomEvictionCache(int maxSize, BiPredicate<Map<Key, Value>, Key> evictionRule) {
        this.maxSize = maxSize;
        this.evictionRule = evictionRule;
    }

    public Value get(Key key) {
        return cache.get(key);
    }

    public void put(Key key, Value value) {
        if (cache.size() >= maxSize && evictionRule != null) {
            Key toEvict = cache.keySet().stream().filter(k -> evictionRule.test(cache, k)).findFirst().orElse(null);
            if (toEvict != null) cache.remove(toEvict);
        }
        cache.put(key, value);
    }

    public boolean containsKey(Key key) {
        return cache.containsKey(key);
    }
}
