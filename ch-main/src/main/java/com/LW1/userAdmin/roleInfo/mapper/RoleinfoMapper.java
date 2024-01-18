package com.LW1.userAdmin.roleInfo.mapper;

import java.util.List;
import com.LW1.userAdmin.roleInfo.domain.Roleinfo;

/**
 * 角色权限Mapper接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public interface RoleinfoMapper 
{
    /**
     * 查询角色权限
     * 
     * @param infoId 角色权限主键
     * @return 角色权限
     */
    public Roleinfo selectRoleinfoByInfoId(Long infoId);

    /**
     * 查询角色权限列表
     * 
     * @param roleinfo 角色权限
     * @return 角色权限集合
     */
    public List<Roleinfo> selectRoleinfoList(Roleinfo roleinfo);

    /**
     * 新增角色权限
     * 
     * @param roleinfo 角色权限
     * @return 结果
     */
    public int insertRoleinfo(Roleinfo roleinfo);

    /**
     * 修改角色权限
     * 
     * @param roleinfo 角色权限
     * @return 结果
     */
    public int updateRoleinfo(Roleinfo roleinfo);

    /**
     * 删除角色权限
     * 
     * @param infoId 角色权限主键
     * @return 结果
     */
    public int deleteRoleinfoByInfoId(Long infoId);

    /**
     * 批量删除角色权限
     * 
     * @param infoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRoleinfoByInfoIds(Long[] infoIds);
}
