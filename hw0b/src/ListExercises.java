import java.util.ArrayList;
import java.util.List;


public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int sum = 0;
        for (int i: L) sum += i;
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> E = new ArrayList<>();
        for (Integer i: L) if (i % 2 == 0) E.add(i);
        return E;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> commonList = new ArrayList<>();
        for (Integer num1: L1){
            if (L2.contains(num1) && !commonList.contains(num1)) commonList.add(num1);
        }
        return commonList;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int count = 0;
        for (String word: words){
            for (int i=0; i < word.length(); i++){
                if (word.charAt(i) == c) count++;
            }
        }
        return count;
    }
}
