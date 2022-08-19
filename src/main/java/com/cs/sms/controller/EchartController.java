package com.cs.sms.controller;

import com.cs.sms.service.ISaleService;
import com.cs.sms.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Api(tags = "统计报表模块")
@RestController
@RequestMapping("/echarts")
public class EchartController {
    @Autowired
    private ISaleService saleService;

    @ApiOperation("每日销售额和销量统计")
    @ApiOperationSupport(order = 500)
    @GetMapping("/goodsSale")
    public JsonResult goodsSale() {
        log.debug("每日销售额和销量统计");
        List<HashMap<String, Object>> list = saleService.getGoodsSale();
        log.debug("查询销量的数据:{}",list);
        return JsonResult.ok("goods",list);
    }
}
