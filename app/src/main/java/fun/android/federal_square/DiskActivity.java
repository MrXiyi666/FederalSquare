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
import fun.android.federal_square.window.查看图片窗口;
import fun.android.federal_square.fun.Fun_账号;
import fun.android.federal_square.network.NetWork_网盘_上传;
import fun.android.federal_square.network.NetWork_网盘_刷新;
import fun.android.federal_square.window.删除窗口;

public class DiskActivity extends AppCompatActivity {
    ActivityResultLauncher<PickVisualMediaRequest> 上传图片;
    public SwipeRefreshLayout swiperefre;
    public GridView gridView;
    private AppCompatButton button_network_disk;
    private NetWork_网盘_上传 netWork_网盘_上传;
    ImageView return_icon;
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
        gridView = findViewById(R.id.gridview);
        swiperefre = findViewById(R.id.swiperefee);
        return_icon = findViewById(R.id.return_icon);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        netWork_网盘_上传 = new NetWork_网盘_上传(this);
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
            上传图片.launch( new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build());
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
            if(Fun.获取Uri文件大小(DiskActivity.this, uri) > 10485760){
                Fun.mess(DiskActivity.this, "图片大于10mb");
                button_network_disk.setEnabled(true);
                return;
            }

            String 后缀 = Fun.获取文件扩展名(Fun.获取Uri文件名(this, uri));
            Bitmap bitmap;
            if(后缀.equals("jpg") | 后缀.equals("jpeg") | 后缀.equals("png") | 后缀.equals("webp")){

            }else{
                后缀 = "";
            }
            if(后缀.isEmpty()){
                button_network_disk.setEnabled(true);
                return;
            }
            try {
                bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
                Fun_图片.保存缓存图片(bitmap, 后缀);
                netWork_网盘_上传.传递参数(后缀, account_id, DiskActivity.this, button_network_disk);
                netWork_网盘_上传.start();
            } catch (FileNotFoundException e) {
                Log.w("加载图片初始化", e);
                Fun.mess(DiskActivity.this, "加载失败");
                button_network_disk.setEnabled(true);
            }

        });

    }

    public void 初始化数据(){
        this.button_network_disk.setEnabled(true);
        List<String> file_list = Fun_图片.遍历所有图片不带域名();
        gridView.setAdapter(new Disk_Grid_Adapter(DiskActivity.this, file_list));
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            查看图片窗口.启动(DiskActivity.this, able.URL_Name + "federal-square/Account/" + Fun_账号.GetID() + "/Image_Resources/" + file_list.get(position));
        });
        gridView.setOnItemLongClickListener((parent, view, position, id) -> {
            删除窗口.删除网盘图片窗口(DiskActivity.this, file_list.get(position));
            return true;
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(查看图片窗口.photoView != null && 查看图片窗口.photoView.getVisibility() == View.VISIBLE){
                查看图片窗口.photoView.setVisibility(View.GONE);
                return false;
            }

        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }
}
