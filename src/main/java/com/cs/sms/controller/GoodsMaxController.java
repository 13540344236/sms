package com.cs.sms.controller;

import com.cs.sms.pojo.dto.GoodsMaxDTO;
import com.cs.sms.pojo.vo.GoodsMaxVO;
import com.cs.sms.service.IGoodsMaxService;
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

@Slf4j
@Api(tags = "商品报溢模块")
@RestController
@RequestMapping("/goodsmax") // 自己修改
public class GoodsMaxController {
    @Autowired
    private IGoodsMaxService service;

    @ApiOperation("删除商品")
    @PostMapping("/add-new")
    public JsonResult addNew(@RequestBody GoodsMaxDTO goodsmaxDTO){
        service.addNew(goodsmaxDTO);
        return JsonResult.ok();
    }

    @ApiOperation("删除商品")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Long id) {
        log.debug("需要删除的商品的ID = {}",id);
        service.deleteById(id);
        log.debug("删除成功!");
        return JsonResult.ok();
    }

    @ApiOperation("编辑商品")
    @ApiOperationSupport(order = 400)
    @PostMapping("/{id:[0-9]+}/edit")
    public JsonResult edit(@PathVariable Long id, @RequestBody GoodsMaxVO goodsMaxVO) {
        log.debug("接收到的参数：id = {}",id);
        log.debug("接收到的参数：{}",goodsMaxVO);
        service.update(id,goodsMaxVO);
        log.debug("编辑成功!");
        return JsonResult.ok();
    }

    @ApiOperation("商品列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("")
    public JsonResult list() {
        log.debug("接收到查询品牌列表的请求");
        List<GoodsMaxVO> goods = service.list();
        return JsonResult.ok(goods);
    }

    @ApiOperation("导出商品报表")
    @ApiOperationSupport(order = 500)
    @GetMapping("/exportExcel")
    public void download(HttpServletResponse response) throws IOException {
        log.debug("接收到导出商品报表的请求");
        service.createExcel(response);

    }
}
