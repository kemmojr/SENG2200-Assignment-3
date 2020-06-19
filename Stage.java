import java.util.*;

public abstract class Stage{


        double productionTimePercentage, starvingTimePercentage, blockedTimePercentage;
        double totalTime, productionTime, starvingTime, blockedTime;
        Item currentItem;
        int size, M, N, numProcessed;
        Random r;
        String ID;
        boolean blocked, starved, processing;
        double stopTime;
        private ItemQueue previousQueue, nextQueue;
        private static double globalTime;

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

        public Event processStart(Item item){//The beginning of the processing of the Items.
            // Collects metrics about starving producing or blocked and starts the process finish which moves the item out of the stage and into the next queue
            //check if blocked or starved
            if (blocked){
                blockedTime += globalTime - stopTime;
            } else if (starved){
                starvingTime += globalTime - stopTime;
            } else {
                productionTime += globalTime;
            }
                currentItem = item;
                double d = r.nextDouble();
                double t =  M + N * (d-0.5);
                t = t + globalTime;
                return new Event(t,this);
        }

    public LinkedList<Event> processFinish(){//Checks the next queue and adds the item to it if the queue is not full.
        //Also updates the numprocessed
        //Returns a list of all of the events that occur during it's duration
        LinkedList<Event> l = new LinkedList<>();
        Event ev;
        if (!nextQueue.isFull()){
                    ev =nextQueue.add(currentItem);
                    if(ev!=null)
                        l.add(ev);
                    numProcessed++;
            } else{
                 blocked = true;
                 stopTime = globalTime;
                 return null;
            }
            if (previousQueue.hasNext()){
               l.add(processStart(previousQueue.next()));
            }
            else{
               starved = true;
                stopTime = globalTime;
            }
            return l;

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

    public abstract void calculateTotalTime();

    @Override
    public String toString() {//A toString method that formats the stage to display the metrics
        return ID + "\t\t" + productionTimePercentage + "\t\t" + starvingTime + "\t\t\t" + blockedTime + "\t\t\t" +totalTime;
    }
}
