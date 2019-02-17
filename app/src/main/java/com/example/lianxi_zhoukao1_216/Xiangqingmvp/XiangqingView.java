package com.example.lianxi_zhoukao1_216.Xiangqingmvp;

import com.example.lianxi_zhoukao1_216.entity.XiangqingEntity;

public interface XiangqingView {
    void successful(XiangqingEntity data);
    void failed(Exception e);
}
