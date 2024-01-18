package com.LW1.userAdmin.roleProcess.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 角色认证审核对象 ch_role_authentication
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public class RoleAuthentication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色认证审核表 */
    private Long id;

    /** 申请审核人id */
    @Excel(name = "申请审核人id")
    private Long userId;

    /** 申请角色 */
    @Excel(name = "申请角色")
    private Long role;

    /** 申请的资料地址 */
    private String infoUrl;

    /** 审核状态   0：待审核   1：审核通过   2：审核不通过 */
    @Excel(name = "审核状态   0：待审核   1：审核通过   2：审核不通过")
    private Long status;

    /** 审核员id */
    @Excel(name = "审核员id")
    private Long adminId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setRole(Long role) 
    {
        this.role = role;
    }

    public Long getRole() 
    {
        return role;
    }
    public void setInfoUrl(String infoUrl) 
    {
        this.infoUrl = infoUrl;
    }

    public String getInfoUrl() 
    {
        return infoUrl;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setAdminId(Long adminId) 
    {
        this.adminId = adminId;
    }

    public Long getAdminId() 
    {
        return adminId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("role", getRole())
            .append("infoUrl", getInfoUrl())
            .append("status", getStatus())
            .append("adminId", getAdminId())
            .toString();
    }
}
