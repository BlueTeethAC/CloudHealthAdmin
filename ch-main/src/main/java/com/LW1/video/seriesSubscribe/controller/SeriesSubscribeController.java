package com.LW1.video.seriesSubscribe.controller;

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
import com.LW1.video.seriesSubscribe.domain.SeriesSubscribe;
import com.LW1.video.seriesSubscribe.service.ISeriesSubscribeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 系列订阅Controller
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/video/seriesSubscribe")
public class SeriesSubscribeController extends BaseController
{
    @Autowired
    private ISeriesSubscribeService seriesSubscribeService;

    /**
     * 查询系列订阅列表
     */
    @PreAuthorize("@ss.hasPermi('video:seriesSubscribe:list')")
    @GetMapping("/list")
    public TableDataInfo list(SeriesSubscribe seriesSubscribe)
    {
        startPage();
        List<SeriesSubscribe> list = seriesSubscribeService.selectSeriesSubscribeList(seriesSubscribe);
        return getDataTable(list);
    }

    /**
     * 导出系列订阅列表
     */
    @PreAuthorize("@ss.hasPermi('video:seriesSubscribe:export')")
    @Log(title = "系列订阅", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SeriesSubscribe seriesSubscribe)
    {
        List<SeriesSubscribe> list = seriesSubscribeService.selectSeriesSubscribeList(seriesSubscribe);
        ExcelUtil<SeriesSubscribe> util = new ExcelUtil<SeriesSubscribe>(SeriesSubscribe.class);
        util.exportExcel(response, list, "系列订阅数据");
    }

    /**
     * 获取系列订阅详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:seriesSubscribe:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(seriesSubscribeService.selectSeriesSubscribeById(id));
    }

    /**
     * 新增系列订阅
     */
    @PreAuthorize("@ss.hasPermi('video:seriesSubscribe:add')")
    @Log(title = "系列订阅", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SeriesSubscribe seriesSubscribe)
    {
        return toAjax(seriesSubscribeService.insertSeriesSubscribe(seriesSubscribe));
    }

    /**
     * 修改系列订阅
     */
    @PreAuthorize("@ss.hasPermi('video:seriesSubscribe:edit')")
    @Log(title = "系列订阅", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SeriesSubscribe seriesSubscribe)
    {
        return toAjax(seriesSubscribeService.updateSeriesSubscribe(seriesSubscribe));
    }

    /**
     * 删除系列订阅
     */
    @PreAuthorize("@ss.hasPermi('video:seriesSubscribe:remove')")
    @Log(title = "系列订阅", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(seriesSubscribeService.deleteSeriesSubscribeByIds(ids));
    }
}
