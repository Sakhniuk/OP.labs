import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class GeneratorAndTimeoutDemo {
    // 1
    public static class FibonacciGenerator implements Iterator<Integer> {
        private int a = 0;
        private int b = 1;

        @Override
        public boolean hasNext() {
            return true;
        }
        @Override
        public Integer next() {
            int temp = a;
            a = b;
            b = temp + b;
            return temp;
        }
    }
    // 2
    public static class ColorCycleGenerator implements Iterator<String> {
        private final String[] colors = {"Red", "Green", "Blue", "Yellow", "Purple"};
        private int index = 0;

        @Override
        public boolean hasNext() {
            return true;
        }
        @Override
        public String next() {
            String color = colors[index];
            index = (index + 1) % colors.length;
            return color;
        }
    }
    // 3
    public static class WeekDayGenerator implements Iterator<String> {
        private final DayOfWeek[] days = DayOfWeek.values();
        private int index = 0;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public String next() {
            String day = days[index].toString();
            index = (index + 1) % days.length;
            return day;
        }
    }
    // 4
    public static void consumeWithTimeout(Iterator<?> iterator, int timeoutSeconds) {
        long startTime = System.currentTimeMillis();
        long timeoutMillis = TimeUnit.SECONDS.toMillis(timeoutSeconds);

        while (System.currentTimeMillis() - startTime < timeoutMillis) {
            if (iterator.hasNext()) {
                Object value = iterator.next();
                System.out.println(LocalDateTime.now() + " => " + value);
            } else {
                break;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted");
                break;
            }
        }
        System.out.println("Timeout reached. Stopped consuming.");
    }
    public static void main(String[] args) {
        System.out.println("Fibonacci Generator:");
        consumeWithTimeout(new FibonacciGenerator(), 5);

        System.out.println("\n Color Cycle Generator:");
        consumeWithTimeout(new ColorCycleGenerator(), 5);

        System.out.println("\n Weekday Generator:");
        consumeWithTimeout(new WeekDayGenerator(), 5);
    }
}
