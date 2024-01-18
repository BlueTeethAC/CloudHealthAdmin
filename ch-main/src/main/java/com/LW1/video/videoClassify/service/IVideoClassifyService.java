package com.LW1.video.videoClassify.service;

import java.util.List;
import com.LW1.video.videoClassify.domain.VideoClassify;

/**
 * 视频课程分类Service接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public interface IVideoClassifyService 
{
    /**
     * 查询视频课程分类
     * 
     * @param classifyId 视频课程分类主键
     * @return 视频课程分类
     */
    public VideoClassify selectVideoClassifyByClassifyId(Long classifyId);

    /**
     * 查询视频课程分类列表
     * 
     * @param videoClassify 视频课程分类
     * @return 视频课程分类集合
     */
    public List<VideoClassify> selectVideoClassifyList(VideoClassify videoClassify);

    /**
     * 新增视频课程分类
     * 
     * @param videoClassify 视频课程分类
     * @return 结果
     */
    public int insertVideoClassify(VideoClassify videoClassify);

    /**
     * 修改视频课程分类
     * 
     * @param videoClassify 视频课程分类
     * @return 结果
     */
    public int updateVideoClassify(VideoClassify videoClassify);

    /**
     * 批量删除视频课程分类
     * 
     * @param classifyIds 需要删除的视频课程分类主键集合
     * @return 结果
     */
    public int deleteVideoClassifyByClassifyIds(Long[] classifyIds);

    /**
     * 删除视频课程分类信息
     * 
     * @param classifyId 视频课程分类主键
     * @return 结果
     */
    public int deleteVideoClassifyByClassifyId(Long classifyId);
}
