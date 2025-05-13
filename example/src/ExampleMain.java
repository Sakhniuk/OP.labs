public class ExampleMain {
    public static void main(String[] args) {
        System.out.println("Fibonacci Generator:");
        consumeWithTimeout(new FibonacciGenerator(), 5);

        System.out.println("\n Color Cycle Generator:");
        consumeWithTimeout(new ColorCycleGenerator(), 5);

        System.out.println("\n Weekday Generator:");
        consumeWithTimeout(new WeekDayGenerator(), 5);
    }
}
