import java.util.*;
/**
 * 
 */
public class DailyDataCollector {
    private List<String> records;
    public DailyDataCollector() {
        records = new ArrayList<String>();
    }
    public void addRecord(String inUrl) {
        records.add(inUrl);
    }
    public void printDailyRecords() {
        records.forEach((record) -> {
            System.out.println(record);
        });
    }
}