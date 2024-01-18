package com.LW1.video.seriesSubscribe.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 系列订阅对象 ch_series_subscribe
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public class SeriesSubscribe extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订阅记录id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 系列id */
    @Excel(name = "系列id")
    private Long seriesId;

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
    public void setSeriesId(Long seriesId) 
    {
        this.seriesId = seriesId;
    }

    public Long getSeriesId() 
    {
        return seriesId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("seriesId", getSeriesId())
            .toString();
    }
}
