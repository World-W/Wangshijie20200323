package com.bawei.myapplication.presenter;

import com.bawei.myapplication.base.DataCall;
import com.bawei.myapplication.bean.ShopBen;
import com.bawei.myapplication.model.Model;

/**
 * Time:2020/3/23
 * Author:40644
 * Name :贾涵
 * Description:
 */
public class Presenter {
   public DataCall dataCall;

    public Presenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }
    public ShopBen.DataBean requeest(Object...agrs){

        return Model.dataBean((int)agrs[0]);
    }
}
