package com.oliveoa.controller.company;

import com.oliveoa.common.Const;
import com.oliveoa.common.ResponseCode;
import com.oliveoa.common.ServerResponse;
import com.oliveoa.pojo.CompanyInfo;
import com.oliveoa.pojo.Position;
import com.oliveoa.service.IPositionService;
import com.oliveoa.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Lee on 2018/5/24.
 */
@Controller
@RequestMapping("/manage/position/")
public class PositionManageController {

    @Autowired
    private IPositionService iPositionService;

    @RequestMapping(value = "add_position.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add_position(HttpSession session,String dcid,String ppid,String name,String limit){
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        Integer limit1 = Integer.parseInt(limit);
        Position position = new Position();
        if (!ppid.equals(""))
            position.setPpid(ppid);
        position.setName(name);
        position.setDcid(dcid);
        position.setLimit(limit1);
        position.setPcid(CommonUtils.uuid());
        return iPositionService.add_position(position);
    }
    @RequestMapping(value = "get_position.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse get_position(HttpSession session,String dcid){
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        return iPositionService.get_position(dcid);
    }
    @RequestMapping(value = "update_position.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse update_position(HttpSession session,String pcid,String ppid,String name,String limit){
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        Position position = new Position();
        position.setPcid(pcid);
        if(!ppid.equals("")) {
            position.setPpid(ppid);
        }
        if(!name.equals("")) {
            position.setName(name);
        }
        if(!limit.equals("")){
            Integer limit1 = Integer.parseInt(limit);
            position.setLimit(limit1);
        }
        return iPositionService.update_position(position);
    }

    @RequestMapping(value = "delete_position.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse delete_position(HttpSession session,String pcid){
        CompanyInfo companyInfo = (CompanyInfo) session.getAttribute(Const.CURRENT_COMPANY);
        if (companyInfo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,请先登录");
        }
        return iPositionService.delete_position(pcid);
    }
}
