import java.util.LinkedList;

public class Event implements Comparable<Event> {
    private double time;
    private Stage s;

    public Event(double t, Stage s1) {
        time = t;
        s = s1;
    }

    public LinkedList<Event> process() {
        return s.processFinish();
    }

    public double getTime(){
        return time;
    }


    @Override
    public int compareTo(Event o) {
        //compare the events
        return 0;
    }

    @Override
    public String toString() {
        return "Event{" +
                "Time=" + time +
                ", Stage=" + s +
                "} \n";
    }
}
