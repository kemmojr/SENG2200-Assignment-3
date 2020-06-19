import java.text.NumberFormat;
import java.util.*;

public abstract class Stage{


        double productionTimePercentage, starvingTimePercentage, blockedTimePercentage;
        double totalTime, productionTime, starvingTime, blockedTime;
        Item currentItem;
        int M, N, numProcessed;
        Random r;
        String ID;
        boolean blocked, starved, processing;
        double stopTime;
        private ItemQueue previousQueue, nextQueue;
        protected static double globalTime;

        public Stage(int iM, int iN){//creates an empty stage
           M = iM;
           N = iN;
           r = new Random();
        }

        public Stage(Item it, ItemQueue next, ItemQueue previous, int iM, int iN){//creates a stage with a next and previous and all the necessary data
            currentItem = it;
            nextQueue = next;
            previousQueue =  previous;
            M = iM;
            N = iN;
            r = new Random(100);
        }

        public static void updateTime(double time){
            globalTime = time;
        }//Initialises the global time in stage

        public Event attemptProcess(){
            if (previousQueue.size()>0){
                return processStart(previousQueue.poll());
            }
            return null;
        }

        public Event attemptUnblock(){
            if (nextQueue.size()<nextQueue.getQmax()){
                return processFinish();
            }
            return null;
        }

        public Event processStart(Item item){//The beginning of the processing of the Items.
            // Collects metrics about starving producing or blocked and starts the process finish which moves the item out of the stage and into the next queue
            //check if blocked or starved
            if (blocked){
                blockedTime += globalTime - stopTime;
                blocked = false;
            } else if (starved){
                starvingTime += globalTime - stopTime;
                starved = false;
            } else {
                productionTime += globalTime;//WRONG
            }
                currentItem = item;
                double d = r.nextDouble();
                double t =  M + N * (d-0.5);
                t = t + globalTime;
                return new Event(t,this);
        }

    public Event processFinish(){//Checks the next queue and adds the item to it if the queue is not full.
        //Also updates the numprocessed
        //Returns a list of all of the events that occur during it's duration


        if (!nextQueue.isFull()){
            nextQueue.add(currentItem);
            numProcessed++;
        } else{
            blocked = true;
            stopTime = globalTime;
            return null;
        } if (previousQueue.hasNext()){
           return (processStart(previousQueue.next()));
        } else{
            starved = true;
            currentItem = null;
            stopTime = globalTime;
        }
        return null;

    }

    //getters
    public ItemQueue getNext() {
        return nextQueue;
    }

    public ItemQueue getPrevious() {
        return previousQueue;
    }

    //setters
    public void setNext(ItemQueue next) {
        nextQueue = next;
    }

    public void setPrevious(ItemQueue prev){
            previousQueue = prev;
    }

    public boolean isBlocked(){
            return blocked;
    }//gets blocked

    public boolean isStarved() {
        return starved;
    }//gets starved

    @Override
    public String toString() {//A toString method that formats the stage to display the metrics
        productionTimePercentage = (10000000 - (starvingTime+blockedTime))/10000000;
        NumberFormat n = NumberFormat.getPercentInstance();
        NumberFormat n2 = NumberFormat.getInstance();
        n2.setGroupingUsed(true);
        n2.setMaximumFractionDigits(2);
        n2.setMinimumFractionDigits(2);
        n.setMaximumFractionDigits(2);
        n.setMinimumFractionDigits(2);
        return ID + "\t\t" + n.format(productionTimePercentage) + "\t\t" + n2.format(starvingTime) + "\t\t\t\t" + n2.format(blockedTime);
    }
}
