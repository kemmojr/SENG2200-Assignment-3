import java.util.*;

public class ItemQueue {

    private ArrayList<Stage> previousStage, nextStage;
    private LinkedList<Item> items;
    private int Qmax;
    private double averageTimeIn, averageNumItems;
    private double totalTimeIn, totalItems;
    private ArrayList<Double> timeIn;
    private static double globalTime;

    public static void updateTime(double time){
        globalTime = time;
    }
    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public ItemQueue(int q){
        Qmax = q;
        items = new LinkedList<>();
        previousStage = new ArrayList<>();
        nextStage = new ArrayList<>();
        timeIn = new ArrayList<>();
    }

    public ItemQueue(Stage prev, int Q){
        previousStage.add(prev);
        Qmax = Q;
    }

    public Item poll(){
        return items.poll();
    }

    public Item peek(){
        return items.peek();
    }

    public boolean isFull(){
        if (size()==Qmax){
            return true;
        }
        return false;
    }

    public void add(Item it){
        double addedTime = globalTime;
        double removedTime;
        if (size()<Qmax)
            items.add(it);
        if  (nextStage.size()==1 || (nextStage!=null && nextStage.get(0).isStarved()) || (nextStage.size() == 2 && nextStage.get(1)!=null && nextStage.get(1).isStarved())){
            for (Stage s:nextStage){
                if (s.isStarved()){
                    s.processStart(items.poll());
                    removedTime = globalTime;
                    timeIn.add(addedTime-removedTime);

                }
            }
        }
    }

    public boolean hasNext(){
        if (items.peek()!=null){
            return true;
        }
        return false;
    }

    public Item next(){
        return items.poll();
    }

    public Stage getNext1() {
        if (nextStage.size()==0){
            return null;
        }
        return nextStage.get(0);
    }

    public Stage getNext2() {
        if (nextStage.size()<2){
            return null;
        }
        return nextStage.get(1);
    }

    public Stage getPrevious1() {
        return previousStage.get(0);
    }

    public Stage getPrevious2() {
        return previousStage.get(1);
    }

    public void setNext(Stage next) {
        if (nextStage.size()<2){
            nextStage.add(next);
        }
    }

    public void setPrevious(Stage prev){
        if (previousStage.size()<2){
            previousStage.add(prev);
        }
    }
}
