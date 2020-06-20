/*
Author: Timothy Kemmis
std no: c3329386
FinalStage.java
SENG2200 Assignment 3
 */

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FinalStage extends Stage {

    //member variables
    LinkedList<Item> finishedItems;

    public FinalStage(ItemQueue previous, int iM, int iN) {//A constructor that initialises an empty final stage with all of the necessary data
        super(iM, iN);
        super.currentItem = null;
        setPrevious(previous);
        super.starved = true;
        finishedItems = new LinkedList<>();
    }

    public FinalStage( ItemQueue p, int iM, int iN, String id) {//Initialises a final stage
        super(iM, iN);
        setPrevious(p);
        super.starved = true;
        super.ID = id;
        super.currentItem = null;
        finishedItems = new LinkedList<>();
    }

    public Event processFinish(){//A specialised process finish for final stage
            numProcessed++;
            stopTime = globalTime;
            finishedItems.add(currentItem);
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

    public int getProductionPath(String firstStageID, String secondStageID){
        int out =0;
        for (Item i:finishedItems){
            if (i.doesPathInclude(firstStageID) && i.doesPathInclude(secondStageID)){
                out++;
            }
        }
        return out;
    }
}
