package com.LW1.activity.activityClassify.service;

import java.util.List;
import com.LW1.activity.activityClassify.domain.ActivityClassify;

/**
 * 活动分类Service接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-19
 */
public interface IActivityClassifyService 
{
    /**
     * 查询活动分类
     * 
     * @param classifyId 活动分类主键
     * @return 活动分类
     */
    public ActivityClassify selectActivityClassifyByClassifyId(Long classifyId);

    /**
     * 查询活动分类列表
     * 
     * @param activityClassify 活动分类
     * @return 活动分类集合
     */
    public List<ActivityClassify> selectActivityClassifyList(ActivityClassify activityClassify);

    /**
     * 新增活动分类
     * 
     * @param activityClassify 活动分类
     * @return 结果
     */
    public int insertActivityClassify(ActivityClassify activityClassify);

    /**
     * 修改活动分类
     * 
     * @param activityClassify 活动分类
     * @return 结果
     */
    public int updateActivityClassify(ActivityClassify activityClassify);

    /**
     * 批量删除活动分类
     * 
     * @param classifyIds 需要删除的活动分类主键集合
     * @return 结果
     */
    public int deleteActivityClassifyByClassifyIds(Long[] classifyIds);

    /**
     * 删除活动分类信息
     * 
     * @param classifyId 活动分类主键
     * @return 结果
     */
    public int deleteActivityClassifyByClassifyId(Long classifyId);
}
