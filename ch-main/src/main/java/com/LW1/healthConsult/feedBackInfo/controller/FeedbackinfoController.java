package com.LW1.healthConsult.feedBackInfo.controller;

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
import com.LW1.healthConsult.feedBackInfo.domain.Feedbackinfo;
import com.LW1.healthConsult.feedBackInfo.service.IFeedbackinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资讯信息反馈Controller
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/healthConsult/feedBackInfo")
public class FeedbackinfoController extends BaseController
{
    @Autowired
    private IFeedbackinfoService feedbackinfoService;

    /**
     * 查询资讯信息反馈列表
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:feedBackInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Feedbackinfo feedbackinfo)
    {
        startPage();
        List<Feedbackinfo> list = feedbackinfoService.selectFeedbackinfoList(feedbackinfo);
        return getDataTable(list);
    }

    /**
     * 导出资讯信息反馈列表
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:feedBackInfo:export')")
    @Log(title = "资讯信息反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Feedbackinfo feedbackinfo)
    {
        List<Feedbackinfo> list = feedbackinfoService.selectFeedbackinfoList(feedbackinfo);
        ExcelUtil<Feedbackinfo> util = new ExcelUtil<Feedbackinfo>(Feedbackinfo.class);
        util.exportExcel(response, list, "资讯信息反馈数据");
    }

    /**
     * 获取资讯信息反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:feedBackInfo:query')")
    @GetMapping(value = "/{feedbackinfoId}")
    public AjaxResult getInfo(@PathVariable("feedbackinfoId") Long feedbackinfoId)
    {
        return success(feedbackinfoService.selectFeedbackinfoByFeedbackinfoId(feedbackinfoId));
    }

    /**
     * 新增资讯信息反馈
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:feedBackInfo:add')")
    @Log(title = "资讯信息反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Feedbackinfo feedbackinfo)
    {
        return toAjax(feedbackinfoService.insertFeedbackinfo(feedbackinfo));
    }

    /**
     * 修改资讯信息反馈
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:feedBackInfo:edit')")
    @Log(title = "资讯信息反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Feedbackinfo feedbackinfo)
    {
        return toAjax(feedbackinfoService.updateFeedbackinfo(feedbackinfo));
    }

    /**
     * 删除资讯信息反馈
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:feedBackInfo:remove')")
    @Log(title = "资讯信息反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feedbackinfoIds}")
    public AjaxResult remove(@PathVariable Long[] feedbackinfoIds)
    {
        return toAjax(feedbackinfoService.deleteFeedbackinfoByFeedbackinfoIds(feedbackinfoIds));
    }


    // 查询历史信息用的接口
    @GetMapping("/list/{consultationFormId}")
    public TableDataInfo getListInfoWithRole(@PathVariable("consultationFormId") Long consultationFormId){
        System.err.println(feedbackinfoService.getListInfoWithRole(consultationFormId));
        return getDataTable(feedbackinfoService.getListInfoWithRole(consultationFormId));
    }

}
