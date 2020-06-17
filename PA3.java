import java.lang.*;
import java.util.*;
import java.io.*;

public class PA3 {
    public static void main(String args[]){
        int M, N, Qmax, numProcess = 0;
        double time = 0;
        Event currentEvent;
        //Parsing the string arguments into int for later use
        M = Integer.parseInt(args[0]);
        N = Integer.parseInt(args[1]);
        Qmax = Integer.parseInt(args[2]);

        List system = new List(M,N,Qmax);
        //Assemble the system structure as per the spec. I have named each addition the same as it's counterpart on the spec diagram
        system.append();
        system.append("S1");
        system.append(1.2);
        system.append("S2");
        system.append(2.3);
        system.append("S3a");
        system.append("S3b");
        system.append(3.4);
        system.append("S4");
        system.append(4.5);
        system.append("S5a");
        system.append("S5b");
        system.append(5.6);
        system.append(6);

        PriorityQueue<Event> events = new PriorityQueue<Event>();
        while (numProcess < 100000) {
            events.add(system.getFirst().itemCreation());
            currentEvent = events.poll();
            time = currentEvent.getTime();
            Stage.updateTime(time);
            system.getFirst().updateTime(time);
            ItemQueue.updateTime(time);
            events.add(system.getFirst().itemCreation());
            numProcess++;
            if (numProcess>99999){
                System.out.println("99999");
            }
        }
        System.out.println(events);


    }
}
