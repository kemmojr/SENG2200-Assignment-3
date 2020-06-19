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

    public BeginningStage(ItemQueue next, int iM, int iN, String identifier) {//Constructor that creates a beginning stage with a next queue, identifier and all of the necessary data
        super(iM, iN);
        ID = identifier;
        currentItem = null;
        nextQueue = next;
    }


    public BeginningStage(int iM, int iN, String id) {//Creates a beginning stage with an identifier and all of the necessary data
        super(iM, iN);
        ID = id;
        currentItem = null;
    }

    public static void updateTime(double time){
        globalTime = time;
    }//initialises the global time variable in beginning stage

    @Override
    public LinkedList<Event> processFinish(){//An overridden process finish which adds to the next queue if it isn't full and then starts the creation of a new item
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


    @Override
    public void calculateTotalTime() {
        //
    }


    //getters and setters
    public void setNext(ItemQueue it){
        nextQueue = it;
    }

    public ItemQueue getNext(){
        return nextQueue;
    }


}
