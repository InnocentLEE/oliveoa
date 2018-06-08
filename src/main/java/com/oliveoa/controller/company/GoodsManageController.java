package com.oliveoa.controller.company;

import com.oliveoa.common.Const;
import com.oliveoa.common.ResponseCode;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.CompanyInfo;
import com.oliveoa.pojo.Goods;
import com.oliveoa.service.IGoodsService;
import com.oliveoa.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Lee on 2018/5/31.
 */
@Controller
@RequestMapping("/manage/goods/")
public class GoodsManageController {

    @Autowired
    private IGoodsService iGoodsService;

    @RequestMapping(value = "add_goods.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add_goods(HttpSession session,String name,String describe,String total,String pcid) {
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        if(name.equals("")||describe.equals("")||total.equals("")||pcid.equals(""))
            return ServerResponse.createByErrorMessage("请填写完整的信息");
        int total1 = Integer.parseInt(total);
        int remaining = total1;
        Goods goods = new Goods();
        goods.setGid(CommonUtils.uuid());
        goods.setName(name);
        goods.setDescribe(describe);
        goods.setTotal(total1);
        goods.setRemaining(remaining);
        goods.setPcid(pcid);
        return iGoodsService.add_goods(goods);
    }

    @RequestMapping(value = "get_goods.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_goods(HttpSession session){
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        return iGoodsService.get_goods();
    }

    @RequestMapping(value = "get_goods_by_gid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_goods_by_gid(HttpSession session,String gid){
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        return iGoodsService.get_goods_by_gid(gid);
    }

    @RequestMapping(value = "update_goods.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse update_goods(HttpSession session,String gid,String name,String describe,String pcid){
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        if(gid.equals(""))
            return ServerResponse.createByErrorMessage("请填写完整信息");
        Goods goods = new Goods();
        goods.setGid(gid);
        if(!name.equals(""))
            goods.setName(name);
        if(!describe.equals(""))
            goods.setDescribe(describe);
        if(!pcid.equals(""))
            goods.setPcid(pcid);
        return iGoodsService.update_goods(goods);
    }

    @RequestMapping(value = "delete_goods.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse delete_goods(HttpSession session,String gid){
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        if(gid.equals(""))
            return ServerResponse.createByErrorMessage("请填写完整信息");
        return iGoodsService.delete_goods(gid);
    }
}
