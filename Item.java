public class Item {
    private Item next, previous;
    private String data;

    public Item(String s){
        data = s;
    }

    public Item(String s, Item nxt, Item prev){
        next = nxt;
        previous = nxt;
        data = s;
    }

    public Item(Item q){//copy constructor
        this.next = q.next;
        this.previous = q.previous;
        this.data = q.data;
    }

    public Item getNext() {
        return next;
    }

    public Item getPrevious() {
        return previous;
    }

    public String getData() {
        return data;
    }

    public void setNext(Item next) {
        this.next = next;
    }

    public void setPrevious(Item previous) {
        this.previous = previous;
    }

    public void setData(String data) {
        this.data = data;
    }
}
