package com.LW1.activity.activityClassify.mapper;

import java.util.List;
import com.LW1.activity.activityClassify.domain.ActivityClassify;

/**
 * 活动分类Mapper接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-19
 */
public interface ActivityClassifyMapper 
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
     * 删除活动分类
     * 
     * @param classifyId 活动分类主键
     * @return 结果
     */
    public int deleteActivityClassifyByClassifyId(Long classifyId);

    /**
     * 批量删除活动分类
     * 
     * @param classifyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActivityClassifyByClassifyIds(Long[] classifyIds);
}
