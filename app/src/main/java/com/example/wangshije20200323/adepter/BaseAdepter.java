package com.bawei.myapplication.adepter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.myapplication.R;
import com.bawei.myapplication.bean.ShopBen;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Time:2020/3/23
 * Author:40644
 * Name :贾涵
 * Description:
 */
public class BaseAdepter extends BaseAdapter {
    List<ShopBen.DataBean>list;

    public BaseAdepter(List<ShopBen.DataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item,parent,false);
            holder = new ViewHolder();
            holder.mName = convertView.findViewById(R.id.name);
            holder.mPrice = convertView.findViewById(R.id.price);
            holder.mIv = convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ShopBen.DataBean dataBean = list.get(position);
        String goods_name = dataBean.getGoods_name();
        holder.mName.setText(goods_name);
        String price = dataBean.getCurrency_price();
        holder.mPrice.setText(price);
        String goods_thumb = dataBean.getGoods_thumb();
        Picasso.get().load(goods_thumb).into(holder.mIv);
        return convertView;
    }
    class ViewHolder{
        TextView mName,mPrice;
        ImageView mIv;
    }
}
