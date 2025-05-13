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