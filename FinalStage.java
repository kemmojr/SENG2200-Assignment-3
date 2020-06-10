import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FinalStage extends Stage {

    double totalTime, productionTimePercentage, starvingTimePercentage, blockedTimePercentage;
    Item currentItem;
    int size, M, N;
    Random r;
    boolean blocked, starved, processing;
    private ItemQueue previousQueue;

    public FinalStage(Item it, ItemQueue previous, int iM, int iN) {
        super(iM, iN);
        currentItem = it;
        previousQueue = previous;
        M = iM;
        N = iN;
        r = new Random();
    }

    public FinalStage(int iM, int iN) {
        super(iM, iN);
    }

    @Override
    public void calculateTotalTime() {
        //
    }

}
