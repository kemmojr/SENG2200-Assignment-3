import java.util.*;

public abstract class Stage{


        double totalTime, productionTimePercentage, starvingTimePercentage, blockedTimePercentage;
        Item currentItem;
        int size, M, N, numProcessed;
        Random r;
        boolean blocked, starved, processing;
        double stopTime;
        double blockedTime, starvedTime, processingTime;
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
            if (blocked){
                blockedTime += globalTime - stopTime;
            } else if (starved){
                starvedTime += globalTime - stopTime;
            } else {
                processingTime += globalTime;
            }
                currentItem = item;
                double d = r.nextDouble();
                double t =  M + N * (d-0.5);
                return new Event(t,this);

        }

        public Event processFinish(){

            if (!nextQueue.isFull()){
                        nextQueue.add(currentItem);
                        numProcessed++;
                } else{
                     blocked = true;
                     stopTime = globalTime;
                     return null;
                }
                if (previousQueue.hasNext())
                {
                   return processStart(previousQueue.next());
                }
                else{
                   starved = true;
                    stopTime = globalTime;
                   return null;
                }

        }

    public boolean checkForItems(){
            if (!(previousQueue==null) && previousQueue.peek()!=null){
                double d = r.nextDouble();
                double t =  M + N * (d-0.5);
                processStart(previousQueue.poll());
                return true;
            }
            return false;
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
}
