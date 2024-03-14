package com.LW1.booking.booking.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 私教，健身房预约对象 ch_booking
 * 
 * @author BlueTeethAC
 * @date 2024-03-14
 */
public class ChBooking extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预约信息id */
    private Long infoId;

    /** 预约人id */
    @Excel(name = "预约人id")
    private Long userInfoId;

    /** 信息生成日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "信息生成日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date infoTime;

    /** 预约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预约日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bookingTime;

    /** 预约目标 id */
    @Excel(name = "预约目标 id")
    private Long targetUserId;

    /** 预约状态 */
    @Excel(name = "预约状态")
    private Long status;

    public void setInfoId(Long infoId) 
    {
        this.infoId = infoId;
    }

    public Long getInfoId() 
    {
        return infoId;
    }
    public void setUserInfoId(Long userInfoId) 
    {
        this.userInfoId = userInfoId;
    }

    public Long getUserInfoId() 
    {
        return userInfoId;
    }
    public void setInfoTime(Date infoTime) 
    {
        this.infoTime = infoTime;
    }

    public Date getInfoTime() 
    {
        return infoTime;
    }
    public void setBookingTime(Date bookingTime) 
    {
        this.bookingTime = bookingTime;
    }

    public Date getBookingTime() 
    {
        return bookingTime;
    }
    public void setTargetUserId(Long targetUserId) 
    {
        this.targetUserId = targetUserId;
    }

    public Long getTargetUserId() 
    {
        return targetUserId;
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
            .append("userInfoId", getUserInfoId())
            .append("infoTime", getInfoTime())
            .append("bookingTime", getBookingTime())
            .append("targetUserId", getTargetUserId())
            .append("status", getStatus())
            .toString();
    }
}
