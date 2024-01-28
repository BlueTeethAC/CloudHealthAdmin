package com.LW1.userAdmin.roleProcess.service;

import java.util.List;
import com.LW1.userAdmin.roleProcess.domain.RoleAuthentication;

/**
 * 角色认证审核Service接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public interface IRoleAuthenticationService 
{
    /**
     * 查询角色认证审核
     * 
     * @param id 角色认证审核主键
     * @return 角色认证审核
     */
    public RoleAuthentication selectRoleAuthenticationById(Long id);

    /**
     * 查询角色认证审核列表
     * 
     * @param roleAuthentication 角色认证审核
     * @return 角色认证审核集合
     */
    public List<RoleAuthentication> selectRoleAuthenticationList(RoleAuthentication roleAuthentication);

    /**
     * 新增角色认证审核
     * 
     * @param roleAuthentication 角色认证审核
     * @return 结果
     */
    public int insertRoleAuthentication(RoleAuthentication roleAuthentication);

    /**
     * 修改角色认证审核
     * 
     * @param roleAuthentication 角色认证审核
     * @return 结果
     */
    public int updateRoleAuthentication(RoleAuthentication roleAuthentication);

    /**
     * 批量删除角色认证审核
     * 
     * @param ids 需要删除的角色认证审核主键集合
     * @return 结果
     */
    public int deleteRoleAuthenticationByIds(Long[] ids);

    /**
     * 删除角色认证审核信息
     * 
     * @param id 角色认证审核主键
     * @return 结果
     */
    public int deleteRoleAuthenticationById(Long id);


    /**
     * 审核角色认证
     *
     * @param roleAuthentication 角色认证审核
     * @return 结果
     */
    public int updateProcessRoleAuthentication(RoleAuthentication roleAuthentication);
}
