package com.example.lianxi_zhoukao1_216.Xiangqingmvp;

import com.example.lianxi_zhoukao1_216.inter.ApiUrl;
import com.example.lianxi_zhoukao1_216.utils.HttpUtils;
import com.example.lianxi_zhoukao1_216.utils.ICallBack;

import java.lang.reflect.Type;

public class XiangqingModel {
    public  void  getXiangqing(int commodityId, final ICallBack callBack, final Type type){
        String url= ApiUrl.XiangqingUrl+"?commodityId="+commodityId;
        HttpUtils.getInstance().get(url,callBack,type);
    }
}
