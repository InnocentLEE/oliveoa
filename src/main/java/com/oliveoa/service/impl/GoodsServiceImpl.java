package com.oliveoa.service.impl;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.dao.GoodsMapper;
import com.oliveoa.pojo.Goods;
import com.oliveoa.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lee on 2018/5/31.
 */
@Service("iGoodsService")
public class GoodsServiceImpl implements IGoodsService{

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public ServerResponse add_goods(Goods goods){
        int result = goodsMapper.insert(goods);
        if(result>0)
            return ServerResponse.createBySuccessMessage("录入物品成功");
        else
            return ServerResponse.createByErrorMessage("录入物品失败");
    }

    @Override
    public ServerResponse update_goods(Goods goods){
        int result = goodsMapper.updateByPrimaryKeySelective(goods);
        if(result>0)
            return ServerResponse.createBySuccessMessage("更新物品信息成功");
        else
            return ServerResponse.createByErrorMessage("更新物品信息失败");
    }

    @Override
    public ServerResponse delete_goods(String gid){
        int total = goodsMapper.selectTotal(gid);
        int remaining = goodsMapper.selectRemaining(gid);
        if(total!=remaining)
            return ServerResponse.createByErrorMessage("该物品正在被借用，无法删除");
        int result = goodsMapper.deleteByPrimaryKey(gid);
        if(result>0)
            return ServerResponse.createBySuccessMessage("删除物品成功");
        else
            return ServerResponse.createByErrorMessage("删除物品失败");
    }

    @Override
    public ServerResponse get_goods(){
        List<Goods> goodsList = goodsMapper.select();
        return ServerResponse.createBySuccess("查找物品成功",goodsList);
    }

    public ServerResponse get_goods_by_gid(String gid){
        Goods goods = goodsMapper.selectByPrimaryKey(gid);
        return ServerResponse.createBySuccess("查找物品成功",goods);
    }
}
