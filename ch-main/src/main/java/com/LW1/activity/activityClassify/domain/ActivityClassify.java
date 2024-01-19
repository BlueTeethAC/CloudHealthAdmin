package com.LW1.activity.activityClassify.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动分类对象 ch_activity_classify
 * 
 * @author BlueTeethAC
 * @date 2024-01-19
 */
public class ActivityClassify extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类id */
    private Long classifyId;

    /** 分类编号 */
    @Excel(name = "分类编号")
    private Long classifyNum;

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
    public void setClassifyNum(Long classifyNum) 
    {
        this.classifyNum = classifyNum;
    }

    public Long getClassifyNum() 
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
