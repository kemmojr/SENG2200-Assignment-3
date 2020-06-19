import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FinalStage extends Stage {

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

    public Event processFinish(){//A specialised process finish for final stage
            numProcessed++;
            stopTime = globalTime;
        if (super.getPrevious().hasNext()){
            return processStart(super.getPrevious().next());
        }
        else{
            starved = true;
            stopTime = globalTime;
            return null;
        }
    }

    public static void updateTime(double time){
        globalTime = time;
    }//Initialises global timeon final stage

    public int numProcessed(){
        return super.numProcessed;
    }//get numProcessed

    @Override
    public void calculateTotalTime() {
        //
    }

}
