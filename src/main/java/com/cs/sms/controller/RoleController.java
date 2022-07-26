package com.cs.sms.controller;


import com.cs.sms.pojo.dto.RoleDTO;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Role;
import com.cs.sms.pojo.entity.RoleMenu;
import com.cs.sms.pojo.vo.RoleVO;
import com.cs.sms.service.IRoleService;
import com.cs.sms.web.JsonPage;
import com.cs.sms.web.JsonResult;
import com.cs.sms.web.Results;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Api(tags = "角色模块")
@RestController
@RequestMapping("/roles") // 自己修改
public class RoleController {

    @Autowired
    public IRoleService roleService;


    @ApiOperation("添加角色")
    @ApiOperationSupport(order = 401)
    @PostMapping("/addNew")
    public JsonResult addNew(@RequestBody RoleDTO roleDTO) {
        roleService.addNew(roleDTO);
        return JsonResult.ok();
    }

    @ApiOperation("查询角色信息")
    @ApiOperationSupport(order = 402)
    @GetMapping("")
    public JsonResult selectAll() {
        List<RoleVO> list = roleService.list();
        return JsonResult.ok(list);
    }

    @ApiOperation("分页查询商品")
    @ApiOperationSupport(order = 401)
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "pageNum", example = "1"),
            @ApiImplicitParam(value = "每页条数", name = "pageSize", example = "5")
    })
    public JsonResult<JsonPage<Role>> pageRole(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        // 分页查询调用
        JsonPage<Role> allRoleByPage = roleService.getAllRoleByPage(pageNum, pageSize);
        return JsonResult.ok("查询成功!",allRoleByPage);
    }

    @ApiOperation("修改角色信息")
    @ApiOperationSupport(order = 403)
    @PostMapping("/{id:[0-9]+}/edit")
    public JsonResult updateById(@RequestBody Role role) {
        log.debug("接收需要修改的员工信息:{}", role);
        roleService.updateById(role);
        return JsonResult.ok();
    }

    @ApiOperation("删除员工")
    @ApiOperationSupport(order = 201)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult deleteById(@PathVariable Long id) {
        log.debug("接收需要删除的员工id:{}", id);
        roleService.delete(id);
        return JsonResult.ok();
    }

    @PostMapping("/upload")
    public Results<Object> importData(@RequestParam("file") MultipartFile file) throws IOException {
        return roleService.upload(file);
    }
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        roleService.createExcel(response);
    }

    /**
     * 绑定角色与菜单的关系
     * @param roleId 角色id
     * @param menuIds 菜单id
     * @return
     */
    @PostMapping("/roleMenu/{roleId}")
    public JsonResult roleMenu(@PathVariable Integer roleId,@RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(roleId,menuIds);
        return JsonResult.ok();
    }

    @ApiOperation("菜单id")
    @GetMapping("/roleMenu/{roleId}")
    public JsonResult getRoleMenu(@PathVariable Integer roleId) {
        List<RoleMenu> roleMenu = roleService.getRoleMenu(roleId);
        return JsonResult.ok(roleMenu);
    }

}
