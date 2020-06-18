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

        public Stage(int iM, int iN){//creates an empty queue
           M = iM;
           N = iN;
           r = new Random();
        }

        public Stage(Item it, ItemQueue next, ItemQueue previous, int iM, int iN){
            currentItem = it;
            nextQueue = next;
            previousQueue =  previous;
            M = iM;
            N = iN;
            r = new Random(100);
        }

        public static void updateTime(double time){
            globalTime = time;
        }

        public Event processStart(Item item){
            //check if blocked or starved
            //if so add time based on stopTime
            //i.e. time halted = time-StopTime;
            //Random n = new Random();
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

    public LinkedList<Event> processFinish(){
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

    public ItemQueue getNext() {
        return nextQueue;
    }

    public ItemQueue getPrevious() {
        return previousQueue;
    }

    public void setNext(ItemQueue next) {
        nextQueue = next;
    }

    public void setPrevious(ItemQueue prev){
            previousQueue = prev;
    }

    public boolean isBlocked(){
            return blocked;
    }

    public boolean isStarved() {
        return starved;
    }

    public abstract void calculateTotalTime();

    @Override
    public String toString() {
        return ID + "\t\t" + productionTimePercentage + "\t\t" + starvingTime + "\t\t" + blockedTime + "\t\t" +totalTime;
    }
}
