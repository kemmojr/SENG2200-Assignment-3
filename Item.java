/*
Author: Timothy Kemmis
std no: c3329386
Item.java
SENG2200 Assignment 3
 */

import java.util.LinkedList;

public class Item {
    //member variables
    private String data;//the string data
    private LinkedList<String> path;//A linkedList of all of the stages that this item passes through

    public Item(String s){//Item constructor which creates an item with data s
        data = s;
        path = new LinkedList<>();
    }

    public Item(Item i){//copy constructor
        this.data = i.data;
    }//copy constructor

    public String getData() {//gets the data
        return data;
    }//gets the data

    public void setData(String data) {
        this.data = data;
    }//sets the data

    public void addToPath(String id){//Adds a new stage id to the path of the item
        path.add(id);
    }

    public LinkedList<String> getPath(){//gets the path
        return path;
    }

    public boolean doesPathInclude(String id){//A check to see if 2 stages are part of the same path for getting the production paths metrics
        for (String s:path){
            if (id.equals(s)){
                return true;
            }
        }
        return false;
    }
}
