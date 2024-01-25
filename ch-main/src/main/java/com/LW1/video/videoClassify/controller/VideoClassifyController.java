package com.LW1.video.videoClassify.controller;

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
import com.LW1.video.videoClassify.domain.VideoClassify;
import com.LW1.video.videoClassify.service.IVideoClassifyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频课程分类Controller
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/video/videoClassify")
public class VideoClassifyController extends BaseController
{
    @Autowired
    private IVideoClassifyService videoClassifyService;

    /**
     * 查询视频课程分类列表
     */
    // @PreAuthorize("@ss.hasPermi('video:videoClassify:list')")
    @GetMapping("/list")
    public TableDataInfo list(VideoClassify videoClassify)
    {
        startPage();
        List<VideoClassify> list = videoClassifyService.selectVideoClassifyList(videoClassify);
        return getDataTable(list);
    }

    /**
     * 导出视频课程分类列表
     */
    @PreAuthorize("@ss.hasPermi('video:videoClassify:export')")
    @Log(title = "视频课程分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VideoClassify videoClassify)
    {
        List<VideoClassify> list = videoClassifyService.selectVideoClassifyList(videoClassify);
        ExcelUtil<VideoClassify> util = new ExcelUtil<VideoClassify>(VideoClassify.class);
        util.exportExcel(response, list, "视频课程分类数据");
    }

    /**
     * 获取视频课程分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:videoClassify:query')")
    @GetMapping(value = "/{classifyId}")
    public AjaxResult getInfo(@PathVariable("classifyId") Long classifyId)
    {
        return success(videoClassifyService.selectVideoClassifyByClassifyId(classifyId));
    }

    /**
     * 新增视频课程分类
     */
    @PreAuthorize("@ss.hasPermi('video:videoClassify:add')")
    @Log(title = "视频课程分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VideoClassify videoClassify)
    {
        return toAjax(videoClassifyService.insertVideoClassify(videoClassify));
    }

    /**
     * 修改视频课程分类
     */
    @PreAuthorize("@ss.hasPermi('video:videoClassify:edit')")
    @Log(title = "视频课程分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VideoClassify videoClassify)
    {
        return toAjax(videoClassifyService.updateVideoClassify(videoClassify));
    }

    /**
     * 删除视频课程分类
     */
    @PreAuthorize("@ss.hasPermi('video:videoClassify:remove')")
    @Log(title = "视频课程分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{classifyIds}")
    public AjaxResult remove(@PathVariable Long[] classifyIds)
    {
        return toAjax(videoClassifyService.deleteVideoClassifyByClassifyIds(classifyIds));
    }
}
