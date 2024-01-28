package com.LW1.userAdmin.roleProcess.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.userAdmin.roleProcess.mapper.RoleAuthenticationMapper;
import com.LW1.userAdmin.roleProcess.domain.RoleAuthentication;
import com.LW1.userAdmin.roleProcess.service.IRoleAuthenticationService;

/**
 * 角色认证审核Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@Service
public class RoleAuthenticationServiceImpl implements IRoleAuthenticationService 
{
    @Autowired
    private RoleAuthenticationMapper roleAuthenticationMapper;

    /**
     * 查询角色认证审核
     * 
     * @param id 角色认证审核主键
     * @return 角色认证审核
     */
    @Override
    public RoleAuthentication selectRoleAuthenticationById(Long id)
    {
        return roleAuthenticationMapper.selectRoleAuthenticationById(id);
    }

    /**
     * 查询角色认证审核列表
     * 
     * @param roleAuthentication 角色认证审核
     * @return 角色认证审核
     */
    @Override
    public List<RoleAuthentication> selectRoleAuthenticationList(RoleAuthentication roleAuthentication)
    {
        return roleAuthenticationMapper.selectRoleAuthenticationList(roleAuthentication);
    }

    /**
     * 新增角色认证审核
     * 
     * @param roleAuthentication 角色认证审核
     * @return 结果
     */
    @Override
    public int insertRoleAuthentication(RoleAuthentication roleAuthentication)
    {
        return roleAuthenticationMapper.insertRoleAuthentication(roleAuthentication);
    }

    /**
     * 修改角色认证审核
     * 
     * @param roleAuthentication 角色认证审核
     * @return 结果
     */
    @Override
    public int updateRoleAuthentication(RoleAuthentication roleAuthentication)
    {
        return roleAuthenticationMapper.updateRoleAuthentication(roleAuthentication);
    }

    /**
     * 批量删除角色认证审核
     * 
     * @param ids 需要删除的角色认证审核主键
     * @return 结果
     */
    @Override
    public int deleteRoleAuthenticationByIds(Long[] ids)
    {
        return roleAuthenticationMapper.deleteRoleAuthenticationByIds(ids);
    }

    /**
     * 删除角色认证审核信息
     * 
     * @param id 角色认证审核主键
     * @return 结果
     */
    @Override
    public int deleteRoleAuthenticationById(Long id)
    {
        return roleAuthenticationMapper.deleteRoleAuthenticationById(id);
    }


    /**
     * 审核角色认证
     *
     * @param roleAuthentication 角色认证审核
     * @return 结果
     */
    @Override
    public int updateProcessRoleAuthentication(RoleAuthentication roleAuthentication) {

        // 修改审核表状态
        int i = roleAuthenticationMapper.updateRoleAuthentication(roleAuthentication);

        System.err.println("i："+i);

        if (i == 1 && (roleAuthentication.getStatus() == 1)){
            Map<String,Long> map = new HashMap<>();
            map.put("userId",roleAuthentication.getUserId());
            map.put("roleId",roleAuthentication.getRole());
            return roleAuthenticationMapper.updateUserRoleByUserId(map);
        }

        return i;
    }
}
