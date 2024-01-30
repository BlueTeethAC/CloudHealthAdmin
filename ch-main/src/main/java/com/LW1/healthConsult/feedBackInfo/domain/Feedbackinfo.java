package com.LW1.healthConsult.feedBackInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资讯信息反馈对象 ch_feedbackinfo
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public class Feedbackinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 反馈id */
    private Long feedbackinfoId;

    /** 咨询表id */
    @Excel(name = "咨询表id")
    private Long consultationFormId;


    /** 回复者id */
    @Excel(name = "回复者id")
    private Long replyPersonId;

    /** 回复内容 */
    private String replyInfo;

    public void setFeedbackinfoId(Long feedbackinfoId) 
    {
        this.feedbackinfoId = feedbackinfoId;
    }

    public Long getFeedbackinfoId() 
    {
        return feedbackinfoId;
    }
    public void setConsultationFormId(Long ConsultationFormId) 
    {
        this.consultationFormId = ConsultationFormId;
    }

    public Long getConsultationFormId() 
    {
        return consultationFormId;
    }
    public void setReplyPersonId(Long replyPersonId) 
    {
        this.replyPersonId = replyPersonId;
    }

    public Long getReplyPersonId() 
    {
        return replyPersonId;
    }
    public void setReplyInfo(String replyInfo) 
    {
        this.replyInfo = replyInfo;
    }

    public String getReplyInfo() 
    {
        return replyInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("feedbackinfoId", getFeedbackinfoId())
            .append("ConsultationFormId", getConsultationFormId())
            .append("replyPersonId", getReplyPersonId())
            .append("replyInfo", getReplyInfo())
            .toString();
    }
}
