package com.LW1.video.seriesInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.video.seriesInfo.mapper.VideoSeriesMapper;
import com.LW1.video.seriesInfo.domain.VideoSeries;
import com.LW1.video.seriesInfo.service.IVideoSeriesService;

/**
 * 视频系列Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@Service
public class VideoSeriesServiceImpl implements IVideoSeriesService 
{
    @Autowired
    private VideoSeriesMapper videoSeriesMapper;

    /**
     * 查询视频系列
     * 
     * @param seriesId 视频系列主键
     * @return 视频系列
     */
    @Override
    public VideoSeries selectVideoSeriesBySeriesId(Long seriesId)
    {
        return videoSeriesMapper.selectVideoSeriesBySeriesId(seriesId);
    }

    /**
     * 查询视频系列列表
     * 
     * @param videoSeries 视频系列
     * @return 视频系列
     */
    @Override
    public List<VideoSeries> selectVideoSeriesList(VideoSeries videoSeries)
    {
        return videoSeriesMapper.selectVideoSeriesList(videoSeries);
    }

    /**
     * 新增视频系列
     * 
     * @param videoSeries 视频系列
     * @return 结果
     */
    @Override
    public int insertVideoSeries(VideoSeries videoSeries)
    {
        return videoSeriesMapper.insertVideoSeries(videoSeries);
    }

    /**
     * 修改视频系列
     * 
     * @param videoSeries 视频系列
     * @return 结果
     */
    @Override
    public int updateVideoSeries(VideoSeries videoSeries)
    {
        return videoSeriesMapper.updateVideoSeries(videoSeries);
    }

    /**
     * 批量删除视频系列
     * 
     * @param seriesIds 需要删除的视频系列主键
     * @return 结果
     */
    @Override
    public int deleteVideoSeriesBySeriesIds(Long[] seriesIds)
    {
        return videoSeriesMapper.deleteVideoSeriesBySeriesIds(seriesIds);
    }

    /**
     * 删除视频系列信息
     * 
     * @param seriesId 视频系列主键
     * @return 结果
     */
    @Override
    public int deleteVideoSeriesBySeriesId(Long seriesId)
    {
        return videoSeriesMapper.deleteVideoSeriesBySeriesId(seriesId);
    }
}
