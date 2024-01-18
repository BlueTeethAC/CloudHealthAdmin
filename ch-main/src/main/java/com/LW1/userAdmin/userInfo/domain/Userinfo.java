package com.LW1.userAdmin.userInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户信息对象 ch_userinfo
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public class Userinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Long userId;

    /** 预留字段 微信用户唯一识别码 */
    @Excel(name = "预留字段 微信用户唯一识别码")
    private String openId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 用户密码 */
    @Excel(name = "用户密码")
    private String password;

    /** 用户手机号码 */
    @Excel(name = "用户手机号码")
    private String phoneNumber;

    /** 用户介绍 */
    private String userIntroduce;

    /** 存放图片 url */
    private String accountPicture;

    /** 常用地址 */
    @Excel(name = "常用地址")
    private String userPlace;

    /** 用户角色 */
    @Excel(name = "用户角色")
    private Long userRole;

    /** 账号状态 */
    @Excel(name = "账号状态")
    private Long userStatus;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setUserIntroduce(String userIntroduce) 
    {
        this.userIntroduce = userIntroduce;
    }

    public String getUserIntroduce() 
    {
        return userIntroduce;
    }
    public void setAccountPicture(String accountPicture) 
    {
        this.accountPicture = accountPicture;
    }

    public String getAccountPicture() 
    {
        return accountPicture;
    }
    public void setUserPlace(String userPlace) 
    {
        this.userPlace = userPlace;
    }

    public String getUserPlace() 
    {
        return userPlace;
    }
    public void setUserRole(Long userRole) 
    {
        this.userRole = userRole;
    }

    public Long getUserRole() 
    {
        return userRole;
    }
    public void setUserStatus(Long userStatus) 
    {
        this.userStatus = userStatus;
    }

    public Long getUserStatus() 
    {
        return userStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("openId", getOpenId())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("phoneNumber", getPhoneNumber())
            .append("userIntroduce", getUserIntroduce())
            .append("accountPicture", getAccountPicture())
            .append("userPlace", getUserPlace())
            .append("userRole", getUserRole())
            .append("userStatus", getUserStatus())
            .toString();
    }
}
