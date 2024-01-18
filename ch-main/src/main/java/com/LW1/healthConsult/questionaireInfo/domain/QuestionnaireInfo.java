package com.LW1.healthConsult.questionaireInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 问卷信息对象 ch_questionnaire_info
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public class QuestionnaireInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 问卷信息id */
    private Long infoId;

    /** 问卷型号 */
    @Excel(name = "问卷型号")
    private Long model;

    /** 题号 */
    @Excel(name = "题号")
    private Long questionNumber;

    /** 正确答案 */
    @Excel(name = "正确答案")
    private String answer;

    /** 考题分数  */
    @Excel(name = "考题分数 ")
    private Long fraction;

    public void setInfoId(Long infoId) 
    {
        this.infoId = infoId;
    }

    public Long getInfoId() 
    {
        return infoId;
    }
    public void setModel(Long model) 
    {
        this.model = model;
    }

    public Long getModel() 
    {
        return model;
    }
    public void setQuestionNumber(Long questionNumber) 
    {
        this.questionNumber = questionNumber;
    }

    public Long getQuestionNumber() 
    {
        return questionNumber;
    }
    public void setAnswer(String answer) 
    {
        this.answer = answer;
    }

    public String getAnswer() 
    {
        return answer;
    }
    public void setFraction(Long fraction) 
    {
        this.fraction = fraction;
    }

    public Long getFraction() 
    {
        return fraction;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("infoId", getInfoId())
            .append("model", getModel())
            .append("questionNumber", getQuestionNumber())
            .append("answer", getAnswer())
            .append("fraction", getFraction())
            .toString();
    }
}
