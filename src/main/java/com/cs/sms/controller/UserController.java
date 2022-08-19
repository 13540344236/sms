package com.cs.sms.controller;

import com.cs.sms.pojo.dto.UserDTO;
import com.cs.sms.pojo.dto.UserLoginDTO;
import com.cs.sms.pojo.entity.Admin;
import com.cs.sms.pojo.entity.User;
import com.cs.sms.pojo.vo.AdminVO;
import com.cs.sms.pojo.vo.UserVO;
import com.cs.sms.service.IUserService;
import com.cs.sms.web.JsonPage;
import com.cs.sms.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 *  登录相关
 *
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
//    @ApiOperation("管理员登录")
//    @ApiOperationSupport(order = 400)
//    @PostMapping("/login")
//    public JsonResult login(@RequestBody UserLoginDTO userLoginDTO) {
//        log.debug("接收到的请求参数：{}", userLoginDTO);
//        String jwt = userService.login(userLoginDTO);
//        return JsonResult.ok(jwt);
//    }


    /**
     * 增加用户(管理员)
     *
     * @param
     */
    @ApiOperation("添加用户")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
//    @PreAuthorize("hasAuthority('/sys/users')")
    public JsonResult addNew(@RequestBody UserDTO userDTO) {
        log.debug("接收用户信息:{}", userDTO);
        userService.addNew(userDTO);
        return JsonResult.ok();
    }

    @ApiOperation("删除用户")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
//    @PreAuthorize("hasAuthority('/sys/users')")
    public JsonResult deleteById(@PathVariable Long id) {
        log.debug("接收需要删除的用户id:{}", id);
        userService.deleteById(id);
        return JsonResult.ok();
    }

    @ApiOperation("修改用户信息")
    @ApiOperationSupport(order = 300)
    @PostMapping("/{id:[0-9]+}/update")
//    @PreAuthorize("hasAuthority('/sys/users')")
    public JsonResult updateById(@PathVariable Integer id,@RequestBody UserDTO userDTO) {
        log.debug("接收需要修改的用户信息:{}", userDTO);
        userService.updateById(id,userDTO);
        return JsonResult.ok();
    }

    @ApiOperation("通过id查询用户信息")
    @ApiOperationSupport(order = 400)
    @GetMapping("/{id:[0-9]+}/selectById")
//    @PreAuthorize("hasAuthority('/sys/users')")
    public JsonResult selectById(@PathVariable Long id) {
        log.debug("接收需要查询的用户id:{}", id);
        UserVO userVO = userService.selectById(id);
        return JsonResult.ok(userVO);
    }

    @ApiOperation("通过姓名查询用户信息")
    @ApiOperationSupport(order = 401)
    @PostMapping("/selectByName")
//    @PreAuthorize("hasAuthority('/sys/users')")
    public JsonResult selectByName(String staffName) {
        log.debug("接收需要查询的用户姓名:{}", staffName);
        UserVO userVOs = userService.selectByName(staffName);
        return JsonResult.ok(userVOs);
    }

    @ApiOperation("查询所有用户信息")
    @ApiOperationSupport(order = 402)
    @GetMapping("")
//    @PreAuthorize("hasAuthority('/sys/users')")
    public JsonResult list() {
        List<UserVO> list = userService.list();
        return JsonResult.ok(list);
    }

    @ApiOperation("分页查询用户")
    @ApiOperationSupport(order = 401)
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "pageNum", example = "1"),
            @ApiImplicitParam(value = "每页条数", name = "pageSize", example = "5")
    })
//    @PreAuthorize("hasAuthority('/sys/users')")
    public JsonResult<JsonPage<User>> pageUser(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        // 分页查询调用
        JsonPage<User> allAdminByPage = userService.getAllUserByPage(pageNum, pageSize);
        return JsonResult.ok("查询成功!",allAdminByPage);
    }

    @ApiOperation("导出用户信息")
    @ApiOperationSupport(order = 500)
    @GetMapping("/exportExcel")
    public void download(HttpServletResponse response) throws IOException {
        log.debug("接收到导出商品报表的请求");
        userService.createExcel(response);
    }
}

