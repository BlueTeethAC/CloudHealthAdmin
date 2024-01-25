package com.LW1.video.videoClassify.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 视频课程分类对象 ch_video_classify
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public class VideoClassify extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类信息id */
    private Long classifyId;

    /** 分类编号 */
    @Excel(name = "分类编号")
    private Integer classifyNum;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String classifyName;

    public void setClassifyId(Long classifyId) 
    {
        this.classifyId = classifyId;
    }

    public Long getClassifyId() 
    {
        return classifyId;
    }
    public void setClassifyNum(Integer classifyNum)
    {
        this.classifyNum = classifyNum;
    }

    public Integer getClassifyNum()
    {
        return classifyNum;
    }
    public void setClassifyName(String classifyName) 
    {
        this.classifyName = classifyName;
    }

    public String getClassifyName() 
    {
        return classifyName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("classifyId", getClassifyId())
            .append("classifyNum", getClassifyNum())
            .append("classifyName", getClassifyName())
            .toString();
    }
}
