package com.LW1.video.seriesInfo.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 视频系列对象 ch_video_series
 * 
 * @author BlueTeethAC
 * @date 2024-01-22
 */
public class VideoSeries extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 系列id */
    private Long seriesId;

    /** 系列名称 */
    @Excel(name = "系列名称")
    private String seriesName;

    /** 系列创建者id */
    @Excel(name = "系列创建者id")
    private Long seriesCreaterId;

    /** 系列分类 */
    @Excel(name = "系列分类")
    private Long seriesClassify;

    /** 系列简介 */
    @Excel(name = "系列简介")
    private String seriesIntroduction;

    /** 系列封面路径 */
    @Excel(name = "系列封面路径")
    private String seriesPhoto;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date seriesUploadDate;

    /** 是否免费 */
    @Excel(name = "是否免费")
    private Long seriesFree;

    /** 订阅价格 */
    @Excel(name = "订阅价格")
    private Float seriesPrice;

    /** 系列状态 */
    @Excel(name = "系列状态")
    private Long status;

    public void setSeriesId(Long seriesId) 
    {
        this.seriesId = seriesId;
    }

    public Long getSeriesId() 
    {
        return seriesId;
    }
    public void setSeriesName(String seriesName) 
    {
        this.seriesName = seriesName;
    }

    public String getSeriesName() 
    {
        return seriesName;
    }
    public void setSeriesCreaterId(Long seriesCreaterId) 
    {
        this.seriesCreaterId = seriesCreaterId;
    }

    public Long getSeriesCreaterId() 
    {
        return seriesCreaterId;
    }
    public void setSeriesClassify(Long seriesClassify) 
    {
        this.seriesClassify = seriesClassify;
    }

    public Long getSeriesClassify() 
    {
        return seriesClassify;
    }
    public void setSeriesIntroduction(String seriesIntroduction) 
    {
        this.seriesIntroduction = seriesIntroduction;
    }

    public String getSeriesIntroduction() 
    {
        return seriesIntroduction;
    }
    public void setSeriesPhoto(String seriesPhoto) 
    {
        this.seriesPhoto = seriesPhoto;
    }

    public String getSeriesPhoto() 
    {
        return seriesPhoto;
    }
    public void setSeriesUploadDate(Date seriesUploadDate) 
    {
        this.seriesUploadDate = seriesUploadDate;
    }

    public Date getSeriesUploadDate() 
    {
        return seriesUploadDate;
    }
    public void setSeriesFree(Long seriesFree) 
    {
        this.seriesFree = seriesFree;
    }

    public Long getSeriesFree() 
    {
        return seriesFree;
    }
    public void setSeriesPrice(Float seriesPrice)
    {
        this.seriesPrice = seriesPrice;
    }

    public Float getSeriesPrice()
    {
        return seriesPrice;
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
            .append("seriesId", getSeriesId())
            .append("seriesName", getSeriesName())
            .append("seriesCreaterId", getSeriesCreaterId())
            .append("seriesClassify", getSeriesClassify())
            .append("seriesIntroduction", getSeriesIntroduction())
            .append("seriesPhoto", getSeriesPhoto())
            .append("seriesUploadDate", getSeriesUploadDate())
            .append("seriesFree", getSeriesFree())
            .append("seriesPrice", getSeriesPrice())
            .append("status", getStatus())
            .toString();
    }
}
