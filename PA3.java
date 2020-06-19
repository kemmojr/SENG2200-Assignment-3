import java.lang.*;
import java.util.*;
import java.io.*;

public class PA3 {
    private static Object Iterator;

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

        LinkedList<ItemQueue> queueList = new LinkedList<>();
        ItemQueue Q01 = new ItemQueue(Qmax,"Q01");
        ItemQueue Q12 = new ItemQueue(Qmax,"Q12");
        ItemQueue Q23 = new ItemQueue(Qmax,"Q23");
        ItemQueue Q34 = new ItemQueue(Qmax,"Q34");
        ItemQueue Q45 = new ItemQueue(Qmax,"Q45");
        ItemQueue Q56 = new ItemQueue(Qmax,"Q56");

        queueList.add(Q01);
        queueList.add(Q12);
        queueList.add(Q23);
        queueList.add(Q34);
        queueList.add(Q45);
        queueList.add(Q56);

        LinkedList<Stage> stageList = new LinkedList<>();
        BeginningStage S0a = new BeginningStage(Q01,M,N,"S0a");
        BeginningStage S0b = new BeginningStage(Q01,M,N,"S0b");
        InterStage S1 = new InterStage(Q01,Q12,M,N,"S1");
        InterStage S2 = new InterStage(Q12,Q23,M,N,"S2");
        InterStage S3a = new InterStage(Q23,Q34,M,N,"S3a");
        InterStage S3b = new InterStage(Q23,Q34,M,N,"S3b");
        InterStage S4 = new InterStage(Q34,Q45,M,N,"S4");
        InterStage S5a = new InterStage(Q45,Q56,M,N,"S5a");
        InterStage S5b = new InterStage(Q45,Q56,M,N,"S5b");
        FinalStage S6 = new FinalStage(Q56,M,N,"S6");
        stageList.add(S0a);
        stageList.add(S0b);
        stageList.add(S1);
        stageList.add(S2);
        stageList.add(S3a);
        stageList.add(S3b);
        stageList.add(S4);
        stageList.add(S5a);
        stageList.add(S5b);
        stageList.add(S6);

        Q01.setPrevious(S0a);
        Q01.setPrevious(S0b);
        Q01.setNext(S1);
        Q12.setPrevious(S1);
        Q12.setNext(S2);
        Q23.setPrevious(S2);
        Q23.setNext(S3a);
        Q23.setNext(S3b);
        Q34.setPrevious(S3a);
        Q34.setPrevious(S3b);
        Q34.setNext(S4);
        Q45.setPrevious(S4);
        Q34.setNext(S5a);
        Q34.setNext(S5b);
        Q56.setPrevious(S5a);
        Q56.setPrevious(S5b);
        Q56.setNext(S6);


        /*system.append();
        system.append("S1");
        system.append(1.2);
        system.append("S2");
        system.append(2.3);
        system.append("S3A");
        system.append("S3B");
        system.append(3.4);
        system.append("S4");
        system.append(4.5);
        system.append("S5A");
        system.append("S5B");
        system.append(5.6);
        system.append(6);*/
/*
        PriorityQueue<Event> events = new PriorityQueue<Event>();
        events.add(S0a.itemCreation());
        events.add(S0b.itemCreation());
        while (time < 10000000) {
            currentEvent = events.poll();
            time = currentEvent.getTime();
            Stage.updateTime(time);
            ItemQueue.updateTime(time);
            events.addAll(currentEvent.process());
        }*/
        //Iterator = system.iterator();
        //Stage current = system.getFirst();
        //ItemQueue currentQueue = system.getFirst().getNext();
        System.out.println("Production Stations:");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Stage:\tWork[%]\tStarve[t]\tBlock[t]\tTotal[t]");
        for (Stage s: stageList){
            System.out.println(s);
        }
        System.out.println("Storage Queues:");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Store\tAvgTime[t]\tAvgItems");
        for (ItemQueue i: queueList){
            System.out.println(i);
        }

        System.out.println("Production Paths:");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("S3a\t->\tS5a:");
        System.out.println("S3a\t->\tS5b:");
        System.out.println("S3b\t->\tS5a:");
        System.out.println("S3b\t->\tS5b:");

        System.out.println("Production Items:");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("S0a:\t" + "items produced");
        System.out.println("S0b:\t" + "items produced");

        //System.out.println(events);


    }
}
