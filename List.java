import java.util.Iterator;
import java.util.*;
import java.io.*;

public class List{

        private BeginningStage head;
        private BeginningStage head2;
        private Stage stageTail;
        private ItemQueue queueTail;
        private FinalStage finalTail;
        private int size = 0;
        private int Qmax;

        public List(int iM, int iN, int q) {//Creates a Linkedlist object with one node from a polygon
            ItemQueue n = new ItemQueue(q,"Q01");
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
            ItemQueue item = new ItemQueue(Qmax, "id");
            item.setPrevious(before);
            item.setNext(before.getNext().getNext1());
            before.getNext().getNext1();
            before.setNext(item);
        }

        public void append(double itemQueue) {//Add a new ItemQueue at the end of the LL
            ItemQueue n = new ItemQueue(Qmax, "Q"+itemQueue);
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
            InterStage in = new InterStage(head.M,head.N,interStage);
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
            head2 = b;
        }

        public void append(int finalStage){
            FinalStage f = new FinalStage(queueTail,head.M,head.N);
            finalTail = f;
        }

        public BeginningStage getFirst(){
            return head;
        }

        public BeginningStage getSecond(){
            return head2;
        }

        public FinalStage getLast(){
            if (size>10){
                return finalTail;
            }
            return null;
        }

        public int getSize(){
            return size;
        }

        @Override
        public String toString() {//A toString method that steps through the LinkedList and outputs in in the correct format
            String out = "";
            return out;
        }


}



