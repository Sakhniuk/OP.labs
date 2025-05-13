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