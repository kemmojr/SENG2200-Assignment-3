/*
Author: Timothy Kemmis
std no: c3329386
PA3.java
SENG2200 Assignment 3
 */

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

        PriorityQueue<Event> events = new PriorityQueue<Event>();
        int counter = 0;
        events.add(S0a.itemCreation());
        events.add(S0b.itemCreation());
        Event newEvent;
        while (time < 10000000) {
            currentEvent = events.poll();
            if (currentEvent==null){
                System.out.println("Current event is null");
            }
            time = currentEvent.getTime();
            Stage.updateTime(time);
            ItemQueue.updateTime(time);

            try {
                newEvent = currentEvent.process();
                if (newEvent!=null){
                    events.add(newEvent);
                }
                for (Stage s: stageList){
                    if (s.isStarved()){
                        newEvent = s.attemptProcess();
                        if (newEvent != null)
                            events.add(newEvent);
                    } else if (s.isBlocked()){
                        newEvent = s.attemptUnblock();
                        if (newEvent != null)
                            events.add(newEvent);
                    }
                }
            } catch (Exception e){
                //catches any errors
            }
            counter++;
        }

        System.out.println("\nProduction Stations:");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Stage:\tWork[%]\t\tStarve[t]\t\t\t\t\t\t\t\tBlock[t]");
        for (Stage s: stageList){
            System.out.println(s);
        }
        System.out.println("\nStorage Queues:");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Store\tAvgTime[t]\t\tAvgItems");
        for (ItemQueue i: queueList){
            System.out.println(i);
        }

        System.out.println("\nProduction Paths:");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("S3a\t->\tS5a:\t" + S6.getProductionPath("S3a","S5a") );
        System.out.println("S3a\t->\tS5b:\t" + S6.getProductionPath("S3a","S5b"));
        System.out.println("S3b\t->\tS5a:\t" + S6.getProductionPath("S3b","S5a"));
        System.out.println("S3b\t->\tS5b:\t" + S6.getProductionPath("S3b","S5b"));

        System.out.println("\nProduction Items:");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("S0a:\t" + S0a.getNumItemsProduced());
        System.out.println("S0b:\t" + S0b.getNumItemsProduced());
    }
}
