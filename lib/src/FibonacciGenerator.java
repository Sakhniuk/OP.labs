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