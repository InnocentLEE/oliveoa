package com.oliveoa.service;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.Goods;

/**
 * Created by Lee on 2018/5/31.
 */
public interface IGoodsService {
    ServerResponse add_goods(Goods goods);
    ServerResponse update_goods(Goods goods);
    ServerResponse delete_goods(String gid);
    ServerResponse get_goods();
    ServerResponse get_goods_by_gid(String gid);
}
