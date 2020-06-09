public class Event implements Comparable<Event> {
    double time;
    Stage s;

    public Event(double t, Stage s1) {
        time = t;
        s = s1;
    }

    public Event process() {
        return s.processFinish(time);
    }


    @Override
    public int compareTo(Event o) {
        //compare the events
        return 0;
    }
}
