package com.LW1.healthConsult.bodyInfo.service;

import java.util.List;
import com.LW1.healthConsult.bodyInfo.domain.Bodyinfo;

/**
 * 用户个人身体信息Service接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public interface IBodyinfoService 
{
    /**
     * 查询用户个人身体信息
     * 
     * @param infoId 用户个人身体信息主键
     * @return 用户个人身体信息
     */
    public Bodyinfo selectBodyinfoByInfoId(Long infoId);

    /**
     * 查询用户个人身体信息列表
     * 
     * @param bodyinfo 用户个人身体信息
     * @return 用户个人身体信息集合
     */
    public List<Bodyinfo> selectBodyinfoList(Bodyinfo bodyinfo);

    /**
     * 新增用户个人身体信息
     * 
     * @param bodyinfo 用户个人身体信息
     * @return 结果
     */
    public int insertBodyinfo(Bodyinfo bodyinfo);

    /**
     * 修改用户个人身体信息
     * 
     * @param bodyinfo 用户个人身体信息
     * @return 结果
     */
    public int updateBodyinfo(Bodyinfo bodyinfo);

    /**
     * 批量删除用户个人身体信息
     * 
     * @param infoIds 需要删除的用户个人身体信息主键集合
     * @return 结果
     */
    public int deleteBodyinfoByInfoIds(Long[] infoIds);

    /**
     * 删除用户个人身体信息信息
     * 
     * @param infoId 用户个人身体信息主键
     * @return 结果
     */
    public int deleteBodyinfoByInfoId(Long infoId);
}
