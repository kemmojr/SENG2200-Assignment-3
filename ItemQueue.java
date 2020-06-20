import java.text.NumberFormat;
import java.util.*;

public class ItemQueue {

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

    public static void updateTime(double time){
        globalTime = time;
    }//initialises the global time variable in ItemQueue

    public int size() {
        return items.size();
    }//getSize

    public boolean isEmpty() {
        return items.isEmpty();
    }//is the queue empty

    public Item poll(){
        return items.poll();
    }//return the top element and remove it

    public Item peek(){
        return items.peek();
    }//return the top item of the queue

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

    public Item next(){
        numProcessed++;
        timeIn.add(globalTime-addedTime);
        numItemTime[size()] += globalTime - addedTime;
        addedTime = globalTime;
        return items.poll();
    }//gets the next item and removes it

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

    public int getQmax(){
        return Qmax;
    }

    public double getAverageTime(){
        double avgTime = 0;
        for (double d:timeIn){
            avgTime+=d;
        }
        return avgTime/numProcessed;
    }

    public double getAvgItems(){
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
