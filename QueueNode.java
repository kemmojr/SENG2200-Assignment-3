public class QueueNode {
    private QueueNode next, previous;
    private String data;

    public QueueNode(String s){
        data = s;
    }

    public QueueNode(String s, QueueNode nxt, QueueNode prev){
        next = nxt;
        previous = nxt;
        data = s;
    }

    public QueueNode(QueueNode q){//copy constructor
        this.next = q.next;
        this.previous = q.previous;
        this.data = q.data;
    }

    public QueueNode getNext() {
        return next;
    }

    public QueueNode getPrevious() {
        return previous;
    }

    public String getData() {
        return data;
    }

    public void setNext(QueueNode next) {
        this.next = next;
    }

    public void setPrevious(QueueNode previous) {
        this.previous = previous;
    }

    public void setData(String data) {
        this.data = data;
    }
}
