package fun.android.federal_square.fun;

import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

import fun.android.federal_square.data.Post_Data;
import fun.android.federal_square.data.able;

public class Fun_账号 {
    public static List<Post_Data> 读取账号(){
        String txt = Fun_文件.读取文件(able.app_path + "Account/account.json");
        if(txt.isEmpty()){
            return new ArrayList<>();
        }
        return able.gson.fromJson(Fun_文件.读取文件(able.app_path + "Account/account.json"), new TypeToken<List<Post_Data>>(){}.getType());
    }

    public static String 读取账号_String(){
        String txt = Fun_文件.读取文件(able.app_path + "Account/account.json");
        if(txt.isEmpty()){
            return "";
        }
        return txt;
    }

    public static List<Post_Data> 重新生成(String ID, String PassWord, String Name, String Sign, String Avatar_Url, String Back_Url, String 发贴开关, String 评论开关){
        if(ID.isEmpty() | PassWord.isEmpty() | 发贴开关.isEmpty() | 评论开关.isEmpty()){
            return new ArrayList<>();
        }
        List<Post_Data> post_dataList = new ArrayList<>();
        Post_Data post_data_ID = new Post_Data();
        post_data_ID.setName("ID");
        post_data_ID.setText(ID);
        Post_Data post_data_PassWord = new Post_Data();
        post_data_PassWord.setName("PassWord");
        post_data_PassWord.setText(PassWord);
        Post_Data post_data_Name = new Post_Data();
        post_data_Name.setName("Name");
        post_data_Name.setText(Name);
        Post_Data post_data_Sign = new Post_Data();
        post_data_Sign.setName("Sign");
        post_data_Sign.setText(Sign);
        Post_Data post_data_Avatar_Url = new Post_Data();
        post_data_Avatar_Url.setName("Avatar_Url");
        post_data_Avatar_Url.setText(Avatar_Url);
        Post_Data post_data_Back_Url = new Post_Data();
        post_data_Back_Url.setName("Back_Url");
        post_data_Back_Url.setText(Back_Url);
        Post_Data post_data_发贴开关 = new Post_Data();
        post_data_发贴开关.setName("发贴开关");
        post_data_发贴开关.setText(发贴开关);
        Post_Data post_data_评论开关 = new Post_Data();
        post_data_评论开关.setName("评论开关");
        post_data_评论开关.setText(评论开关);
        post_dataList.add(post_data_ID);
        post_dataList.add(post_data_PassWord);
        post_dataList.add(post_data_Name);
        post_dataList.add(post_data_Sign);
        post_dataList.add(post_data_Avatar_Url);
        post_dataList.add(post_data_Back_Url);
        post_dataList.add(post_data_发贴开关);
        post_dataList.add(post_data_评论开关);
        return post_dataList;
    }

    public static boolean 保存账号(List<Post_Data> post_dataList){
        return Fun_文件.写入文件(able.app_path + "Account/account.json", able.gson.toJson(post_dataList));
    }

    public static String GetID(){
        List<Post_Data> post_dataList = able.gson.fromJson(Fun_文件.读取文件(able.app_path + "Account/account.json"), new TypeToken<List<Post_Data>>(){}.getType());
        if(post_dataList == null){
            return "";
        }
        if(post_dataList.isEmpty()){
            return "";
        }
        for(Post_Data pd : post_dataList){
            if(pd.getName().equals("ID")){
                return pd.getText();
            }
        }
        return "";
    }

    public static String GetPassWord(){
        List<Post_Data> post_dataList = able.gson.fromJson(Fun_文件.读取文件(able.app_path + "Account/account.json"), new TypeToken<List<Post_Data>>(){}.getType());
        if(post_dataList == null){
            return "";
        }
        if(post_dataList.isEmpty()){
            return "";
        }
        for(Post_Data pd : post_dataList){
            if(pd.getName().equals("PassWord")){
                return pd.getText();
            }
        }
        return "";
    }
    public static String GetName(){
        List<Post_Data> post_dataList = able.gson.fromJson(Fun_文件.读取文件(able.app_path + "Account/account.json"), new TypeToken<List<Post_Data>>(){}.getType());
        if(post_dataList == null){
            return "";
        }
        if(post_dataList.isEmpty()){
            return "";
        }
        for(Post_Data pd : post_dataList){
            if(pd.getName().equals("Name")){
                return pd.getText();
            }
        }
        return "";
    }
    public static String GetSign(){
        List<Post_Data> post_dataList = able.gson.fromJson(Fun_文件.读取文件(able.app_path + "Account/account.json"), new TypeToken<List<Post_Data>>(){}.getType());
        if(post_dataList == null){
            return "";
        }
        if(post_dataList.isEmpty()){
            return "";
        }
        for(Post_Data pd : post_dataList){
            if(pd.getName().equals("Sign")){
                return pd.getText();
            }
        }
        return "";
    }

    public static String GetAvatar_Url(){
        List<Post_Data> post_dataList = able.gson.fromJson(Fun_文件.读取文件(able.app_path + "Account/account.json"), new TypeToken<List<Post_Data>>(){}.getType());
        if(post_dataList == null){
            return "";
        }
        if(post_dataList.isEmpty()){
            return "";
        }
        for(Post_Data pd : post_dataList){
            if(pd.getName().equals("Avatar_Url")){
                return pd.getText();
            }
        }
        return "";
    }

    public static String GetBack_Url(){
        List<Post_Data> post_dataList = able.gson.fromJson(Fun_文件.读取文件(able.app_path + "Account/account.json"), new TypeToken<List<Post_Data>>(){}.getType());
        if(post_dataList == null){
            return "";
        }
        if(post_dataList.isEmpty()){
            return "";
        }
        for(Post_Data pd : post_dataList){
            if(pd.getName().equals("Back_Url")){
                return pd.getText();
            }
        }
        return "";
    }

    public static String Get发贴开关(){
        List<Post_Data> post_dataList = able.gson.fromJson(Fun_文件.读取文件(able.app_path + "Account/account.json"), new TypeToken<List<Post_Data>>(){}.getType());
        if(post_dataList == null){
            return "";
        }
        if(post_dataList.isEmpty()){
            return "";
        }
        for(Post_Data pd : post_dataList){
            if(pd.getName().equals("发贴开关")){
                return pd.getText();
            }
        }
        return "";
    }

    public static String Get评论开关(){
        List<Post_Data> post_dataList = able.gson.fromJson(Fun_文件.读取文件(able.app_path + "Account/account.json"), new TypeToken<List<Post_Data>>(){}.getType());
        if(post_dataList == null){
            return "";
        }
        if(post_dataList.isEmpty()){
            return "";
        }
        for(Post_Data pd : post_dataList){
            if(pd.getName().equals("评论开关")){
                return pd.getText();
            }
        }
        return "";
    }

}
