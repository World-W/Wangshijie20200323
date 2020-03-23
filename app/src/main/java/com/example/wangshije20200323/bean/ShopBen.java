package com.bawei.myapplication.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Time:2020/3/23
 * Author:40644
 * Name :贾涵
 * Description:
 */
public class ShopBen {

    private List<DataBean> data;

    public static ShopBen objectFromData(String str) {

        return new Gson().fromJson(str, ShopBen.class);
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String currency_price;
        private String goods_name;
        private String goods_thumb;
        private String goods_english_name;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public String getCurrency_price() {
            return currency_price;
        }

        public void setCurrency_price(String currency_price) {
            this.currency_price = currency_price;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_thumb() {
            return goods_thumb;
        }

        public void setGoods_thumb(String goods_thumb) {
            this.goods_thumb = goods_thumb;
        }

        public String getGoods_english_name() {
            return goods_english_name;
        }

        public void setGoods_english_name(String goods_english_name) {
            this.goods_english_name = goods_english_name;
        }
    }
}
