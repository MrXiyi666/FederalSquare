package fun.android.federal_square.network;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;

import androidx.appcompat.widget.AppCompatButton;

import java.io.File;

import fun.android.federal_square.DiskActivity;
import fun.android.federal_square.data.ProgressRequestBody;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_文件;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class NetWork_网盘_上传视频 extends NetWork_Main_MultipartBody{
    public NetWork_网盘_上传视频(Activity activity) {
        super(activity);
    }
    private String 后缀;
    private DiskActivity diskActivity;
    private AppCompatButton button_network_disk;
    private String file_name;
    @SuppressLint("SimpleDateFormat")
    public void 传递参数(String 后缀, String account_id, DiskActivity diskActivity, AppCompatButton button_network_disk){
        this.后缀 = 后缀;
        this.diskActivity = diskActivity;
        this.button_network_disk = button_network_disk;
        url = able.URL + "federal-square/Upload_Image.php";
        this.body = RequestBody.Companion.create(new File(able.app_path + "/cache/cache." + 后缀), MediaType.Companion.parse("video/" + 后缀));
        file_name = Fun.获取时间() + "." + 后缀;
        multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("PassWord", able.PassWord)
                .addFormDataPart("file", file_name, body)
                .addFormDataPart("Account", account_id)
                .build();
        requestBody = new ProgressRequestBody(
                multipartBody,
                new ProgressRequestBody.ProgressListener() {
                    @Override
                    public void onProgress(long bytesWritten, long contentLength, boolean done) {
                        // 更新进度条
                        int progress = (int) ((bytesWritten * 100) / contentLength);
                        _上传进度窗口.提交进度(progress, done);
                    }
                });
    }
    @Override
    public void 事件(String string) {
        super.事件(string);
        if(string.equals("no_up")){
            Fun.mess(activity, "上传失败 数据出错", 300);
            return;
        }
        if(string.equals("no_file")){
            Fun.mess(activity, "上传失败 存储失败", 300);
            return;
        }
        if(!string.equals("ok")){
            Fun.mess(activity, string);
            return;
        }
        Fun.mess(activity, "上传成功", 2000);
        this.b_update = true;
        Fun_文件.写入文件(able.app_path + "Disk_Data/" + file_name, "");
        Fun_文件.删除文件(able.app_path + "/cache/cache." + 后缀);
    }

    @Override
    public void 刷新() {
        super.刷新();
        if(diskActivity!=null){
            diskActivity.初始化数据();
        }
        if(button_network_disk!=null){
            button_network_disk.setEnabled(true);
        }
    }

    @Override
    public void 失败() {
        super.失败();
        if(button_network_disk!=null){
            button_network_disk.setEnabled(true);
        }
        Fun.mess(activity, "上传失败", 300);
    }
}
