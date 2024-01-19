package com.LW1.activity.application.mapper;

import java.util.List;
import com.LW1.activity.application.domain.ActivityApplication;

/**
 * 活动报名Mapper接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-19
 */
public interface ActivityApplicationMapper 
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
     * 删除活动报名
     * 
     * @param infoId 活动报名主键
     * @return 结果
     */
    public int deleteActivityApplicationByInfoId(Long infoId);

    /**
     * 批量删除活动报名
     * 
     * @param infoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActivityApplicationByInfoIds(Long[] infoIds);
}
