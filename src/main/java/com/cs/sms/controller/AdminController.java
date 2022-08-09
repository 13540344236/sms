package com.cs.sms.controller;

import com.cs.sms.pojo.dto.AdminDTO;
import com.cs.sms.pojo.vo.AdminVO;
import com.cs.sms.service.IAdminService;
import com.cs.sms.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "管理员模块")
@RestController
@RequestMapping("/admins") // 自己修改
public class AdminController {

    @Autowired(required = false)
    IAdminService adminService;

    /**
     * 增加员工(管理员)
     *
     * @param adminDTO
     */
    @ApiOperation("添加员工")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult addNew(@RequestBody AdminDTO adminDTO) {
        log.debug("接收员工信息:{}", adminDTO);
        adminService.addNew(adminDTO);
        return JsonResult.ok();
    }

    @ApiOperation("删除员工")
    @ApiOperationSupport(order = 200)
    @PostMapping("/delete")
    public JsonResult deleteById(Long id) {
        log.debug("接收需要删除的员工id:{}", id);
        adminService.deleteById(id);
        return JsonResult.ok();
    }

    @ApiOperation("修改员工信息")
    @ApiOperationSupport(order = 300)
    @PostMapping("/update")
    public JsonResult updateById(@RequestBody AdminDTO adminDTO) {
        log.debug("接收需要修改的员工信息:{}", adminDTO);
        adminService.updateById(adminDTO);
        return JsonResult.ok();
    }

    @ApiOperation("通过id查询员工信息")
    @ApiOperationSupport(order = 400)
    @PostMapping("/selectById")
    public JsonResult selectById(Long id) {
        log.debug("接收需要查询的员工id:{}", id);
        AdminVO adminVO = adminService.selectById(id);
        return JsonResult.ok(adminVO);
    }

    @ApiOperation("通过姓名查询员工信息")
    @ApiOperationSupport(order = 401)
    @PostMapping("/selectByName")
    public JsonResult selectByName(String staffName) {
        log.debug("接收需要查询的员工姓名:{}", staffName);
        AdminVO adminVOs = adminService.selectByName(staffName);
        return JsonResult.ok(adminVOs);
    }

    @ApiOperation("查询所有员工信息")
    @ApiOperationSupport(order = 402)
    @GetMapping("")
    public JsonResult list() {
        List<AdminVO> list = adminService.list();
        return JsonResult.ok(list);
    }
}