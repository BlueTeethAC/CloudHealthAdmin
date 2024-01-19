package com.LW1.activity.application.controller;

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
import com.LW1.activity.application.domain.ActivityApplication;
import com.LW1.activity.application.service.IActivityApplicationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动报名Controller
 * 
 * @author BlueTeethAC
 * @date 2024-01-19
 */
@RestController
@RequestMapping("/activity/application")
public class ActivityApplicationController extends BaseController
{
    @Autowired
    private IActivityApplicationService activityApplicationService;

    /**
     * 查询活动报名列表
     */
    @PreAuthorize("@ss.hasPermi('activity:application:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActivityApplication activityApplication)
    {
        startPage();
        List<ActivityApplication> list = activityApplicationService.selectActivityApplicationList(activityApplication);
        return getDataTable(list);
    }

    /**
     * 导出活动报名列表
     */
    @PreAuthorize("@ss.hasPermi('activity:application:export')")
    @Log(title = "活动报名", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActivityApplication activityApplication)
    {
        List<ActivityApplication> list = activityApplicationService.selectActivityApplicationList(activityApplication);
        ExcelUtil<ActivityApplication> util = new ExcelUtil<ActivityApplication>(ActivityApplication.class);
        util.exportExcel(response, list, "活动报名数据");
    }

    /**
     * 获取活动报名详细信息
     */
    @PreAuthorize("@ss.hasPermi('activity:application:query')")
    @GetMapping(value = "/{infoId}")
    public AjaxResult getInfo(@PathVariable("infoId") Long infoId)
    {
        return success(activityApplicationService.selectActivityApplicationByInfoId(infoId));
    }

    /**
     * 新增活动报名
     */
    @PreAuthorize("@ss.hasPermi('activity:application:add')")
    @Log(title = "活动报名", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActivityApplication activityApplication)
    {
        return toAjax(activityApplicationService.insertActivityApplication(activityApplication));
    }

    /**
     * 修改活动报名
     */
    @PreAuthorize("@ss.hasPermi('activity:application:edit')")
    @Log(title = "活动报名", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActivityApplication activityApplication)
    {
        return toAjax(activityApplicationService.updateActivityApplication(activityApplication));
    }

    /**
     * 删除活动报名
     */
    @PreAuthorize("@ss.hasPermi('activity:application:remove')")
    @Log(title = "活动报名", businessType = BusinessType.DELETE)
	@DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(activityApplicationService.deleteActivityApplicationByInfoIds(infoIds));
    }
}
