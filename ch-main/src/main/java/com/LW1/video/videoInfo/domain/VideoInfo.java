package com.LW1.video.videoInfo.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 线上课程视频对象 ch_video_info
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public class VideoInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 视频id */
    private Long videoId;

    /** 视频类型 */
    @Excel(name = "视频类型")
    private Long videoModel;

    /** 视频分类 */
    @Excel(name = "视频分类")
    private Long videoCalssify;

    /** 访问路径 */
    private String videoPath;

    /** 视频所属系列 */
    @Excel(name = "视频所属系列")
    private Long videoSeries;

    /** 视频名称 */
    @Excel(name = "视频名称")
    private String videoName;

    /** 视频封面路径 */
    private String videoPhoto;

    /** 视频上传者id */
    @Excel(name = "视频上传者id")
    private Long videoUploadUser;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date videoUploadDate;

    /** 视频状态 */
    @Excel(name = "视频状态")
    private Long status;

    public void setVideoId(Long videoId) 
    {
        this.videoId = videoId;
    }

    public Long getVideoId() 
    {
        return videoId;
    }
    public void setVideoModel(Long videoModel) 
    {
        this.videoModel = videoModel;
    }

    public Long getVideoModel() 
    {
        return videoModel;
    }
    public void setVideoCalssify(Long videoCalssify) 
    {
        this.videoCalssify = videoCalssify;
    }

    public Long getVideoCalssify() 
    {
        return videoCalssify;
    }
    public void setVideoPath(String videoPath) 
    {
        this.videoPath = videoPath;
    }

    public String getVideoPath() 
    {
        return videoPath;
    }
    public void setVideoSeries(Long videoSeries) 
    {
        this.videoSeries = videoSeries;
    }

    public Long getVideoSeries() 
    {
        return videoSeries;
    }
    public void setVideoName(String videoName) 
    {
        this.videoName = videoName;
    }

    public String getVideoName() 
    {
        return videoName;
    }
    public void setVideoPhoto(String videoPhoto) 
    {
        this.videoPhoto = videoPhoto;
    }

    public String getVideoPhoto() 
    {
        return videoPhoto;
    }
    public void setVideoUploadUser(Long videoUploadUser) 
    {
        this.videoUploadUser = videoUploadUser;
    }

    public Long getVideoUploadUser() 
    {
        return videoUploadUser;
    }
    public void setVideoUploadDate(Date videoUploadDate) 
    {
        this.videoUploadDate = videoUploadDate;
    }

    public Date getVideoUploadDate() 
    {
        return videoUploadDate;
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
            .append("videoId", getVideoId())
            .append("videoModel", getVideoModel())
            .append("videoCalssify", getVideoCalssify())
            .append("videoPath", getVideoPath())
            .append("videoSeries", getVideoSeries())
            .append("videoName", getVideoName())
            .append("videoPhoto", getVideoPhoto())
            .append("videoUploadUser", getVideoUploadUser())
            .append("videoUploadDate", getVideoUploadDate())
            .append("status", getStatus())
            .toString();
    }
}
