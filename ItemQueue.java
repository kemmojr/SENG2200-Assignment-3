/*
Author: Timothy Kemmis
std no: c3329386
ItemQueue.java
SENG2200 Assignment 3
 */

import java.text.NumberFormat;
import java.util.*;

public class ItemQueue {

    //member variables
    private ArrayList<Stage> previousStage, nextStage;
    private LinkedList<Item> items;
    private int Qmax;
    String ID;
    private int numProcessed;
    private double averageTimeIn, averageNumItems;
    private double totalTimeIn, totalItems, addedTime;
    private ArrayList<Double> timeIn;
    private static double globalTime;
    private double[] numItemTime;

    public ItemQueue(int q, String queueID){//ItemQueue constructor which initialises the id of the itemQueue, next and previous the item storage
        Qmax = q;
        items = new LinkedList<>();
        previousStage = new ArrayList<>();
        nextStage = new ArrayList<>();
        timeIn = new ArrayList<>();
        ID = queueID;
        numItemTime = new double[Qmax+1];
    }

    public ItemQueue(Stage prev, int Q){//empty constructor
        previousStage.add(prev);
        Qmax = Q;
        numProcessed = 0;
    }

    public static void updateTime(double time){//initialises the global time variable in ItemQueue
        globalTime = time;
    }

    public int size() {//getSize
        return items.size();
    }

    public boolean isEmpty() {//is the queue empty
        return items.isEmpty();
    }

    public Item poll(){//return the top element and remove it
        return items.poll();
    }

    public Item peek(){//return the top item of the queue
        return items.peek();
    }

    public boolean isFull(){//return true if the queue is full
        if (size()==Qmax){
            return true;
        }
        return false;
    }

    public boolean add(Item it){//checks to see the next stage is empty and adds the item to the stage. Otherwise the item is added to the queue
        if (size()>-1){
            numItemTime[size()] += globalTime - addedTime;
            addedTime = globalTime;
        }
        if (size()<Qmax) {
            items.add(it);
            return true;
        }
        return false;
    }

    public boolean hasNext(){//checks if there are any items in the queue
        if (items.peek()!=null){
            return true;
        }
        return false;
    }

    public Item next(){//gets the next item and removes it
        numProcessed++;
        timeIn.add(globalTime-addedTime);
        numItemTime[size()] += globalTime - addedTime;
        addedTime = globalTime;
        return items.poll();
    }

    public Stage getNext1() {//gets the first stage that is after the itemQueue
        if (nextStage.size()==0){
            return null;
        }
        return nextStage.get(0);
    }

    public Stage getNext2() {//gets the second stage that is after the ItemQueue
        if (nextStage.size()<2){
            return null;
        }
        return nextStage.get(1);
    }

    public Stage getPrevious1() {//gets the 1st previous stage
        return previousStage.get(0);
    }

    public Stage getPrevious2() {//gets the 2nd previous stage
        return previousStage.get(1);
    }

    public void setNext(Stage next) {//sets the next stage
        if (nextStage.size()<2){
            nextStage.add(next);
        }
    }

    public void setPrevious(Stage prev){//sets the previous stage
        if (previousStage.size()<2){
            previousStage.add(prev);
        }
    }

    public int getQmax(){//gets Qmax
        return Qmax;
    }

    public double getAverageTime(){//Calculates the average amount of time an item spends in the queue
        double avgTime = 0;
        for (double d:timeIn){
            avgTime+=d;
        }
        return avgTime/numProcessed;
    }

    public double getAvgItems(){//Calculates the average number of items in the queue at any time
        double avgItem = 0;
        double totalTime = 0;

        for (int i =0;i<numItemTime.length;i++){
            avgItem += i*numItemTime[i];
            totalTime += numItemTime[i];
        }
        return avgItem/totalTime;
    }

    @Override
    public String toString() {//A toString method that outputs a formatted set of itemQueue statistics
        NumberFormat n = NumberFormat.getInstance();
        n.setMinimumFractionDigits(2);
        n.setMaximumFractionDigits(2);

        return  ID + "\t\t" + n.format(getAverageTime()) + "\t\t\t" + n.format(getAvgItems());
    }
}
