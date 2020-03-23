package com.bawei.myapplication.model;

import com.bawei.myapplication.bean.ShopBen;
import com.bawei.myapplication.util.Netutil;
import com.google.gson.Gson;

/**
 * Time:2020/3/23
 * Author:40644
 * Name :贾涵
 * Description:
 */
public class Model {
    public static ShopBen.DataBean dataBean(int page){
        Netutil.getInstance().doString("http://blog.zhaoliang5156.cn/api/shop/shop"+page+".json", new Netutil.IcallBack() {
            @Override
            public void Ok(String msg) {
                Gson gson = new Gson();
                ShopBen shopBen = gson.fromJson(msg, ShopBen.class);
            }

            @Override
            public void No() {

            }
        });
        return null;
    }
}
