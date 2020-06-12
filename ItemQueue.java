import java.util.*;

public class ItemQueue {

    private ArrayList<Stage> previousStage, nextStage;
    private Queue<Item> items = new Queue<Item>() {
        @Override
        public boolean add(Item item) {
            return false;
        }

        @Override
        public boolean offer(Item item) {
            return false;
        }

        @Override
        public Item remove() {
            return null;
        }

        @Override
        public Item poll() {
            return null;
        }

        @Override
        public Item element() {
            return null;
        }

        @Override
        public Item peek() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Item> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Item> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }
    };
    private int Qmax, size;


    public ItemQueue(int q){
        Qmax = q;
        //items = new Queue<>();
        previousStage = new ArrayList<>();
        nextStage = new ArrayList<>();
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

    public Item poll(){
        return items.poll();
    }

    public Item peek(){
        return items.peek();
    }

    public boolean isFull(){
        if (size==Qmax){
            return true;
        }
        return false;
    }

    public void add(Item it){
        if (size<Qmax)
            items.add(it);
        return;
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

    public int size(){
        return items.size();
    }
}
