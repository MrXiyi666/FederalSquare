package fun.android.federal_square.system;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.ScrollView;

import fun.android.federal_square.view.Home_View;
import fun.android.federal_square.view.Popular_View;
import fun.android.federal_square.view.TimeLine_View;
import fun.android.federal_square.view.View_Main;
import okhttp3.OkHttpClient;

public class Static {
    public static String app_path="";
    public static String url="http://mrxiyi.top/federal-square-7-1/";
    public static String Example_Name="federal-square-7-1";

    public static OkHttpClient okHttpClient;


    public static ScrollView main;
    public static View_Main view_main;
    public static TimeLine_View timeLine_view ;
    public static Popular_View popular_view;
    public static Home_View home_view;
    public static int timeLine_view_y=0, popular_view_y=0;
    public static String[] 青春薄荷绿 = {
            "#E6FEF2",  // 整个软件背景 - 清新薄荷绿
            "#10B981",  // 底部菜单栏背景 - 活力薄荷绿
            "#F0FFFA",  // 文章背景 - 微薄荷白
            "#A7F3D0",  // 描边颜色 - 中薄荷绿
            "#065F46",  // 按钮按下 - 深薄荷绿
            "#34D399",  // 按钮弹起 - 亮薄荷绿
            "#F3F4F6"   // 输入框背景 - 浅灰
    };
    public static String[] 活力天空蓝 = {
            "#E0F2FE",  // 整个软件背景 - 明亮天蓝
            "#0EA5E9",  // 底部菜单栏背景 - 天空蓝
            "#F8FBFF",  // 文章背景 - 微天蓝白
            "#7DD3FC",  // 描边颜色 - 中蓝
            "#0369A1",  // 按钮按下 - 深海蓝
            "#38BDF8",  // 按钮弹起 - 亮天蓝
            "#F3F4F6"   // 输入框背景 - 浅灰
    };
    public static String[] 甜美樱花粉 = {
            "#FFF1F7",  // 整个软件背景 - 柔美樱花粉
            "#EC4899",  // 底部菜单栏背景 - 樱花粉
            "#FFF9FB",  // 文章背景 - 微樱花白
            "#FBCFE8",  // 描边颜色 - 中粉
            "#9D174D",  // 按钮按下 - 深粉
            "#F472B6",  // 按钮弹起 - 亮粉
            "#F3F4F6"   // 输入框背景 - 浅灰
    };
    public static String[] 阳光柑橘橙 = {
            "#FFF7ED",  // 整个软件背景 - 温暖柑橘橙
            "#F97316",  // 底部菜单栏背景 - 活力橙
            "#FFFBF8",  // 文章背景 - 微橙白
            "#FED7AA",  // 描边颜色 - 中橙
            "#C2410C",  // 按钮按下 - 深橙
            "#FB923C",  // 按钮弹起 - 亮橙
            "#F3F4F6"   // 输入框背景 - 浅灰
    };
    public static String[] 梦幻薰衣草紫 = {
            "#FAF5FF",  // 整个软件背景 - 梦幻薰衣草紫
            "#8B5CF6",  // 底部菜单栏背景 - 薰衣草紫
            "#FCFAFF",  // 文章背景 - 微薰衣草白
            "#E9D5FF",  // 描边颜色 - 中紫
            "#7C3AED",  // 按钮按下 - 深紫
            "#A78BFA",  // 按钮弹起 - 亮紫
            "#F3F4F6"   // 输入框背景 - 浅灰
    };
    public static String 当前主题 = "青春薄荷绿";
    public static GradientDrawable up_normalDrawable = new GradientDrawable();
    public static GradientDrawable down_pressedDrawable = new GradientDrawable();
    public static String drawable_color, menu_color, article_color, child_color, button_down_color, button_up_color, button_stroke_color;
    public static void create(Context context){
        switch (Static.当前主题){
            case "青春薄荷绿":
                drawable_color = Static.青春薄荷绿[0];
                menu_color = Static.青春薄荷绿[1];
                article_color = Static.青春薄荷绿[2];
                button_down_color = Static.青春薄荷绿[4];
                button_up_color = Static.青春薄荷绿[5];
                button_stroke_color = Static.青春薄荷绿[3];
                break;
            case "活力天空蓝":
                drawable_color = Static.活力天空蓝[0];
                menu_color = Static.活力天空蓝[1];
                article_color = Static.活力天空蓝[2];
                button_down_color = Static.活力天空蓝[4];
                button_up_color = Static.活力天空蓝[5];
                button_stroke_color = Static.活力天空蓝[3];
                break;
            case "甜美樱花粉":
                drawable_color = Static.甜美樱花粉[0];
                menu_color = Static.甜美樱花粉[1];
                article_color = Static.甜美樱花粉[2];
                button_down_color = Static.甜美樱花粉[4];
                button_up_color = Static.甜美樱花粉[5];
                button_stroke_color = Static.甜美樱花粉[3];
                break;
            case "阳光柑橘橙":
                drawable_color = Static.阳光柑橘橙[0];
                menu_color = Static.阳光柑橘橙[1];
                article_color = Static.阳光柑橘橙[2];
                button_down_color = Static.阳光柑橘橙[4];
                button_up_color = Static.阳光柑橘橙[5];
                button_stroke_color = Static.阳光柑橘橙[3];
            case "梦幻薰衣草紫":
                drawable_color = Static.梦幻薰衣草紫[0];
                menu_color = Static.梦幻薰衣草紫[1];
                article_color = Static.梦幻薰衣草紫[2];
                button_stroke_color = Static.梦幻薰衣草紫[3];
                button_down_color = Static.梦幻薰衣草紫[4];
                button_up_color = Static.梦幻薰衣草紫[5];

                break;
            default:
                drawable_color="#ffffff";
                menu_color = "#ffffff";
                article_color="#f2f3f7";
                button_down_color = "#808080";
                button_up_color = "#f2f3f7";
                button_stroke_color="#ffffff";
        }
        float radius = Fun.DPToPX(context, 8);
        up_normalDrawable.setCornerRadii(new float[]{
                radius, radius,
                radius, radius,
                radius, radius,
                radius, radius
        });
        up_normalDrawable.setColor(Color.parseColor(button_up_color));
        up_normalDrawable.setStroke(
                Fun.DPToPX(context, 2),
                Color.parseColor(button_stroke_color)
        );
        down_pressedDrawable.setCornerRadii(new float[]{
                radius, radius,
                radius, radius,
                radius, radius,
                radius, radius
        });
        down_pressedDrawable.setColor(Color.parseColor(button_down_color));
        down_pressedDrawable.setStroke(
                Fun.DPToPX(context, 2),
                Color.parseColor(button_stroke_color)
        );
    }
}