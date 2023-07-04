import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Cron {

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = dateFormat.format(now);

                System.out.println("Current time: " + currentTime);
            }
        };

        timer.scheduleAtFixedRate(task, 0, 60 * 1000);
    }
}
