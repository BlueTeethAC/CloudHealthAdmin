package com.LW1.userAdmin.userInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.userAdmin.userInfo.mapper.UserinfoMapper;
import com.LW1.userAdmin.userInfo.domain.Userinfo;
import com.LW1.userAdmin.userInfo.service.IUserinfoService;

/**
 * 用户信息Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@Service
public class UserinfoServiceImpl implements IUserinfoService 
{
    @Autowired
    private UserinfoMapper userinfoMapper;

    /**
     * 查询用户信息
     * 
     * @param userId 用户信息主键
     * @return 用户信息
     */
    @Override
    public Userinfo selectUserinfoByUserId(Long userId)
    {
        return userinfoMapper.selectUserinfoByUserId(userId);
    }

    /**
     * 查询用户信息列表
     * 
     * @param userinfo 用户信息
     * @return 用户信息
     */
    @Override
    public List<Userinfo> selectUserinfoList(Userinfo userinfo)
    {
        return userinfoMapper.selectUserinfoList(userinfo);
    }

    /**
     * 新增用户信息
     * 
     * @param userinfo 用户信息
     * @return 结果
     */
    @Override
    public int insertUserinfo(Userinfo userinfo)
    {
        return userinfoMapper.insertUserinfo(userinfo);
    }

    /**
     * 修改用户信息
     * 
     * @param userinfo 用户信息
     * @return 结果
     */
    @Override
    public int updateUserinfo(Userinfo userinfo)
    {
        return userinfoMapper.updateUserinfo(userinfo);
    }

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUserinfoByUserIds(Long[] userIds)
    {
        return userinfoMapper.deleteUserinfoByUserIds(userIds);
    }

    /**
     * 删除用户信息信息
     * 
     * @param userId 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteUserinfoByUserId(Long userId)
    {
        return userinfoMapper.deleteUserinfoByUserId(userId);
    }
}
