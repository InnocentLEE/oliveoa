package com.oliveoa.service.impl;

import com.oliveoa.common.ServerResponse;
import com.oliveoa.dao.EmployeesMapper;
import com.oliveoa.dao.GoodsMapper;
import com.oliveoa.dao.PositionMapper;
import com.oliveoa.pojo.Position;
import com.oliveoa.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lee on 2018/5/24.
 */
@Service("iPositionService")
public class PositionServiceImpl implements IPositionService {

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private EmployeesMapper employeesMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public ServerResponse add_position(Position position) {
        int result = positionMapper.insert(position);
        if (result > 0)
            return ServerResponse.createBySuccessMessage("职务创建成功");
        else
            return ServerResponse.createByErrorMessage("职务创建失败");
    }

    @Override
    public ServerResponse get_position(String dcid) {
        List<Position> positionList = positionMapper.selectByDcid(dcid);
        return ServerResponse.createBySuccess(positionList);
    }

    @Override
    public ServerResponse update_position(Position position) {
        int result = positionMapper.updateByPrimaryKeySelective(position);
        if (result > 0)
            return ServerResponse.createBySuccessMessage("更新职务成功");
        else
            return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse delete_position(String pcid) {
        //判断有没有子级职务
        int children = positionMapper.selectCountByPpid(pcid);
        if (children > 0)
            return ServerResponse.createByErrorMessage("该职务下有子级职务，删除失败");
        //判断该职务下有没有员工
        int employeeNumber = employeesMapper.selectCountByPcid(pcid);
        if (employeeNumber > 0)
            return ServerResponse.createByErrorMessage("有员工正担任该职务，无法删除");
        //判断该职务是否管理资产
        int number = goodsMapper.selectCountByPcid(pcid);
        if(number>0)
            return ServerResponse.createByErrorMessage("该职务为资产管理职务，无法删除");
        //删除职务
        int result = positionMapper.deleteByPrimaryKey(pcid);
        if (result > 0)
            return ServerResponse.createBySuccessMessage("删除职务成功");
        else
            return ServerResponse.createByErrorMessage("删除职务失败");
    }
}
