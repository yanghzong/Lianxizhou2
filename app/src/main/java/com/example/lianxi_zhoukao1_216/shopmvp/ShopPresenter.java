package com.example.lianxi_zhoukao1_216.shopmvp;

import com.example.lianxi_zhoukao1_216.entity.ShopEntity;
import com.example.lianxi_zhoukao1_216.utils.ICallBack;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ShopPresenter {
    private ShopModel shopModel;
    private ShopView shopView;

    public  void  attach(ShopView shopView){
        this.shopView=shopView;
        shopModel=new ShopModel();

    }

    public void getData(String keyword,int page,int count){
        Type type=new TypeToken<ShopEntity>(){}.getType();
        shopModel.getShop(keyword, page, count, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                ShopEntity data= (ShopEntity) obj;
                if (data!=null){
                    shopView.successful(data);
                }
            }

            @Override
            public void onFailed(Exception e) {
                    shopView.failed(e);
            }


        },type);
    }
    public void detach(){
        if (shopView!=null){
            shopView=null;
        }
    }
}
