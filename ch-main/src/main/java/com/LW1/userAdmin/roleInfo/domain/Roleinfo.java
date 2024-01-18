package com.LW1.userAdmin.roleInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 角色权限对象 ch_roleinfo
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public class Roleinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 信息id */
    private Long infoId;

    /** 权限id */
    @Excel(name = "权限id")
    private Long roleId;

    /** 角色名 */
    @Excel(name = "角色名")
    private String roleName;

    public void setInfoId(Long infoId) 
    {
        this.infoId = infoId;
    }

    public Long getInfoId() 
    {
        return infoId;
    }
    public void setRoleId(Long roleId) 
    {
        this.roleId = roleId;
    }

    public Long getRoleId() 
    {
        return roleId;
    }
    public void setRoleName(String roleName) 
    {
        this.roleName = roleName;
    }

    public String getRoleName() 
    {
        return roleName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("infoId", getInfoId())
            .append("roleId", getRoleId())
            .append("roleName", getRoleName())
            .toString();
    }
}
