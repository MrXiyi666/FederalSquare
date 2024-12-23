package fun.android.federal_square.network;

import android.app.Activity;
import fun.android.federal_square.DiskActivity;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import okhttp3.FormBody;

public class NetWork_删除网盘图片 extends NetWork_Main {
    private DiskActivity diskActivity;
    private String name;
    public NetWork_删除网盘图片(Activity activity) {
        super(activity);
    }
    public void 传递参数(String name, DiskActivity diskActivity){
        this.name = name;
        this.diskActivity = diskActivity;
        formBody = new FormBody.Builder()
                .add("PassWord", able.PassWord)
                .add("path",  "./Account/" + Fun_账号.GetID() + "/Image_Resources/" + name)
                .build();
        url = able.URL;
        password = able.PassWord;
        url_path = "federal-square/Delete_File.php";
        b_mess = false;
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("no_file")){
            Fun.mess(activity, "文件不存在");
            return;
        }
        if(string.equals("no_delete")){
            Fun.mess(activity, "删除失败");
            return;
        }
        Fun.mess(activity, "删除成功");
        this.b_update = true;
        Fun_文件.删除文件(able.app_path + "Disk_Data/" + name);
    }

    @Override
    public void 刷新() {
        super.刷新();
        diskActivity.初始化数据();
    }

}