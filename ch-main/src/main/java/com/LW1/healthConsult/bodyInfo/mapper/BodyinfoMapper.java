package com.LW1.healthConsult.bodyInfo.mapper;

import java.util.List;
import com.LW1.healthConsult.bodyInfo.domain.Bodyinfo;

/**
 * 用户个人身体信息Mapper接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public interface BodyinfoMapper 
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
     * 删除用户个人身体信息
     * 
     * @param infoId 用户个人身体信息主键
     * @return 结果
     */
    public int deleteBodyinfoByInfoId(Long infoId);

    /**
     * 批量删除用户个人身体信息
     * 
     * @param infoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBodyinfoByInfoIds(Long[] infoIds);
}
