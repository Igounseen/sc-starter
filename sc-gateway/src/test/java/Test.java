import com.alibaba.fastjson.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.util.CollectionUtils;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * @author shiwenxiang
 * @date 2020/4/2
 */
public class Test {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<Integer> data = new ArrayList<>();
        List<String> delayTime = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(3);
        delayTime.add("2020-04-08 17:16:06");
        delayTime.add("2020-04-08 17:17:06");
        delayTime.add("2020-04-08 17:18:06");
        map.put("data", data);
        map.put("delayTime", delayTime);
        f(map, 7);
        System.out.println(JSONObject.toJSONString(map));
    }

    public static void f(Map<String, Object> map, int size) throws ParseException {
        List<Double> data = (List<Double>) map.get("data");
        List<String> delayTime = (List) map.get("delayTime");
        Calendar instance = Calendar.getInstance();
        if (CollectionUtils.isEmpty(delayTime)) {
            data = new ArrayList<>();
            delayTime = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                delayTime.add(0, sdf.format(instance.getTime()));
                data.add(null);
                instance.add(Calendar.MINUTE, -1);
            }
        } else {
            String lastTime = delayTime.get(delayTime.size() - 1);
            Date lastDate = sdf.parse(lastTime);
            long deta = instance.getTimeInMillis() - lastDate.getTime();
            // 很长时间没有数据
            if (deta > (size * 60 * 1000)) {
                for (int i = 0; i < size; i++) {
                    delayTime.add(0, sdf.format(instance.getTime()));
                    data.add(null);
                    instance.add(Calendar.MINUTE, -1);
                }
            } else if (deta > 65 * 1000) {
                // 短时间没数据，填充null
                // 计算末尾补全null的个数
                int k = (int) (deta / (60 * 1000));
                k = Math.min(k, size);
                Calendar cursor = Calendar.getInstance();
                cursor.setTime(lastDate);
                for (int i = 0; i < k; i++) {
                    cursor.add(Calendar.MINUTE, 1);
                    delayTime.add(delayTime.size(), sdf.format(cursor.getTime()));
                    data.add(data.size(), null);
                }
            }
            // 去掉多余的数据
            int all = delayTime.size();
            if (all > size) {
                delayTime = delayTime.subList(all - size, all);
                data = data.subList(all - size, all);
            }
        }
        map.put("data", data);
        map.put("delayTime", delayTime);
    }

}