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
