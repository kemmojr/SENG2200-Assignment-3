import java.util.Random;

public class getID{
    Random r;
    int count;

    public getID(){
        count = 0;
    }

    public int getID(){//Generates an ID by getting a random number from a random number generator
        r = new Random(count);
        return r.nextInt();
    }
}
