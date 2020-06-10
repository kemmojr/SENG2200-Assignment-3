import java.util.Iterator;
import java.util.*;
import java.io.*;

public class List implements Iterable{

        private BeginningStage sentinel;
        private FinalStage tail;
        private int size = 0;

        public List(Item first,  int iM, int iN) {//Creates a Linkedlist object with one node from a polygon
            ItemQueue n = new ItemQueue();
            sentinel = new BeginningStage(first, n, iM, iN);
            size++;

        }

        public List() {//creates an empty LinkedList
            sentinel = null;
        }

        public BeginningStage getSentinel(){
            return sentinel;
        }

        public void insert(ItemQueue before,InterStage inserting){//insert a item before a specified node
            InterStage newS = new InterStage(inserting);
            newS.setPrevious(before);
            newS.setNext(before.getNext1().getNext());
            before.setNext(newS);
            size++;
        }

        public void insert(InterStage before, ItemQueue it){
            ItemQueue item = new ItemQueue(it);
            item.setPrevious(before);
            item.setNext(before.getNext().getNext1());
            before.setNext(item);
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


        /*public void remove() {//remove from the start of the list
            sentinel.getNext().getNext1().setPrevious(sentinel);
            sentinel.setNext(sentinel.getNext().getNext1());
            //sentinel.getNext().delete();
            size--;
        }*/

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



