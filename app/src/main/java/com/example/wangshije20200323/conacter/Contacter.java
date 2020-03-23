package com.bawei.myapplication.conacter;

/**
 * Time:2020/3/23
 * Author:40644
 * Name :贾涵
 * Description:
 */
public interface Contacter {
    interface IView{
        void onGetSuccess(String msg);
        void onGetFail(String msg);
    }
    interface Ipresenter{
        void onGetList(String msg);
    }
    interface IModel{
        void onGetlist(String msg,Back back);
        interface Back{
            void Ok(String msg);
            void No(String msg);
        }
    }
}
