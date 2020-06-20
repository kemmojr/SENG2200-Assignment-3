/*
Author: Timothy Kemmis
std no: c3329386
getID.java
SENG2200 Assignment 3
 */

import java.util.Random;

public class getID{
    Random r;
    //int seed;

    public getID(){
        r = new Random();
    }

    public int getID(){//Generates an ID by getting a random number from a random number generator
        return r.nextInt();
    }
}
