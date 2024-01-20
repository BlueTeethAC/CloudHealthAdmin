package com.LW1.video.videoInfo.service.impl;

import java.util.Date;
import java.util.List;

import com.LW1.utils.DateUtil;
import com.LW1.utils.GetDateTimeUtilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.video.videoInfo.mapper.VideoInfoMapper;
import com.LW1.video.videoInfo.domain.VideoInfo;
import com.LW1.video.videoInfo.service.IVideoInfoService;

/**
 * 线上课程视频Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@Service
public class VideoInfoServiceImpl implements IVideoInfoService 
{
    @Autowired
    private VideoInfoMapper videoInfoMapper;

    /**
     * 查询线上课程视频
     * 
     * @param videoId 线上课程视频主键
     * @return 线上课程视频
     */
    @Override
    public VideoInfo selectVideoInfoByVideoId(Long videoId)
    {
        return videoInfoMapper.selectVideoInfoByVideoId(videoId);
    }

    /**
     * 查询线上课程视频列表
     * 
     * @param videoInfo 线上课程视频
     * @return 线上课程视频
     */
    @Override
    public List<VideoInfo> selectVideoInfoList(VideoInfo videoInfo)
    {
        return videoInfoMapper.selectVideoInfoList(videoInfo);
    }

    /**
     * 新增线上课程视频
     * 
     * @param videoInfo 线上课程视频
     * @return 结果
     */
    @Override
    public int insertVideoInfo(VideoInfo videoInfo)
    {
        videoInfo.setVideoUploadDate(new Date());// 设置当前时间
        return videoInfoMapper.insertVideoInfo(videoInfo);
    }

    /**
     * 修改线上课程视频
     * 
     * @param videoInfo 线上课程视频
     * @return 结果
     */
    @Override
    public int updateVideoInfo(VideoInfo videoInfo)
    {
        return videoInfoMapper.updateVideoInfo(videoInfo);
    }

    /**
     * 批量删除线上课程视频
     * 
     * @param videoIds 需要删除的线上课程视频主键
     * @return 结果
     */
    @Override
    public int deleteVideoInfoByVideoIds(Long[] videoIds)
    {
        return videoInfoMapper.deleteVideoInfoByVideoIds(videoIds);
    }

    /**
     * 删除线上课程视频信息
     * 
     * @param videoId 线上课程视频主键
     * @return 结果
     */
    @Override
    public int deleteVideoInfoByVideoId(Long videoId)
    {
        return videoInfoMapper.deleteVideoInfoByVideoId(videoId);
    }
}
