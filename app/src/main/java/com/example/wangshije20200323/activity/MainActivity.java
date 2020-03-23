package com.example.wangshije20200323;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.myapplication.adepter.BaseAdepter;
import com.bawei.myapplication.base.BaseActivity;
import com.bawei.myapplication.base.DataCall;
import com.bawei.myapplication.bean.ShopBen;
import com.bawei.myapplication.presenter.Presenter;
import com.bawei.myapplication.util.Netutil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

public class MainActivity extends BaseActivity implements DataCall {
    PullToRefreshListView pull;
    int page=1;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        pull = findViewById(R.id.pull);
        Boolean wiFi = Netutil.getInstance().isWiFi(this);
        Boolean work = Netutil.getInstance().isWork(this);
        if (work){ Toast.makeText(this, "有网", Toast.LENGTH_SHORT).show(); }
        if (wiFi){ Toast.makeText(this, "wifi", Toast.LENGTH_SHORT).show(); }
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        Presenter presenter = new Presenter(this);
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                toJson(page);
                pull.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                toJson(page);
                pull.onRefreshComplete();
            }
        });
        toJson(page);
    }
    private void toJson(int page) {
        String path="http://blog.zhaoliang5156.cn/api/shop/shop"+page+".json";
        Netutil.getInstance().doString(path, new Netutil.IcallBack() {
            @Override
            public void Ok(String msg) {
                Gson gson = new Gson();
                ShopBen shopBen = gson.fromJson(msg, ShopBen.class);
                List<ShopBen.DataBean> data = shopBen.getData();
                Log.i("aaa",""+data);
                BaseAdepter adepter = new BaseAdepter(data);
                pull.setAdapter(adepter);
            }
            @Override
            public void No() {

            }
        });
    }

    @Override
    public void OngetSuccess(String msg) {
        Gson gson = new Gson();
        ShopBen shopBen = gson.fromJson(msg, ShopBen.class);
        List<ShopBen.DataBean> data = shopBen.getData();
        Log.i("aaa",""+data);
        BaseAdepter adepter = new BaseAdepter(data);
        pull.setAdapter(adepter);
    }

    @Override
    public void OngetFail(String msg) {

    }
}
