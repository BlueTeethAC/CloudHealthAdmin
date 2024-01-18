package com.LW1.video.seriesInfo.controller;

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
import com.LW1.video.seriesInfo.domain.VideoSeries;
import com.LW1.video.seriesInfo.service.IVideoSeriesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频系列Controller
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/video/seriesInfo")
public class VideoSeriesController extends BaseController
{
    @Autowired
    private IVideoSeriesService videoSeriesService;

    /**
     * 查询视频系列列表
     */
    @PreAuthorize("@ss.hasPermi('video:seriesInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(VideoSeries videoSeries)
    {
        startPage();
        List<VideoSeries> list = videoSeriesService.selectVideoSeriesList(videoSeries);
        return getDataTable(list);
    }

    /**
     * 导出视频系列列表
     */
    @PreAuthorize("@ss.hasPermi('video:seriesInfo:export')")
    @Log(title = "视频系列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VideoSeries videoSeries)
    {
        List<VideoSeries> list = videoSeriesService.selectVideoSeriesList(videoSeries);
        ExcelUtil<VideoSeries> util = new ExcelUtil<VideoSeries>(VideoSeries.class);
        util.exportExcel(response, list, "视频系列数据");
    }

    /**
     * 获取视频系列详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:seriesInfo:query')")
    @GetMapping(value = "/{seriesId}")
    public AjaxResult getInfo(@PathVariable("seriesId") Long seriesId)
    {
        return success(videoSeriesService.selectVideoSeriesBySeriesId(seriesId));
    }

    /**
     * 新增视频系列
     */
    @PreAuthorize("@ss.hasPermi('video:seriesInfo:add')")
    @Log(title = "视频系列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VideoSeries videoSeries)
    {
        return toAjax(videoSeriesService.insertVideoSeries(videoSeries));
    }

    /**
     * 修改视频系列
     */
    @PreAuthorize("@ss.hasPermi('video:seriesInfo:edit')")
    @Log(title = "视频系列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VideoSeries videoSeries)
    {
        return toAjax(videoSeriesService.updateVideoSeries(videoSeries));
    }

    /**
     * 删除视频系列
     */
    @PreAuthorize("@ss.hasPermi('video:seriesInfo:remove')")
    @Log(title = "视频系列", businessType = BusinessType.DELETE)
	@DeleteMapping("/{seriesIds}")
    public AjaxResult remove(@PathVariable Long[] seriesIds)
    {
        return toAjax(videoSeriesService.deleteVideoSeriesBySeriesIds(seriesIds));
    }
}
