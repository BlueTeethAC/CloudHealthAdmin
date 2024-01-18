package com.LW1.healthConsult.consultInfo.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 咨询信息对象 ch_consultationform
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public class Consultationform extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 咨询表id */
    private Long ConsultationFormId;

    /** 身体健康信息id */
    @Excel(name = "身体健康信息id")
    private Long bodyinfoId;

    /** 健康问卷得分 */
    @Excel(name = "健康问卷得分")
    private Long score;

    /** 输入日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "输入日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputDate;

    /** 输入人 id */
    @Excel(name = "输入人 id")
    private Long userId;

    /** 咨询单状态 */
    @Excel(name = "咨询单状态")
    private Long state;

    /** 问卷答题记录 */
    @Excel(name = "问卷答题记录")
    private String questionnaireRecordings;

    public void setConsultationFormId(Long ConsultationFormId) 
    {
        this.ConsultationFormId = ConsultationFormId;
    }

    public Long getConsultationFormId() 
    {
        return ConsultationFormId;
    }
    public void setBodyinfoId(Long bodyinfoId) 
    {
        this.bodyinfoId = bodyinfoId;
    }

    public Long getBodyinfoId() 
    {
        return bodyinfoId;
    }
    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
    }
    public void setInputDate(Date inputDate) 
    {
        this.inputDate = inputDate;
    }

    public Date getInputDate() 
    {
        return inputDate;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }
    public void setQuestionnaireRecordings(String questionnaireRecordings) 
    {
        this.questionnaireRecordings = questionnaireRecordings;
    }

    public String getQuestionnaireRecordings() 
    {
        return questionnaireRecordings;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ConsultationFormId", getConsultationFormId())
            .append("bodyinfoId", getBodyinfoId())
            .append("score", getScore())
            .append("inputDate", getInputDate())
            .append("userId", getUserId())
            .append("state", getState())
            .append("questionnaireRecordings", getQuestionnaireRecordings())
            .toString();
    }
}
