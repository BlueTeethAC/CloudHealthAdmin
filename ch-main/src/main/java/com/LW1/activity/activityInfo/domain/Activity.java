package com.LW1.activity.activityInfo.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 线下活动对象 ch_activity
 * 
 * @author BlueTeethAC
 * @date 2024-01-19
 */
public class Activity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动id */
    @Excel(name = "活动id")
    private Long activityId;

    /** 活动名 */
    @Excel(name = "活动名")
    private String activityName;

    /** 活动时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date activityDate;

    /** 系列分类 */
    @Excel(name = "系列分类")
    private Long activityClassify;

    /** 是否免费  */
    @Excel(name = "是否免费 ")
    private Long isFree;

    /** 价格 */
    @Excel(name = "价格")
    private Long activityPrice;

    /** 活动地点 */
    @Excel(name = "活动地点")
    private String activityPlace;

    /** 发布人id */
    @Excel(name = "发布人id")
    private Long publishId;

    /** 审核状态   0：待审核   1：审核通过    2：审核不通过 */
    @Excel(name = "审核状态   0：待审核   1：审核通过    2：审核不通过")
    private Long status;

    public void setActivityId(Long activityId) 
    {
        this.activityId = activityId;
    }

    public Long getActivityId() 
    {
        return activityId;
    }
    public void setActivityName(String activityName) 
    {
        this.activityName = activityName;
    }

    public String getActivityName() 
    {
        return activityName;
    }
    public void setActivityDate(Date activityDate) 
    {
        this.activityDate = activityDate;
    }

    public Date getActivityDate() 
    {
        return activityDate;
    }
    public void setActivityClassify(Long activityClassify) 
    {
        this.activityClassify = activityClassify;
    }

    public Long getActivityClassify() 
    {
        return activityClassify;
    }
    public void setIsFree(Long isFree) 
    {
        this.isFree = isFree;
    }

    public Long getIsFree() 
    {
        return isFree;
    }
    public void setActivityPrice(Long activityPrice) 
    {
        this.activityPrice = activityPrice;
    }

    public Long getActivityPrice() 
    {
        return activityPrice;
    }
    public void setActivityPlace(String activityPlace) 
    {
        this.activityPlace = activityPlace;
    }

    public String getActivityPlace() 
    {
        return activityPlace;
    }
    public void setPublishId(Long publishId) 
    {
        this.publishId = publishId;
    }

    public Long getPublishId() 
    {
        return publishId;
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
            .append("activityId", getActivityId())
            .append("activityName", getActivityName())
            .append("activityDate", getActivityDate())
            .append("activityClassify", getActivityClassify())
            .append("isFree", getIsFree())
            .append("activityPrice", getActivityPrice())
            .append("activityPlace", getActivityPlace())
            .append("publishId", getPublishId())
            .append("status", getStatus())
            .toString();
    }
}
