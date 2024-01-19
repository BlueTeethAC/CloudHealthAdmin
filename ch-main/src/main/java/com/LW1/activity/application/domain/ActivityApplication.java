package com.LW1.activity.application.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动报名对象 ch_activity_application
 * 
 * @author BlueTeethAC
 * @date 2024-01-19
 */
public class ActivityApplication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报名信息id */
    private Long infoId;

    /** 活动id */
    @Excel(name = "活动id")
    private Long activity;

    /** 报名用户 id */
    @Excel(name = "报名用户 id")
    private Long userId;

    /** 报名审核状态   0：待审核   1：审核通过    2：审核不通过 */
    @Excel(name = "报名审核状态   0：待审核   1：审核通过    2：审核不通过")
    private Long status;

    public void setInfoId(Long infoId) 
    {
        this.infoId = infoId;
    }

    public Long getInfoId() 
    {
        return infoId;
    }
    public void setActivity(Long activity) 
    {
        this.activity = activity;
    }

    public Long getActivity() 
    {
        return activity;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("infoId", getInfoId())
            .append("activity", getActivity())
            .append("userId", getUserId())
            .append("status", getStatus())
            .toString();
    }
}
