package hackerhank.angryanimals;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    public static boolean existEnemyTogheter (List<Integer> animalGroup, List<List<Integer>> enemyPairs){
        boolean ret = false;

        for (int pair=0; pair < enemyPairs.size(); pair++) {
            Integer xAnimal = enemyPairs.get(pair).get(0);
            Integer yAnimal = enemyPairs.get(pair).get(1);


            ret = animalGroup.contains(xAnimal) && 
                    animalGroup.contains(yAnimal);

            if (ret) {
                break;
            }
            
        }

        return ret;
    }

    public static List<List<Integer>> findEnemyPair (List<Integer> a, List<Integer> b) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> pair = null;


        for (int i =0;i<a.size();i++) {
            pair = new ArrayList<>();

            pair.add(a.get(i));
            pair.add(b.get(i));

            result.add(pair);
        }

        return result;
    }


    /*
     * Complete the 'angryAnimals' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY a
     *  3. INTEGER_ARRAY b
     */
    public static long angryAnimals(int n, List<Integer> a, List<Integer> b) {
    // Write your code here

        List<Integer> joinedList = IntStream.rangeClosed(1, n).mapToObj(Integer::valueOf)
            .collect(Collectors.toList());

        List<List<Integer>> enemyPairs = findEnemyPair (a,b);
        
        int animalGroup = 0;

        for (int offset= 1; offset < n; offset++) {
            for (int pos= 0; pos<n; pos++) {
                List<Integer> result = new ArrayList<>();

                int limit = pos+offset;
                if (limit >= n) {
                    break;
                } else {
                    for (int i = pos; i <= pos+offset; i++) {
                        result.add (joinedList.get(i));
                    }

                    if (!existEnemyTogheter (result , enemyPairs)){
                        animalGroup++;
                    }
                }
            }
        }


        return animalGroup+joinedList.size();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = IntStream.range(0, aCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int bCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> b = IntStream.range(0, bCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        long result = Result.angryAnimals(n, a, b);

        System.out.println(result);
        
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
    }
}
