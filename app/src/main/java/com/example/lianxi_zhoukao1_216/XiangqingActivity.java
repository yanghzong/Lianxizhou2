package com.example.lianxi_zhoukao1_216;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lianxi_zhoukao1_216.Xiangqingmvp.XiangqingPresenter;
import com.example.lianxi_zhoukao1_216.Xiangqingmvp.XiangqingView;
import com.example.lianxi_zhoukao1_216.adapter.XiangqingAdapter;
import com.example.lianxi_zhoukao1_216.entity.XiangqingEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class XiangqingActivity extends AppCompatActivity implements XiangqingView {

    @BindView(R.id.btn_shop)
    TextView btnShop;
    @BindView(R.id.btn_xiangqing)
    TextView btnXiangqing;
    @BindView(R.id.btn_pinlun)
    TextView btnPinlun;
    @BindView(R.id.ly_shang)
    LinearLayout lyShang;
    @BindView(R.id.product_show)
    RecyclerView productShow;
    @BindView(R.id.wb_show)
    WebView wbShow;
    private Unbinder bind;
    private XiangqingPresenter presenter;
    private XiangqingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        bind = ButterKnife.bind(this);

        //接收从intent传过来的数据
        Intent intent = getIntent();
        String s = intent.getStringExtra("cid");
        int cid = Integer.valueOf(s);
        //初始化p层
        presenter = new XiangqingPresenter();
        presenter.attach(this);
        presenter.getData(cid);
    }



    @Override
    public void successful(XiangqingEntity data) {
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        productShow.setLayoutManager(layoutManager);
        adapter=new XiangqingAdapter(XiangqingActivity.this,data.getResult());
        adapter.setXiangqingClickListener(new XiangqingAdapter.OnXiangqingClickListener() {
            @Override
            public void onXiangqingClick(String pic) {
                Intent intent=new Intent(XiangqingActivity.this,PhotoViewActivity.class);
                String s = String.valueOf(pic);
                intent.putExtra("pic",s);
                startActivity(intent);
            }




        });
        productShow.setAdapter(adapter);
        WebSettings settings = wbShow.getSettings();
        settings.setJavaScriptEnabled(true);//支持JS
        String js = "<script type=\"text/javascript\">"+
                "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                "imgs[i].style.width = '100%';" +  // 宽度改为100%
                "imgs[i].style.height = 'auto';" +
                "}"+
                "</script>";
        wbShow.loadData(data.getResult().getDetails()+js,"text/html; charset=UTF-8", null);
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
        bind.unbind();
    }
}
