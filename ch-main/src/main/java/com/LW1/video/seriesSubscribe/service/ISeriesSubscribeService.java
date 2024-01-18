package com.LW1.video.seriesSubscribe.service;

import java.util.List;
import com.LW1.video.seriesSubscribe.domain.SeriesSubscribe;

/**
 * 系列订阅Service接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public interface ISeriesSubscribeService 
{
    /**
     * 查询系列订阅
     * 
     * @param id 系列订阅主键
     * @return 系列订阅
     */
    public SeriesSubscribe selectSeriesSubscribeById(Long id);

    /**
     * 查询系列订阅列表
     * 
     * @param seriesSubscribe 系列订阅
     * @return 系列订阅集合
     */
    public List<SeriesSubscribe> selectSeriesSubscribeList(SeriesSubscribe seriesSubscribe);

    /**
     * 新增系列订阅
     * 
     * @param seriesSubscribe 系列订阅
     * @return 结果
     */
    public int insertSeriesSubscribe(SeriesSubscribe seriesSubscribe);

    /**
     * 修改系列订阅
     * 
     * @param seriesSubscribe 系列订阅
     * @return 结果
     */
    public int updateSeriesSubscribe(SeriesSubscribe seriesSubscribe);

    /**
     * 批量删除系列订阅
     * 
     * @param ids 需要删除的系列订阅主键集合
     * @return 结果
     */
    public int deleteSeriesSubscribeByIds(Long[] ids);

    /**
     * 删除系列订阅信息
     * 
     * @param id 系列订阅主键
     * @return 结果
     */
    public int deleteSeriesSubscribeById(Long id);
}
