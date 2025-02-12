package fun.android.federal_square.fun;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class TimeUtils {
    /**
     * 根据给定的时间字符串与当前时间计算并返回一个友好的时间描述。
     * 格式：如果超过一年显示多少年以前，超过一天显示多少天以前，超过一小时显示多少小时以前，
     * 超过一小时就显示多少分钟以前，超过一分钟就显示多少秒以前。
     *
     * @param dateTimeStr 时间字符串，格式为 yyyy_mm_dd_HH_mm_ss_SSS
     * @return 友好的时间描述字符串
     */
    public static String calculateRelativeTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_SSS");
        LocalDateTime oldDateTime;

        // 分离时间字符串中的日期部分和可能的附加信息（如 _epoch）
        String[] parts = dateTimeStr.split("_");
        if (parts.length < 7) {
            throw new IllegalArgumentException("时间字符串格式不正确，无法解析。");
        }

        // 取前7个部分作为日期时间格式
        String dateTimePart = String.join("_", parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);

        try {
            oldDateTime = LocalDateTime.parse(dateTimePart, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("时间字符串格式不正确，无法解析。");
        }

        LocalDateTime currentDateTime = LocalDateTime.now();

        // 计算时间差
        long yearDifference = ChronoUnit.YEARS.between(oldDateTime, currentDateTime);
        if (yearDifference > 0) {
            return yearDifference + "年前";
        } else {
            Duration duration = Duration.between(oldDateTime, currentDateTime);
            long totalSeconds = duration.getSeconds();

            if (totalSeconds < 0) {
                totalSeconds = Math.abs(totalSeconds); // 如果 oldDateTime 在未来，取绝对值
            }

            long days = totalSeconds / (24 * 60 * 60);
            long hours = (totalSeconds % (24 * 60 * 60)) / (60 * 60);
            long minutes = (totalSeconds % (60 * 60)) / 60;
            long seconds = totalSeconds % 60;

            if (days > 0) {
                return days + "天前";
            } else if (hours > 0) {
                return hours + "小时前";
            } else if (minutes > 0) {
                return minutes + "分钟前";
            } else {
                return seconds + "秒前";
            }
        }
    }
}
