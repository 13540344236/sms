package com.cs.sms.controller;


import com.cs.sms.pojo.dto.PurchaseAddNewDTO;
import com.cs.sms.pojo.dto.PurchaseEditDTO;
import com.cs.sms.pojo.vo.PurchaseListItemVO;
import com.cs.sms.service.IPurchaseService;
import com.cs.sms.web.JsonPage;
import com.cs.sms.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Api(tags = "进货模块")
@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private IPurchaseService purchaseService;
    public PurchaseController() {
        log.debug("创建控制器对象：PurchaseController");
    }
    //http://localhost:9091/doc.html#
    @ApiOperation("添加商品")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult addNew(@RequestBody PurchaseAddNewDTO purchaseAddNewDTO){
        log.debug("接收到的请求参数：{}", purchaseAddNewDTO);

        purchaseService.addNew(purchaseAddNewDTO);
        return JsonResult.ok();
    }
    @ApiOperation("删除商品")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Long id) {
        purchaseService.deleteByPrimaryKey(id);
        return JsonResult.ok();
    }

    @ApiOperation("编辑商品")
    @ApiOperationSupport(order = 300)
    @PostMapping("/{id:[0-9]+}/edit")
    public JsonResult edit(@PathVariable Long id, @RequestBody PurchaseEditDTO purchaseEditDTO) {
        log.debug("接收到的请求参数：id=" + id);
        purchaseService.update(id, purchaseEditDTO);
        log.debug("接收到的请求参数：" + purchaseEditDTO);
        return JsonResult.ok();
    }
    @ApiOperation("查询商品列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("")
    public JsonResult list() {
        log.debug("接收到查询商品列表的请求");
        List<PurchaseListItemVO> purchases = purchaseService.list();
        return JsonResult.ok(purchases);
    }
    @ApiOperation("导出商品报表")
    @ApiOperationSupport(order = 500)
    @GetMapping("/exportExcel")
    public void download(HttpServletResponse response)throws IOException{
        log.debug("接收到导出商品报表的请求");
        purchaseService.createExcel(response);
    }
    @GetMapping("/page")
    @ApiOperation("分页查询订单")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码",name="pageNum",example = "1"),
            @ApiImplicitParam(value = "每页条数",name="pageSize",example = "10")
    })
    public JsonResult pageOrders(Integer pageNum, Integer pageSize){
        // 分页查询调用
        JsonPage<PurchaseListItemVO> jsonPage=purchaseService.getAllOrdersByPage(pageNum,pageSize);
        return JsonResult.ok(jsonPage);
    }
}
