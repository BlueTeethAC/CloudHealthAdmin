package com.LW1.booking.booking.mapper;

import java.util.List;
import com.LW1.booking.booking.domain.ChBooking;

/**
 * 私教，健身房预约Mapper接口
 * 
 * @author BlueTeethAC
 * @date 2024-03-14
 */
public interface ChBookingMapper 
{
    /**
     * 查询私教，健身房预约
     * 
     * @param infoId 私教，健身房预约主键
     * @return 私教，健身房预约
     */
    public ChBooking selectChBookingByInfoId(Long infoId);

    /**
     * 查询私教，健身房预约列表
     * 
     * @param chBooking 私教，健身房预约
     * @return 私教，健身房预约集合
     */
    public List<ChBooking> selectChBookingList(ChBooking chBooking);

    /**
     * 新增私教，健身房预约
     * 
     * @param chBooking 私教，健身房预约
     * @return 结果
     */
    public int insertChBooking(ChBooking chBooking);

    /**
     * 修改私教，健身房预约
     * 
     * @param chBooking 私教，健身房预约
     * @return 结果
     */
    public int updateChBooking(ChBooking chBooking);

    /**
     * 删除私教，健身房预约
     * 
     * @param infoId 私教，健身房预约主键
     * @return 结果
     */
    public int deleteChBookingByInfoId(Long infoId);

    /**
     * 批量删除私教，健身房预约
     * 
     * @param infoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChBookingByInfoIds(Long[] infoIds);
}
