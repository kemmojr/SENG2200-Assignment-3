import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FinalStage extends Stage {

    double totalTime, productionTimePercentage, starvingTimePercentage, blockedTimePercentage;
    Item currentItem;
    int size, numProcessed;
    Random r;
    boolean blocked, starved, processing;
    private ItemQueue previousQueue;
    private static double globalTime;

    public FinalStage(ItemQueue previous, int iM, int iN) {//A constructor that initialises an empty final stage with all of the necessary data
        super(iM, iN);
        super.currentItem = null;
        setPrevious(previous);
        super.starved = true;
    }

    public FinalStage( ItemQueue p, int iM, int iN, String id) {//Initialises a final stage
        super(iM, iN);
        setPrevious(p);
        super.starved = true;
        super.ID = id;
        super.currentItem = null;
    }

    public Event processFinished(double time){//A specialised process finish for final stage
            numProcessed++;
            stopTime = time;
        if (previousQueue.hasNext()){
            return processStart(previousQueue.next());
        }
        else{
            starved = true;
            stopTime = time;
            return null;
        }
    }

    public static void updateTime(double time){
        globalTime = time;
    }//Initialises global timeon final stage

    public int numProcessed(){
        return numProcessed;
    }//get numProcessed

    @Override
    public void calculateTotalTime() {
        //
    }

}
