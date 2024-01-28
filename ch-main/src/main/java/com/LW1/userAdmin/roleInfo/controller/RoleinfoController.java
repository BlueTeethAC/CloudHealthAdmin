package com.LW1.userAdmin.roleInfo.controller;

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
import com.LW1.userAdmin.roleInfo.domain.Roleinfo;
import com.LW1.userAdmin.roleInfo.service.IRoleinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 角色权限Controller
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/userAdmin/roleinfo")
public class RoleinfoController extends BaseController
{
    @Autowired
    private IRoleinfoService roleinfoService;

    /**
     * 查询角色权限列表
     */
    // @PreAuthorize("@ss.hasPermi('userAdmin:roleinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Roleinfo roleinfo)
    {
        startPage();
        List<Roleinfo> list = roleinfoService.selectRoleinfoList(roleinfo);
        return getDataTable(list);
    }

    /**
     * 导出角色权限列表
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:roleinfo:export')")
    @Log(title = "角色权限", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Roleinfo roleinfo)
    {
        List<Roleinfo> list = roleinfoService.selectRoleinfoList(roleinfo);
        ExcelUtil<Roleinfo> util = new ExcelUtil<Roleinfo>(Roleinfo.class);
        util.exportExcel(response, list, "角色权限数据");
    }

    /**
     * 获取角色权限详细信息
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:roleinfo:query')")
    @GetMapping(value = "/{infoId}")
    public AjaxResult getInfo(@PathVariable("infoId") Long infoId)
    {
        return success(roleinfoService.selectRoleinfoByInfoId(infoId));
    }

    /**
     * 新增角色权限
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:roleinfo:add')")
    @Log(title = "角色权限", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Roleinfo roleinfo)
    {
        return toAjax(roleinfoService.insertRoleinfo(roleinfo));
    }

    /**
     * 修改角色权限
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:roleinfo:edit')")
    @Log(title = "角色权限", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Roleinfo roleinfo)
    {
        return toAjax(roleinfoService.updateRoleinfo(roleinfo));
    }

    /**
     * 删除角色权限
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:roleinfo:remove')")
    @Log(title = "角色权限", businessType = BusinessType.DELETE)
	@DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(roleinfoService.deleteRoleinfoByInfoIds(infoIds));
    }
}
