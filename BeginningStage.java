import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BeginningStage extends Stage {

    double totalTime, productionTimePercentage, starvingTimePercentage, blockedTimePercentage;
    Item head, tail;
    int size;

    @Override
    public void calculateTotalTime() {
        //
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size<0){
            return true;
        }
        return false;
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
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
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
    public Object remove() {
        return null;
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
    public Object peek() {
        return null;
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
