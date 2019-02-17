package com.example.lianxi_zhoukao1_216.Xiangqingmvp;

import com.example.lianxi_zhoukao1_216.entity.XiangqingEntity;
import com.example.lianxi_zhoukao1_216.utils.ICallBack;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class XiangqingPresenter {
    private XiangqingModel xiangqingModel;
    private XiangqingView xiangqingView;

    public  void attach(XiangqingView xiangqingView){
        this.xiangqingView=xiangqingView;
        xiangqingModel=new XiangqingModel();
    }
    public void getData(int commodityId){
        Type type=new TypeToken<XiangqingEntity>(){}.getType();
        xiangqingModel.getXiangqing(commodityId, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                XiangqingEntity data= (XiangqingEntity) obj;
                if (data!=null & "0000".equals(data.getStatus())){
                    xiangqingView.successful(data);
                }
            }

            @Override
            public void onFailed(Exception e) {
                 xiangqingView.failed(e);
            }



        },type);

    }
    public void detach(){
        if (xiangqingView!=null){
            xiangqingView=null;
        }
    }

}
