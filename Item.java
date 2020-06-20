import java.util.LinkedList;

public class Item {
    private String data;//the string data
    private LinkedList<String> path;

    public Item(String s){//Item constructor which creates an item with data s
        data = s;
        path = new LinkedList<>();
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

    public void addToPath(String id){
        path.add(id);
    }

    public LinkedList<String> getPath() {
        return path;
    }

    public boolean doesPathInclude(String id){
        for (String s:path){
            if (id.equals(s)){
                return true;
            }
        }
        return false;
    }
}
