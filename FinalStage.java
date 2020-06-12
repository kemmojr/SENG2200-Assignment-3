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
    int size, M, N, numProcessed;
    Random r;
    boolean blocked, starved, processing;
    private ItemQueue previousQueue;

    public FinalStage(ItemQueue previous, int iM, int iN) {
        super(iM, iN);
        currentItem = null;
        previousQueue = previous;
        M = iM;
        N = iN;
        r = new Random();
    }

    public FinalStage(int iM, int iN) {
        super(iM, iN);
    }

    public Event processFinished(double time){
            numProcessed++;
            stopTime = time;
        if (previousQueue.hasNext()){
            return processStart(previousQueue.next(),time);
        }
        else{
            starved = true;
            stopTime = time;
            return null;
        }
    }

    public int numProcessed(){
        return numProcessed;
    }

    @Override
    public void calculateTotalTime() {
        //
    }

}
