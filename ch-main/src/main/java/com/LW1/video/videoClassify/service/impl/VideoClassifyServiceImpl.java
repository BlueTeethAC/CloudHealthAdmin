package com.LW1.video.videoClassify.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.video.videoClassify.mapper.VideoClassifyMapper;
import com.LW1.video.videoClassify.domain.VideoClassify;
import com.LW1.video.videoClassify.service.IVideoClassifyService;

/**
 * 视频课程分类Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@Service
public class VideoClassifyServiceImpl implements IVideoClassifyService 
{
    @Autowired
    private VideoClassifyMapper videoClassifyMapper;

    /**
     * 查询视频课程分类
     * 
     * @param classifyId 视频课程分类主键
     * @return 视频课程分类
     */
    @Override
    public VideoClassify selectVideoClassifyByClassifyId(Long classifyId)
    {
        return videoClassifyMapper.selectVideoClassifyByClassifyId(classifyId);
    }

    /**
     * 查询视频课程分类列表
     * 
     * @param videoClassify 视频课程分类
     * @return 视频课程分类
     */
    @Override
    public List<VideoClassify> selectVideoClassifyList(VideoClassify videoClassify)
    {
        return videoClassifyMapper.selectVideoClassifyList(videoClassify);
    }

    /**
     * 新增视频课程分类
     * 
     * @param videoClassify 视频课程分类
     * @return 结果
     */
    @Override
    public int insertVideoClassify(VideoClassify videoClassify)
    {
        return videoClassifyMapper.insertVideoClassify(videoClassify);
    }

    /**
     * 修改视频课程分类
     * 
     * @param videoClassify 视频课程分类
     * @return 结果
     */
    @Override
    public int updateVideoClassify(VideoClassify videoClassify)
    {
        return videoClassifyMapper.updateVideoClassify(videoClassify);
    }

    /**
     * 批量删除视频课程分类
     * 
     * @param classifyIds 需要删除的视频课程分类主键
     * @return 结果
     */
    @Override
    public int deleteVideoClassifyByClassifyIds(Long[] classifyIds)
    {
        return videoClassifyMapper.deleteVideoClassifyByClassifyIds(classifyIds);
    }

    /**
     * 删除视频课程分类信息
     * 
     * @param classifyId 视频课程分类主键
     * @return 结果
     */
    @Override
    public int deleteVideoClassifyByClassifyId(Long classifyId)
    {
        return videoClassifyMapper.deleteVideoClassifyByClassifyId(classifyId);
    }
}
