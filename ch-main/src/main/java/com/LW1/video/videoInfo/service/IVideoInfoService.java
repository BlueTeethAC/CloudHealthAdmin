package com.LW1.video.videoInfo.service;

import java.util.List;
import com.LW1.video.videoInfo.domain.VideoInfo;

/**
 * 线上课程视频Service接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public interface IVideoInfoService 
{
    /**
     * 查询线上课程视频
     * 
     * @param videoId 线上课程视频主键
     * @return 线上课程视频
     */
    public VideoInfo selectVideoInfoByVideoId(Long videoId);

    /**
     * 查询线上课程视频列表
     * 
     * @param videoInfo 线上课程视频
     * @return 线上课程视频集合
     */
    public List<VideoInfo> selectVideoInfoList(VideoInfo videoInfo);

    /**
     * 新增线上课程视频
     * 
     * @param videoInfo 线上课程视频
     * @return 结果
     */
    public int insertVideoInfo(VideoInfo videoInfo);

    /**
     * 修改线上课程视频
     * 
     * @param videoInfo 线上课程视频
     * @return 结果
     */
    public int updateVideoInfo(VideoInfo videoInfo);

    /**
     * 批量删除线上课程视频
     * 
     * @param videoIds 需要删除的线上课程视频主键集合
     * @return 结果
     */
    public int deleteVideoInfoByVideoIds(Long[] videoIds);

    /**
     * 删除线上课程视频信息
     * 
     * @param videoId 线上课程视频主键
     * @return 结果
     */
    public int deleteVideoInfoByVideoId(Long videoId);
}
