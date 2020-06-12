import java.util.Random;

public class getID{
    Random r;
    int count;

    public getID(){
        count = 0;
    }

    public int getID(){
        r = new Random(count);
        return r.nextInt();
    }
}
