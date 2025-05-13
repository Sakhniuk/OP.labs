
import java.util.function.Function;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Function<Integer, Integer> square = x -> {
            System.out.println("Обчислюємо " + x + "^2");
            return x * x;
        };
                System.out.println("=== LRU Cache ===");
                Memoizer<Integer, Integer> lruMemoizer = new Memoizer<>(square, new LRUCache<>(2));
                System.out.println(lruMemoizer.apply(2));
                System.out.println(lruMemoizer.apply(2));
                System.out.println(lruMemoizer.apply(3));
                System.out.println(lruMemoizer.apply(4));
                System.out.println(lruMemoizer.apply(2));

                System.out.println("\n=== LFU Cache ===");
                Memoizer<Integer, Integer> lfuMemoizer = new Memoizer<>(square, new LFUCache<>(2));
                System.out.println(lfuMemoizer.apply(5));
                System.out.println(lfuMemoizer.apply(6));
                System.out.println(lfuMemoizer.apply(5));
                System.out.println(lfuMemoizer.apply(7));
                System.out.println(lfuMemoizer.apply(6));

                System.out.println("\n=== Time-Based Cache ===");
                Memoizer<Integer, Integer> timeMemoizer = new Memoizer<>(square, new TimeBasedCache<>(1000));
                System.out.println(timeMemoizer.apply(8));
                Thread.sleep(500);
                System.out.println(timeMemoizer.apply(8));
                Thread.sleep(1000);
                System.out.println(timeMemoizer.apply(8));

            }
        }

