package com.LW1.activity.application.service;

import java.util.List;
import com.LW1.activity.application.domain.ActivityApplication;

/**
 * 活动报名Service接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-19
 */
public interface IActivityApplicationService 
{
    /**
     * 查询活动报名
     * 
     * @param infoId 活动报名主键
     * @return 活动报名
     */
    public ActivityApplication selectActivityApplicationByInfoId(Long infoId);

    /**
     * 查询活动报名列表
     * 
     * @param activityApplication 活动报名
     * @return 活动报名集合
     */
    public List<ActivityApplication> selectActivityApplicationList(ActivityApplication activityApplication);

    /**
     * 新增活动报名
     * 
     * @param activityApplication 活动报名
     * @return 结果
     */
    public int insertActivityApplication(ActivityApplication activityApplication);

    /**
     * 修改活动报名
     * 
     * @param activityApplication 活动报名
     * @return 结果
     */
    public int updateActivityApplication(ActivityApplication activityApplication);

    /**
     * 批量删除活动报名
     * 
     * @param infoIds 需要删除的活动报名主键集合
     * @return 结果
     */
    public int deleteActivityApplicationByInfoIds(Long[] infoIds);

    /**
     * 删除活动报名信息
     * 
     * @param infoId 活动报名主键
     * @return 结果
     */
    public int deleteActivityApplicationByInfoId(Long infoId);
}
