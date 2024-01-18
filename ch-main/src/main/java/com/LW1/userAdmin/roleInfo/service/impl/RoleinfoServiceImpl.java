package com.LW1.userAdmin.roleInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.userAdmin.roleInfo.mapper.RoleinfoMapper;
import com.LW1.userAdmin.roleInfo.domain.Roleinfo;
import com.LW1.userAdmin.roleInfo.service.IRoleinfoService;

/**
 * 角色权限Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@Service
public class RoleinfoServiceImpl implements IRoleinfoService 
{
    @Autowired
    private RoleinfoMapper roleinfoMapper;

    /**
     * 查询角色权限
     * 
     * @param infoId 角色权限主键
     * @return 角色权限
     */
    @Override
    public Roleinfo selectRoleinfoByInfoId(Long infoId)
    {
        return roleinfoMapper.selectRoleinfoByInfoId(infoId);
    }

    /**
     * 查询角色权限列表
     * 
     * @param roleinfo 角色权限
     * @return 角色权限
     */
    @Override
    public List<Roleinfo> selectRoleinfoList(Roleinfo roleinfo)
    {
        return roleinfoMapper.selectRoleinfoList(roleinfo);
    }

    /**
     * 新增角色权限
     * 
     * @param roleinfo 角色权限
     * @return 结果
     */
    @Override
    public int insertRoleinfo(Roleinfo roleinfo)
    {
        return roleinfoMapper.insertRoleinfo(roleinfo);
    }

    /**
     * 修改角色权限
     * 
     * @param roleinfo 角色权限
     * @return 结果
     */
    @Override
    public int updateRoleinfo(Roleinfo roleinfo)
    {
        return roleinfoMapper.updateRoleinfo(roleinfo);
    }

    /**
     * 批量删除角色权限
     * 
     * @param infoIds 需要删除的角色权限主键
     * @return 结果
     */
    @Override
    public int deleteRoleinfoByInfoIds(Long[] infoIds)
    {
        return roleinfoMapper.deleteRoleinfoByInfoIds(infoIds);
    }

    /**
     * 删除角色权限信息
     * 
     * @param infoId 角色权限主键
     * @return 结果
     */
    @Override
    public int deleteRoleinfoByInfoId(Long infoId)
    {
        return roleinfoMapper.deleteRoleinfoByInfoId(infoId);
    }
}
