package com.example.lianxi_zhoukao1_216.shopmvp;

import com.example.lianxi_zhoukao1_216.entity.ShopEntity;


public interface ShopView {
    void  successful(ShopEntity data);
    void  failed(Exception e);

}
