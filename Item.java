public class Item {
    private String data;//the string data

    public Item(String s){//Item constructor which creates an item with data s
        data = s;
    }

    public Item(Item i){//copy constructor
        this.data = i.data;
    }

    public String getData() {//gets the data
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }//sets the data
}
