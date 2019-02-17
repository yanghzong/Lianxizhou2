package com.example.lianxi_zhoukao1_216.shopmvp;

import com.example.lianxi_zhoukao1_216.inter.ApiUrl;
import com.example.lianxi_zhoukao1_216.utils.HttpUtils;
import com.example.lianxi_zhoukao1_216.utils.ICallBack;

import java.lang.reflect.Type;

public class ShopModel {
    public  void  getShop(String keyword, int page, int count, ICallBack callBack, Type type){
        String url= ApiUrl.ShowShopUrl +"?keyword="+keyword+"&page="+page+"&count="+count;
        HttpUtils.getInstance().get(url,callBack,type);
    }
}
