
import java.util.function.Function;

public class Memoizer<Type, RetType> {
    private final Function<Type, RetType> function;
    private final EvictionPolicy<Type, RetType> cache;

    public Memoizer(Function<Type, RetType> function, EvictionPolicy<Type, RetType> cache) {
        this.function = function;
        this.cache = cache;
    }

    public RetType apply(Type input) {
        if (cache.containsKey(input)) {
            return cache.get(input);
        } else {
            RetType result = function.apply(input);
            cache.put(input, result);
            return result;
        }
    }
}
