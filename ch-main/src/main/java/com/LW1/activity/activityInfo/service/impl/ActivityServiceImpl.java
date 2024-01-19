package com.LW1.activity.activityInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.activity.activityInfo.mapper.ActivityMapper;
import com.LW1.activity.activityInfo.domain.Activity;
import com.LW1.activity.activityInfo.service.IActivityService;

/**
 * 线下活动Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-19
 */
@Service
public class ActivityServiceImpl implements IActivityService 
{
    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 查询线下活动
     * 
     * @param activityId 线下活动主键
     * @return 线下活动
     */
    @Override
    public Activity selectActivityByActivityId(Long activityId)
    {
        return activityMapper.selectActivityByActivityId(activityId);
    }

    /**
     * 查询线下活动列表
     * 
     * @param activity 线下活动
     * @return 线下活动
     */
    @Override
    public List<Activity> selectActivityList(Activity activity)
    {
        return activityMapper.selectActivityList(activity);
    }

    /**
     * 新增线下活动
     * 
     * @param activity 线下活动
     * @return 结果
     */
    @Override
    public int insertActivity(Activity activity)
    {
        return activityMapper.insertActivity(activity);
    }

    /**
     * 修改线下活动
     * 
     * @param activity 线下活动
     * @return 结果
     */
    @Override
    public int updateActivity(Activity activity)
    {
        return activityMapper.updateActivity(activity);
    }

    /**
     * 批量删除线下活动
     * 
     * @param activityIds 需要删除的线下活动主键
     * @return 结果
     */
    @Override
    public int deleteActivityByActivityIds(Long[] activityIds)
    {
        return activityMapper.deleteActivityByActivityIds(activityIds);
    }

    /**
     * 删除线下活动信息
     * 
     * @param activityId 线下活动主键
     * @return 结果
     */
    @Override
    public int deleteActivityByActivityId(Long activityId)
    {
        return activityMapper.deleteActivityByActivityId(activityId);
    }
}
