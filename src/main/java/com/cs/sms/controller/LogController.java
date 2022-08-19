package com.cs.sms.controller;


import com.cs.sms.annotation.RequiredLog;
import com.cs.sms.mapper.LogMapper;
import com.cs.sms.pojo.entity.Goods;
import com.cs.sms.pojo.entity.Log;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.service.impl.LogServiceImpl;
import com.cs.sms.web.JsonPage;
import com.cs.sms.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = "登录日志模块")
@RestController
@RequestMapping("logs")
public class LogController {
    @Autowired
    private LogServiceImpl logService;

    @ApiOperation("日志列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("")
    public JsonResult list() {
        log.debug("接收到查询日志列表的请求");
        List<Log> goods = logService.list();
        return JsonResult.ok(goods);
    }

    @ApiOperation("分页查询商品")
    @ApiOperationSupport(order = 401)
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码", name = "pageNum", example = "1"),
            @ApiImplicitParam(value = "每页条数", name = "pageSize", example = "5")
    })
    public JsonResult<JsonPage<Log>> pageGood(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        // 分页查询调用
        JsonPage<Log> allLogByPage = logService.getAllLogByPage(pageNum, pageSize);
        return JsonResult.ok("查询成功!",allLogByPage);
    }

}
