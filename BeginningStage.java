import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BeginningStage extends Stage {

    double totalTimePercentage, productionTimePercentage, starvingTimePercentage, blockedTimePercentage;
    double totalTime, productionTime, starvingTime, blockedTime;

    Item currentItem;
    String ID;
    int size, M, N;
    Random r;
    boolean blocked, starved, processing;
    private ItemQueue nextQueue;
    private static double globalTime;

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
    public Event processStart(Item item){
        //check if blocked or starved
        //if so add time based on stopTime
        //i.e. time halted = time-StopTime;
        if (blocked){
            blockedTime += globalTime - stopTime;
        } else if (starved){
            starvingTime += globalTime - stopTime;
        } else {
            productionTime += globalTime;
        }
        double d = r.nextDouble();
        double t =  M + N * (d-0.5);
        return new Event(t,this);

    }

    @Override
    public Event processFinish(){

        if (!nextQueue.isFull()){
            nextQueue.add(currentItem);
            numProcessed++;
        } else{
            blocked = true;
            stopTime = globalTime;
            return null;
        }
        itemCreation();
        return processStart(currentItem);
    }

    public Event itemCreation(){//Generates a unique ID using the getID class and appends it's identifier to the end
        //getID g = new getID();
        //String sG = g + "";
        //String identifier = sG.substring(0,5) +ID;
        currentItem = new Item("123abc");
        double d = r.nextDouble();
        double t =  M + N * (d-0.5);
        return processStart(currentItem);
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
