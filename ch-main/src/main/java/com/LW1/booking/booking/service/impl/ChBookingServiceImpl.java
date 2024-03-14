package com.LW1.booking.booking.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LW1.booking.booking.mapper.ChBookingMapper;
import com.LW1.booking.booking.domain.ChBooking;
import com.LW1.booking.booking.service.IChBookingService;

/**
 * 私教，健身房预约Service业务层处理
 * 
 * @author BlueTeethAC
 * @date 2024-03-14
 */
@Service
public class ChBookingServiceImpl implements IChBookingService 
{
    @Autowired
    private ChBookingMapper chBookingMapper;

    /**
     * 查询私教，健身房预约
     * 
     * @param infoId 私教，健身房预约主键
     * @return 私教，健身房预约
     */
    @Override
    public ChBooking selectChBookingByInfoId(Long infoId)
    {
        return chBookingMapper.selectChBookingByInfoId(infoId);
    }

    /**
     * 查询私教，健身房预约列表
     * 
     * @param chBooking 私教，健身房预约
     * @return 私教，健身房预约
     */
    @Override
    public List<ChBooking> selectChBookingList(ChBooking chBooking)
    {
        return chBookingMapper.selectChBookingList(chBooking);
    }

    /**
     * 新增私教，健身房预约
     * 
     * @param chBooking 私教，健身房预约
     * @return 结果
     */
    @Override
    public int insertChBooking(ChBooking chBooking)
    {
        return chBookingMapper.insertChBooking(chBooking);
    }

    /**
     * 修改私教，健身房预约
     * 
     * @param chBooking 私教，健身房预约
     * @return 结果
     */
    @Override
    public int updateChBooking(ChBooking chBooking)
    {
        return chBookingMapper.updateChBooking(chBooking);
    }

    /**
     * 批量删除私教，健身房预约
     * 
     * @param infoIds 需要删除的私教，健身房预约主键
     * @return 结果
     */
    @Override
    public int deleteChBookingByInfoIds(Long[] infoIds)
    {
        return chBookingMapper.deleteChBookingByInfoIds(infoIds);
    }

    /**
     * 删除私教，健身房预约信息
     * 
     * @param infoId 私教，健身房预约主键
     * @return 结果
     */
    @Override
    public int deleteChBookingByInfoId(Long infoId)
    {
        return chBookingMapper.deleteChBookingByInfoId(infoId);
    }
}
