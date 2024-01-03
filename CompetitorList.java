import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.String;
import java.util.Collections;
import java.util.Comparator;
//Imports of classes and libraries



public class CompetitorList {

        // Method to read competitors from a CSV file and add them to the list
        public static void readCompetitorsFromCSV(String filePath, ArrayList<AZCompetitor> competitorsList) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 10) { // Assuming 8 columns: ID, FName, LName, Email, Age, Country, and score
                        int id = Integer.parseInt(data[0]);
                        String fName = data[1];
                        String lName = data[2];
                        int age = Integer.parseInt(data[3]);
                        String gender = data[4];
                        String country = data[4];
                        int[] scores = Arrays.stream(Arrays.copyOfRange(data, 6, data.length))
                                .mapToInt(Integer::parseInt)
                                .toArray();

                        AZCompetitor competitor = new AZCompetitor(id, fName, lName, gender, age, country, scores);
                        competitorsList.add(competitor);
                    } else {
                        System.out.println("Invalid data format in CSV file. Skipping line: " + line);
                    }
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }


        //Method to write data to the csv file
        public static void writeCompetitorsToCSV(String filePath, ArrayList<AZCompetitor> competitorsList) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (AZCompetitor competitor : competitorsList) {
                    StringBuilder line = new StringBuilder();
                    line.append(competitor.getID()).append(',')
                            .append(competitor.getFName()).append(',')
                            .append(competitor.getLName()).append(',')
                            .append(competitor.getGender()).append(',')
                            .append(competitor.getAge()).append(',')
                            .append(competitor.getCountry()).append(',')
                            .append(Arrays.stream(competitor.getScores())
                                    .mapToObj(String::valueOf)
                                    .collect(java.util.stream.Collectors.joining(",")));


                    writer.write(line.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
        
        
        //BONUS WRITING TO TEXT FILES:
        
              public static void outputShortDetailsToTxt(String filePath, ArrayList<AZCompetitor> competitorsList) {
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
                for (AZCompetitor competitor : competitorsList) {
                    writer.println(competitor.getFullDetails() + '\n');
                    writer.println("----------------------------------------------------------------");
                }
                writer.println("----------------------------------------------------------------");
                writer.println("Highest Overall Score");
                writer.println(getHighestOverallScore(competitorsList).getFullDetails());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
