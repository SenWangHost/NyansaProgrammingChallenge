import java.text.SimpleDateFormat;
import java.util.*;
/**
 * This is the class where we collector every record of the text file and save them in the
 * corresponding data structure
 */
public class DataCollector {
    /**
     * The hashmap mapping from date string to daily data collector where it holds
     * all the data about one day
     */
    private Map<String, DailyDataCollector> map;
    /**
     * The heap for maintaining the order of date
     */
    private PriorityQueue<String> dates;
    /**
     * The constructor for the data collector, initialize the data structure
     */
    public DataCollector() {
        map = new HashMap<String, DailyDataCollector>();
        dates = new PriorityQueue<String>();
    }
    /**
     * This is the function where we add one record/line to the data collector
     * @param inRecord
     */
    public void addRecord(String inRecord) {
        if (inRecord == null || inRecord.length() == 0) {
            return;
        }
        String[] strs = inRecord.split("\\|");
        if (strs.length != 2) {
            return;
        }
        // get the date and url
        String dateString = convertEpochTimeToDateString(strs[0]);
        String url = strs[1];
        if (!map.containsKey(dateString)) {
            map.put(dateString, new DailyDataCollector());
            dates.add(dateString);
        }
        DailyDataCollector ddCollector = map.get(dateString);
        ddCollector.addRecord(url);
    }
    /**
     * This is the helper function to convert the epoch time in seconds to date string
     * @param inEpochTimeInSeconds
     */
    private String convertEpochTimeToDateString(String inEpochTimeInSeconds) {
        Date date = new Date(Long.parseLong(inEpochTimeInSeconds) * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);
    }
    /**
     * This is the function where we print the report to stdout
     */
    public void printReport() {
        // print the record in chronological order
        List<String> list = new ArrayList<>();
        while (dates.size() > 0) {
            String dateTime = dates.poll();
            System.out.println(formatDateOutput(dateTime) + " GMT");
            DailyDataCollector ddCollector = map.get(dateTime);
            ddCollector.printDailyRecords();
            list.add(dateTime);
        }
        list.forEach(dateTime -> {
            dates.add(dateTime);
        });
    }
    /**
     * This is the helper function format the date output, the output should be MM/dd/yyyy
     */
    private String formatDateOutput(String inDatetime) {
        String[] strs = inDatetime.split("/");
        return strs[1] + "/" + strs[2] + "/" + strs[0];
    }
}