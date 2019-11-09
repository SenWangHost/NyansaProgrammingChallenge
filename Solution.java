import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.*;
/**
 * This is the main function to run the solution, which takes the filename as the command line
 * arguments and read the file as data stream instead of the whole file because the file could
 * be very large and cannot fit in the memory
 */
public class Solution {
    /**
     * This is the main function for running the solution to this coding challenge
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You need to provide the file name as the command line argument!");
            return;
        }
        DataCollector dataCollector = new DataCollector();
        String fileName = args[0];
        // use java buffered reader to read the file one line by one line to avoid caching the whole file
        // into memory
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                dataCollector.addRecord(line);
            }
            reader.close();
            dataCollector.printReport();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}