package com.LW1.healthConsult.feedBackInfo.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

// 作为查询结果用的实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackinfoWithRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 反馈id */
    private Long feedbackinfoId;

    /** 咨询表id */
    private Long consultationFormId;


    /** 回复者id */
    private Long replyPersonId;

    /** 回复内容 */
    private String replyInfo;

    // 用户角色
    private Long role;
}
