package com.LW1.healthConsult.bodyInfo.controller;

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
import com.LW1.healthConsult.bodyInfo.domain.Bodyinfo;
import com.LW1.healthConsult.bodyInfo.service.IBodyinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户个人身体信息Controller
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/healthConsult/bodyinfo")
public class BodyinfoController extends BaseController
{
    @Autowired
    private IBodyinfoService bodyinfoService;

    /**
     * 查询用户个人身体信息列表
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:bodyinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Bodyinfo bodyinfo)
    {
        startPage();
        List<Bodyinfo> list = bodyinfoService.selectBodyinfoList(bodyinfo);
        return getDataTable(list);
    }

    /**
     * 导出用户个人身体信息列表
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:bodyinfo:export')")
    @Log(title = "用户个人身体信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Bodyinfo bodyinfo)
    {
        List<Bodyinfo> list = bodyinfoService.selectBodyinfoList(bodyinfo);
        ExcelUtil<Bodyinfo> util = new ExcelUtil<Bodyinfo>(Bodyinfo.class);
        util.exportExcel(response, list, "用户个人身体信息数据");
    }

    /**
     * 获取用户个人身体信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:bodyinfo:query')")
    @GetMapping(value = "/{infoId}")
    public AjaxResult getInfo(@PathVariable("infoId") Long infoId)
    {
        return success(bodyinfoService.selectBodyinfoByInfoId(infoId));
    }

    /**
     * 新增用户个人身体信息
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:bodyinfo:add')")
    @Log(title = "用户个人身体信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Bodyinfo bodyinfo)
    {
        return toAjax(bodyinfoService.insertBodyinfo(bodyinfo));
    }

    /**
     * 修改用户个人身体信息
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:bodyinfo:edit')")
    @Log(title = "用户个人身体信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Bodyinfo bodyinfo)
    {
        return toAjax(bodyinfoService.updateBodyinfo(bodyinfo));
    }

    /**
     * 删除用户个人身体信息
     */
    @PreAuthorize("@ss.hasPermi('healthConsult:bodyinfo:remove')")
    @Log(title = "用户个人身体信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(bodyinfoService.deleteBodyinfoByInfoIds(infoIds));
    }
}
