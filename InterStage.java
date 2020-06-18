import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class InterStage extends Stage{
    double totalTime, productionTimePercentage, starvingTimePercentage, blockedTimePercentage;
    Item currentItem;

    Random r;
    boolean blocked, starved, processing;
    private ItemQueue previousQueue, nextQueue;
    private static double globalTime;

    public InterStage(ItemQueue p, int iM, int iN, String id) {
        super(iM, iN);
        previousQueue = p;
        currentItem = null;
        M = iM;
        N = iN;
        r = new Random();
        starved = true;
        ID = id;
    }

    public InterStage(ItemQueue p, ItemQueue n, int iM, int iN, String id) {
        super(iM, iN);
        previousQueue = p;
        nextQueue = n;
        currentItem = null;
        M = iM;
        N = iN;
        r = new Random();
        starved = true;
        ID = id;
    }

    public InterStage(int iM, int iN, String id){
        super(iM,iN);
        ID = id;
        r = new Random();
        starved = true;
        currentItem = null;
    }

    public InterStage(InterStage inter){//copy constructor
        super(inter.M, inter.N);
        currentItem = inter.currentItem;
        nextQueue = inter.nextQueue;
        previousQueue = inter.previousQueue;
        M = inter.M;
        N = inter.N;
        r = new Random();
        starved = inter.starved;
        ID = inter.ID;
    }

    @Override
    public boolean isStarved() {
        return starved;
    }

    public static void updateTime(double time){
        globalTime = time;
    }

    public void setPrevious(ItemQueue previousQueue) {
        this.previousQueue = previousQueue;
    }

    public void setNext(ItemQueue nextQueue) {
        this.nextQueue = nextQueue;
    }

    @Override
    public void calculateTotalTime() {
        //
    }

}
