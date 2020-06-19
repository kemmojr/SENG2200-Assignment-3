import java.util.*;

public class ItemQueue {

    private ArrayList<Stage> previousStage, nextStage;
    private LinkedList<Item> items;
    private int Qmax;
    String ID;
    private double averageTimeIn, averageNumItems;
    private double totalTimeIn, totalItems;
    private ArrayList<Double> timeIn;
    private static double globalTime;

    public ItemQueue(int q, String queueID){//ItemQueue constructor which initialises the id of the itemQueue, next and previous the item storage
        Qmax = q;
        items = new LinkedList<>();
        previousStage = new ArrayList<>();
        nextStage = new ArrayList<>();
        timeIn = new ArrayList<>();
        ID = queueID;
    }

    public ItemQueue(Stage prev, int Q){//empty constructor
        previousStage.add(prev);
        Qmax = Q;
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

    public Event add(Item it){//checks to see the next stage is empty and adds the item to the stage. Otherwise the item is added to the queue
        double addedTime = globalTime;
        double removedTime;
        if (size()<Qmax) {
            items.add(it);
            for (Stage s : nextStage) {
                if (s.isStarved()) {
                    removedTime = globalTime;
                    timeIn.add(addedTime - removedTime); //Time where an item is in the queue
                    return s.processStart(items.poll());
                }
            }
        }
        return null;

    }

    public boolean hasNext(){//checks if there are any items in the queue
        if (items.peek()!=null){
            return true;
        }
        return false;
    }

    public Item next(){
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

    @Override
    public String toString() {//A toString method that outputs a formatted set of itemQueue statistics
        return  ID + "\t\t" + averageTimeIn + "\t\t\t" + averageNumItems;
    }
}
