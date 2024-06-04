package Q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class phishingScanner {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Name of file in emails folder without extension(EX: PhishingEmailExample): ");
        String emailName = input.nextLine();

        Path path = Paths.get("./emails/" + emailName + ".txt");
        if (Files.exists(path)) {
            System.out.println("File exists!\n");
            try (BufferedReader reader = Files.newBufferedReader(path)) {

                String email = Files.readString(path);

                email = email.replace("'","");

                String[] emailWords = email.split("\\W+");
                //The \\W+ will match all non-alphabetic characters occurring one or more times.

                scanContent(emailWords);

            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
            }
        } else {
            System.out.println("File does not exist\n");
            }
        }

    private static void scanContent(String[] content) {
        Map<String, Integer> occurrences = new HashMap<>();
        int totalPoints = 0;

        Map<String, Integer> keywords = new HashMap<>();
        keywords.put("free", 3);
        keywords.put("prince", 3);
        keywords.put("verify", 3);
        keywords.put("login", 3);
        keywords.put("click", 3);
        keywords.put("update", 3);
        keywords.put("security", 3);
        keywords.put("immediate", 3);
        keywords.put("password", 3);
        keywords.put("account", 2);
        keywords.put("king", 1);
        keywords.put("win", 2);
        keywords.put("prize", 2);
        keywords.put("limited", 2);
        keywords.put("exclusive", 2);
        keywords.put("offer", 2);
        keywords.put("access", 2);
        keywords.put("important", 2);
        keywords.put("notification", 2);
        keywords.put("credit", 2);
        keywords.put("bank", 2);
        keywords.put("verify", 2);
        keywords.put("confirm", 2);
        keywords.put("update", 2);
        keywords.put("secure", 1);
        keywords.put("confidential", 1);
        keywords.put("statement", 1);
        keywords.put("billing", 1);
        keywords.put("action", 1);
        keywords.put("alert", 1);

        for (String keyword : keywords.keySet()) {
            occurrences.put(keyword, 0);
        }

        for (String word : content) {
            for (int i = 0; i < content.length; i++) {
                content[i] = content[i].toLowerCase();
            }
            if (occurrences.containsKey(word)) {
                int newTotal = occurrences.get(word) + 1;
                occurrences.replace(word, newTotal);
                totalPoints = totalPoints + keywords.get(word);
            }
        }

        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            String keyword = entry.getKey();
            int count = entry.getValue();
            if (count > 0) {
                int points = count * keywords.get(keyword);
                System.out.println("Keyword: '" + keyword + "' Occurrences: " + count + " Points: " + points);
            }
        }


        System.out.println("_________________________________________________");

        System.out.println("Total Points: " + totalPoints);
    }
}
