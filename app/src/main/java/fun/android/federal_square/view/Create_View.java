package fun.android.federal_square.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatButton;

import fun.android.federal_square.NetWork.NetWork_UpLoad;
import fun.android.federal_square.R;
import fun.android.federal_square.system.Fun;
import fun.android.federal_square.system.Fun_文件;
import fun.android.federal_square.system.Static;

public class Create_View extends View_Main{
    public Create_View(Context context) {
        super(context);
        view = View.inflate(context, R.layout.create_view, null);
        linear = view.findViewById(R.id.linear);
        AppCompatButton button_ok = view.findViewById(R.id.button_ok);
        EditText edit_view_1 = view.findViewById(R.id.edit_view_1);
        EditText edit_view_2 = view.findViewById(R.id.edit_view_2);


        Fun.setButtonTheme(context, button_ok);
        button_ok.setOnClickListener(V->{
            Static.上传图片.launch( new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageAndVideo.INSTANCE).build());
            //Fun_文件.创建文件夹(Static.app_path + "Example_Address");
        });
        linear.addView(new View(context), 0, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Fun.获取状态栏高度(context)));
        Fun.setEditTextTheme(context, edit_view_1);
        Fun.setEditTextTheme(context, edit_view_2);





    }
}
