package fun.android.federal_square.network;

import android.app.Activity;
import java.io.File;
import fun.android.federal_square.DiskActivity;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.fun.Fun_账号;
import okhttp3.FormBody;

public class NetWork_网盘_刷新 extends NetWork_Main {
    public NetWork_网盘_刷新(Activity activity) {
        super(activity);
        formBody = new FormBody.Builder()
                .add("Read_PassWord", able.Read_PassWord)
                .add("Account", Fun_账号.GetID())
                .build();
        url = able.URL_Name;
        url_path = "federal-square/Read_The_Disk_List.php";
        b_mess = true;
    }

    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("no")){
            this.b_update = true;
            Fun_文件.删除文件夹(new File(able.app_path + "Disk_Data"));
            Fun_文件.创建文件夹(able.app_path + "Disk_Data");
            Fun.mess(activity, "没有数据");
            return;
        }
        String[] dd = string.split("\n");
        for(String da : dd){
            Fun_文件.写入文件(able.app_path + "Disk_Data/" + da, "");
            this.b_update = true;
        }

    }

    @Override
    public void 刷新() {
        super.刷新();
        DiskActivity diskActivity = (DiskActivity)this.activity;
        diskActivity.初始化数据();
    }

}
