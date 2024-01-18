package com.LW1.userAdmin.userInfo.controller;

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
import com.LW1.userAdmin.userInfo.domain.Userinfo;
import com.LW1.userAdmin.userInfo.service.IUserinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户信息Controller
 * 
 * @author BlueTeethAC
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/userAdmin/userInfo")
public class UserinfoController extends BaseController
{
    @Autowired
    private IUserinfoService userinfoService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:userInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Userinfo userinfo)
    {
        startPage();
        List<Userinfo> list = userinfoService.selectUserinfoList(userinfo);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:userInfo:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Userinfo userinfo)
    {
        List<Userinfo> list = userinfoService.selectUserinfoList(userinfo);
        ExcelUtil<Userinfo> util = new ExcelUtil<Userinfo>(Userinfo.class);
        util.exportExcel(response, list, "用户信息数据");
    }

    /**
     * 获取用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:userInfo:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(userinfoService.selectUserinfoByUserId(userId));
    }

    /**
     * 新增用户信息
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:userInfo:add')")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Userinfo userinfo)
    {
        return toAjax(userinfoService.insertUserinfo(userinfo));
    }

    /**
     * 修改用户信息
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:userInfo:edit')")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Userinfo userinfo)
    {
        return toAjax(userinfoService.updateUserinfo(userinfo));
    }

    /**
     * 删除用户信息
     */
    @PreAuthorize("@ss.hasPermi('userAdmin:userInfo:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(userinfoService.deleteUserinfoByUserIds(userIds));
    }
}
