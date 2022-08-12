package com.cs.sms.controller;

import com.cs.sms.pojo.dto.GoodsEditDTO;
import com.cs.sms.pojo.dto.RefundAddNewDTO;
import com.cs.sms.pojo.dto.RefundEditDTO;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.pojo.vo.RefundListItemVO;
import com.cs.sms.service.IRefundService;
import com.cs.sms.service.impl.RefundServiceImpl;
import com.cs.sms.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@CrossOrigin
@Slf4j
@Api(tags = "退货模块")
@RestController
@RequestMapping("/refunds")
public class RefundController {
    @Autowired
    private IRefundService refundService;

    //http://localhost:9091/doc.html#
    @ApiOperation("新增商品")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult addNew(@RequestBody RefundAddNewDTO refundAddNewDTO){
        log.debug("接收到的请求参数：{}", refundAddNewDTO);
        refundService.addNew(refundAddNewDTO);
        log.debug("添加成功!");
        return JsonResult.ok();
    }
    @ApiOperation("退货商品")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Long id) {
        log.debug("需要退货的商品的ID = {}",id);
        refundService.deleteByPrimaryKey(id);
        log.debug("退货成功!");
        return JsonResult.ok();
    }
    @ApiOperation("编辑商品")
    @ApiOperationSupport(order = 300)
    @PostMapping("/{id:[0-9]+}/edit")
    public JsonResult edit(@PathVariable Long id, @RequestBody RefundEditDTO refundEditDTO) {
        log.debug("接收到的请求参数：id=" + id);
        refundService.update(id, refundEditDTO);
        log.debug("接收到的请求参数：" + refundEditDTO);
        return JsonResult.ok();
    }
    @ApiOperation("商品列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("")
    public JsonResult list() {
        log.debug("接收到查询品牌列表的请求");
        List<RefundListItemVO> refund = refundService.list();
        return JsonResult.ok(refund);
    }

    @ApiOperation("导出商品报表")
    @ApiOperationSupport(order = 500)
    @GetMapping("/exportExcel")
    public void download(HttpServletResponse response) throws IOException {
        log.debug("接收到导出商品报表的请求");
        refundService.createExcel(response);

    }

}

