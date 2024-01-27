package com.LW1.activity.activityClassify.controller;

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
import com.LW1.activity.activityClassify.domain.ActivityClassify;
import com.LW1.activity.activityClassify.service.IActivityClassifyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动分类Controller
 * 
 * @author BlueTeethAC
 * @date 2024-01-19
 */
@RestController
@RequestMapping("/activity/activityClassify")
public class ActivityClassifyController extends BaseController
{
    @Autowired
    private IActivityClassifyService activityClassifyService;

    /**
     * 查询活动分类列表
     */
    // @PreAuthorize("@ss.hasPermi('activity:activityClassify:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActivityClassify activityClassify)
    {
        startPage();
        List<ActivityClassify> list = activityClassifyService.selectActivityClassifyList(activityClassify);
        return getDataTable(list);
    }

    /**
     * 导出活动分类列表
     */
    @PreAuthorize("@ss.hasPermi('activity:activityClassify:export')")
    @Log(title = "活动分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActivityClassify activityClassify)
    {
        List<ActivityClassify> list = activityClassifyService.selectActivityClassifyList(activityClassify);
        ExcelUtil<ActivityClassify> util = new ExcelUtil<ActivityClassify>(ActivityClassify.class);
        util.exportExcel(response, list, "活动分类数据");
    }

    /**
     * 获取活动分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('activity:activityClassify:query')")
    @GetMapping(value = "/{classifyId}")
    public AjaxResult getInfo(@PathVariable("classifyId") Long classifyId)
    {
        return success(activityClassifyService.selectActivityClassifyByClassifyId(classifyId));
    }

    /**
     * 新增活动分类
     */
    @PreAuthorize("@ss.hasPermi('activity:activityClassify:add')")
    @Log(title = "活动分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActivityClassify activityClassify)
    {
        return toAjax(activityClassifyService.insertActivityClassify(activityClassify));
    }

    /**
     * 修改活动分类
     */
    @PreAuthorize("@ss.hasPermi('activity:activityClassify:edit')")
    @Log(title = "活动分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActivityClassify activityClassify)
    {
        return toAjax(activityClassifyService.updateActivityClassify(activityClassify));
    }

    /**
     * 删除活动分类
     */
    @PreAuthorize("@ss.hasPermi('activity:activityClassify:remove')")
    @Log(title = "活动分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{classifyIds}")
    public AjaxResult remove(@PathVariable Long[] classifyIds)
    {
        return toAjax(activityClassifyService.deleteActivityClassifyByClassifyIds(classifyIds));
    }
}
