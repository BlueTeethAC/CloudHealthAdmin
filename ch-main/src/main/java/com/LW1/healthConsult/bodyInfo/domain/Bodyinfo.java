package com.LW1.healthConsult.bodyInfo.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户个人身体信息对象 ch_bodyinfo
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public class Bodyinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 健康信息id */
    private Long infoId;

    /** 输入日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "输入日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputDate;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 身高 */
    @Excel(name = "身高")
    private Long height;

    /** 体重 */
    @Excel(name = "体重")
    private Long weight;

    /** 心率 */
    @Excel(name = "心率")
    private Long heartRule;

    /** 血压（上压） */
    @Excel(name = "血压", readConverterExp = "上=压")
    private Long elevatedBloodPressure;

    /** 血压（下压） */
    @Excel(name = "血压", readConverterExp = "下=压")
    private Long lowerBloodPressure;

    /** 血糖 */
    @Excel(name = "血糖")
    private Long bloodSugar;

    /** 性别 */
    @Excel(name = "性别")
    private Long sex;

    /** 腰围 */
    @Excel(name = "腰围")
    private Long waist;

    /** 臀围 */
    @Excel(name = "臀围")
    private Long hip;

    /** 上臂臂围 */
    @Excel(name = "上臂臂围")
    private Long upperArmCircumference;

    /** 体脂 */
    @Excel(name = "体脂")
    private Long bodyFat;

    /** 内脂 */
    @Excel(name = "内脂")
    private Long lactone;

    public void setInfoId(Long infoId) 
    {
        this.infoId = infoId;
    }

    public Long getInfoId() 
    {
        return infoId;
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
    public void setHeight(Long height) 
    {
        this.height = height;
    }

    public Long getHeight() 
    {
        return height;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setHeartRule(Long heartRule) 
    {
        this.heartRule = heartRule;
    }

    public Long getHeartRule() 
    {
        return heartRule;
    }
    public void setElevatedBloodPressure(Long elevatedBloodPressure) 
    {
        this.elevatedBloodPressure = elevatedBloodPressure;
    }

    public Long getElevatedBloodPressure() 
    {
        return elevatedBloodPressure;
    }
    public void setLowerBloodPressure(Long lowerBloodPressure) 
    {
        this.lowerBloodPressure = lowerBloodPressure;
    }

    public Long getLowerBloodPressure() 
    {
        return lowerBloodPressure;
    }
    public void setBloodSugar(Long bloodSugar) 
    {
        this.bloodSugar = bloodSugar;
    }

    public Long getBloodSugar() 
    {
        return bloodSugar;
    }
    public void setSex(Long sex) 
    {
        this.sex = sex;
    }

    public Long getSex() 
    {
        return sex;
    }
    public void setWaist(Long waist) 
    {
        this.waist = waist;
    }

    public Long getWaist() 
    {
        return waist;
    }
    public void setHip(Long hip) 
    {
        this.hip = hip;
    }

    public Long getHip() 
    {
        return hip;
    }
    public void setUpperArmCircumference(Long upperArmCircumference) 
    {
        this.upperArmCircumference = upperArmCircumference;
    }

    public Long getUpperArmCircumference() 
    {
        return upperArmCircumference;
    }
    public void setBodyFat(Long bodyFat) 
    {
        this.bodyFat = bodyFat;
    }

    public Long getBodyFat() 
    {
        return bodyFat;
    }
    public void setLactone(Long lactone) 
    {
        this.lactone = lactone;
    }

    public Long getLactone() 
    {
        return lactone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("infoId", getInfoId())
            .append("inputDate", getInputDate())
            .append("userId", getUserId())
            .append("height", getHeight())
            .append("weight", getWeight())
            .append("heartRule", getHeartRule())
            .append("elevatedBloodPressure", getElevatedBloodPressure())
            .append("lowerBloodPressure", getLowerBloodPressure())
            .append("bloodSugar", getBloodSugar())
            .append("sex", getSex())
            .append("waist", getWaist())
            .append("hip", getHip())
            .append("upperArmCircumference", getUpperArmCircumference())
            .append("bodyFat", getBodyFat())
            .append("lactone", getLactone())
            .toString();
    }
}
