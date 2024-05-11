package com.LW1.booking.booking.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.LW1.booking.booking.domain.ChBooking;
import com.LW1.booking.booking.service.IChBookingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 私教，健身房预约Controller
 * 
 * @author BlueTeethAC
 * @date 2024-03-14
 */
@RestController
@RequestMapping("/booking/booking")
public class ChBookingController extends BaseController
{
    @Autowired
    private IChBookingService chBookingService;

    /**
     * 查询私教，健身房预约列表
     */
    // @PreAuthorize("@ss.hasPermi('booking:booking:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChBooking chBooking)
    {
        startPage();
        List<ChBooking> list = chBookingService.selectChBookingList(chBooking);
        return getDataTable(list);
    }

    /**
     * 导出私教，健身房预约列表
     */
    // @PreAuthorize("@ss.hasPermi('booking:booking:export')")
    @Log(title = "私教，健身房预约", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChBooking chBooking)
    {
        List<ChBooking> list = chBookingService.selectChBookingList(chBooking);
        ExcelUtil<ChBooking> util = new ExcelUtil<ChBooking>(ChBooking.class);
        util.exportExcel(response, list, "私教，健身房预约数据");
    }

    /**
     * 获取私教，健身房预约详细信息
     */
    // @PreAuthorize("@ss.hasPermi('booking:booking:query')")
    @GetMapping(value = "/{infoId}")
    public AjaxResult getInfo(@PathVariable("infoId") Long infoId)
    {
        return success(chBookingService.selectChBookingByInfoId(infoId));
    }

    /**
     * 新增私教，健身房预约
     */
    // @PreAuthorize("@ss.hasPermi('booking:booking:add')")
    @Log(title = "私教，健身房预约", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChBooking chBooking)
    {
        return toAjax(chBookingService.insertChBooking(chBooking));
    }

    /**
     * 修改私教，健身房预约
     */
    // @PreAuthorize("@ss.hasPermi('booking:booking:edit')")
    @Log(title = "私教，健身房预约", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChBooking chBooking)
    {
        return toAjax(chBookingService.updateChBooking(chBooking));
    }

    /**
     * 删除私教，健身房预约
     */
    // @PreAuthorize("@ss.hasPermi('booking:booking:remove')")
    @Log(title = "私教，健身房预约", businessType = BusinessType.DELETE)
	@DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(chBookingService.deleteChBookingByInfoIds(infoIds));
    }
}
