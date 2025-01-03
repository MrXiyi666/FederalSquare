package fun.android.federal_square.fun;

import java.util.Comparator;
import java.util.List;
import fun.android.federal_square.data.able;

public class Fun_图片 {
    public static List<String> 遍历所有图片(){
        List<String> list = Fun_文件.遍历文件夹(able.app_path + "Disk_Data");
        Comparator<String> comparator = Comparator.reverseOrder();
        list.sort(comparator);
        return list;
    }
}
