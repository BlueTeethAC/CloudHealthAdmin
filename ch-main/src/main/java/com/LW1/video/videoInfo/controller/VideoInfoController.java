package com.LW1.video.videoInfo.controller;

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
import com.LW1.video.videoInfo.domain.VideoInfo;
import com.LW1.video.videoInfo.service.IVideoInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 线上课程视频Controller
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/video/videoInfo")
public class VideoInfoController extends BaseController
{
    @Autowired
    private IVideoInfoService videoInfoService;

    /**
     * 查询线上课程视频列表
     */
    @PreAuthorize("@ss.hasPermi('video:videoInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(VideoInfo videoInfo)
    {
        startPage();
        List<VideoInfo> list = videoInfoService.selectVideoInfoList(videoInfo);
        return getDataTable(list);
    }

    /**
     * 导出线上课程视频列表
     */
    @PreAuthorize("@ss.hasPermi('video:videoInfo:export')")
    @Log(title = "线上课程视频", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VideoInfo videoInfo)
    {
        List<VideoInfo> list = videoInfoService.selectVideoInfoList(videoInfo);
        ExcelUtil<VideoInfo> util = new ExcelUtil<VideoInfo>(VideoInfo.class);
        util.exportExcel(response, list, "线上课程视频数据");
    }

    /**
     * 获取线上课程视频详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:videoInfo:query')")
    @GetMapping(value = "/{videoId}")
    public AjaxResult getInfo(@PathVariable("videoId") Long videoId)
    {
        return success(videoInfoService.selectVideoInfoByVideoId(videoId));
    }

    /**
     * 新增线上课程视频
     */
    @PreAuthorize("@ss.hasPermi('video:videoInfo:add')")
    @Log(title = "线上课程视频", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VideoInfo videoInfo)
    {
        return toAjax(videoInfoService.insertVideoInfo(videoInfo));
    }

    /**
     * 修改线上课程视频
     */
    @PreAuthorize("@ss.hasPermi('video:videoInfo:edit')")
    @Log(title = "线上课程视频", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VideoInfo videoInfo)
    {
        return toAjax(videoInfoService.updateVideoInfo(videoInfo));
    }

    /**
     * 删除线上课程视频
     */
    @PreAuthorize("@ss.hasPermi('video:videoInfo:remove')")
    @Log(title = "线上课程视频", businessType = BusinessType.DELETE)
	@DeleteMapping("/{videoIds}")
    public AjaxResult remove(@PathVariable Long[] videoIds)
    {
        return toAjax(videoInfoService.deleteVideoInfoByVideoIds(videoIds));
    }
}
