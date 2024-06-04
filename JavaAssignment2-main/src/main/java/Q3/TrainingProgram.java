package Q3;

import javax.xml.bind.JAXB;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class TrainingProgram {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Path path = Paths.get("./logs/settings.xml");
        if (Files.exists(path))
        {
            System.out.println("File exists");



            try(BufferedReader output = Files.newBufferedReader(path)) {

                Settings settings = JAXB.unmarshal(output, Settings.class);

                boolean changed = false;

                while (true) {
                    System.out.println("\nOptions:");
                    System.out.println("1) View settings");
                    System.out.println("2) Update settings");
                    System.out.println("3) Exit (Save)");

                    String choice = input.nextLine();

                    if (Objects.equals(choice, "1")) {
                        System.out.printf("Name: %s\n", settings.getName());
                        System.out.printf("Height: %.2f\n", settings.getHeight());
                        System.out.printf("Weight: %.2f\n", settings.getWeight());
                        System.out.printf("Birthday: %s\n", settings.getDateToString());
                        System.out.printf("Power Level: %.2f\n", settings.getFunctionalThresholdPower());

                        if (changed) {
                            System.out.printf("\nCHANGES NOT SAVED\n");
                        }


                    } else if (Objects.equals(choice, "2")) {
                        System.out.println("Name:");
                        String name = input.nextLine();

                        settings.setName(name);

                        System.out.println("Height:");
                        double height = input.nextDouble();

                        settings.setHeight(height);

                        System.out.println("Weight:");
                        double weight = input.nextDouble();

                        settings.setWeight(weight);

                        input.nextLine();

                        System.out.println("Birthday (YYYY-MM-DD):");

                        String birthdayStr = input.nextLine();
                        try {
                            Date birthday = dateFormat.parse(birthdayStr);
                            settings.setBirthday(birthday);
                        } catch (Exception e) {
                            System.err.println("Invalid date format. Please use YYYY-MM-DD.");
                            break;
                        }


                        System.out.println("5) Functional Threshold Power");
                        double fTP = input.nextDouble();

                        settings.setFunctionalThresholdPower(fTP);

                        input.nextLine();

                        changed = true;


                    } else if (Objects.equals(choice, "3")) {
                        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                            JAXB.marshal(settings, writer);
                            System.out.println("Settings updated successfully.");
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.err.println(e.getMessage());
                        }
                        break;
                    }
                }

            }
            catch(FileNotFoundException | SecurityException ex) {
                ex.printStackTrace();
                System.err.println(ex.getMessage());
            }
            catch(IOException ex) {
                ex.printStackTrace();
                System.err.println(ex.getMessage());
            }

        }
        else {

            try(BufferedWriter output = Files.newBufferedWriter(Paths.get("./logs/Settings" + ".xml"))) {
                System.out.println("Settings.xml Created");

                Settings settings = new Settings();

                System.out.println("Name:");
                String name = input.nextLine();

                settings.setName(name);

                System.out.println("Height:");
                double height = input.nextDouble();

                settings.setHeight(height);

                System.out.println("Weight:");
                double weight = input.nextDouble();

                settings.setWeight(weight);

                input.nextLine();

                System.out.println("Birthday (YYYY-MM-DD):");

                String birthdayStr = input.nextLine();
                try {
                    Date birthday = dateFormat.parse(birthdayStr);
                    settings.setBirthday(birthday);
                } catch (Exception e) {
                    System.err.println("Invalid date format. Please use YYYY-MM-DD.");
                }

                System.out.println("5) Functional Threshold Power");
                double fTP = input.nextDouble();

                settings.setFunctionalThresholdPower(fTP);
                input.nextLine();
                JAXB.marshal(settings, output);
            }
            catch(IOException ex) {
                ex.printStackTrace();
                System.err.println(ex.getMessage());
            }

        }
    }

}
