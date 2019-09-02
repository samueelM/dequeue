package hackerhank.dequeue;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    private static final int MIN_LIM = 0;
    private static final int MAX_PARAM_LIM = 100000;
    private static final int MAX_NUM_LIM = 10000000;

    /**
    * Validate the Entries
    */
    private static void validateEntry (int n, int m) {

        if ((n < MIN_LIM && n > MAX_PARAM_LIM) ||
            (m < MIN_LIM && m > MAX_PARAM_LIM)){
                throw new IllegalArgumentException ("Invalid values for params");
            }
    }

    /**
    * Validate the elements
    */
    private static void validateArrayElement (int num) {

        if (num < MIN_LIM && num > MAX_NUM_LIM){
            throw new IllegalArgumentException ("Invalid values for elements");
        }
    }

    /**
        Prepare de Sub-arrays
    */
    private static Deque<Deque> fillDeck (Deque deckReturn, Deque fullDeck, int m) {

        Object[] subArray = fullDeck.toArray();
        Deque subDeck = new ArrayDeque<>();

        if (fullDeck.size() >= m) {

            for (int i =0;i<m;i++){
                subDeck.offerLast(subArray[i]);
            }

            fullDeck.remove();
            deckReturn.addLast (subDeck);
            fillDeck (deckReturn, fullDeck, m); 
        } 
        
        return deckReturn;

    }

    /**
    * Find the quantity of unique numbers by Sub-array
    */
    private static int findQuantityUniqueNumbers (Deque deque) {
        
        Set<Integer> numbers = new HashSet<>();
        numbers.addAll(deque);

        return numbers.size();
    }

    public static void main(String[] args) {
    	System.out.println("Inicio: "+ Instant.now());
    	Scanner in = new Scanner(System.in);
        Deque deque = new ArrayDeque<>();
        //amount element
        int n = in.nextInt();
        //subarray size
        int m = in.nextInt();
        final AtomicInteger maxUniqueNumber = new AtomicInteger (-1);

        validateEntry (n,m);

        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            validateArrayElement (num);
            deque.offerLast (num);
        }

        Deque decks = fillDeck(new ArrayDeque<>(), deque,m);

        List<Deque> sub = new ArrayList<>();
        sub.addAll (decks);

        sub.forEach ( subArray -> {
            int quant = findQuantityUniqueNumbers (subArray);

            if (quant > maxUniqueNumber.get()) {
               maxUniqueNumber.set (quant);
            }

        });

        System.out.println (maxUniqueNumber);
    	System.out.println("Fim: "+ Instant.now());

    }
}



