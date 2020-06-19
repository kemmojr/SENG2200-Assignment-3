import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class InterStage extends Stage{
    private static double globalTime;

    public InterStage(ItemQueue p, ItemQueue n, int iM, int iN, String id) {//A constructor that creates an interstage with a next, previous and all other necessary data
        //The use of super is to avoid duplicates of various member variables
        super(iM, iN);
        super.setPrevious(p);
        super.setNext(n);
        super.currentItem = null;
        super.starved = true;
        super.ID = id;
    }

    public InterStage(int iM, int iN, String id){//empty constructor
        super(iM,iN);
        super.currentItem = null;
        super.starved = true;
        super.ID = id;
    }

    public InterStage(InterStage inter){//copy constructor
        super(inter.M, inter.N);
        super.setNext(inter.getNext());
        super.setPrevious(inter.getNext());
        super.currentItem = inter.currentItem;
        super.starved = inter.starved;
        super.ID = inter.ID;
    }

    @Override
    public boolean isStarved() {//get starved
        return starved;
    }

    public static void updateTime(double time){
        globalTime = time;
    }//initialises the global time in interstage

    //setters
    public void setPrevious(ItemQueue previousQueue) {
        super.setPrevious(previousQueue);
    }

    public void setNext(ItemQueue nextQueue) {
        super.setNext(nextQueue);
    }

    @Override
    public void calculateTotalTime() {
        //
    }

}
