package com.LW1.video.seriesSubscribe.mapper;

import java.util.List;
import com.LW1.video.seriesSubscribe.domain.SeriesSubscribe;

/**
 * 系列订阅Mapper接口
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
public interface SeriesSubscribeMapper 
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
     * 删除系列订阅
     * 
     * @param id 系列订阅主键
     * @return 结果
     */
    public int deleteSeriesSubscribeById(Long id);

    /**
     * 批量删除系列订阅
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSeriesSubscribeByIds(Long[] ids);
}
