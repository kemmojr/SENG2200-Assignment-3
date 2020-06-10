import java.util.*;

public abstract class Stage{


        double totalTime, productionTimePercentage, starvingTimePercentage, blockedTimePercentage;
        Item currentItem;
        int size, M, N;
        Random r;
        boolean blocked, starved, processing;
        private ItemQueue previousQueue, nextQueue;

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
            r = new Random();
        }

        /*public Event processStart(Item h)
        {
                //calculate end time
                return (end time, this)
        }

        public void processFinish()
        {
                //moveitemtostorage
                //getnextitem
        }

        Event(Time,Item)*/

        public Event processStart(Item item, double time){
            //check if blocked or stavered
            //if so add time based on stopTime
            //i.e. time halted = time-StopTime;
                currentItem = item;
                double d = r.nextDouble();
                double t =  M + N * (d-0.5);
                return new Event(t,this);

        }

        public Event processFinish(double time){
            double stopTime;
            if (!nextQueue.isFull()){
                        nextQueue.add(currentItem);
                } else{
                     blocked = true;
                stopTime = time;
                     return null;

                }
                if (previousQueue.hasNext())
                {
                   return processStart(previousQueue.next(),time);
                }
                else{
                   starved = true;
                    stopTime = time;
                   return null;
                }

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

    public abstract void calculateTotalTime();
}
