package fun.android.federal_square.fun;

import java.time.LocalDateTime;
import java.util.List;

public class FileNameSort {

    public static void sortByDateTime(List<String> fileNames) {
        // 使用自定义比较器排序
        fileNames.sort((s1, s2) -> {
            LocalDateTime time1 = parseDateTime(s1);
            LocalDateTime time2 = parseDateTime(s2);
            // 降序排列（新时间在前）
            return time2.compareTo(time1);
        });
    }

    private static LocalDateTime parseDateTime(String fileName) {
        // 去掉 ".json" 后缀并分割各部分
        String[] parts = fileName.substring(0, fileName.length() - 5).split("_");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        int hour = Integer.parseInt(parts[3]);
        int minute = Integer.parseInt(parts[4]);
        int second = Integer.parseInt(parts[5]);
        int millis = Integer.parseInt(parts[6]);
        // 将毫秒转换为纳秒
        int nanos = millis * 1_000_000;
        // 构建 LocalDateTime 对象
        return LocalDateTime.of(year, month, day, hour, minute, second, nanos);
    }

}
