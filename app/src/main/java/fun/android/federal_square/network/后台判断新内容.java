package fun.android.federal_square.network;

import android.app.Activity;
import fun.android.federal_square.fun.Fun;

public class 后台判断新内容 {
    private boolean 逻辑 = true;
    public 后台判断新内容(Activity activity){
        new Thread(()->{
            var fun_多少秒获取广场数据 = new NetWork_多少秒获取广场数据(activity);
            while (逻辑){
                fun_多少秒获取广场数据.start();
                try {
                    Thread.sleep(Fun.获取广场计时数量());
                }catch (Exception ignored){
                }
            }
        }).start();
    }

    public void close(){
        逻辑 = false;
    }
}