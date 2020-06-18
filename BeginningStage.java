import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BeginningStage extends Stage {

    double totalTimePercentage, productionTimePercentage, starvingTimePercentage, blockedTimePercentage;
    double totalTime, productionTime, starvingTime, blockedTime;

    Item currentItem;
    int size;
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


    public BeginningStage(int iM, int iN, String id) {
        super(iM, iN);
        ID = id;
        currentItem = null;
        r = new Random();
    }

    public static void updateTime(double time){
        globalTime = time;
    }

    @Override
    public Event processStart(Item item){
        //check if blocked or starved
        //if so add time based on stopTime
        //i.e. time halted = time-StopTime;
        double tempTime = 0;
        if (blocked){
            tempTime= globalTime - stopTime;
            blockedTime += tempTime;
        } else if (starved){
            tempTime = globalTime - stopTime;
            starvingTime += tempTime;
        } else {
            tempTime = globalTime;
            productionTime += tempTime;
        }
        double d = r.nextDouble();
        double t =  M + N * (d-0.5);
        t = t + globalTime;
        return new Event(t,this);

    }

    @Override
    public LinkedList<Event> processFinish(){
        LinkedList<Event> l = new LinkedList<>();
        Event ev;
        if (!nextQueue.isFull()){
            ev =nextQueue.add(currentItem);
            if(ev!=null)
                l.add(ev);
            numProcessed++;
            l.add(itemCreation());

        } else{
            blocked = true;
            stopTime = globalTime;
            return null;
        }

        return l;

/*
        if (!nextQueue.isFull()){
            nextQueue.add(currentItem);
            numProcessed++;
        } else{
            blocked = true;
            stopTime = globalTime;
            return null;
        }
        itemCreation();
        return processStart(currentItem);*/
    }

    public Event itemCreation(){//Generates a unique ID using the getID class and appends it's identifier to the end
        //getID g = new getID();
        //String sG = g + "";
        //String identifier = sG.substring(0,5) +ID;
        currentItem = new Item("123abc");
        //double d = r.nextDouble();
        //double t =  M + N * (d-0.5);
        return processStart(currentItem);
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
