import java.util.LinkedList;

public class Event implements Comparable<Event> {//An event class that is used for returning multiple data types from a function
    private double time;
    private Stage s;

    public Event(double t, Stage s1) {//A event constructor that initialises the event with a time and the stage
        time = t;
        s = s1;
    }

    public Event process() {
        return s.processFinish();
    }

    public double getTime(){
        return time;
    }


    @Override
    public int compareTo(Event o) {
        if (this.time<o.getTime()){
            return -1;
        }else if (this.time>o.getTime()){
            return 1;
        }
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
