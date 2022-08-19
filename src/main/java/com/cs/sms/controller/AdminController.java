package com.cs.sms.controller;

import com.cs.sms.annotation.RequiredLog;
import com.cs.sms.pojo.dto.AdminDTO;
import com.cs.sms.pojo.entity.Admin;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.vo.AdminVO;
import com.cs.sms.service.IAdminService;
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
    @RequiredLog(operation = "添加员工")
    @ApiOperation("添加员工")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult addNew(@RequestBody AdminDTO adminDTO) {
        log.debug("接收员工信息:{}", adminDTO);
        adminService.addNew(adminDTO);
        return JsonResult.ok();
    }


    @RequiredLog(operation = "删除员工")
    @ApiOperation("删除员工")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult deleteById(@PathVariable Long id) {
        log.debug("接收需要删除的员工id:{}", id);
        adminService.deleteById(id);
        return JsonResult.ok();
    }

    @RequiredLog(operation = "修改员工信息")
    @ApiOperation("修改员工信息")
    @ApiOperationSupport(order = 300)
    @PostMapping("/{id}/update")
    public JsonResult updateById(@PathVariable Long id,@RequestBody AdminDTO adminDTO) {
        log.debug("接收需要修改的员工信息:{}", adminDTO);
        adminService.updateById(id,adminDTO);
        return JsonResult.ok();
    }

    @RequiredLog(operation = "通过id查询员工信息")
    @ApiOperation("通过id查询员工信息")
    @ApiOperationSupport(order = 400)
    @GetMapping("/{id:[0-9]+}/selectById")
    public JsonResult selectById(@PathVariable Long id) {
        log.debug("接收需要查询的员工id:{}", id);
        AdminVO adminVO = adminService.selectById(id);
        return JsonResult.ok(adminVO);
    }

    @RequiredLog(operation = "通过姓名查询员工信息")
    @ApiOperation("通过姓名查询员工信息")
    @ApiOperationSupport(order = 401)
    @PostMapping("/selectByName")
    public JsonResult selectByName(String staffName) {
        log.debug("接收需要查询的员工姓名:{}", staffName);
        AdminVO adminVOs = adminService.selectByName(staffName);
        return JsonResult.ok(adminVOs);
    }

    @RequiredLog(operation = "查询所有员工信息")
    @ApiOperation("查询所有员工信息")
    @ApiOperationSupport(order = 402)
    @GetMapping("")
    public JsonResult list() {
        List<AdminVO> list = adminService.list();
        return JsonResult.ok(list);
    }

    @ApiOperation("分页查询员工")
    @ApiOperationSupport(order = 401)
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "pageNum", example = "1"),
            @ApiImplicitParam(value = "每页条数", name = "pageSize", example = "5")
    })
    public JsonResult<JsonPage<Admin>> pageAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        // 分页查询调用
        JsonPage<Admin> allAdminByPage = adminService.getAllAdminByPage(pageNum, pageSize);
        return JsonResult.ok("查询成功!",allAdminByPage);
    }

    @RequiredLog(operation = "导出员工信息")
    @ApiOperation("导出员工信息")
    @ApiOperationSupport(order = 500)
    @GetMapping("/exportExcel")
    public void download(HttpServletResponse response) throws IOException {
        log.debug("接收到导出商品报表的请求");
        adminService.createExcel(response);
    }

    @RequiredLog(operation = "批量导入员工")
    @ApiOperation("批量导入员工")
    @ApiOperationSupport(order = 501)
    @PostMapping("/importExcel")
    public Results<Object> importData(@RequestParam("file") MultipartFile file) throws IOException {
        log.debug("批量导入员工");
        return adminService.upload(file);
    }

//    @ApiOperation("查询所有用户信息")
//    @ApiOperationSupport(order = 402)
//    @GetMapping("/user")
//    public JsonResult userList() {
//        List<AdminVO> list = adminService.userList();
//        return JsonResult.ok(list);
//    }
}