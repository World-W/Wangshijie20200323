package com.bawei.myapplication.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Time:2020/3/23
 * Author:40644
 * Name :贾涵
 * Description:
 */
public class Netutil {
    public static Netutil netutil = new Netutil();

    public static Netutil getInstance(){

        return netutil;
    }

    public Netutil() {

    }
    public Boolean isWork(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info!=null){
            return true;
        }
        return false;
    }
    public Boolean isWiFi(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info!=null&&info.getType()==ConnectivityManager.TYPE_WIFI){
            return true;
        }
        return false;
    }
    public void doString(final String str, final IcallBack icall){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(str);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(7000);
                    int code = httpURLConnection.getResponseCode();
                    if (code==200){
                        Log.i("aaa",""+code);
                        InputStream inputStream = httpURLConnection.getInputStream();
                        final String json = is2toString(inputStream);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                icall.Ok(json);
                        Log.i("aaa","回传数据成功");
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String is2toString(InputStream inputStream) throws Exception {
        int len=0;
        byte[] bytes = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while ((len=inputStream.read(bytes))!=-1){
            String s = new String(bytes, 0, len);
            sb.append(s);
        }
        String json = sb.toString();
        return json;
    }
    Handler handler = new Handler();
    public interface IcallBack{
        void Ok(String msg);
        void No();
    }
}
