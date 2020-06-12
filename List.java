import java.util.Iterator;
import java.util.*;
import java.io.*;

public class List implements Iterable{

        private BeginningStage head;
        private Stage stageTail;
        private ItemQueue queueTail;
        private FinalStage finalTail;
        private int size = 0;
        private int Qmax;

        public List(int iM, int iN, int q) {//Creates a Linkedlist object with one node from a polygon
            ItemQueue n = new ItemQueue(q);
            head = new BeginningStage(n, iM, iN,"A");
            Qmax = q;
            size++;

        }

        public List() {//creates an empty LinkedList
            head = null;
        }

        public BeginningStage getHead(){
            return head;
        }

        public void insert(ItemQueue before,InterStage inserting){//insert a item before a specified node
            InterStage newS = new InterStage(inserting);
            newS.setPrevious(before);
            newS.setNext(before.getNext1().getNext());
            before.setNext(newS);
            size++;
        }

        public void insert(InterStage before, ItemQueue it){
            ItemQueue item = new ItemQueue(Qmax);
            item.setPrevious(before);
            item.setNext(before.getNext().getNext1());
            before.getNext().getNext1();
            before.setNext(item);
        }

        public void append(double ItemQueue) {//Add a new ItemQueue at the end of the LL
            ItemQueue n = new ItemQueue(Qmax);
            if (queueTail.getNext2()!=null && queueTail.getNext1()!=null && queueTail.size()<3){
                queueTail.getNext1().setNext(n);
                queueTail.getNext2().setNext(n);
                n.setPrevious(queueTail.getNext1());
                n.setPrevious(queueTail.getNext2());
                queueTail = n;
                n.setNext(null);
            } else if (queueTail.getNext1()!=null ){
                queueTail.getNext1().setNext(n);
                n.setPrevious(queueTail.getNext1());
                n.setNext(null);
                queueTail = n;
            } else {
                //
            }
            size++;
        }

        public void append(InterStage s){
            InterStage in = new InterStage(s);
            if (queueTail.getNext1()==null){
                in.setNext(null);
                queueTail.setNext(in);
                in.setPrevious(queueTail);
                stageTail = in;
            } else if (queueTail.getNext2()==null){
                in.setNext(null);
                queueTail.setNext(in);
                in.setPrevious(queueTail);
                stageTail = in;
            }
            size++;
        }

        public void append(String interStage){
            InterStage in = new InterStage(queueTail,head.M,head.N);
            if (queueTail.getNext1()==null){
                in.setNext(null);
                queueTail.setNext(in);
                stageTail = in;
                in.setPrevious(queueTail);
            } else if (queueTail.getNext2()==null){
                in.setNext(null);
                queueTail.setNext(in);
                stageTail = in;
                in.setPrevious(queueTail);
            }
            size++;
        }

        public void append(){
            BeginningStage b = new BeginningStage(head.getNext(),head.M,head.N,"B");
            head.getNext().setPrevious(b);
            queueTail = head.getNext();
        }

        public void append(int finalStage){
            FinalStage f = new FinalStage(queueTail,head.M,head.N);
            finalTail = f;
        }

        public BeginningStage getFirst(){
            return head;
        }

        public FinalStage getLast(){
            if (size>10){
                return finalTail;
            }
            return null;
        }

        // return Iterator instance
        public Iterator iterator(){
            return new ListIterator();
        }

        public int getSize(){
            return size;
        }

        @Override
        public String toString() {//A toString method that steps through the LinkedList and outputs in in the correct format
            String out = "";

            ListIterator it = new ListIterator();//Creates an iterator to cycle through the linkedlist
            while (it.hasNext()){
                Data d = it.next();
                if (d.getQueue()!=null){//Some basic if statement logic that prints the type of the iterator, either ItemQueue or Stage for each alternation of the iterator
                    System.out.println(d.getQueue());
                } else {
                    System.out.println(d.getStage());
                }
            }
            return out;
        }

        private class ListIterator implements Iterator {//implementation of iterator
            //These are the current variables that alternate with each iteration
            private Stage currentStage; //A current variable that is used if current is of type stage
            private ItemQueue currentQueue; //A current variable that is used if current is of type ItemQueue
            private boolean currentType = true; //true is Stage, false is queue


            public ListIterator(){//implementation of iterator
                currentStage = head;
            }//A basic constructor that sets the iterator to the head of the linkedlist

            public boolean hasNext(){//implementation of iterator check for next
                if (currentStage.getNext()!=null){
                    return true;
                }
                return false;
            }


            public Data next(){//implementation of iterator move to next and return data
                //Features the additional checks to see if there are parallel stages and to accommodate iterating them
                boolean startingType = currentType;
                Data d = null;
                if (currentType && currentStage.getPrevious().getNext2()==null){//Checks to see if there are parallel stages and alternates the type accordingly
                    currentType =false;
                } else {
                    currentType = true;
                }



                if (currentType==false){
                    currentQueue = currentStage.getNext();
                    d = new Data(currentQueue,null);
                } else if (currentType){
                    currentStage = currentStage.getNext().getNext1();
                    d = new Data(null,currentStage);
                } else if (currentType==startingType){//A check to make sure that parallel stages are iterated
                    currentStage = currentStage.getPrevious().getNext2();
                    d = new Data(null,currentStage);
                }

                return d;
            }

            public void remove(){//required to implement iterator but not used
                throw new UnsupportedOperationException();
            }
        }


}



