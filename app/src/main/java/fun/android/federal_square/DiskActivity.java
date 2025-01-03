package fun.android.federal_square;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.bumptech.glide.Glide;
import java.io.FileNotFoundException;
import java.util.List;
import fun.android.federal_square.adatper.Disk_Grid_Adapter;
import fun.android.federal_square.data.able;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_图片;
import fun.android.federal_square.fun.Fun_文件;
import fun.android.federal_square.network.NetWork_网盘_上传视频;
import fun.android.federal_square.window.查看图片窗口;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.network.NetWork_网盘_上传图片;
import fun.android.federal_square.network.NetWork_网盘_刷新;
import fun.android.federal_square.window.删除窗口;
import fun.android.federal_square.window.查看视频窗口;
import fun.android.federal_square.window.网盘设置窗口;

public class DiskActivity extends AppCompatActivity {
    ActivityResultLauncher<PickVisualMediaRequest> 上传图片;
    public SwipeRefreshLayout swiperefre;
    public GridView gridView;
    private AppCompatButton button_network_disk, button_menu;
    private NetWork_网盘_上传图片 netWork_网盘_上传图片;
    private TextView title_index;
    private ImageView return_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(Color.WHITE);
        window.setNavigationBarColor(Color.WHITE);
        setContentView(R.layout.activity_disk);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        初始化();
        事件();
    }

    public void 初始化(){
        button_network_disk = findViewById(R.id.button_network_disk);
        button_menu = findViewById(R.id.button_menu);
        gridView = findViewById(R.id.gridview);
        swiperefre = findViewById(R.id.swiperefee);
        return_icon = findViewById(R.id.return_icon);
        title_index = findViewById(R.id.title_index);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        netWork_网盘_上传图片 = new NetWork_网盘_上传图片(this);
        加载图片初始化(Fun_账号.GetID());
        初始化数据();

    }

    public void 事件(){
        swiperefre.setOnRefreshListener(()->{
            NetWork_网盘_刷新 netWork_网盘_刷新 = new NetWork_网盘_刷新(this);
            netWork_网盘_刷新.start();
            swiperefre.setRefreshing(false);
        });
        button_network_disk.setOnClickListener(V->{
            button_network_disk.setEnabled(false);
            上传图片.launch( new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageAndVideo.INSTANCE).build());
        });
        button_menu.setOnClickListener(V->{
            网盘设置窗口.启动(this);
        });

        return_icon.setOnClickListener(V->{
            finish();
        });
    }

    public void 加载图片初始化(String account_id){

        上传图片 = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            if(uri == null){
                button_network_disk.setEnabled(true);
                return;
            }
            if(Fun.获取Uri文件大小(DiskActivity.this, uri) > 5242880){
                Fun.mess(DiskActivity.this, "文件大于 5 MB 请压缩后上传");
                button_network_disk.setEnabled(true);
                return;
            }
            String 后缀 = Fun.获取文件扩展名(Fun.获取Uri文件名(this, uri));

            if(Fun.图片格式判断(后缀)){
                Fun_文件.copy_Uri_File(this, uri, able.app_path + "/cache/cache." + 后缀);
                netWork_网盘_上传图片.传递参数(后缀, account_id, DiskActivity.this, button_network_disk);
                netWork_网盘_上传图片.start();
                return;
            }
            if(Fun.视频格式判断(后缀)){
                Fun_文件.copy_Uri_File(this, uri, able.app_path + "/cache/cache." + 后缀);
                NetWork_网盘_上传视频 netWork_网盘_上传视频 = new NetWork_网盘_上传视频(this);
                netWork_网盘_上传视频.传递参数(后缀, account_id, DiskActivity.this, button_network_disk);
                netWork_网盘_上传视频.start();
                return;
            }

            Fun.mess(this, "不支持的格式 " + 后缀);
        });

    }

    public void 初始化数据(){
        int Disk_Index = 3;
        String Str_index = Fun_文件.读取文件(able.app_path + "System_Data/Disk_index.txt");
        if(!Str_index.isEmpty()){
            Disk_Index = Integer.parseInt(Str_index);
        }
        gridView.setNumColumns(Disk_Index);
        this.button_network_disk.setEnabled(true);
        List<String> file_list = Fun_图片.遍历所有图片();
        title_index.setText("统计数量： " + file_list.size());
        gridView.setAdapter(new Disk_Grid_Adapter(DiskActivity.this, file_list, Disk_Index));
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            String 后缀 = Fun_文件.获取后缀(file_list.get(position));
            String url = able.URL + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + file_list.get(position);
            if(后缀.equals("jpg") | 后缀.equals("jpeg") | 后缀.equals("png") | 后缀.equals("webp")){
                查看图片窗口.启动_Dialog(this, url);
            }
            if(后缀.equals("mp4") | 后缀.equals("3gp") | 后缀.equals("mov") | 后缀.equals("avi") | 后缀.equals("mkv") | 后缀.equals("flv") | 后缀.equals("rmvb")){
                查看视频窗口.启动_Dialog(this, url);
            }
        });
        gridView.setOnItemLongClickListener((parent, view, position, id) -> {
            删除窗口.删除网盘图片窗口(DiskActivity.this, file_list.get(position));
            return true;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }
}
