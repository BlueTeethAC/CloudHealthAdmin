package com.LW1.healthConsult.questionaireInfo.controller;

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
import com.LW1.healthConsult.questionaireInfo.domain.QuestionnaireInfo;
import com.LW1.healthConsult.questionaireInfo.service.IQuestionnaireInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 问卷信息Controller
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/healthConsult/questionaireInfo")
public class QuestionnaireInfoController extends BaseController
{
    @Autowired
    private IQuestionnaireInfoService questionnaireInfoService;

    /**
     * 查询问卷信息列表
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:questionaireInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(QuestionnaireInfo questionnaireInfo)
    {
        startPage();
        List<QuestionnaireInfo> list = questionnaireInfoService.selectQuestionnaireInfoList(questionnaireInfo);
        return getDataTable(list);
    }

    /**
     * 导出问卷信息列表
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:questionaireInfo:export')")
    @Log(title = "问卷信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QuestionnaireInfo questionnaireInfo)
    {
        List<QuestionnaireInfo> list = questionnaireInfoService.selectQuestionnaireInfoList(questionnaireInfo);
        ExcelUtil<QuestionnaireInfo> util = new ExcelUtil<QuestionnaireInfo>(QuestionnaireInfo.class);
        util.exportExcel(response, list, "问卷信息数据");
    }

    /**
     * 获取问卷信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:questionaireInfo:query')")
    @GetMapping(value = "/{infoId}")
    public AjaxResult getInfo(@PathVariable("infoId") Long infoId)
    {
        return success(questionnaireInfoService.selectQuestionnaireInfoByInfoId(infoId));
    }

    /**
     * 新增问卷信息
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:questionaireInfo:add')")
    @Log(title = "问卷信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QuestionnaireInfo questionnaireInfo)
    {
        return toAjax(questionnaireInfoService.insertQuestionnaireInfo(questionnaireInfo));
    }

    /**
     * 修改问卷信息
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:questionaireInfo:edit')")
    @Log(title = "问卷信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QuestionnaireInfo questionnaireInfo)
    {
        return toAjax(questionnaireInfoService.updateQuestionnaireInfo(questionnaireInfo));
    }

    /**
     * 删除问卷信息
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:questionaireInfo:remove')")
    @Log(title = "问卷信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(questionnaireInfoService.deleteQuestionnaireInfoByInfoIds(infoIds));
    }
}
