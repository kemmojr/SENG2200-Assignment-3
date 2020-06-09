import java.util.Iterator;
import java.util.*;
import java.io.*;

public class QueueList implements Iterable{

     private FactoryQueue sentinel;
        private int size = 0;

        public QueueList(Item first) {//Creates a Linkedlist object with one node from a polygon
            FactoryQueue n = new FactoryQueue(first);
            sentinel = new FactoryQueue();
            sentinel.setNext(n);
            sentinel.setPrevious(n);
            size++;

        }

        public QueueList() {//creates an empty LinkedList
            sentinel = new FactoryQueue();
        }

        private FactoryQueue getSentinel(){
            return sentinel;
        }

        private void insert(FactoryQueue before,Item inserting){//insert a item before a specified node
            FactoryQueue newN = new FactoryQueue(inserting);
            newN.setNext(before);
            newN.setPrevious(before.getPrevious());
            before.getPrevious().setNext( newN);
            before.setPrevious(newN);
            size++;
        }

        public void prepend(Item p) {//Add a new node at the start of the LL
            FactoryQueue n = new FactoryQueue(p);
            n.setPrevious(sentinel);
            n.setNext(sentinel.getNext());
            sentinel.getNext().setPrevious(n);
            sentinel.setNext(n);
            size++;
        }

        public void append(Item p) {//Add a new node at the end of the LL
            FactoryQueue n = new FactoryQueue(p);
            n.setNext(sentinel);
            n.setPrevious(sentinel.getPrevious());
            sentinel.getPrevious().setNext(n);
            sentinel.setPrevious(n);
            size++;
        }

        // return Iterator instance
        public Iterator iterator(){
            return new ListIterator();
        }


        public void remove() {//remove from the start of the list
            sentinel.getNext().getNext().setPrevious(sentinel);
            sentinel.setNext(sentinel.getNext().getNext());
            //sentinel.getNext().delete();
            size--;
        }

        public int getSize(){
            return size;
        }

        public void insertSorted(Item input){
            //insert a new node into it's correctly sorted position

            //FactoryQueue comp = sorted.sentinel.getNext();//The node we are comparing to which changes
            if (getSize()==0){
                append(input);
                return;
            }
            /*for (int i = 0; i < getSize(); i++) {
                if (compareTo(input,comp.getData())<0){
                    sorted.insert(comp,input);
                    return;
                } else if (i==getSize()-1){
                    append(input);
                    return;
                }
                comp = comp.getNext();*/
        }


        @Override
        public String toString() {//A toString method that steps through the LinkedList and outputs in in the correct format
            String out = "";

            ListIterator it = new ListIterator();
            while (it.hasNext()){
                System.out.println(it.next());
            }
            return out;
        }

        private class ListIterator implements Iterator {//implementation of iterator
            private FactoryQueue current;


            public ListIterator(){//implementation of iterator
                current = sentinel;
            }

            public boolean hasNext(){//implementation of iterator check for next
                if (current.getNext()!=sentinel){
                    return true;
                }
                return false;
            }


            public FactoryQueue next(){//implementation of iterator move to next and return data

                current = current.getNext();
                FactoryQueue data = current.getData();
                return data;
            }

            public void remove(){//required to implement iterator but not used
                throw new UnsupportedOperationException();
            }
        }


}



