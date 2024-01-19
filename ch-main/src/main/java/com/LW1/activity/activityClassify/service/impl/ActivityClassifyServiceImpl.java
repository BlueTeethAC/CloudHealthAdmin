package com.LW1.activity.activityClassify.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.activity.activityClassify.mapper.ActivityClassifyMapper;
import com.LW1.activity.activityClassify.domain.ActivityClassify;
import com.LW1.activity.activityClassify.service.IActivityClassifyService;

/**
 * 活动分类Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-19
 */
@Service
public class ActivityClassifyServiceImpl implements IActivityClassifyService 
{
    @Autowired
    private ActivityClassifyMapper activityClassifyMapper;

    /**
     * 查询活动分类
     * 
     * @param classifyId 活动分类主键
     * @return 活动分类
     */
    @Override
    public ActivityClassify selectActivityClassifyByClassifyId(Long classifyId)
    {
        return activityClassifyMapper.selectActivityClassifyByClassifyId(classifyId);
    }

    /**
     * 查询活动分类列表
     * 
     * @param activityClassify 活动分类
     * @return 活动分类
     */
    @Override
    public List<ActivityClassify> selectActivityClassifyList(ActivityClassify activityClassify)
    {
        return activityClassifyMapper.selectActivityClassifyList(activityClassify);
    }

    /**
     * 新增活动分类
     * 
     * @param activityClassify 活动分类
     * @return 结果
     */
    @Override
    public int insertActivityClassify(ActivityClassify activityClassify)
    {
        return activityClassifyMapper.insertActivityClassify(activityClassify);
    }

    /**
     * 修改活动分类
     * 
     * @param activityClassify 活动分类
     * @return 结果
     */
    @Override
    public int updateActivityClassify(ActivityClassify activityClassify)
    {
        return activityClassifyMapper.updateActivityClassify(activityClassify);
    }

    /**
     * 批量删除活动分类
     * 
     * @param classifyIds 需要删除的活动分类主键
     * @return 结果
     */
    @Override
    public int deleteActivityClassifyByClassifyIds(Long[] classifyIds)
    {
        return activityClassifyMapper.deleteActivityClassifyByClassifyIds(classifyIds);
    }

    /**
     * 删除活动分类信息
     * 
     * @param classifyId 活动分类主键
     * @return 结果
     */
    @Override
    public int deleteActivityClassifyByClassifyId(Long classifyId)
    {
        return activityClassifyMapper.deleteActivityClassifyByClassifyId(classifyId);
    }
}
