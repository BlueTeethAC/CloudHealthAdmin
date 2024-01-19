package com.LW1.activity.application.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.activity.application.mapper.ActivityApplicationMapper;
import com.LW1.activity.application.domain.ActivityApplication;
import com.LW1.activity.application.service.IActivityApplicationService;

/**
 * 活动报名Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-19
 */
@Service
public class ActivityApplicationServiceImpl implements IActivityApplicationService 
{
    @Autowired
    private ActivityApplicationMapper activityApplicationMapper;

    /**
     * 查询活动报名
     * 
     * @param infoId 活动报名主键
     * @return 活动报名
     */
    @Override
    public ActivityApplication selectActivityApplicationByInfoId(Long infoId)
    {
        return activityApplicationMapper.selectActivityApplicationByInfoId(infoId);
    }

    /**
     * 查询活动报名列表
     * 
     * @param activityApplication 活动报名
     * @return 活动报名
     */
    @Override
    public List<ActivityApplication> selectActivityApplicationList(ActivityApplication activityApplication)
    {
        return activityApplicationMapper.selectActivityApplicationList(activityApplication);
    }

    /**
     * 新增活动报名
     * 
     * @param activityApplication 活动报名
     * @return 结果
     */
    @Override
    public int insertActivityApplication(ActivityApplication activityApplication)
    {
        return activityApplicationMapper.insertActivityApplication(activityApplication);
    }

    /**
     * 修改活动报名
     * 
     * @param activityApplication 活动报名
     * @return 结果
     */
    @Override
    public int updateActivityApplication(ActivityApplication activityApplication)
    {
        return activityApplicationMapper.updateActivityApplication(activityApplication);
    }

    /**
     * 批量删除活动报名
     * 
     * @param infoIds 需要删除的活动报名主键
     * @return 结果
     */
    @Override
    public int deleteActivityApplicationByInfoIds(Long[] infoIds)
    {
        return activityApplicationMapper.deleteActivityApplicationByInfoIds(infoIds);
    }

    /**
     * 删除活动报名信息
     * 
     * @param infoId 活动报名主键
     * @return 结果
     */
    @Override
    public int deleteActivityApplicationByInfoId(Long infoId)
    {
        return activityApplicationMapper.deleteActivityApplicationByInfoId(infoId);
    }
}
