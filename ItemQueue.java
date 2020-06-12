import java.util.ArrayList;
import java.util.Queue;

public class ItemQueue {

    private ArrayList<Stage> previousStage, nextStage;
    private Queue<Item> items;
    private int Qmax, size;


    public ItemQueue(){
        items.add(null);
    }

    public ItemQueue(Item it){
        items.add(it);
    }

    public ItemQueue(Stage prev, Stage next, int Q){
        previousStage.add(prev);
        nextStage.add(next);
        Qmax = Q;
    }

    public ItemQueue(Stage prev, int Q){
        previousStage.add(prev);
        Qmax = Q;
    }

    public boolean isFull(){
        if (items.size()==Qmax){
            return true;
        }
        return false;
    }

    public void add(Item it){
        items.add(it);
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
        return nextStage.get(0);
    }

    public Stage getNext2() {
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

    public int size(){
        return items.size();
    }
}
