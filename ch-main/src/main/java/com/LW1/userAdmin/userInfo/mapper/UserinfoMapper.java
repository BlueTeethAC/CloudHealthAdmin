package com.LW1.userAdmin.userInfo.mapper;

import java.util.List;
import com.LW1.userAdmin.userInfo.domain.Userinfo;

/**
 * 用户信息Mapper接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public interface UserinfoMapper 
{
    /**
     * 查询用户信息
     * 
     * @param userId 用户信息主键
     * @return 用户信息
     */
    public Userinfo selectUserinfoByUserId(Long userId);

    /**
     * 查询用户信息列表
     * 
     * @param userinfo 用户信息
     * @return 用户信息集合
     */
    public List<Userinfo> selectUserinfoList(Userinfo userinfo);

    /**
     * 新增用户信息
     * 
     * @param userinfo 用户信息
     * @return 结果
     */
    public int insertUserinfo(Userinfo userinfo);

    /**
     * 修改用户信息
     * 
     * @param userinfo 用户信息
     * @return 结果
     */
    public int updateUserinfo(Userinfo userinfo);

    /**
     * 删除用户信息
     * 
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteUserinfoByUserId(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserinfoByUserIds(Long[] userIds);
}
