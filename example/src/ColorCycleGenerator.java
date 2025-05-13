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