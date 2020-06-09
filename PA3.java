import java.lang.*;
import java.util.*;
import java.io.*;

public class PA3 {
    public static void main(String args[]){
        int M, N, Qmax, numProccessed = 0;
        M = Integer.parseInt(args[0]);
        N = Integer.parseInt(args[1]);
        Qmax = Integer.parseInt(args[2]);


        PriorityQueue<Event> events = new PriorityQueue<Event>();
        while (stage6.numProccessed < 100000) {
            events.add(events.poll().process());
            numProccessed++;
        }


    }
}
