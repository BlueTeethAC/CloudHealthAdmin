package com.LW1.video.seriesSubscribe.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.video.seriesSubscribe.mapper.SeriesSubscribeMapper;
import com.LW1.video.seriesSubscribe.domain.SeriesSubscribe;
import com.LW1.video.seriesSubscribe.service.ISeriesSubscribeService;

/**
 * 系列订阅Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@Service
public class SeriesSubscribeServiceImpl implements ISeriesSubscribeService 
{
    @Autowired
    private SeriesSubscribeMapper seriesSubscribeMapper;

    /**
     * 查询系列订阅
     * 
     * @param id 系列订阅主键
     * @return 系列订阅
     */
    @Override
    public SeriesSubscribe selectSeriesSubscribeById(Long id)
    {
        return seriesSubscribeMapper.selectSeriesSubscribeById(id);
    }

    /**
     * 查询系列订阅列表
     * 
     * @param seriesSubscribe 系列订阅
     * @return 系列订阅
     */
    @Override
    public List<SeriesSubscribe> selectSeriesSubscribeList(SeriesSubscribe seriesSubscribe)
    {
        return seriesSubscribeMapper.selectSeriesSubscribeList(seriesSubscribe);
    }

    /**
     * 新增系列订阅
     * 
     * @param seriesSubscribe 系列订阅
     * @return 结果
     */
    @Override
    public int insertSeriesSubscribe(SeriesSubscribe seriesSubscribe)
    {
        return seriesSubscribeMapper.insertSeriesSubscribe(seriesSubscribe);
    }

    /**
     * 修改系列订阅
     * 
     * @param seriesSubscribe 系列订阅
     * @return 结果
     */
    @Override
    public int updateSeriesSubscribe(SeriesSubscribe seriesSubscribe)
    {
        return seriesSubscribeMapper.updateSeriesSubscribe(seriesSubscribe);
    }

    /**
     * 批量删除系列订阅
     * 
     * @param ids 需要删除的系列订阅主键
     * @return 结果
     */
    @Override
    public int deleteSeriesSubscribeByIds(Long[] ids)
    {
        return seriesSubscribeMapper.deleteSeriesSubscribeByIds(ids);
    }

    /**
     * 删除系列订阅信息
     * 
     * @param id 系列订阅主键
     * @return 结果
     */
    @Override
    public int deleteSeriesSubscribeById(Long id)
    {
        return seriesSubscribeMapper.deleteSeriesSubscribeById(id);
    }
}
