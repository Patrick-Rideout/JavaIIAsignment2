package Q1;

import java.io.FileNotFoundException;
import java.util.*;

public class TelephoneNumberWordGenerator {
    public static void main(String[] args) {

        Date date = new Date();
        Long timestamp = date.getTime();

        Map<Integer, ArrayList<String>> numberWordPairings = new HashMap<>();

        numberWordPairings.put(2, new ArrayList<String>() {{ add("A"); add("B"); add("C"); }});
        numberWordPairings.put(3, new ArrayList<String>() {{ add("D"); add("E"); add("F"); }});
        numberWordPairings.put(4, new ArrayList<String>() {{ add("G"); add("H"); add("I"); }});
        numberWordPairings.put(5, new ArrayList<String>() {{ add("J"); add("K"); add("L"); }});
        numberWordPairings.put(6, new ArrayList<String>() {{ add("M"); add("N"); add("O"); }});
        numberWordPairings.put(7, new ArrayList<String>() {{ add("P"); add("R"); add("S"); }});
        numberWordPairings.put(8, new ArrayList<String>() {{ add("T"); add("U"); add("V"); }});
        numberWordPairings.put(9, new ArrayList<String>() {{ add("W"); add("X"); add("Y"); }});

        try (Formatter output = new Formatter("./logs/generatedFile"+timestamp+".txt")){


            for (int totalNumbers = 9999999; totalNumbers >= 1000000; totalNumbers--) {
                LinkedList<Integer> listOfNumbers = new LinkedList<>();
                int tempNumber = totalNumbers;

                while (tempNumber > 0) {
                    listOfNumbers.push(tempNumber % 10); /* Gets remainder after dividing by 10*/
                    tempNumber /= 10; /* Removes last digit*/
                }

                if(listOfNumbers.contains(0)) {
                    continue;
                }
                else if(listOfNumbers.contains(1)) {
                    continue;
                }
                else {
                    System.out.println(listOfNumbers);
                }




            }

        }
        catch( FileNotFoundException | SecurityException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }


}
}
