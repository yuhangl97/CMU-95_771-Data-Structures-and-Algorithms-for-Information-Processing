import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class CrimeReader {
    private List<String> records;
    private String url = "http://www.andrew.cmu.edu/user/mm6/95-771/CrimeData/CrimeLatLonXY1990.csv";


    public void initialize() throws IOException {
        String line;
        records = new ArrayList<>();
        BufferedReader read = new BufferedReader(new InputStreamReader(
                new URL(url).openConnection().getInputStream(), StandardCharsets.UTF_8));
        read.readLine();
        while ((line = read.readLine()) != null) {
            records.add(line);
        }
    }


    public List<String> getRecords() {
        return records;
    }


    public List<String> getCrimes(String start, String end) {
        List<String> result = new ArrayList<String>();

        for (String record : records) {
            String date = getDate(record);
            if (compareDate(date, start) >= 0 && compareDate(date, end) <= 0) {
                result.add(record);
            }
        }

        return result;
    }


    private static int compareDate(String s1, String s2) {
        String[] date1 = s1.split("/");
        String[] date2 = s2.split("/");
        int mouth1 = Integer.parseInt(date1[0]);
        int mouth2 = Integer.parseInt(date2[0]);
        int day1 = Integer.parseInt(date1[1]);
        int day2 = Integer.parseInt(date2[1]);
        if (mouth1 > mouth2)
            return 1;
        else if (mouth1 < mouth2)
            return -1;
        else {
            if (day1 > day2)
                return 1;
            else if (day1 < day2)
                return -1;
        }
        return 0;
    }

    private static String getDate(String s) {
        String[] ss = s.split(",");
        return ss[5];
    }


    public static void main(String[] args) {

    }
}
