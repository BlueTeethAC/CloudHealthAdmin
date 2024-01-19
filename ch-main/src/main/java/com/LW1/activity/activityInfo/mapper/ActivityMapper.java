package com.LW1.activity.activityInfo.mapper;

import java.util.List;
import com.LW1.activity.activityInfo.domain.Activity;

/**
 * 线下活动Mapper接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-19
 */
public interface ActivityMapper 
{
    /**
     * 查询线下活动
     * 
     * @param activityId 线下活动主键
     * @return 线下活动
     */
    public Activity selectActivityByActivityId(Long activityId);

    /**
     * 查询线下活动列表
     * 
     * @param activity 线下活动
     * @return 线下活动集合
     */
    public List<Activity> selectActivityList(Activity activity);

    /**
     * 新增线下活动
     * 
     * @param activity 线下活动
     * @return 结果
     */
    public int insertActivity(Activity activity);

    /**
     * 修改线下活动
     * 
     * @param activity 线下活动
     * @return 结果
     */
    public int updateActivity(Activity activity);

    /**
     * 删除线下活动
     * 
     * @param activityId 线下活动主键
     * @return 结果
     */
    public int deleteActivityByActivityId(Long activityId);

    /**
     * 批量删除线下活动
     * 
     * @param activityIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActivityByActivityIds(Long[] activityIds);
}
