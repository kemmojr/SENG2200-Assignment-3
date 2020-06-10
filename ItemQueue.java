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

    public ItemQueue(ItemQueue item){
        previousStage = item.previousStage;
        nextStage = item.nextStage;
        items = item.items;
        Qmax = item.Qmax;
        size = item.size;
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
}
