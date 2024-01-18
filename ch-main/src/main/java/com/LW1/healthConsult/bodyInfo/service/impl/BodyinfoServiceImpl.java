package com.LW1.healthConsult.bodyInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.healthConsult.bodyInfo.mapper.BodyinfoMapper;
import com.LW1.healthConsult.bodyInfo.domain.Bodyinfo;
import com.LW1.healthConsult.bodyInfo.service.IBodyinfoService;

/**
 * 用户个人身体信息Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@Service
public class BodyinfoServiceImpl implements IBodyinfoService 
{
    @Autowired
    private BodyinfoMapper bodyinfoMapper;

    /**
     * 查询用户个人身体信息
     * 
     * @param infoId 用户个人身体信息主键
     * @return 用户个人身体信息
     */
    @Override
    public Bodyinfo selectBodyinfoByInfoId(Long infoId)
    {
        return bodyinfoMapper.selectBodyinfoByInfoId(infoId);
    }

    /**
     * 查询用户个人身体信息列表
     * 
     * @param bodyinfo 用户个人身体信息
     * @return 用户个人身体信息
     */
    @Override
    public List<Bodyinfo> selectBodyinfoList(Bodyinfo bodyinfo)
    {
        return bodyinfoMapper.selectBodyinfoList(bodyinfo);
    }

    /**
     * 新增用户个人身体信息
     * 
     * @param bodyinfo 用户个人身体信息
     * @return 结果
     */
    @Override
    public int insertBodyinfo(Bodyinfo bodyinfo)
    {
        return bodyinfoMapper.insertBodyinfo(bodyinfo);
    }

    /**
     * 修改用户个人身体信息
     * 
     * @param bodyinfo 用户个人身体信息
     * @return 结果
     */
    @Override
    public int updateBodyinfo(Bodyinfo bodyinfo)
    {
        return bodyinfoMapper.updateBodyinfo(bodyinfo);
    }

    /**
     * 批量删除用户个人身体信息
     * 
     * @param infoIds 需要删除的用户个人身体信息主键
     * @return 结果
     */
    @Override
    public int deleteBodyinfoByInfoIds(Long[] infoIds)
    {
        return bodyinfoMapper.deleteBodyinfoByInfoIds(infoIds);
    }

    /**
     * 删除用户个人身体信息信息
     * 
     * @param infoId 用户个人身体信息主键
     * @return 结果
     */
    @Override
    public int deleteBodyinfoByInfoId(Long infoId)
    {
        return bodyinfoMapper.deleteBodyinfoByInfoId(infoId);
    }
}
