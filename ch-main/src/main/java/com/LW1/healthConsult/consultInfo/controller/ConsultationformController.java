package com.LW1.healthConsult.consultInfo.controller;

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
import com.LW1.healthConsult.consultInfo.domain.Consultationform;
import com.LW1.healthConsult.consultInfo.service.IConsultationformService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 咨询信息Controller
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/healthConsult/consultationForm")
public class ConsultationformController extends BaseController
{
    @Autowired
    private IConsultationformService consultationformService;

    /**
     * 查询咨询信息列表
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:consultationForm:list')")
    @GetMapping("/list")
    public TableDataInfo list(Consultationform consultationform)
    {
        startPage();
        List<Consultationform> list = consultationformService.selectConsultationformList(consultationform);
        return getDataTable(list);
    }

    /**
     * 导出咨询信息列表
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:consultationForm:export')")
    @Log(title = "咨询信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Consultationform consultationform)
    {
        List<Consultationform> list = consultationformService.selectConsultationformList(consultationform);
        ExcelUtil<Consultationform> util = new ExcelUtil<Consultationform>(Consultationform.class);
        util.exportExcel(response, list, "咨询信息数据");
    }

    /**
     * 获取咨询信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:consultationForm:query')")
    @GetMapping(value = "/{ConsultationFormId}")
    public AjaxResult getInfo(@PathVariable("ConsultationFormId") Long ConsultationFormId)
    {
        return success(consultationformService.selectConsultationformByConsultationFormId(ConsultationFormId));
    }

    /**
     * 新增咨询信息
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:consultationForm:add')")
    @Log(title = "咨询信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Consultationform consultationform)
    {
        return toAjax(consultationformService.insertConsultationform(consultationform));
    }

    /**
     * 修改咨询信息
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:consultationForm:edit')")
    @Log(title = "咨询信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Consultationform consultationform)
    {
        return toAjax(consultationformService.updateConsultationform(consultationform));
    }

    /**
     * 删除咨询信息
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:consultationForm:remove')")
    @Log(title = "咨询信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ConsultationFormIds}")
    public AjaxResult remove(@PathVariable Long[] ConsultationFormIds)
    {
        return toAjax(consultationformService.deleteConsultationformByConsultationFormIds(ConsultationFormIds));
    }
}
