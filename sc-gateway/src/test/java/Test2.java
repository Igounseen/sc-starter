import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.cglib.core.Local;

/**
 * @author shiwenxiang
 * @date 2020/4/8
 */
public class Test2 {

    /**
     * <dependency>
     * <groupId>joda-time</groupId>
     * <artifactId>joda-time</artifactId>
     * <version>2.10.5</version>
     * </dependency>
     *
     * @param args
     */
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String s = "2020-04-09T10:07:19.283";
        Date date = new DateTime(s).toDate();
        LocalDateTime ldt = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        System.out.println(ldt);
    }
}
