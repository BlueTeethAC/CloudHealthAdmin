package com.LW1.video.seriesInfo.service;

import java.util.List;
import com.LW1.video.seriesInfo.domain.VideoSeries;

/**
 * 视频系列Service接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-22
 */
public interface IVideoSeriesService 
{
    /**
     * 查询视频系列
     * 
     * @param seriesId 视频系列主键
     * @return 视频系列
     */
    public VideoSeries selectVideoSeriesBySeriesId(Long seriesId);

    /**
     * 查询视频系列列表
     * 
     * @param videoSeries 视频系列
     * @return 视频系列集合
     */
    public List<VideoSeries> selectVideoSeriesList(VideoSeries videoSeries);

    /**
     * 新增视频系列
     * 
     * @param videoSeries 视频系列
     * @return 结果
     */
    public int insertVideoSeries(VideoSeries videoSeries);

    /**
     * 修改视频系列
     * 
     * @param videoSeries 视频系列
     * @return 结果
     */
    public int updateVideoSeries(VideoSeries videoSeries);

    /**
     * 批量删除视频系列
     * 
     * @param seriesIds 需要删除的视频系列主键集合
     * @return 结果
     */
    public int deleteVideoSeriesBySeriesIds(Long[] seriesIds);

    /**
     * 删除视频系列信息
     * 
     * @param seriesId 视频系列主键
     * @return 结果
     */
    public int deleteVideoSeriesBySeriesId(Long seriesId);
}
