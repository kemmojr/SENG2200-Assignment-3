import java.lang.*;
import java.util.*;
import java.io.*;

public class PA3 {
    public static void main(String args[]){
        int M, N, Qmax, numProccess = 0;
        M = Integer.parseInt(args[0]);
        N = Integer.parseInt(args[1]);
        Qmax = Integer.parseInt(args[2]);
        List system = new List(M,N);
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
        while (system.getLast().numProcessed() < 100000) {
            //events.add(events.poll().process());
            system.getFirst().itemCreation();
            numProccess++;
        }


    }
}
