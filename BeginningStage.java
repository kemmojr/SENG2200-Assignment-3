import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BeginningStage extends Stage {

    int numItemsProduced;


    public BeginningStage(ItemQueue next, int iM, int iN, String identifier) {//Constructor that creates a beginning stage with a next queue, identifier and all of the necessary data
        super(iM, iN);
        ID = identifier;
        currentItem = null;
        setNext(next);
        numItemsProduced = 0;
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
    public Event processFinish(){//An overridden process finish which adds to the next queue if it isn't full and then starts the creation of a new item
        if (!getNext().isFull()){
            getNext().add(currentItem);
            numProcessed++;
            return itemCreation();
        } else{
            blocked = true;
            stopTime = globalTime;
            return null;
        }
    }

    public Event itemCreation(){//Generates a unique ID using the getID class and appends it's identifier to the end
        //getID g = new getID();
        //String sG = g + "";
        //String identifier = sG.substring(0,5) +ID;
        numItemsProduced++;
        currentItem = new Item("123abc");
        double d = super.r.nextDouble();
        double t =  M + N * (d-0.5);
        return processStart(currentItem);
    }

    public int getNumItemsProduced(){
        return numItemsProduced;
    }
}
