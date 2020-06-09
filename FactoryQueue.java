import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class FactoryQueue implements Queue, Iterable{
    double totalTime, productionTimePercentage, starvingTimePercentage, blockedTimePercentage;
    FactoryQueue next, previous;
    Item head, tail;
    int size;

    public FactoryQueue(){//creates an empty queue
        head = null;
        tail = null;
        totalTime = 0;
        productionTimePercentage = 0;
        starvingTimePercentage = 0;
        blockedTimePercentage = 0;
        size = 0;
    }

    public FactoryQueue(Item h){
        head = h;
        tail = h;
        totalTime = 0;
        productionTimePercentage = 0;
        starvingTimePercentage = 0;
        blockedTimePercentage = 0;
        size = 1;
    }

    public void setNext(FactoryQueue next) {
        this.next = next;
    }

    public void setPrevious(FactoryQueue previous) {
        this.previous = previous;
    }

    public FactoryQueue getNext() {
        return next;
    }

    public FactoryQueue getPrevious() {
        return previous;
    }

    public FactoryQueue getData(){
        return this;
    }

    public void calculateTotalTime(){
        totalTime = 0;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size>0){
            return false;
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        Item n = head;
        for (int i = 0; i < size; i++) {
            if (n.getData().equals(o)){
                return true;
            } else {
                n = n.getNext();
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new QueueIterator();
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(IntFunction generator) {
        return null;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return null;
    }

    @Override
    public boolean add(Object o) {
        Item n = new Item((String) o);
        n.setPrevious(tail);
        tail.setNext(n);
        n.setNext(null);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Item n = head;
        for (int i = 0; i < size; i++) {
            if (n.getData().equals(o)){
                if (n==head){
                    n.getNext().setPrevious(null);
                    n.setNext(null);
                    n.setPrevious(null);
                    n.setData(null);
                } else if (n==tail){
                    n.getPrevious().setNext(null);
                    n.setNext(null);
                    n.setPrevious(null);
                    n.setData(null);
                } else {
                    n.getNext().setPrevious(null);
                    n.getPrevious().setNext(null);
                    n.setNext(null);
                    n.setPrevious(null);
                    n.setData(null);
                }
            }
        }
        size--;
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate filter) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    @Override
    public Stream stream() {
        return null;
    }

    @Override
    public Stream parallelStream() {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public Object remove() {//returns and removes the head of the queue
        if (size==0){
            return null;
        }
        Item h = head;
        Item n = new Item(head);//Not the greatest way to acomplish this but due to java's garbage collection system it works
        h.getNext().setPrevious(null);
        h.setNext(null);
        h.setPrevious(null);
        h.setData(null);
        size--;
        return n;
    }

    @Override
    public Object poll() {
        return null;
    }

    @Override
    public Object element() {
        return null;
    }

    @Override
    public Object peek() {//returns the head of the queue
        if (size==0){
            return null;
        }
        return head;
    }

    private class QueueIterator implements Iterator {//implementation of iterator
        private Item current;


        public QueueIterator(){//implementation of iterator
            current = head;
        }

        public boolean hasNext(){//implementation of iterator check for next
            if (current.getNext()!=head){
                return true;
            }
            return false;
        }


        public String next(){//implementation of iterator move to next and return data

            current = current.getNext();
            String data = current.getData();
            return data;
        }

        public void remove(){//required to implement iterator but not used
            throw new UnsupportedOperationException();
        }
    }
}
