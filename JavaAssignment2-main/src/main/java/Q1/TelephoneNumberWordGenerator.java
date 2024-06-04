package Q1;

import java.io.File;
import java.util.*;

public class TelephoneNumberWordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a seven-digit number(That does not contain 0 or 1): ");
        String input = scanner.nextLine();

        if (input.contains("0") || input.contains("1") || input.length() != 7) {
            System.out.println("Input bad.");
        }
        else {

            Date date = new Date();
            Long timestamp = date.getTime();

            Map<Integer, List<String>> numberWordPairings = new HashMap<>();
            numberWordPairings.put(2, Arrays.asList("A", "B", "C"));
            numberWordPairings.put(3, Arrays.asList("D", "E", "F"));
            numberWordPairings.put(4, Arrays.asList("G", "H", "I"));
            numberWordPairings.put(5, Arrays.asList("J", "K", "L"));
            numberWordPairings.put(6, Arrays.asList("M", "N", "O"));
            numberWordPairings.put(7, Arrays.asList("P", "R", "S"));
            numberWordPairings.put(8, Arrays.asList("T", "U", "V"));
            numberWordPairings.put(9, Arrays.asList("W", "X", "Y"));

            List<Integer> listOfNumbers = new LinkedList<>();
            for (char ch : input.toCharArray()) {
                listOfNumbers.add(Character.getNumericValue(ch));
            }

            new File("./logs").mkdirs();

            try (Formatter output = new Formatter("./logs/WordGenerator" + timestamp + ".txt")) {
                List<String> result = new ArrayList<>();
                generateWords(listOfNumbers, 0, "", result, numberWordPairings);

                for (String word : result) {
                    output.format("%s%n", word);
                }

                System.out.println("Completed");

            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    private static void generateWords(List<Integer> numbers, int index, String current, List<String> result, Map<Integer, List<String>> numberWordPairings) {
        if (index == numbers.size()) {
            result.add(current);
            return;
        }

        int digit = numbers.get(index);
        List<String> letters = numberWordPairings.get(digit);

        for (String letter : letters) {
            generateWords(numbers, index + 1, current + letter, result, numberWordPairings);
        }
    }
}
