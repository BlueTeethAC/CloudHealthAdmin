package com.LW1.healthConsult.feedBackInfo.service;

import java.util.List;
import com.LW1.healthConsult.feedBackInfo.domain.Feedbackinfo;

/**
 * 资讯信息反馈Service接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public interface IFeedbackinfoService 
{
    /**
     * 查询资讯信息反馈
     * 
     * @param feedbackinfoId 资讯信息反馈主键
     * @return 资讯信息反馈
     */
    public Feedbackinfo selectFeedbackinfoByFeedbackinfoId(Long feedbackinfoId);

    /**
     * 查询资讯信息反馈列表
     * 
     * @param feedbackinfo 资讯信息反馈
     * @return 资讯信息反馈集合
     */
    public List<Feedbackinfo> selectFeedbackinfoList(Feedbackinfo feedbackinfo);

    /**
     * 新增资讯信息反馈
     * 
     * @param feedbackinfo 资讯信息反馈
     * @return 结果
     */
    public int insertFeedbackinfo(Feedbackinfo feedbackinfo);

    /**
     * 修改资讯信息反馈
     * 
     * @param feedbackinfo 资讯信息反馈
     * @return 结果
     */
    public int updateFeedbackinfo(Feedbackinfo feedbackinfo);

    /**
     * 批量删除资讯信息反馈
     * 
     * @param feedbackinfoIds 需要删除的资讯信息反馈主键集合
     * @return 结果
     */
    public int deleteFeedbackinfoByFeedbackinfoIds(Long[] feedbackinfoIds);

    /**
     * 删除资讯信息反馈信息
     * 
     * @param feedbackinfoId 资讯信息反馈主键
     * @return 结果
     */
    public int deleteFeedbackinfoByFeedbackinfoId(Long feedbackinfoId);
}
