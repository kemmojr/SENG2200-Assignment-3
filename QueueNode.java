public class QueueNode {
    private QueueNode next, previous;
    private String data;

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
