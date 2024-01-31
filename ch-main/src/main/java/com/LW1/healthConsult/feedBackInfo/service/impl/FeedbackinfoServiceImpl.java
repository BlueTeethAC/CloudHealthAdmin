package com.LW1.healthConsult.feedBackInfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.LW1.healthConsult.feedBackInfo.domain.FeedbackinfoWithRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.healthConsult.feedBackInfo.mapper.FeedbackinfoMapper;
import com.LW1.healthConsult.feedBackInfo.domain.Feedbackinfo;
import com.LW1.healthConsult.feedBackInfo.service.IFeedbackinfoService;

/**
 * 资讯信息反馈Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@Service
public class FeedbackinfoServiceImpl implements IFeedbackinfoService 
{
    @Autowired
    private FeedbackinfoMapper feedbackinfoMapper;

    /**
     * 查询资讯信息反馈
     * 
     * @param feedbackinfoId 资讯信息反馈主键
     * @return 资讯信息反馈
     */
    @Override
    public Feedbackinfo selectFeedbackinfoByFeedbackinfoId(Long feedbackinfoId)
    {
        return feedbackinfoMapper.selectFeedbackinfoByFeedbackinfoId(feedbackinfoId);
    }

    /**
     * 查询资讯信息反馈列表
     * 
     * @param feedbackinfo 资讯信息反馈
     * @return 资讯信息反馈
     */
    @Override
    public List<Feedbackinfo> selectFeedbackinfoList(Feedbackinfo feedbackinfo)
    {
        return feedbackinfoMapper.selectFeedbackinfoList(feedbackinfo);
    }

    /**
     * 新增资讯信息反馈
     * 
     * @param feedbackinfo 资讯信息反馈
     * @return 结果
     */
    @Override
    public int insertFeedbackinfo(Feedbackinfo feedbackinfo)
    {
        return feedbackinfoMapper.insertFeedbackinfo(feedbackinfo);
    }

    /**
     * 修改资讯信息反馈
     * 
     * @param feedbackinfo 资讯信息反馈
     * @return 结果
     */
    @Override
    public int updateFeedbackinfo(Feedbackinfo feedbackinfo)
    {
        return feedbackinfoMapper.updateFeedbackinfo(feedbackinfo);
    }

    /**
     * 批量删除资讯信息反馈
     * 
     * @param feedbackinfoIds 需要删除的资讯信息反馈主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackinfoByFeedbackinfoIds(Long[] feedbackinfoIds)
    {
        return feedbackinfoMapper.deleteFeedbackinfoByFeedbackinfoIds(feedbackinfoIds);
    }

    /**
     * 删除资讯信息反馈信息
     * 
     * @param feedbackinfoId 资讯信息反馈主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackinfoByFeedbackinfoId(Long feedbackinfoId)
    {
        return feedbackinfoMapper.deleteFeedbackinfoByFeedbackinfoId(feedbackinfoId);
    }


    // 查询移动端历史信息集合搭配 role
    @Override
    public List<FeedbackinfoWithRole> getListInfoWithRolePE(Long consultationFormId) {
        return feedbackinfoMapper.getListInfoWithRolePE(consultationFormId);
    }

    // 查询管理端历史信息集合搭配 role
    @Override
    public List<FeedbackinfoWithRole> getListInfoWithRolePC(Long consultationFormId) {
        return feedbackinfoMapper.getListInfoWithRolePC(consultationFormId);
    }

}
