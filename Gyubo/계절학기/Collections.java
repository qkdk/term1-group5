import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Collections {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();

        Map<Integer, Integer> monthMap = new HashMap<>();
        monthMap.put(0, 30);
        monthMap.put(1, 31);

        int days = monthMap.get(month % 2);

        if (month == 2) {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        }

        System.out.println(days + " days for " + year + "-" + (month));
    }
}
