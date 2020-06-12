import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BeginningStage extends Stage {

    double totalTime, productionTimePercentage, starvingTimePercentage, blockedTimePercentage;
    Item currentItem;
    String ID;
    int size, M, N;
    Random r;
    boolean blocked, starved, processing;
    private ItemQueue nextQueue;

    public BeginningStage(ItemQueue next, int iM, int iN, String identifier) {
        super(iM, iN);
        ID = identifier;
        currentItem = null;
        nextQueue = next;
        M = iM;
        N = iN;
        r = new Random();
    }


    @Override
    public Event processStart(Item item, double time){
        //check if blocked or starved
        //if so add time based on stopTime
        //i.e. time halted = time-StopTime;
        if (blocked){
            blockedTime += time - stopTime;
        } else if (starved){
            starvedTime += time - stopTime;
        } else {
            processingTime += time;
        }
        double d = r.nextDouble();
        double t =  M + N * (d-0.5);
        //return processFinish(150);
        nextQueue.getNext1().checkForItems();
        return new Event(t,this);

    }

    @Override
    public Event processFinish(double time){

        if (!nextQueue.isFull()){
            nextQueue.add(currentItem);
            numProcessed++;
        } else{
            blocked = true;
            stopTime = time;
            return null;
        }
        itemCreation();
        return processStart(currentItem,time);
    }

    public Event itemCreation(){//Generates a unique ID using the getID class and appends it's identifier to the end
        //getID g = new getID();
        //String sG = g + "";
        //String identifier = sG.substring(0,5) +ID;
        currentItem = new Item("123abc");
        //double d = r.nextDouble();
        //double t =  M + N * (d-0.5);
        return processStart(currentItem,100);
    }

    public BeginningStage(int iM, int iN) {
        super(iM, iN);
    }

    @Override
    public void calculateTotalTime() {
        //
    }

    public void setNext(ItemQueue it){
        nextQueue = it;
    }

    public ItemQueue getNext(){
        return nextQueue;
    }

}
