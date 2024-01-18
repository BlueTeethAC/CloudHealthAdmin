package com.LW1.userAdmin.roleProcess.controller;

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
import com.LW1.userAdmin.roleProcess.domain.RoleAuthentication;
import com.LW1.userAdmin.roleProcess.service.IRoleAuthenticationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 角色认证审核Controller
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/userAdmin/roleProcess")
public class RoleAuthenticationController extends BaseController
{
    @Autowired
    private IRoleAuthenticationService roleAuthenticationService;

    /**
     * 查询角色认证审核列表
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:roleProcess:list')")
    @GetMapping("/list")
    public TableDataInfo list(RoleAuthentication roleAuthentication)
    {
        startPage();
        List<RoleAuthentication> list = roleAuthenticationService.selectRoleAuthenticationList(roleAuthentication);
        return getDataTable(list);
    }

    /**
     * 导出角色认证审核列表
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:roleProcess:export')")
    @Log(title = "角色认证审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RoleAuthentication roleAuthentication)
    {
        List<RoleAuthentication> list = roleAuthenticationService.selectRoleAuthenticationList(roleAuthentication);
        ExcelUtil<RoleAuthentication> util = new ExcelUtil<RoleAuthentication>(RoleAuthentication.class);
        util.exportExcel(response, list, "角色认证审核数据");
    }

    /**
     * 获取角色认证审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:roleProcess:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(roleAuthenticationService.selectRoleAuthenticationById(id));
    }

    /**
     * 新增角色认证审核
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:roleProcess:add')")
    @Log(title = "角色认证审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RoleAuthentication roleAuthentication)
    {
        return toAjax(roleAuthenticationService.insertRoleAuthentication(roleAuthentication));
    }

    /**
     * 修改角色认证审核
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:roleProcess:edit')")
    @Log(title = "角色认证审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RoleAuthentication roleAuthentication)
    {
        return toAjax(roleAuthenticationService.updateRoleAuthentication(roleAuthentication));
    }

    /**
     * 删除角色认证审核
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:roleProcess:remove')")
    @Log(title = "角色认证审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(roleAuthenticationService.deleteRoleAuthenticationByIds(ids));
    }
}
